package model;

public class Rating {
    private int stars;
    private String comments;

    public Rating(int rating, String comment) {
        this.stars = rating;
        this.comments = comment;
    }

    public int getStars() {
        return stars;
    }

    public String getComments() {
        return comments;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
