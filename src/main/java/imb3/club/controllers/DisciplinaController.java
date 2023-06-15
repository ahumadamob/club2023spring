package imb3.club.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb3.club.entity.Disciplina;
import imb3.club.service.IDisciplinaService;

@RestController
@RequestMapping("/api/v1/disciplinas")
public class DisciplinaController implements IDisciplinaService  {
	
	@Autowired
	public IDisciplinaService service;

	@Override
	public List<Disciplina> buscarDisciplinas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Disciplina buscarDisciplinaPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarDisciplina(Disciplina disciplina) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarDisciplina(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
