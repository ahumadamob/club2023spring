package imb.pr2.club.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import imb.pr2.club.entity.Asistencia;
import imb.pr2.club.repository.AsistenciaRepository;
import imb.pr2.club.service.IAsistenciaService;


@Service
public class AsistenciaServiceImplJpa implements IAsistenciaService {

	@Autowired
	private AsistenciaRepository repo;
	
	@Override
	public void guardar(Asistencia asistencia) {
		repo.save(asistencia);
		
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);		
	}

	@Override
	public List<Asistencia> obtenerPorClase(Integer claseId) {		
		return repo.findByClaseId(claseId);

	}

	@Override
	public List<Asistencia> obtenerTodos() {
		return repo.findAll();
	}

}
