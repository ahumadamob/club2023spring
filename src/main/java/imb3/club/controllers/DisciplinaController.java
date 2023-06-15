package imb3.club.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb3.club.entity.Disciplina;
import imb3.club.service.IDisciplinaService;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController {
	
	@Autowired
	public IDisciplinaService service;


}
