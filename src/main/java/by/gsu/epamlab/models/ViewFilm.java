package by.gsu.epamlab.models;

import by.gsu.epamlab.dao.models.Actor;
import by.gsu.epamlab.dao.models.Director;
import by.gsu.epamlab.dao.models.Genre;

import java.sql.Date;
import java.util.List;

public class ViewFilm {

    private int filmId;
    private String title;
    private Date year;
    private Director director;
    private List<Actor> actors;
    private String description;
    private String image;
    private List<Genre> genres;

    public ViewFilm(int filmId,
                    String title,
                    Date year,
                    Director director,
                    List<Actor> actors,
                    String description,
                    String image,
                    List<Genre> genres) {
        this.filmId = filmId;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.description = description;
        this.image = image;
        this.genres = genres;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}