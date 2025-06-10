package com.distribuida.model;

import java.util.Date;

public class Factura {
    private int idFactura;
    private String billNo;
    private Date date;
    private Double netTotal;
    private Double iva;
    private Double total;
    private Cliente cliente;

    public Factura() {
    }

    public Factura(int idFactura, String billNo, Date date, Double netTotal, Double iva, Double total, Cliente cliente) {
        this.idFactura = idFactura;
        this.billNo = billNo;
        this.date = date;
        this.netTotal = netTotal;
        this.iva = iva;
        this.total = total;
        this.cliente = cliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", billNo='" + billNo + '\'' +
                ", date=" + date +
                ", netTotal=" + netTotal +
                ", iva=" + iva +
                ", total=" + total +
                ", cliente=" + cliente +
                '}';
    }
}
