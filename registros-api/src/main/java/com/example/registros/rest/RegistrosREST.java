package com.example.registros.rest;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.registros.dao.RegistrosDAO;
import com.example.registros.entitys.Registro;

@RestController
@RequestMapping("registros")
public class RegistrosREST {

	@Autowired
	private RegistrosDAO registrosDAO;
	
	// Todos los registros
	@GetMapping
	public ResponseEntity<List<Registro>> getRegistro(){
		List<Registro> registros = registrosDAO.findAll();
		return ResponseEntity.ok(registros);
	}
	
	// Registro por ID
	@RequestMapping(value = "{id}")
	public ResponseEntity<Registro> getRegistroById(@PathVariable("id") Long id){
		Optional<Registro> optionalRegistro = registrosDAO.findById(id);
		if(optionalRegistro.isPresent()) {
			return ResponseEntity.ok(optionalRegistro.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	// Insertar Registro
	@PostMapping
	public ResponseEntity<Registro> createRegistro(@RequestBody Registro registro){
		Registro newRegistro = registrosDAO.save(registro);
		return ResponseEntity.ok(newRegistro);
	}
	
	// Eliminar Registro
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteRegistro(@PathVariable("id") Long id){
		registrosDAO.deleteById(id);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Registro> updateRegistro(@RequestBody Registro registro){
		Optional<Registro> optionalRegistro = registrosDAO.findById(registro.getId());
		if(optionalRegistro.isPresent()) {
			Registro updateRegistro = optionalRegistro.get();
			updateRegistro.setNombre(registro.getNombre());
			updateRegistro.setFecha(registro.getFecha());
			updateRegistro.setHora(registro.getHora());
			updateRegistro.setDescripcion(registro.getDescripcion());
			registrosDAO.save(updateRegistro);
			return ResponseEntity.ok(updateRegistro);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
