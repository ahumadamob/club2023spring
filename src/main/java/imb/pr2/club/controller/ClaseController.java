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

import imb.pr2.club.entity.Clase;
import imb.pr2.club.service.IClaseService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/clases")
public class ClaseController {

	@Autowired
	IClaseService claseService;

	@GetMapping
	public ResponseEntity<APIResponse<List<Clase>>> mostrarTodasLasClase() {

		List<Clase> clase = claseService.buscarTodos();
		if (clase.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron clases");
		}
		return ResponseUtil.success(clase);
	}

	/**
	 * Este método es un controlador que responde a solicitudes GET para obtener información
	 * sobre una "Clase" específica a través de su identificador (ID). 
	 * 
	 * Este método es de visibilidad pública, se llama mostrarClasePorId y retorna
	 * un objeto del tipo ResposeEntity(clase propia de Spring para controlar y personalizar la respuesta HTTP)
	 * que envuelve un objeto APIResponse que contendrá una instancia de la clase Clase. 
	 *
	 *La funcionalidad de la línea: Clase clase = claseService.buscarPorId(id), es buscar una clase utilizando el servicio 
	 *claseService filtrando por un Id específico pasado por parámetro y luego asigna ese objeto encontrado a la 
	 *variable clase.
	 *
	 * Si la Clase existe en el sistema (verificado mediante claseService.exists, el cual
	 * devuelve un booleano(false o true) para indicar si se encuentra la clase), se crea una respuesta
	 * exitosa utilizando ResponseUtil.success.(claseService.buscarPorId(id)) Esta respuesta incluye 
	 * un código de estado HTTP 200 (OK) y la Clase recuperada como datos.
	 *
	 * Si la Clase no se encuentra, se genera una respuesta de error utilizando ResponseUtil.badRequest.
	 * ("No se encuentró una clase con el Id proporcionado") Esta respuesta tiene un código de estado 
	 * HTTP 400 (Bad Request) y un mensaje que indica que no se encontró la Clase.
	 *
	 * @param id El identificador de la Clase que se desea recuperar.
	 * @return ResponseUtil.success.(claseService.buscarPorId(id)) Esta respuesta incluye 
	 * un código de estado HTTP 200 (OK) y la Clase recuperada como datos.
	 * @return ResponseUtil.badRequest.("No se encuentró una clase con el Id proporcionado") 
	 * Esta respuesta tiene un código de estado HTTP 400 (Bad Request) y un mensaje que indica que no se encontró la Clase.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Clase>> mostrarClasePorId(@PathVariable("id") Integer id) {
		Clase clase = claseService.buscarPorId(id);

		return (claseService.exists(id)) ? ResponseUtil.success(claseService.buscarPorId(id)) 
				: ResponseUtil.badRequest("No se encuentró una clase con el Id proporcionado");

	}

	@PostMapping
	public ResponseEntity<APIResponse<Clase>> crearClase(@RequestBody Clase clase) {

			return (claseService.exists(clase.getId() ))? ResponseUtil.badRequest("Ya existe una Clase")
					: ResponseUtil.created(claseService.guardar(clase));
		} 

	

	@PutMapping
	public ResponseEntity<APIResponse<Clase>> modificarClase(@RequestBody Clase clase) {
		
		return (claseService.exists(clase.getId())) ? ResponseUtil.success(claseService.guardar(clase)) 
				: ResponseUtil.badRequest("No existe la clase");

		

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<String>> eliminarClase(@PathVariable("id") Integer id) {

		if (claseService.exists(id)) {
			claseService.eliminarClase(id);
			return ResponseUtil.success("La Clase que figura en el cuerpo ha sido eliminada");
		} else {
			return ResponseUtil.badRequest("No existe la clase con el ID = " + id);
		}

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

}
