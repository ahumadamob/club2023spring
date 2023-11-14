package imb.pr2.club.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr2.club.entity.Espacio;
import imb.pr2.club.service.IEspacioService;


@RestController
@RequestMapping("/api/v1/Espacio")
public class EspacioController {

	
	@Autowired
	IEspacioService espacioService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Espacio>>> mostrarEspacios() {

		APIResponse<List<Espacio>> response = new APIResponse<List<Espacio>>(200, null, espacioService.mostrarEspacios());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Espacio>> mostrarEspacioPorId(@PathVariable("id") Integer id) {

		if (this.existe(id)) {
			Espacio espacio = espacioService.mostrarEspacioPorId(id);
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.OK.value(), null, espacio);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró un espacio con el id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	
	@PostMapping
	public ResponseEntity<APIResponse<Espacio>> crearEspacio(@RequestBody Espacio espacio) {

		if (this.existe(espacio.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un espacio con el ID = " + espacio.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			espacioService.modificarEspacio(espacio);;
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.CREATED.value(), null, espacio);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}

	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Espacio>> modificarEspacio(@RequestBody Espacio espacio) {

		if (this.existe(espacio.getId())) {
			espacioService.modificarEspacio(espacio);;
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.OK.value(), null, espacio);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un espacio con el ID especificado");
			messages.add("Para crear uno nuevo utilice el verbo POST");
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Espacio>> eliminarEspacio(@PathVariable("id") Integer id) {

		if (this.existe(id)) {
			espacioService.eliminarEspacio(id);
			List<String> messages = new ArrayList<>();
			messages.add("El espacio que figura en el cuerpo ha sido eliminado");
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un espacio con el ID = " + id.toString());
			APIResponse<Espacio> response = new APIResponse<Espacio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	
	private boolean existe(Integer id) {
		if (id == null) {
			return false;
		} else {
			Espacio espacio = espacioService.mostrarEspacioPorId(id);
			if (espacio == null) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	
}
