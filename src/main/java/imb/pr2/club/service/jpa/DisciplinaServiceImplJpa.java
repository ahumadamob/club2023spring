package imb.pr2.club.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.club.entity.Disciplina;
import imb.pr2.club.repository.DisciplinaRepository;
import imb.pr2.club.service.IDisciplinaService;

@Service
@Primary
public class DisciplinaServiceImplJpa implements IDisciplinaService{
	
	@Autowired
	DisciplinaRepository repo;

	@Override
	public List<Disciplina> buscarDisciplinas() {
	
		return repo.findAll();
		
	}

	@Override
	public Disciplina buscarDisciplinaPorId(Integer id) {
		
		Optional<Disciplina> optionalDisciplina;
		optionalDisciplina =repo.findById(id);
		 if(optionalDisciplina.isPresent()) {
			 return optionalDisciplina.get();
		 }
		 else {
			 return null;
		 }
	}

	@Override
	public void guardarDisciplina(Disciplina disciplina) {
		
		repo.save(disciplina);
		
	}

	@Override
	public void eliminarDisciplina(Integer id) {
		
		repo.deleteById(id);
		
	}

}
