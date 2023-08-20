package club.ies.club.service.jpa;

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
public class ClaseService implements IClaseService{

	@Autowired
	ClaseRepository repo;
	
	
	
	@Override
	public List<Clase> buscarClase() {
		return repo.findAll();
	}

	@Override
	public Clase buscarClasePorId(Integer id) {
		
      Optional<Clase> optional;
		
		optional = repo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public void guardarClase(Clase clase) {
		repo.save(clase);
		
	}

	@Override
	public void eliminarClase(Integer id) {
		repo.deleteById(id);
		
	}

}
