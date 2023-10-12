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
	 * sobre una "Clase" específica a través de su identificador (ID). El método busca la Clase
	 * utilizando el servicio claseService y el ID proporcionado como parámetro.
	 *
	 * Si la Clase existe en el sistema (verificado mediante claseService.exists), se crea una respuesta
	 * exitosa utilizando ResponseUtil.success. Esta respuesta incluye un código de estado HTTP 200 (OK) y
	 * la Clase recuperada como datos.
	 *
	 * Si la Clase no se encuentra, se genera una respuesta de error utilizando ResponseUtil.badRequest.
	 * Esta respuesta tiene un código de estado HTTP 400 (Bad Request) y un mensaje que indica que no se
	 * encontró la Clase.
	 *
	 * @param id El identificador de la Clase que se desea recuperar.
	 * @return Una respuesta exitosa con los datos de la Clase si se encuentra.
	 * @return Una respuesta de error con un mensaje si la Clase no se encuentra.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Clase>> mostrarClasePorId(@PathVariable("id") Integer id) {
		Clase clase = claseService.buscarPorId(id);

		if (claseService.exists(id)) {

			ResponseUtil.badRequest("Ya existe una clase");return ResponseUtil.success(clase);
		} else {

			return ResponseUtil.badRequest("No se encontró la clase");
		}

	}

	@PostMapping
	public ResponseEntity<APIResponse<Clase>> crearClase(@RequestBody Clase clase) {

		if (claseService.exists(clase.getId())) {

			return 
		} else {

			return ResponseUtil.created(claseService.guardar(clase));
		}

	}

	@PutMapping
	public ResponseEntity<APIResponse<Clase>> modificarClase(@RequestBody Clase clase) {

		if (claseService.exists(clase.getId())) {

			return ResponseUtil.success(claseService.guardar(clase));
		} else {

			return ResponseUtil.badRequest("No existe la clase");
		}

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
