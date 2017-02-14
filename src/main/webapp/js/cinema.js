;
var cinema;
(function () {
    cinema = new Cinema();

    var films1 = [
        {
            "filmId": 1,
            "title": "The Great Wall",
            "year": 2016,
            "director": {
                "name": "Spielberg L."
            },
            "actors": [
                {
                    "name": "Hou June"
                },
                {
                    "name": "Lara Croft"
                },
                {
                    "name": "Djoe Rufi"
                }
            ],

            "description": "In the time of the Song dynasty during the reign of the Renzong Emperor, a few miles north of the Great Wall, a mercenary group originally consisting of twenty men searching for black powder are pursued by Khitan bandits, who have already killed some of the men. Upon escaping they seek refuge in a cave but are then attacked by an unknown monster, leaving only William (Matt Damon) and Tovar (Pedro Pascal) alive, with the former having slashed off the monster's hand. The two decide to bring the arm with them. The next day, they stumble upon the Great Wall and are taken prisoner by Chinese soldiers of a secretive military sect called the Nameless Order, led by General Shao (Zhang Hanyu) and Strategist Wang (Andy Lau).",
            "image": "images/velikaya-stena.jpg",
            "genres": [
                {
                    "name": "fantasy"
                }
            ]
        },
        {
            "filmId": 2,
            "title": "Attraction",
            "year": 2017,
            "director": {
                "name": "Djoe Lucas"
            },
            "actors": [
                {
                    "name": "Emy Renzong"
                },
                {
                    "name": "Frody Shao"
                },
                {
                    "name": "Victor Duncan"
                },
                {
                    "name": "Sarah Ballet"

                }
            ],

            "description": "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fugit optio quis, cum quidem ab, obcaecati quam ipsa consequuntur! Asperiores iste dolorum magnam quo maxime optio amet sapiente ad ea quod.",
            "image": "images/prityazhenie.jpg",
            "genres": [
                {
                    "name": "fantasy"
                },
                {
                    "name": "Romance"
                }
            ]
        },
        {
            "filmId": 3,
            "title": "John Wick 2",
            "year": 2016,
            "director": {
                "name": "Rio McDunckey"
            },
            "actors": [
                {
                    "name": "actor1"
                },
                {
                    "name": "actor2"
                },
                {
                    "name": "actor3"
                },
                {
                    "name": "actor4"

                }
            ],

            "description": "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Deserunt at iusto nostrum, quo fugiat sint aspernatur vitae, omnis animi reiciendis veniam pariatur tempore odit debitis quibusdam reprehenderit dolore eos tenetur!",
            "image": "images/dzhon-uik-2.jpg",
            "genres": [
                {
                    "name": "drama"
                }
            ]
        },
        {
            "filmId": 4,
            "title": "The Lego Movie: Batman ",
            "year": 2016,
            "director": {
                "name": "director1"
            },
            "actors": [
                {
                    "name": "actor1"
                },
                {
                    "name": "actor2"
                },
                {
                    "name": "actor3"
                },
                {
                    "name": "actor4"

                }
            ],

            "description": "Great Wall erected to protect the Middle Kingdom from any threats, but with the enemy did not face even the most daring of its defenders. If you stop the invasion - the world will be destroyed. ",
            "image": "images/lego-film-betmen.jpg",
            "genres": [
                {
                    "name": "fantasy"
                }
            ]
        },
        {
            "filmId": 5,
            "title": "La-La-Lend",
            "year": 2016,
            "director": {
                "name": "director1"
            },
            "actors": [
                {
                    "name": "actor1"
                },
                {
                    "name": "actor2"
                },
                {
                    "name": "actor3"
                },
                {
                    "name": "actor4"

                }
            ],

            "description": "Great Wall erected to protect the Middle Kingdom from any threats, but with the enemy did not face even the most daring of its defenders. If you stop the invasion - the world will be destroyed. ",
            "image": "images/la-la-lend.jpg",
            "genres": [
                {
                    "name": "comedy"
                }
            ]
        }
    ];

    function Cookie() {
        this.setCookie = function (cname, cvalue, exdays) {
            var d = new Date();
            if (exdays != undefined) {
                d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
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

        this.deletePlace = function (cname) {
            document.cookie = cname + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        };
    }

    function Cinema() {

        var api = {
            FILMS: "/api/films",
            SESSIONS: "/api/sessions",
            ORDERS: "/api/orders"
        };

        this.getCookieManager = function () {
            return new Cookie();
        }

        this.selectSession = function (sessionId) {
            var cookies = new Cookie();
            cookies.setCookie("session", sessionId);
            window.location = "receive.html";
        };

        function FilmView() {

            var classAttr = {
                PANEL: "panel",
                HEADER: "panel_header",
                TITLE: "panel_title",
                RELEASE: "panel_release",
                SUBTITLE: "panel_subtitle",
                SUB_CAPTION: "panel_subtitle_caption",
                SUB_TEXT: "panel_subtitle_text",
                BODY: "panel_body",
                IMAGE: "panel_image",
                CONTEXT: "panel_text",
                FOOTER: "panel_footer",
                THEATER_SESSIONS: "theater_sessions",
                THEATER_NAME: "theater_name",
                SESSIONS_ITEMS_BLOCK: "sessions_items_block",
                SESSIONS_ITEM: "sessions_item",
                SESSION_WEEKDAY: "session_weekday",
                SESSION_DATE: "session_date",
                SESSION_TIME: "session_time"

            };
            var attr = {
                CLASS: "class",
                SRC: "src",
                ID: "id",
                ONCLICK: "onclick"
            };

            var tag = {
                A: 'a',
                DIV: "div",
                IMG: "img",
                P: "p",
                H2: "h2"
            };


            this.getByFilm = function (film) {
                return new Element(tag.DIV, attr.CLASS, classAttr.PANEL)
                    .addChild(new Element(tag.DIV, attr.CLASS, classAttr.HEADER)
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.TITLE, film.title))
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.RELEASE, film.year.getFullYear()))
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUBTITLE)
                            .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUB_CAPTION, 'Director:'))
                            .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUB_TEXT, film.director.name))
                        )
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUBTITLE)
                            .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUB_CAPTION, 'Casts:'))
                            .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUB_TEXT, function () {
                                var result = "";
                                film.actors.forEach(function (nm) {
                                    result += nm.name + ", "
                                });
                                return result.replace(/, $/, '');
                            }()))
                        )
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUBTITLE)
                            .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUB_CAPTION, 'Genres:'))
                            .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SUB_TEXT, function () {
                                var result = "";
                                film.genres.forEach(function (nm) {
                                    result += nm.name + ", "
                                });
                                return result.replace(/, $/, '');
                            }()))
                        )
                    )
                    .addChild(new Element(tag.DIV, attr.CLASS, classAttr.BODY)
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.IMAGE).addChild(new Element(tag.IMG, attr.SRC, film.image))
                        )
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.CONTEXT)
                            .addChild(new Element(tag.H2, undefined, undefined, 'Plot'))
                            .addChild(new Element(tag.P, undefined, undefined, film.description))
                        )
                        .addChild(new Element(tag.DIV, attr.CLASS, classAttr.FOOTER)
                            .addChild(new Element(tag.DIV, attr.CLASS, classAttr.THEATER_SESSIONS)
                                .addChild(new Element(tag.DIV, attr.CLASS, classAttr.THEATER_NAME, 'October'))
                                .addChild(function () {
                                        var sessionView = new Element(tag.DIV, attr.CLASS, classAttr.SESSIONS_ITEMS_BLOCK);
                                        var sessions = [
                                            {
                                                id: 1,
                                                filmId: 1,
                                                dateTime: new Date(2017, 1, 14, 14, 0),
                                                theaterId: 1
                                            },
                                            {
                                                id: 2,
                                                filmId: 1,
                                                dateTime: new Date(2017, 1, 15, 14, 0),
                                                theaterId: 1
                                            },
                                            {
                                                id: 3,
                                                filmId: 1,
                                                dateTime: new Date(2017, 1, 16, 14, 0),
                                                theaterId: 1
                                            },
                                            {
                                                id: 4,
                                                filmId: 1,
                                                dateTime: new Date(2017, 1, 17, 14, 0),
                                                theaterId: 1
                                            },
                                            {
                                                id: 5,
                                                filmId: 1,
                                                dateTime: new Date(2017, 1, 18, 14, 0),
                                                theaterId: 1
                                            },
                                            {
                                                id: 6,
                                                filmId: 1,
                                                dateTime: new Date(2017, 1, 19, 14, 0),
                                                theaterId: 1
                                            },
                                            {
                                                id: 7,
                                                filmId: 1,
                                                dateTime: new Date(2017, 1, 20, 14, 0),
                                                theaterId: 1
                                            }
                                        ];
                                        sessions.forEach(function (session) {
                                                sessionView.addChild(new Element(tag.DIV, attr.CLASS, classAttr.SESSIONS_ITEM).setAttr(attr.ID, session.id).setAttr(attr.ONCLICK, "cinema.selectSession(this.id)")
                                                    .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SESSION_WEEKDAY, session.dateTime.toLocaleString("en-US", {weekday: 'long'})))
                                                    .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SESSION_DATE, session.dateTime.toLocaleString("en-US", {
                                                        month: 'long',
                                                        day: 'numeric'
                                                    })))
                                                    .addChild(new Element(tag.DIV, attr.CLASS, classAttr.SESSION_TIME, session.dateTime.toLocaleString("en-US", {
                                                        hour: 'numeric',
                                                        minute: 'numeric'
                                                    }))))
                                            }
                                        );
                                        return sessionView;
                                    }()
                                )
                                .addChild(new Element(tag.DIV, attr.CLASS, classAttr.THEATER_NAME, 'sessions'))
                            )
                        )
                    );
            }
        }

        this.renderFilmsContent = function () {
            getFilms();

        }


        var ajax = new function () {

            var method = {
                GET: "get",
                PUT: "put",
                POST: "post",
                DELETE: "delete"
            };


            this.get = function (url, func) {
                return requestFunc(method.GET, url, func);
            };

            this.put = function (url, func) {
                return requestFunc(method.PUT, url, func);
            };

            this.post = function (url, func) {
                return requestFunc(method.POST, url, func);
            };

            this.delete = function (url, func) {
                return requestFunc(method.DELETE, url, func);
            };

            function requestFunc(method, url, func) {
                var req = new XMLHttpRequest();
                req.open(method, url, true);
                req.send();
                req.onreadystatechange = function () {
                    if (req.readyState != 4) return;
                    if (req.status == 200) {
                        var requestResult;
                       // try {
                            requestResult = JSON.parse(req.responseText, function(key, value) {
                                if (key == 'year') return new Date(value);
                                return value;
                            });
                            /* a returned object have next structure:
                             @fields: status  = "error"|"ok",
                             message = "text of the message if an error detected",
                             data    = {}
                             */
                       // } catch (SyntaxError) {
                       //     console.log("Can`t parse from json.");
                       // }

                        if (requestResult.status == "ok") {
                            func(requestResult.data);
                        }

                    } else {
                        alert('Can`t get connection to server.');
                    }
                }
            }
        };

        function getFilms() {
            ajax.get(api.FILMS, function (result) {
                var filmHtml = "";
                result.forEach(function (fm) {
                    filmHtml += new FilmView().getByFilm(fm);
                });
                document.getElementById("context").innerHTML = filmHtml;

                //var films = JSON.parse(result);
                // TODO RENDERING CONTEXT
            });
        }


    }


    function Messages(title, text, button) {

    }

    function Element(tag, attr, classAttr, text) {

        this.mainTag = document.createElement(tag);

        this.setAttr = function (attr, classAttr) {
            if (!classAttr.isArray) {
                this.mainTag.setAttribute(attr, classAttr);
            } else {
                this.mainTag.setAttribute(attr, classAttr.join(" "));
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

        if (attr != undefined) {
            this.setAttr(attr, classAttr);
        }

        if (text != undefined) {
            this.setText(text);
        }

        Element.prototype.toString = function () {
            return this.mainTag.outerHTML;
        };
    }

})();
