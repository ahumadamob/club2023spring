package imb.pr2.club.controller;

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

import imb.pr2.club.entity.Profesor;
import imb.pr2.club.service.IProfesorService;

import java.util.ArrayList;//

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/profesor")
public class ProfesorController {
	
	@Autowired
	IProfesorService profesorService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Profesor>>> mostrarTodos() {		
		APIResponse<List<Profesor>> response = new APIResponse<List<Profesor>>(201, null, profesorService.buscarProfesor());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Profesor>> mostrarProfesorPorId(@PathVariable("id") Integer id) {
		
		if(profesorService.exists(id)) {
			Profesor profesor = profesorService.buscarProfesorPorId(id);
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.OK.value(), null, profesor);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el profesor con id = " + id.toString());
			messages.add("Revise nuevamente el dato");
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
	}
	
	@PostMapping("/postProfe")
	public ResponseEntity<APIResponse<Profesor>> crearProfesor(@RequestBody Profesor profesor) {
				
		if(profesorService.exists(profesor.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe un profesor con el ID = " + profesor.getId().toString());
			messages.add("Para actualizar se debe utilizar el verbo PUT");
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			profesorService.guardarProfesor(profesor);
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.CREATED.value(), null, profesor);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Profesor>> modificarProfesor(@RequestBody Profesor profesor) {
				
		if(profesorService.exists(profesor.getId())) {
			profesorService.guardarProfesor(profesor);
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.OK.value(), null, profesor);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un profesor con ese ID ");
			messages.add("Para crear un nuevo profesor debe utilizar el verbo POST");
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	@DeleteMapping("/deleteProf/{id}")	
	public ResponseEntity<APIResponse<Profesor>> eliminarProfesor(@PathVariable("id") Integer id) {
		
		if(profesorService.exists(id)) {
			profesorService.eliminarProfesor(id);
			List<String> messages = new ArrayList<>();
			messages.add("El profesor que figura en el body ha sido eliminado correctamente") ;			
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.OK.value(), messages, null);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe un profesor con el ID = " + id.toString());
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
		}
		
	}
	
	
	/*mejora de controladores BORRAR!!!
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Profesor profesor = profesorService.buscarProfesorPorId(id);
			if(profesor == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
	*/
	
	//manejador de excepciones
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleConstraintViolationExeption(ConstraintViolationException ex){
		List<String> errors= new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
				errors.add(violation.getMessage());
			}
			APIResponse<Profesor> response = new APIResponse<Profesor>(HttpStatus.BAD_REQUEST.value(),errors, null);
			return ResponseEntity.badRequest().body(response);
		}
		
	
}
