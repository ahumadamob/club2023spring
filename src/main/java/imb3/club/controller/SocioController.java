package imb3.club.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import imb3.club.entity.Socio;
import imb3.club.service.ISocioService;

@RestController
@RequestMapping("/api/v1")
public class SocioController {
	
	@Autowired
	private ISocioService service;
	
	public List<Socio> getSocios() {
		return service.mostrarSocios();
	}
	
}