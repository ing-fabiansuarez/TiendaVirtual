package com.fabiansuarez.tiendavirtual;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String descripcion;
    private Double price;
    private String urlImage;

    public Product() {
    }

    public Product(String name, String descripcion, Double price, String urlImage) {
        this.name = name;
        this.descripcion = descripcion;
        this.price = price;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
