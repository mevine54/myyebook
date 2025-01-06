package fr.afpa.pompey.cda22045.myyebook.model;

import java.awt.*;

public class Book {
    private String title;
    private String summary;
    private Image image;
    private int quantity;

    // Constructeur
    public Book(String title, String summary, Image image, int quantity) {
        setTitle(title);
        setSummary(summary);
        setImage(image);
        setQuantity(quantity);
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", summary=" + summary + ", image=" + image + ", quantity=" + quantity + "]";
    }
}
