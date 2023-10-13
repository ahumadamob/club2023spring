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
	public List<Socio> buscarTodos() {
		return repo.findAll();
	}
	
	@Override
	public Socio buscarPorId(Integer id) {
		Optional<Socio> optional = repo.findById(id);
		return optional.orElse(null);		
	}

	@Override
	public Socio guardar(Socio socio) {
		return repo.save(socio);
		
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public boolean existe(Integer id) {
		 return (id == null)? false: repo.existsById(id);
	}
	

}
