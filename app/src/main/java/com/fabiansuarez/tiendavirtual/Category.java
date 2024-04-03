package com.fabiansuarez.tiendavirtual;

public class Category {
    private String name;
    private String color;
    private String iconImage;

    public Category(String name, String color, String iconImage) {
        this.name = name;
        this.color = color;
        this.iconImage = iconImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }
}
