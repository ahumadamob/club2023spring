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
	
	//public ResponseEntity<APIResponse<List<Project>>> 
		//APIResponse<List<Project>> response = new APIResponse<List<Project>>(200, addSingleMessage("No hay elementos"), projects);
		///return ResponseEntity.status(HttpStatus.OK).body(response);	
		@GetMapping
		public ResponseEntity<APIResponse<List<Clase>>> buscarCategorias() {		
			
			APIResponse<List<Clase>> response = new APIResponse<List<Clase>>(200, null, claseService.buscarClase());
			
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}
	
	
		@GetMapping("/{id}")
		public ResponseEntity<APIResponse<Clase>> buscarClasePorId(@PathVariable("id") Integer id) {
			
			Clase clase = claseService.buscarClasePorId(id);
			
			if(clase == null) {
				
				List<String> messages = new ArrayList<>();
				
				messages.add("No se encontró la Clase con id = " + id.toString());
				
				APIResponse<Clase> response = new APIResponse<Clase>(200, messages, clase);
				
				return ResponseEntity.status(HttpStatus.OK).body(response);	
				
			}else {
				
				APIResponse<Clase> response = new APIResponse<Clase>(200, null, clase);
				
				return ResponseEntity.status(HttpStatus.OK).body(response);	
				
			}
	
		}
		
		@PostMapping
		public ResponseEntity<APIResponse<Clase>> crearClase(@RequestBody Clase clase) {
			
			if(clase.getId() != null) {
				Clase buscaClase = claseService.buscarClasePorId(clase.getId());
				if(buscaClase != null) {
					List<String> messages = new ArrayList<>();
					messages.add("Ya existe una clase con el ID = " + clase.getId().toString());
					messages.add("Para actualizar utilice el verbo PUT");
					APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.BAD_REQUEST.value(), messages, null);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
			}
			claseService.guardarClase(clase);
			APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.CREATED.value(), null, clase);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		
		@PutMapping	
		public ResponseEntity<APIResponse<Clase>> modificarClase(@RequestBody Clase clase) {
			
			boolean isError ;
			String idStr;
			if(clase.getId() != null){
				Clase buscaClase = claseService.buscarClasePorId(clase.getId());
				idStr = clase.getId().toString();
				if(buscaClase != null) {
					// Devolver un OK
					isError = false;
				}else {
					// Devolver un Error
					isError = true;
				}
			}else {
				idStr = "<No definido>";
				// Devolver un error
				isError = true;
			}

			if(isError) {
				// Devolvemos el error
				List<String> messages = new ArrayList<>();
				messages.add("No existe una clase con el ID = " + idStr);
				messages.add("Para crear una nueva utilice el verbo POST");
				APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.BAD_REQUEST.value(), messages, null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}else {
				// Devolvemos el OK
				claseService.guardarClase(clase);
				APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.OK.value(), null, clase);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<APIResponse<Clase>> eliminarClase(@PathVariable("id") Integer id) {
			Clase buscaClase = claseService.buscarClasePorId(id);
			if(buscaClase == null) {
				// Error
				List<String> messages = new ArrayList<>();
				messages.add("No existe una clase con el ID = " + id.toString());
				APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.BAD_REQUEST.value(), messages, null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);			
			}else {
				claseService.eliminarClase(id);
				List<String> messages = new ArrayList<>();
				messages.add("La Clase que figura en el cuerpo ha sido eliminada") ;			
				APIResponse<Clase> response = new APIResponse<Clase>(HttpStatus.OK.value(), messages, buscaClase);
				return ResponseEntity.status(HttpStatus.OK).body(response);			
				// eliminar
			}

		}
		
		
		
		}
