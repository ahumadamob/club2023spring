package imb3.club.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb3.club.entity.Socio;
import imb3.club.repository.SocioRepository;
import imb3.club.service.ISocioService;

@Service
public class SocioService implements ISocioService {
	
	@Autowired
	private SocioRepository repo;

	@Override
	public List<Socio> mostrarSocios() {
		return repo.findAll();
	}

	@Override
	public void guardarSocio(Socio socio) {
		repo.save(socio);
	}

	@Override
	public void eliminarSocio(Integer id) {
		repo.deleteById(id);
	}

}
