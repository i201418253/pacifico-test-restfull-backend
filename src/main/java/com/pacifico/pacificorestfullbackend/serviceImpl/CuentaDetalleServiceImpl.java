package com.pacifico.pacificorestfullbackend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pacifico.pacificorestfullbackend.entity.CuentaDetalle;
import com.pacifico.pacificorestfullbackend.entity.dao.CuentaDetalleDao;
import com.pacifico.pacificorestfullbackend.service.CuentaDetalleService;

@Service
public class CuentaDetalleServiceImpl implements CuentaDetalleService {
	@Autowired
	private CuentaDetalleDao cuentaDetalleDao;

	@Override
	@Transactional(readOnly = true)
	public List<CuentaDetalle> findAll() {
		return (List<CuentaDetalle>) cuentaDetalleDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CuentaDetalle findById(Long id) {
		return cuentaDetalleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public CuentaDetalle save(CuentaDetalle cuentaDetalle) {
		return cuentaDetalleDao.save(cuentaDetalle);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cuentaDetalleDao.deleteById(id);
	}

}
