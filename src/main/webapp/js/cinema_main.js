;
var api = {
	FILMS: "/api/film/",
	SESSIONS: "/api/session/",
	RESERVE: "/api/reserve/",
	ORDERS: "/api/order/"
};
var cinema = (function () {

	function Cinema() {

		this.getSelectSession = function (sessionId) {
			var value = cookies.getCookie("session");
			cookies.deleteCookie("session");
			return value;
		};

		this.setSelectSession = function (sessionId) {
			cookies.setCookie("sessionId", sessionId);
			window.location = "receive.html";
		};

		this.run = function () {
			var FILM_INFO_TEMPLATE = "/templates/film_info.html";
			ajax.get(FILM_INFO_TEMPLATE, function (filmInfoTemplate) {
				ajax.get(api.FILMS, function (actualFilmResults) {
					// at first: a returned object can have next structure:
					// @fields: status  = "error"|"ok",
					// message = "text of the message if an error detected",
					// data    = {}
					var filmRequestResult = JSON.parse(actualFilmResults, function (key, value) {
						if (key == 'date') return new Date(value);
						return value;
					});

					if (filmRequestResult.status == "OK") {
						var viewDataFilms = filmRequestResult.data;

						var targetSessionBlock = document.getElementById("render_context");

						var sessionsList = {};

						// sort by title of films
						viewDataFilms = viewDataFilms.sort(function (a, b) {
							return a.title == b.title;
						});

						viewDataFilms.forEach(function (film) {

							sessionsList["_" + film.filmId] = null;
							film.year = film.date.getFullYear();

							film.actors.names = film.actors.map(function (item) {
								return item.name;
							}).join(", ");

							film.genres.names = film.genres.map(function (item) {
								return item.name;
							}).join(", ");

							film.id_session_block = film.filmId;
							targetSessionBlock.innerHTML += Mustache.render(filmInfoTemplate, film);



							ajax.get(api.SESSIONS + film.filmId, function (sessionsOfFilm) {
								var sessionBlocks = document.getElementById(film.filmId);

								var sessionRequestResult = JSON.parse(sessionsOfFilm, function (key, value) {
									if (key == 'dateTime') return new Date(value);
									return value;
								});
								if (sessionRequestResult.status == "OK") {
									var sessions = sessionRequestResult.data;

									sessions = sessions.sort(function (a, b) {
										return +a.dateTime - +b.dateTime;
									});

									for (var i = 0; i < sessions.length; i++) {

										var options;
										var ticketElem = new Element("div", {
											"class": "sessions_item",
											"id": sessions[i].id,
											"onclick": "cinema.setSelectSession(this.id)"
										})
											.addChild(new Element("div", {"class": "session_weekday"}, sessions[i].dateTime.toLocaleString("en-US", {
												weekday: 'long'
											})))
											.addChild(new Element("div", {"class": "session_date"}, sessions[i].dateTime.toLocaleString("en-US", {
												month: 'long',
												day: 'numeric'
											})))
											.addChild(new Element("div", {"class": "session_time"}, sessions[i].dateTime.toLocaleString("en-US", {
												hour: 'numeric', minute: 'numeric'
											})));
										sessionBlocks.appendChild(ticketElem.mainTag);
										// template	sessionBlocks
										// sessions[i];
									}

								} else {
									// TODO show error
									//
								}
							});
						});
					} else {
						// TODO show error
						//
					}
				});
			});
		};
	}

	function Messages(title, text, button) {
		// TODO window show
	}
	return new Cinema();
})();


var ajax = new function () {
	var method = {
		GET: "get",
		PUT: "put",
		POST: "post",
		DELETE: "delete"
	};

	this.get = function (url, func, data) {
		requestFunc(method.GET, url, func);
	};

	this.put = function (url, func, data) {
		requestFunc(method.PUT, url, func, data);
	};

	this.post = function (url, func, data) {
		requestFunc(method.POST, url, func, data);
	};

	this.delete = function (url, func, data) {
		requestFunc(method.DELETE, url, func, data);
	};

	function requestFunc(method, url, func, data) {
		var req = new XMLHttpRequest();
		req.open(method, url);
		req.send(JSON.stringify(data));
		req.onload = function () {
			if (req.readyState == 4 && req.status == 200) {
				func(req.responseText);
			} else {
				console.log('Can`t get connection to the server.');
			}
		}
	}
};

var cookies = new function Cookie() {

	this.setCookie = function (cname, cvalue, exHours) {
		var d = new Date();
		if (exHours != undefined) {
			d.setTime(d.getTime() + (exHours * 60 * 60 * 1000));
			var expires = "expires=" + d.toUTCString();
			document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
			return;
		}
		document.cookie = cname + "=" + cvalue;
	};

	this.getCookie = function (cname) {
		var name = cname + "=";
		var decodedCookie = decodeURIComponent(document.cookie);
		var ca = decodedCookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				return c.substring(name.length, c.length);
			}
		}
		return "";
	};

	this.checkCookie = function (cname) {
		var checkedCookie = this.getCookie(cname);
		return checkedCookie != "";
	};

	this.deleteCookie = function (cname) {
		document.cookie = cname + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	};
};



function Element(tag, attributes, text) {

	this.mainTag = document.createElement(tag);

	this.setAttr = function (attributes) {
		for (var prop in attributes) {
			if (!(prop.isArray)) {
				this.mainTag.setAttribute(prop, attributes[prop]);
			} else {
				this.mainTag.setAttribute(prop, attributes[prop].join(" "));
			}
		}
		return this;
	};

	this.setWrap = function (wrapTag) {
		var newTag = document.createElement(wrapTag);
		newTag.appendChild(this.mainTag);
		this.mainTag = newTag;
		return this;
	};

	this.setText = function (text) {
		this.mainTag.innerText += text;
		return this;
	};

	this.addChild = function (elem) {
		this.mainTag.appendChild(elem.mainTag);
		return this;
	};

	if (attributes != undefined) {
		this.setAttr(attributes);
	}

	if (text != undefined) {
		this.setText(text);
	}

	Element.prototype.toString = function () {
		return this.mainTag.outerHTML;
	};
}