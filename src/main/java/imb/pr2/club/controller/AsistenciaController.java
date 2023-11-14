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
	        }else {
	        	
	        
	        Asistencia asistencia = asistenciaService.buscarPorId(id);
           
            //respuesta exitosa utilizando ResponseUtil
	        return ResponseUtil.success(asistencia);
	        }
	    }
	
    
	  @PutMapping("/asistencia")
	    public ResponseEntity<APIResponse<Asistencia>> actualizarAsistencia( @RequestBody Asistencia asistencia) {
	        if (!asistenciaService.exists(asistencia.getId())) {
	            return ResponseUtil.notFound("Asistencia no encontrada.");
	        } else {
	       	        	
	            return ResponseUtil.success(asistenciaService.guardar(asistencia));
	        }
	    }

	  @PostMapping("/asistencia")
	    public ResponseEntity<APIResponse<Asistencia>> crearAsistencia( @RequestBody Asistencia asistencia) {
	        if (asistenciaService.exists(asistencia.getId())) {
	            return ResponseUtil.badRequest("ya existe la asistencia");
	        } else {
	       	        	
	            return ResponseUtil.created(asistenciaService.guardar(asistencia));
	        }
	    }

    
	

	  // Método para eliminar una asistencia por ID
	    @DeleteMapping("/asistencia/{id}")
	    public ResponseEntity<APIResponse<Asistencia>> eliminarAsistencia(@PathVariable Integer id) {
	        if (!asistenciaService.exists(id)) {
	            return ResponseUtil.notFound("Asistencia no encontrada.");
	        }

	        // Si se encuentra la asistencia, eliminarla y devolver una respuesta exitosa utilizando ResponseUtil
	       
	        return ResponseUtil.success("SE HA ELIMINADO EL OBJETO"+ id);
	    }

	
}
	

