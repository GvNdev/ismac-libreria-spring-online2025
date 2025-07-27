package com.distribuida.service.impl;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(int id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElse(null);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(int id, Factura factura) {
        Factura foundFactura = findById(id);

        if (foundFactura == null) {
            return null;
        }

        foundFactura.setBillNo(factura.getBillNo());
        foundFactura.setDate(factura.getDate());
        foundFactura.setNetTotal(factura.getNetTotal());
        foundFactura.setIva(factura.getIva());
        foundFactura.setTotal(factura.getTotal());
        foundFactura.setCliente(factura.getCliente());

        return facturaRepository.save(foundFactura);
    }

    @Override
    public void delete(int id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
        }
    }
}
