package imb.pr2.club.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping("/api/v1/socio")
public class SocioController {
	
	@Autowired
	private ISocioService socioService;
	
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Socio>>> buscarTodosLosSocios() {
		List<Socio> socios = socioService.buscarTodos();
		return (socios.isEmpty()) ? ResponseUtil.notFound("No se encontraron Socios.") 
				: ResponseUtil.success(socios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Socio>> buscarSocioPorId(@PathVariable("id") Integer id) {
		
		return (socioService.existe(id)) ? ResponseUtil.success(socioService.buscarPorId(id)) 
				: ResponseUtil.notFound("No se encontro un Socio con el Id especificado.");
		
		}
	
	@PostMapping
	public ResponseEntity<APIResponse<Socio>> crearSocio(@RequestBody Socio socio) {
		
		return (socioService.existe(socio.getId())) ? ResponseUtil.badRequest("Ya existe un Socio con este Id.")
				: ResponseUtil.created(socioService.guardar(socio));
			
	}
	
	@PutMapping
	public ResponseEntity<APIResponse<Socio>> modificarSocio(@RequestBody Socio socio) {
		
		return (socioService.existe(socio.getId())) ? ResponseUtil.success(socioService.guardar(socio)) 
				: ResponseUtil.notFound("No se encontro un Socio con el Id especificado.");
	}
	
	/*-----------------------------------------------------------------------------------------------------------------------------
	 													EXPLICACIÓN MÉTODO POST
	  -----------------------------------------------------------------------------------------------------------------------------
	1- @PutMapping es una anotación que indica que el método Java que sigue a esta anotación manejará solicitudes HTTP de tipo POST
	2- El método devolverá un objeto ResponseEntity que contiene un tipo genérico APIResponse<Socio>. ResponseEntity se utiliza para 
	encapsular la respuesta HTTP que se enviará de vuelta al cliente.
	3- crearSocio: Es el nombre que le vamos a dar al método.
	4- @RequestBody Socio socio: Esta anotación indica que el parámetro socio se vinculará al cuerpo de la solicitud HTTP 
	y se convertirá automáticamente en un objeto Java de tipo Socio.
	5- socioService.existe(socio.getId()): Llama a al método existe en el servicio llamado socioService y pasa el ID del Socio 
	como argumento. El método existe comprueba si ya existe un socio en la base de datos con el mismo ID.
	6- Si existe devuelve true y se ejecuta la siguiente parte del código:
		-> ResponseUtil.badRequest("Ya existe un Socio con este Id."): Esto crea una respuesta de error (código 400 - Bad Request) 
		utilizando el método badRequest de ResponseUtil y proporciona un mensaje de error indicando que ya existe un socio con 
		el mismo ID.
	7- Si no existe devuelve false y se ejecuta la siguiente parte del código:
		-> ResponseUtil.created(socioService.guardar(socio)): Esto crea una respuesta exitosa (código 201 - Created) 
		utilizando el método created de ResponseUtil y llama al método socioService.guardar(socio) para guardar el nuevo socio
		 en la base de datos.
	
	-------------------------------------------------------------------------------------------------------------------------------
	*/
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Socio>> eliminarSocio(@PathVariable("id") Integer id) {
		
		if(socioService.existe(id)) {
			socioService.eliminar(id);
			return ResponseUtil.success(null);
		}else {
			return ResponseUtil.notFound("No se encontro un Socio con el Id especificado.");
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
	
		return ResponseUtil.handleConstraintException(ex);
	}
	
	
}