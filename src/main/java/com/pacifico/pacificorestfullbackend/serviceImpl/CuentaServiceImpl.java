package com.pacifico.pacificorestfullbackend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pacifico.pacificorestfullbackend.entity.Cuenta;
import com.pacifico.pacificorestfullbackend.entity.dao.CuentaDao;
import com.pacifico.pacificorestfullbackend.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService {
	@Autowired
	private CuentaDao cuentaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> findAll() {
		return (List<Cuenta>) cuentaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta findById(Long id) {
		return cuentaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cuenta save(Cuenta cuenta) {
		return cuentaDao.save(cuenta);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cuentaDao.deleteById(id);

	}

}
