package imb.pr2.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr2.club.entity.Asistencia;
import imb.pr2.club.entity.Clase;
import imb.pr2.club.service.IAsistenciaService;
import imb.pr2.club.service.IClaseService;

@RestController
@RequestMapping("/api/v1/asistencia")
public class AsistenciaController {
	
	
	@Autowired
	private IClaseService claseService;
	
	@Autowired
	private IAsistenciaService asistenciaService;
	
	@GetMapping
	public List<Asistencia> obtenerTodasLasAsistencias(){
		return asistenciaService.obtenerTodos();
	}
	
	@PostMapping("/prueba")
	public Asistencia pruebaDesdeElBody(@RequestBody Asistencia asistenciaDesdeElBody) {
		return asistenciaDesdeElBody;
	}
	
	@PostMapping()
	public String inscribir(@RequestBody Asistencia asistenciaDesdeElBody){
		//inscribir (POST) antes de habilitar la inscripción, debe recuperar todos los inscriptos 
		//actuales a la clase id y contarlos. En caso que esta cantidad sea menor al cupo, 
		//hacer la inserción, de lo contrario, devolver el mensaje correspondiente a que se excede la cantidad en el cupo.
	Integer cupos;
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
	

}
	
	
