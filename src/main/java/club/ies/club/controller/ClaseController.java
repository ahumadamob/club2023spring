package club.ies.club.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.ies.club.entity.Clase;
import club.ies.club.service.IClaseService;

@RestController
@RequestMapping("/api/v1/clase")
public class ClaseController {

	@Autowired
	IClaseService claseService;

	// public ResponseEntity<APIResponse<List<Project>>>
	// APIResponse<List<Project>> response = new APIResponse<List<Project>>(200,
	// addSingleMessage("No hay elementos"), projects);
	/// return ResponseEntity.status(HttpStatus.OK).body(response);
	@GetMapping
	public ResponseEntity<APIResponse<List<Clase>>> buscarCategorias() {

		APIResponse<List<Clase>> response = new APIResponse<List<Clase>>(200, null, claseService.buscarClase());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Clase>> buscarClasePorId(@PathVariable("id") Integer id) {

		if (this.existe(id)) {
			Clase clase = claseService.buscarClasePorId(id);
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.OK.value(), null, clase);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Clase con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}

	@PostMapping
	public ResponseEntity<APIResponse<Clase>> crearClase(@RequestBody Clase clase) {

		if (this.existe(clase.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una clase con el ID = " + clase.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			claseService.guardarClase(clase);
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.CREATED.value(), null, clase);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}

	}

	@PutMapping
	public ResponseEntity<APIResponse<Clase>> modificarClase(@RequestBody Clase clase) {

		if (this.existe(clase.getId())) {
			claseService.guardarClase(clase);
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.OK.value(), null, clase);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una clase con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Clase>> eliminarClase(@PathVariable("id") Integer id) {

		if (this.existe(id)) {
			claseService.eliminarClase(id);
			List<String> messages = new ArrayList<>();
			messages.add("La Clase que figura en el cuerpo ha sido eliminada");
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una categoria con el ID = " + id.toString());
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}

	private boolean existe(Integer id) {
		if (id == null) {
			return false;
		} else {
			Clase clase = claseService.buscarClasePorId(id);
			if (clase == null) {
				return false;
			} else {
				return true;
			}
		}
	}

}
