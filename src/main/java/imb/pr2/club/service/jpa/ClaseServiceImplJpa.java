package imb.pr2.club.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.club.entity.Clase;
import imb.pr2.club.repository.ClaseRepository;
import imb.pr2.club.service.IClaseService;


@Service
@Primary
public class ClaseServiceImplJpa implements IClaseService{

	@Autowired
	ClaseRepository repo;
	
	
	
	@Override
	public List<Clase> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public Clase buscarPorId(Integer id) {
		
      Optional<Clase> optional= repo.findById(id);
		
		 
		
		return optional.orElse(null);
	}

	@Override
	public Clase guardar(Clase clase) {
		return repo.save(clase);
		
	}

	@Override
	public void eliminarClase(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public boolean exists(Integer id) {
		
			return (id == null)? false:  repo.existsById(id);
		};
	
	
	
	
	

}
