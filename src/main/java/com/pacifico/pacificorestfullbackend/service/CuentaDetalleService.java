package com.pacifico.pacificorestfullbackend.service;

import java.util.List;

import com.pacifico.pacificorestfullbackend.entity.CuentaDetalle;

public interface CuentaDetalleService {

	public List<CuentaDetalle> findAll();

	public CuentaDetalle findById(Long id);

	public CuentaDetalle save(CuentaDetalle cuentaDetalle);

	public void delete(Long id);

}
