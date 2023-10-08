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
	
	@GetMapping("/asistencia")
	public List<Asistencia> obtenerTodasLasAsistencias(){
		return asistenciaService.obtenerTodos();
	}
	
	  // Agregar el método para eliminar una asistencia por ID
    @DeleteMapping("/{id}")
    public String eliminarAsistencia(@PathVariable Integer id) {
        //Asistencia asistencia = asistenciaService.obtenerPorID(id);

        if (this.existeId(id) == null) {
            return "Asistencia no encontrada.";
        }

        asistenciaService.eliminar(id);
        return "Asistencia eliminada correctamente.";
    }

    

    // Agregar el método para actualizar una asistencia
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAsistencia(@PathVariable Integer id, @RequestBody Asistencia asistenciaActualizada) {
        //Asistencia asistenciaExistente = asistenciaService.obtenerPorID(id);

        if (this.existeId(id) == null) {
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

	@PostMapping
	public Asistencia pruebaDesdeElBody(@RequestBody Asistencia asistenciaDesdeElBody) {
		return asistenciaDesdeElBody;
	}
	
	@PostMapping("/inscribir")
	public String inscribir(@RequestBody Asistencia asistenciaDesdeElBody){
		//inscribir (POST) antes de habilitar la inscripción, debe recuperar todos los inscriptos 
		//actuales a la clase id y contarlos. En caso que esta cantidad sea menor al cupo, 
		//hacer la inserción, de lo contrario, devolver el mensaje correspondiente a que se excede la cantidad en el cupo.
	
		  Integer cupos = asistenciaDesdeElBody.getClase().getCupo();
	      Clase clase = claseService.buscarClasePorId(asistenciaDesdeElBody.getClase().getId());

	        if (clase == null) {
	            return "Clase no encontrada.";
	        }

	        // Obtener la cantidad actual de inscritos por clase
	        List<Asistencia> asistencias = asistenciaService.obtenerPorClase(asistenciaDesdeElBody.getClase().getId());
	        Integer cantidad = asistencias.size();

	        if (cantidad < cupos) {
	            // Si hay espacio disponible, realizar la inserción en asistencia
	            asistenciaService.guardar(asistenciaDesdeElBody);
	            return "Inscripto correctamente.";
	        } else {
	            // Si se supera el cupo máximo, devolver un mensaje de error
	            return "No se puede inscribir porque supera el cupo.";
	        }
	    }
		
		
		
	/*Integer cupos;
	Clase clase;
	Integer cantidad = 0;
	List<Asistencia> asistencias;

	
	clase = claseService.buscarClasePorId(asistenciaDesdeElBody.getClase().getId());
	cupos = clase.getCupo();
	
	asistencias = asistenciaService.obtenerPorClase(asistenciaDesdeElBody.getClase().getId());
	
	//return asistencias;
	cantidad = asistencias.size();
	//clase = claseService.buscarClasePorId(4);
	
	if(cantidad < cupos) {
		//Si voy a hacer la insercion en asistencia
		asistenciaService.guardar(asistenciaDesdeElBody);
		return "Inscripto correctamente.";
	}else {
		return "No se puede inscribir porque supera el cupo.";
	}
	/*
	for (Asistencia elemento : asistencias){
		cantidad++;
	}
	*/
	
}
	

