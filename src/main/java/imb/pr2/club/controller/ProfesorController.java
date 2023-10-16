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
	public ResponseEntity<APIResponse<List<Profesor>>> mostrarTodosLosProfesores() {	
		
		List<Profesor> profesores = profesorService.buscarTodos();
		if(profesores.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron Profesores");
		}else {
			return ResponseUtil.success(profesores);
		}
	}
	/*
	 
	 El codigo escrito para el Metodo GetMapping consiste en:
	 - GetMapping: 
	 				Es una anotacion que podemos utilizar gracias al Framework SpringBoot que nos permite
	 				mapear una solicitud http GET. Por lo tanto, cuando un usuario realiza dicha peticion
	 				con la URL correspondiente("http://localhost:8080/api/v1/profesor") se ejecuta el Metodo GET.
	 
	 - public ResponseEntity<APIResponse<List<Profesor>>> mostrarTodosLosProfesores(): 
	 				En esta linea de codigo
	 				estamos hablando de un metodo publico(se puede acceder a el fuera de la clase) que utiliza o 
	 				devuelve un objeto ResponseEntity(perteneciente a SpringBoot) para controlar la respuesta que
	 				obtiene el usuario o cliente al ejecutar un metodo.
	 				Luego el objeto ResponseEntity devuelve un objeto del tipo APIResponse que contiene una lista
	 				con objetos del tipo Profesor.
	 				
	 - List<Profesor> profesores = profesorService.buscarTodos():
	 				En esta linea de codigo llamamos a un servicio que
	 				busca a los obetos profesores con el metodo 'buscarTodos' y nos devuelve una lista llamada 'profesores'
	 				compuesta por objetos de tipo Profesor.
	 
	 - if(profesores.isEmpty()) { return ResponseUtil.notFound("No se encontraron Profesores":
	 				Cuando el objeto 'profesores' se encuentra vacio(lista vacia) se utiliza la clase y metodo
	 				'ResponseUtil.notFound' para indicar en nuestra respuesta HTTP para contruir una respuesta clara
	 				e indicar que no se encontraron 'profesores'.
	 				
	 - else { return ResponseUtil.success(profesores)}:
	 				Si lla lista 'profesores' no esta vacia, por el contrario, contiene objetos del tipo 'Profesor', 
	 				ResponseUtil es utilizado para crear una respuesta HTTP con un codigo 200(OK) y devolver la lista
	 				de 'profesores.'

	 
	 */
	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Profesor>> mostrarProfesorPorId(@PathVariable("id") Integer id) {
		
		return (profesorService.exists(id)) ? ResponseUtil.success(profesorService.buscarPorId(id)) 
				: ResponseUtil.notFound("No se encuentra Profesor indicado por Id para mostrar");
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Profesor>> crearProfesor(@RequestBody Profesor profesor) {
		
			return (profesorService.exists(profesor.getId() ))? ResponseUtil.badRequest("Ya existe un Profesor")
					: ResponseUtil.created(profesorService.guardar(profesor));
			
		}
	
	@PutMapping
	public ResponseEntity<APIResponse<Profesor>> modificarProfesor(@RequestBody Profesor profesor) {				
		return (profesorService.exists(profesor.getId())) ? ResponseUtil.success(profesorService.guardar(profesor))
				: ResponseUtil.notFound("No se encuentra un Profesor con el Id especificado para modificar");
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Profesor>> eliminarProfesor(@PathVariable("id") Integer id) {
		
		if(profesorService.exists(id)) {
			profesorService.eliminar(id);
			return ResponseUtil.success(null);
		}else {
			return ResponseUtil.notFound("No se encuentra un Profesor con el Id especificado para eliminar");
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
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationExeption(ConstraintViolationException ex){
		return ResponseUtil.handleConstraintException(ex);
		}
		
	
}
