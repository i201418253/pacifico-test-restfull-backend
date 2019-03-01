package com.pacifico.pacificorestfullbackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pacifico.pacificorestfullbackend.entity.CuentaDetalle;
import com.pacifico.pacificorestfullbackend.service.CuentaDetalleService;

@RestController
@RequestMapping("/api")
public class CuantaDetalleRestController {
	@Autowired
	private CuentaDetalleService cuentaDetalleService;

	@GetMapping("/detalles")
	public List<CuentaDetalle> index() {
		return cuentaDetalleService.findAll();
	}

	@GetMapping("/detalles/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		CuentaDetalle cuentaDetalle = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cuentaDetalle = cuentaDetalleService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cuentaDetalle == null) {
			response.put("mensaje", "El cuenta ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CuentaDetalle>(cuentaDetalle, HttpStatus.OK);
	}

	@PostMapping("/detalles")
	public ResponseEntity<?> create(@Valid @RequestBody CuentaDetalle cuentaDetalle, BindingResult result) {
		CuentaDetalle cuentaDetalleNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			cuentaDetalleNew = cuentaDetalleService.save(cuentaDetalle);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La cuenta ha sido creado con éxito!");
		response.put("cuentaDetalle", cuentaDetalleNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/detalles/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CuentaDetalle cuentaDetalle, BindingResult result,
			@PathVariable Long id) {

		CuentaDetalle cuentaDetalleActual = cuentaDetalleService.findById(id);

		CuentaDetalle cuentaDetalleUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (cuentaDetalleActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cuenta ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			cuentaDetalleActual.setCodigo(cuentaDetalle.getCodigo());
			cuentaDetalleActual.setFecha(cuentaDetalle.getFecha());
			cuentaDetalleActual.setImporte(cuentaDetalle.getImporte());
			cuentaDetalleActual.setTipocuenta(cuentaDetalle.getTipocuenta());
			cuentaDetalleActual.setAgencia(cuentaDetalle.getAgencia());
			cuentaDetalleUpdated = cuentaDetalleService.save(cuentaDetalleActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la detalle en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cuenta ha sido actualizado con éxito!");
		response.put("cuenta", cuentaDetalleUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/detalles/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			cuentaDetalleService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la detalle de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La cuenta eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
