package com.distribuida.model;

import java.time.LocalDateTime;

public class Libro {
    private int idLibro;
    private String title;
    private String editorial;
    private int pages;
    private String edition;
    private String language;
    private LocalDateTime publicationDate;
    private String description;
    private String coverType;
    private String ISBN;
    private int copies;
    private String cover;
    private String presentation;
    private Double price;
    private Categoria categoria;
    private Autor autor;

    public Libro() {
    }

    public Libro(int idLibro, String title, String editorial, int pages, String edition, String language, LocalDateTime publicationDate, String description, String coverType, String ISBN, int copies, String cover, String presentation, Double price, Categoria categoria, Autor autor) {
        this.idLibro = idLibro;
        this.title = title;
        this.editorial = editorial;
        this.pages = pages;
        this.edition = edition;
        this.language = language;
        this.publicationDate = publicationDate;
        this.description = description;
        this.coverType = coverType;
        this.ISBN = ISBN;
        this.copies = copies;
        this.cover = cover;
        this.presentation = presentation;
        this.price = price;
        this.categoria = categoria;
        this.autor = autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", title='" + title + '\'' +
                ", editorial='" + editorial + '\'' +
                ", pages=" + pages +
                ", edition='" + edition + '\'' +
                ", language='" + language + '\'' +
                ", publicationDate=" + publicationDate +
                ", description='" + description + '\'' +
                ", coverType='" + coverType + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", copies=" + copies +
                ", cover='" + cover + '\'' +
                ", presentation='" + presentation + '\'' +
                ", price=" + price +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }
}
