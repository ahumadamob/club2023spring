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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import imb.pr2.club.entity.Disciplina;
import imb.pr2.club.service.IDisciplinaService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController {
	
	@Autowired
	public IDisciplinaService disciplinaService;

	@GetMapping
	public ResponseEntity<APIResponse<List<Disciplina>>> listarDisciplinas(){
		APIResponse<List<Disciplina>> response = new APIResponse<List<Disciplina>>(200, null, disciplinaService.buscarTodos());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping ("/{id}")
	public ResponseEntity<APIResponse<Disciplina>> listarDisciplinaPorId(@PathVariable Integer id){
		
		if(disciplinaService.exists(id)) {
			Disciplina disciplina = disciplinaService.buscarDisciplinaPorId(id);
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.OK.value(), null, disciplina);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
	
		} else {
			
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Disciplina con id = " + id.toString());
			messages.add("Verifique el parámetro ingresado");
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			
		}
	}
	
	
	@PostMapping
	public ResponseEntity<APIResponse<Disciplina>> crearDisciplina(@RequestBody Disciplina disciplina){
		
		if(disciplinaService.exists(disciplina.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una discplina con el id = " + disciplina.getId().toString());
			messages.add("Para modificar utilice verbo PUT");
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			
		}else {
			disciplinaService.guardar(disciplina);
			List<String> messages = new ArrayList<>();
			messages.add("Se creó la siguiente Disciplina:");
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.CREATED.value(), messages, disciplina);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
			
		}
	}
	
	
	@PutMapping
	public ResponseEntity<APIResponse<Disciplina>> modificarDisciplina(@RequestBody Disciplina disciplina){
		
		if(disciplinaService.exists(disciplina.getId())) {
			disciplinaService.guardarDisciplina(disciplina);
			List<String> messages = new ArrayList<>();
			messages.add("Se modificó la Disciplina correctamente");
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.OK.value(), messages, disciplina);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
			
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una discplina con el id = " + disciplina.getId().toString());
			messages.add("Verifique que el ID corresponda a una disciplina existente");
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Disciplina>> eliminarDisciplinaPorId(@PathVariable Integer id){
		
		if(disciplinaService.exists(id)) {
			Disciplina disciplina = disciplinaService.buscarDisciplinaPorId(id);
			disciplinaService.eliminarDisciplina(id);
			List<String> messages = new ArrayList<>();
			messages.add("La Disciplina que figura en el cuerpo ha sido eliminada") ;			
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.OK.value(), messages, disciplina);
			return ResponseEntity.status(HttpStatus.OK).body(response);
			
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una discplina con el id = " + id.toString());
			messages.add("Verifique que ID correspona a una disciplina existente");
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			
		}
		
	}
	
	
	
	

@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<APIResponse<?>> handleConstraintViolationException(ConstraintViolationException ex){
	List<String> errors = new ArrayList<>();
	for (ConstraintViolation<?> violation: ex.getConstraintViolations()) {
		
		errors.add(violation.getMessage());
	}
	APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.BAD_REQUEST.value(), errors, null);
	return ResponseEntity.badRequest().body(response);
	
	
}
	
}