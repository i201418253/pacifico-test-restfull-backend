package com.pacifico.pacificorestfullbackend.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String producto;
	@Column
	private String cuentan;
	@Column
	private String alias;
	@Column
	private double saldo;

	///
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuenta_id")
	private List<CuentaDetalle> listaDetalles = new ArrayList<>();

	/// end

	public Long getId() {
		return id;
	}

	public List<CuentaDetalle> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<CuentaDetalle> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getCuentan() {
		return cuentan;
	}

	public void setCuentan(String cuentan) {
		this.cuentan = cuentan;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
