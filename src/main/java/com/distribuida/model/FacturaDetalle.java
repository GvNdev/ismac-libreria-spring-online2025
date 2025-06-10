package com.distribuida.model;

public class FacturaDetalle {
    private int idFacturaDetalle;
    private int q;
    private Double netTotal;
    private Factura factura;
    private Libro libro;

    public FacturaDetalle() {
    }

    public FacturaDetalle(int idFacturaDetalle, int q, Double netTotal, Factura factura, Libro libro) {
        this.idFacturaDetalle = idFacturaDetalle;
        this.q = q;
        this.netTotal = netTotal;
        this.factura = factura;
        this.libro = libro;
    }

    public int getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(int idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "idFacturaDetalle=" + idFacturaDetalle +
                ", q=" + q +
                ", netTotal=" + netTotal +
                ", factura=" + factura +
                ", libro=" + libro +
                '}';
    }
}
