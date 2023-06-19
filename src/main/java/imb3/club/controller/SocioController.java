package imb3.club.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import imb.progra.arq.controllers.APIResponse;
import imb3.club.entity.Socio;
import imb3.club.service.ISocioService;


@RestController
@RequestMapping("/api/v1")
public class SocioController {
	
	@Autowired
	private ISocioService service;
	
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Socio>>> getSocios() {		
		APIResponse<List<Socio>> response = new APIResponse<List<Socio>>(200, null, service.mostrarSocios());
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Socio>> mostrarSocioPorId(@PathVariable("id") Integer id) {
		if(this.existe(id)) {
			Socio socio = service.buscarSocioById(id);
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.OK.value(), null, socio);
			return ResponseEntity.status(HttpStatus.OK).body(response);	
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró la Categoría con id = " + id.toString());
			messages.add("Revise nuevamente el parámetro");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}
	
	@PostMapping
	public ResponseEntity<APIResponse<Socio>> crearSocio(@RequestBody Socio socio) {
		if(this.existe(socio.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una categoria con el ID = " + socio.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			service.guardarSocio(socio);
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.CREATED.value(), null, socio);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}			
	}
	
	
	@PutMapping
	public ResponseEntity<APIResponse<Socio>> editarSocio(@RequestBody Socio socio) {
		if(this.existe(socio.getId())) {
			service.guardarSocio(socio);
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.OK.value(), null, socio);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe una categoria con el ID especificado");
			messages.add("Para crear una nueva utilice el verbo POST");
			APIResponse<Socio> response = new APIResponse<Socio>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}
	
	
	
	

	
	
	
	//--------------Control de existencia de ID-----------------
	private boolean existe(Integer id) {
		if(id == null) {
			return false;
		}else{
			Socio socio = service.buscarSocioById(id);
			if(socio == null) {
				return false;				
			}else {
				return true;
			}
		}
	}
}