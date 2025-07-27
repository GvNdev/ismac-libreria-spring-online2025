package com.distribuida.service.impl;

import com.distribuida.dao.FacturaDetalleRepository;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    @Autowired
    private FacturaDetalleRepository facturaDetalleRepository;

    @Override
    public List<FacturaDetalle> findAll() {
        return facturaDetalleRepository.findAll();
    }

    @Override
    public FacturaDetalle findById(int id) {
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleRepository.findById(id);
        return facturaDetalle.orElse(null);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle facturaDetalle) {
        return facturaDetalleRepository.save(facturaDetalle);
    }

    @Override
    public FacturaDetalle update(int id, FacturaDetalle facturaDetalle) {
        FacturaDetalle foundFacturaDetalle = findById(id);

        if (foundFacturaDetalle == null) {
            return null;
        }

        foundFacturaDetalle.setQ(facturaDetalle.getQ());
        foundFacturaDetalle.setNetTotal(facturaDetalle.getNetTotal());
        foundFacturaDetalle.setFactura(facturaDetalle.getFactura());
        foundFacturaDetalle.setFactura(facturaDetalle.getFactura());
        foundFacturaDetalle.setLibro(facturaDetalle.getLibro());

        return facturaDetalleRepository.save(foundFacturaDetalle);
    }

    @Override
    public void delete(int id) {
        if (facturaDetalleRepository.existsById(id)) {
            facturaDetalleRepository.deleteById(id);
        }
    }
}
