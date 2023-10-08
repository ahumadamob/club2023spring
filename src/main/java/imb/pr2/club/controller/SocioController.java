package imb.pr2.club.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr2.club.entity.Socio;
import imb.pr2.club.service.ISocioService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/socio")
public class SocioController {
	
	@Autowired
	private ISocioService service;
	
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Socio>>> getSocios() {
		List<String> messages = new ArrayList<>();
		messages.add("Socios registrados");
		APIResponse<List<Socio>> response = new APIResponse<List<Socio>>(200, messages, service.mostrarSocios());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Socio>> mostrarSocioPorId(@PathVariable("id") Integer id) {
		if(service.exists(id)) {
			Socio socio = service.buscarSocioById(id);
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.OK.value(), null, socio);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Categoría con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}
	
	@PostMapping
	public ResponseEntity<APIResponse<Socio>> crearSocio(@RequestBody Socio socio) {
		if(service.exists(socio.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una categoria con el ID = " + socio.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			service.guardarSocio(socio);
			List<String> messages = new ArrayList<>();
			messages.add("Socio registrado con exito");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.CREATED.value(), messages, socio);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	
	@PutMapping
	public ResponseEntity<APIResponse<Socio>> editarSocio(@RequestBody Socio socio) {
		if(service.exists(socio.getId())) {
			service.guardarSocio(socio);
			List<String> messages = new ArrayList<>();
			messages.add("Datos de Socio modificados con exito");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.OK.value(), null, socio);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una categoria con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Socio>> eliminarSocio(@PathVariable("id") Integer id) {
		if(service.exists(id)) {
			service.eliminarSocio(id);
			List<String> messages = new ArrayList<>();
			messages.add("El Socio fue eliminado de la Base de Datos") ;			
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un Socio con el ID = " + id.toString());
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for(ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}
	
	
	/*//--------------Control de existencia de ID-----------------
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Socio socio = service.buscarSocioById(id);
			if(socio == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	*/
	
}