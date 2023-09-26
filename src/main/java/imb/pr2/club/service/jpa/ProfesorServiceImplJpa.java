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
	public List<Profesor> buscarProfesor() {		
		return repo.findAll();

	}

	@Override
	public void guardarProfesor(Profesor profesor) {
		repo.save(profesor);
		
	} 

	@Override
	public void eliminarProfesor(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public Profesor buscarProfesorPorId(Integer id) {
		Optional<Profesor> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}
	
	@Override
	public boolean exists(Integer id) {
		return repo.existsById(id);
	};
	
}
