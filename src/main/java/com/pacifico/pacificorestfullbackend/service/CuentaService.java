package com.pacifico.pacificorestfullbackend.service;

import java.util.List;



import com.pacifico.pacificorestfullbackend.entity.Cuenta;

public interface CuentaService {
	//@Query("select * from cuentas c inner join cuenta_detalle cd on c.id = cd.detalle_id")
	public List<Cuenta> findAll();

	public Cuenta findById(Long id);

	public Cuenta save(Cuenta cuenta);

	public void delete(Long id);
}
