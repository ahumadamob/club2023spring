package imb.pr2.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import imb.pr2.club.entity.Asistencia;
import imb.pr2.club.entity.Clase;
import imb.pr2.club.service.IAsistenciaService;
import imb.pr2.club.service.IClaseService;

@RestController
@RequestMapping("/api/v1")
public class AsistenciaController {
	
	@Autowired
	private IClaseService claseService;
	
	@Autowired
	private IAsistenciaService asistenciaService;
	
	//obtener todas las asistencias
	 @GetMapping("/asistencia")
	    public ResponseEntity<APIResponse<List<Asistencia>>> obtenerTodasLasAsistencias() {
	        List<Asistencia> asistencias = asistenciaService.buscarTodos();
	       //devolver respuestas usando ResponseUtil
	        return ResponseUtil.success(asistencias);
	    }
	 
        //obtener asistencia por id
	    @GetMapping("/asistencia/{id}")
	    public ResponseEntity<APIResponse<Asistencia>> obtenerAsistenciaPorId(@PathVariable Integer id) {
	        Asistencia asistencia = (Asistencia) asistenciaService.buscarPorId(id);
           
	        //si no se encuentra la asistencia devuleve una respuesta 404 usando ResponseUtil
	        if (asistencia == null) {
	            return ResponseUtil.notFound("Asistencia no encontrada.");
	        }
            //respuesta exitosa utilizando ResponseUtil
	        return ResponseUtil.success(asistencia);
	    }
	    
	    // Método para actualizar una asistencia por su ID
	@PutMapping("/asistencia/{id}")
	    public ResponseEntity<APIResponse<String>> actualizarAsistencia1(@PathVariable Integer id, @RequestBody Asistencia asistenciaActualizada) {
	        Asistencia asistenciaExistente = (Asistencia) asistenciaService.buscarPorId(id);

	        if (asistenciaExistente == null) {
	            return ResponseUtil.notFound("Asistencia no encontrada.");
	        } else {
	            // Actualizar los datos de la asistencia existente con los datos de la asistencia actualizada
	            // ...

	            asistenciaService.guardar(asistenciaExistente);
	            return ResponseUtil.success("Asistencia actualizada correctamente.");
	        }
	    }
	 
    
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAsistencia(@PathVariable Integer id, @RequestBody Asistencia asistenciaActualizada) {
        //Asistencia asistenciaExistente = asistenciaService.obtenerPorID(id);

        if (this.existeId(id) == null) {
        	// Si la asistencia no existe, devolver una respuesta de error 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asistencia no encontrada.");
        } else {
            // Actualizar los datos de la asistencia existente con los datos de la asistencia actualizada
            //asistenciaExistente.setFechaInscripcion(asistenciaActualizada.getFechaInscripcion());
            //asistenciaExistente.setPresente(asistenciaActualizada.isPresente());
            // Actualiza otros campos si es necesario

            //asistenciaService.guardar(asistenciaExistente);
            return ResponseEntity.status(HttpStatus.OK).body("Asistencia actualizada correctamente.");
        }
    }

    private Object existeId(Integer id) {
        // Implementa la lógica para verificar si la asistencia existe
        // Retorna la asistencia si existe, de lo contrario, retorna null
    	return null;
	    }
    //Método para inscribir a una asistencia en una clase
	@PostMapping
	public Asistencia pruebaDesdeElBody(@RequestBody Asistencia asistenciaDesdeElBody) {
		return asistenciaDesdeElBody;
	}
	
	@PostMapping("/inscribir")
	public ResponseEntity<APIResponse<String>> inscribir(@RequestBody Asistencia asistenciaDesdeElBody) {
	    Integer cupos = asistenciaDesdeElBody.getClase().getCupo();
	    Clase clase = claseService.buscarClasePorId(asistenciaDesdeElBody.getClase().getId());

	    if (clase == null) {
	        return ResponseUtil.notFound("Clase no encontrada.");
	    }

	    List<Asistencia> asistencias = asistenciaService.buscarPorId(asistenciaDesdeElBody.getClase().getId());
	    Integer cantidad = asistencias.size();

	    if (cantidad < cupos) {
	    	// Si hay espacio disponible, realizar la inscripción y devolver una respuesta exitosa
	        asistenciaService.guardar(asistenciaDesdeElBody);
	        return ResponseUtil.success("Inscripto correctamente.");
	    } else {
	    	// Si se supera el cupo máximo, devolver un mensaje de error
	        return ResponseUtil.badRequest("No se puede inscribir porque supera el cupo.");
	    }
	}
	
	 

	  //método para eliminar una asistencia por ID
	 @DeleteMapping("/asistencia/{id}")
	 public ResponseEntity<APIResponse<String>> eliminarAsistencia(@PathVariable Integer id) {
		 // Buscar la asistencia por su ID en el servicio
		 List<Asistencia> asistencia = asistenciaService.buscarPorId(id);

		 // Si no se encuentra la asistencia, devolver una respuesta de error 404 utilizando ResponseUtil
	     if (asistencia == null) {
	         return ResponseUtil.notFound("Asistencia no encontrada.");
	     }

	  // Si se encuentra la asistencia, eliminarla y devolver una respuesta exitosa utilizando ResponseUtil
	     asistenciaService.eliminar(id);
	     return ResponseUtil.success("Asistencia eliminada correctamente.");
	 }

	
}
	

