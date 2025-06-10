package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private int idCategoria;
    @Column(name = "categoria")
    private String category;
    @Column(name = "descripcion")
    private String description;

    public Categoria() {
    }

    public Categoria(int idCategoria, String category, String description) {
        this.idCategoria = idCategoria;
        this.category = category;
        this.description = description;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
