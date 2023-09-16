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
	public Disciplina buscarDisciplinaPorId(Integer id){
		// Buscar una disciplina por su ID en la base de datos
		 Optional<Disciplina> optionalDisciplina = repo.findById(id);
		 // Utilizar orElse(null) para manejar el valor opcional de manera segura
		// Si el Optional contiene una disciplina, se devuelve esa instancia; si no, se devuelve null
		    return optionalDisciplina.orElse(null);
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
