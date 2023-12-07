package edu.lambton.roomify.landlord.model;

public class Picture {
    private Long id;
    private String path;
    private Long creationDate;

    public Picture() {
    }

    public Picture(Long id, String path, Long creationDate) {
        this.id = id;
        this.path = path;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }
}
