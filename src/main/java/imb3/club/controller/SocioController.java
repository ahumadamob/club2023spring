package imb3.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import imb3.club.entity.Socio;
import imb3.club.service.ISocioService;

@RestController
@RequestMapping("/api/v1")
public class SocioController {
	
	@Autowired
	private ISocioService service;
	
	@GetMapping("/socios")
	public List<Socio> getSocios() {
		return service.mostrarSocios();
	}
	
	@PostMapping("/socios")
	public Socio postSocio(@RequestBody Socio socio) {
		service.guardarSocio(socio);
		return socio;
	}
}