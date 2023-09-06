package imb.pr2.club.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import imb.pr2.club.entity.Espacio;
import imb.pr2.club.repository.EspacioRepository;
import imb.pr2.club.service.IEspacioService;


@Service
@Primary
public class EspacioServiceImplJpa implements IEspacioService {
	@Autowired
	EspacioRepository repo;

	@Override
	public List<Espacio> mostrarEspacios() {
		
		return repo.findAll();
	}

	@Override
	public Espacio mostrarEspacioPorId(Integer id) {
Optional<Espacio> optional;
		
		optional = repo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public void modificarEspacio(Espacio espacio) {
		repo.save(espacio);
		
	}

	@Override
	public void eliminarEspacio(Integer id) {
		repo.deleteById(id);
		
	}

	
	
	
	
}
