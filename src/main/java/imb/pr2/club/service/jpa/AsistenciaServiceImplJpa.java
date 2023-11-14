package imb.pr2.club.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import imb.pr2.club.entity.Asistencia;
import imb.pr2.club.repository.AsistenciaRepository;
import imb.pr2.club.service.IAsistenciaService;
import jakarta.persistence.Id;


@Service
public class AsistenciaServiceImplJpa implements IAsistenciaService {

	@Autowired
	private AsistenciaRepository repo;
	
	@Override
	public Asistencia guardar(Asistencia asistencia) {
		return repo.save(asistencia);
		
		
	}

	@Override
	public void eliminar(Integer id) {
		 repo.deleteById(id);		
	}

	@Override
	public Asistencia buscarPorId(Integer id) {	

		return repo.findById(id).orElse(null);

	}

	@Override
	public List<Asistencia> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
