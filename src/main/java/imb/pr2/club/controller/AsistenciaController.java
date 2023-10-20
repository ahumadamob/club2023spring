package imb.pr2.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	IClaseService claseService;
	
	@Autowired
	IAsistenciaService asistenciaService;
	
	//obtener todas las asistencias
	 @GetMapping("/asistencias")
	    public ResponseEntity<APIResponse<List<Asistencia>>> obtenerTodasLasAsistencias() {
	        List<Asistencia> asistencias = asistenciaService.buscarTodos();
	       //devolver respuestas usando ResponseUtil
	        return ResponseUtil.success(asistencias);
	    }
	 
        //obtener asistencia por id
	    @GetMapping("/asistencia/{id}")
	    public ResponseEntity<APIResponse<Asistencia>> obtenerAsistenciaPorId(@PathVariable Integer id) {
	       
	        //si no se encuentra la asistencia devuleve una respuesta 404 usando ResponseUtil
	        if (!asistenciaService.exists(id)) {
	            return ResponseUtil.notFound("Asistencia no encontrada.");
	        }
	        
            Asistencia asistencia = (Asistencia) asistenciaService.buscarPorId(id);
            //respuesta exitosa utilizando ResponseUtil
	        return ResponseUtil.success(asistencia);
	    }
	    
	
    
	  @PutMapping("/asistencia/{id}")
	    public ResponseEntity<APIResponse<String>> actualizarAsistencia(@PathVariable Integer id, @RequestBody Asistencia asistenciaActualizada) {
	        if (!asistenciaService.exists(id)) {
	            return ResponseUtil.notFound("Asistencia no encontrada.");
	        } else {
	            // Actualizar los datos de la asistencia existente con los datos de la asistencia actualizada
	            // asistenciaService.guardar(asistenciaActualizada);
	            // Actualiza otros campos si es necesario

	            return ResponseUtil.success("Asistencia actualizada correctamente.");
	        }
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
	          // Establecer la clase a la que está asociada la asistencia
	          asistenciaDesdeElBody.setClase(clase);
	          
	          // Si hay espacio disponible, realizar la inscripción y devolver una respuesta exitosa
	          asistenciaService.guardar(asistenciaDesdeElBody);
	          return ResponseUtil.success("Inscripto correctamente.");
	      } else {
	          // Si se supera el cupo máximo, devolver un mensaje de error
	          return ResponseUtil.badRequest("No se puede inscribir porque supera el cupo.");
	      }
	  }

	 

	  // Método para eliminar una asistencia por ID
	    @DeleteMapping("/asistencia/{id}")
	    public ResponseEntity<APIResponse<String>> eliminarAsistencia(@PathVariable Integer id) {
	        if (!asistenciaService.exists(id)) {
	            return ResponseUtil.notFound("Asistencia no encontrada.");
	        }

	        // Si se encuentra la asistencia, eliminarla y devolver una respuesta exitosa utilizando ResponseUtil
	        asistenciaService.eliminar(id);
	        return ResponseUtil.success("Asistencia eliminada correctamente.");
	    }

	
}
	

