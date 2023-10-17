package imb.pr2.club.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.club.entity.Profesor;
import imb.pr2.club.repository.ProfesorRepository;
import imb.pr2.club.service.IProfesorService;

@Service
@Primary
public class ProfesorServiceImplJpa  implements IProfesorService{

	@Autowired
	ProfesorRepository repo;

	@Override
	public List<Profesor> buscarTodos() {		
		return repo.findAll();

	}

	@Override
	public Profesor guardar(Profesor profesor) {
		return repo.save(profesor);
		
	} 

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Profesor buscarPorId(Integer id) {
		Optional<Profesor> optional = repo.findById(id);
		return optional.orElse(null);
	}
	
	@Override
	public boolean exists(Integer id) {
		return (id == null)? false: repo.existsById(id);  
		
	};
	
}
