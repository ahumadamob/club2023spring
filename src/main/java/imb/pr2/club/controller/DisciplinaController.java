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



@RestController
@RequestMapping("/api/v1/disciplinas")

public class DisciplinaController {
	
	@Autowired
	public IDisciplinaService disciplinaService;

	@GetMapping
	public ResponseEntity<APIResponse<List<Disciplina>>> mostrarTodasLasDisciplinas(){
		
		List<Disciplina> disciplina = disciplinaService.buscarTodos();
		if(disciplina.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron disciplinas");
		}else {
			return ResponseUtil.success(disciplina);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Disciplina>> mostrarDisciplinaPorId(@PathVariable("id") Integer id) {
	    Disciplina disciplina = disciplinaService.buscarPorId(id);
	    if (disciplinaService.exists(id)) {
	        return ResponseUtil.success(disciplina); // Pasamos el resultado de buscarPorId directamente
	    } else {
	        return ResponseUtil.notFound("No se encontró la disciplina");
	    }
	}

	
	
	@PostMapping
	public ResponseEntity<APIResponse<Disciplina>> crearDisciplina(@RequestBody Disciplina disciplina){
		return (disciplinaService.exists(disciplina.getId()))? ResponseUtil.badRequest("Ya existe una disciplina")
				:ResponseUtil.created(disciplinaService.guardar(disciplina));
	}
	
	
	@PutMapping
	public ResponseEntity<APIResponse<Disciplina>> modificarDisciplina(@RequestBody Disciplina disciplina){
		if (disciplinaService.exists(disciplina.getId())) {
			return ResponseUtil.success(disciplinaService.guardar(disciplina));
		}else {
			return ResponseUtil.badRequest("No existe la disciplina");
		}
}
	
	/*En el controlador, documenta con comentarios el método DELETE para la eliminación de un elemento por su id*/
	/*

1. `@DeleteMapping("/{id}")`: Esta anotación de Spring Boot indica que este método manejará peticiones HTTP DELETE a una URL que tiene un parámetro de ruta llamado "id". Esto significa que el ID de la disciplina que se desea eliminar se proporciona en la URL, por ejemplo, `/disciplinas/123` donde 123 sería el ID de la disciplina.

2. `public ResponseEntity<APIResponse<Disciplina>> eliminarDisciplinaPorId(@PathVariable("id") Integer id)`: Este es el método que maneja las peticiones DELETE. Toma un parámetro de ruta llamado "id", que se pasa como un parámetro en la función. La función devuelve un objeto `ResponseEntity` que contiene una respuesta HTTP y un tipo de contenido. En este caso, el tipo de contenido es `APIResponse` que parece ser un contenedor personalizado para enviar respuestas desde el servidor. El tipo de contenido de la respuesta es `Disciplina`, que parece ser la entidad que se está eliminando.

3. `if (disciplinaService.exists(id)) {`: Aquí, el código verifica si existe una disciplina con el ID proporcionado. Esto se hace llamando a un método `exists` en el objeto `disciplinaService`. Si existe una disciplina con ese ID, se procede a eliminarla.

4. `disciplinaService.eliminar(id);`: Si la disciplina existe, se llama al método `eliminar` en el objeto `disciplinaService` para eliminar la disciplina con el ID proporcionado.

5. `return ResponseUtil.badRequest("La Disciplina ha sido eliminada");`: Si la eliminación es exitosa, se devuelve una respuesta HTTP con un estado "BadRequest" (aunque el mensaje sugiere que debería ser un estado "OK" o "NoContent" ya que la eliminación se realizó con éxito) junto con un mensaje que dice que la disciplina ha sido eliminada.

6. `else {`: Si en el paso 3 se determina que la disciplina con el ID proporcionado no existe, se ejecutará el código en este bloque.

7. `return ResponseUtil.badRequest("No existe la disciplina con el ID = " + id);`: En este caso, se devuelve una respuesta HTTP con estado "BadRequest" y un mensaje que indica que no existe ninguna disciplina con el ID proporcionado.

En resumen, este código es un controlador que maneja solicitudes DELETE para eliminar una disciplina por su ID. Si la disciplina existe, se elimina y se devuelve un mensaje de éxito. Si la disciplina no existe, se devuelve un mensaje de error. Sin embargo, el uso del estado "BadRequest" en lugar de "OK" o "NoContent" puede no ser apropiado en el contexto de eliminar con éxito una entidad.*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Disciplina>> eliminarDisciplinaPorId(@PathVariable ("id") Integer id){
		if(disciplinaService.exists(id)) {
		disciplinaService.eliminar(id);
			return ResponseUtil.badRequest("La Disciplina a sido eliminada");
		}else {
			return ResponseUtil.badRequest("No existe la disciplina con el ID = " + id);
		}		
	}
	
	
@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstrainViolationException(ConstraintViolationException ex){
	return ResponseUtil.handleConstraintException(ex);
}
	
}
/*Además, se debe tener en cuenta lo siguiente:

Convención de nombres: para los nombres de los métodos en el controlador, debes incluir la referencia a su entidad (a diferencia de lo que sucede en el servicio) por ejemplo: buscarPersonaPorId.
Utilidades del response: utilizar el objeto ResponseEntity combinado con el objeto APIResponse. Utilizar la utilidad ResponseUtil.
Manejo de excepciones: añadir el manejo de excepciones utilizando la utilidad del ResponseUtil*/