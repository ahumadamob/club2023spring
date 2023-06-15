package imb3.club.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb3.club.entity.Disciplina;
import imb3.club.service.IDisciplinaService;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController {
	
	@Autowired
	public IDisciplinaService service;

	@GetMapping
	public ResponseEntity<APIResponse<List<Disciplina>>> listarDisciplinas(){
		
		APIResponse<List<Disciplina>> response = new APIResponse<List<Disciplina>>(200, null, service.buscarDisciplinas());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping ("/{id}")
	public ResponseEntity<APIResponse<Disciplina>> listarDisciplinaPorId(@PathVariable Integer id){
		
		Disciplina disciplina = service.buscarDisciplinaPorId(id);
		
		if(disciplina == null) {
			
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Disciplina con id = " + id.toString());
			messages.add("Verifique el parámetro ingresado");
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		else {
			
			APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.OK.value(), null, disciplina);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
			
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Disciplina>> crearDisciplina(@RequestBody Disciplina disciplina){
	
		if(disciplina.getId() != null) {	
			Disciplina buscaDisciplina = service.buscarDisciplinaPorId(disciplina.getId());
			
			if(buscaDisciplina != null) {
				List<String> messages = new ArrayList<>();
				messages.add("Ya existe una discplina con el id = " + disciplina.getId().toString());
				messages.add("Para modificar utilice verbo PUT");
				APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.BAD_REQUEST.value(), messages, null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}
		service.guardarDisciplina(disciplina);
		APIResponse<Disciplina> response = new APIResponse<Disciplina>(HttpStatus.CREATED.value(), null, disciplina);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
