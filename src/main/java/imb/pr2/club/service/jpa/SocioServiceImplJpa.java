package imb.pr2.club.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr2.club.entity.Socio;
import imb.pr2.club.repository.SocioRepository;
import imb.pr2.club.service.ISocioService;

@Service
public class SocioServiceImplJpa implements ISocioService {
	
	@Autowired
	private SocioRepository repo;

	@Override
	public List<Socio> mostrarSocios() {
		return repo.findAll();
	}
	
	@Override
	public Socio buscarSocioById(Integer id) {
		Optional<Socio> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}		
	}

	@Override
	public void guardarSocio(Socio socio) {
		repo.save(socio);
	}

	@Override
	public void eliminarSocio(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public boolean exists(Integer id) {
		return repo.existsById(id);
	}
	

}
