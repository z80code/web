package by.gsu.epamlab.dao.models;

import java.sql.Date;

public class Film {
    private int id;
    private String title;
    private Date release;
    private int directorId;
    private String description;
    private String image;

    public Film() {
    }

    public Film(String title, Date release, int directorId, String description, String image) {
        this(0, title,release,directorId,description,image);

    }

    public Film(int id, String title, Date release, int directorId, String description, String image) {
        this.id = id;
        this.title = title;
        this.release = release;
        this.directorId = directorId;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
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
}
