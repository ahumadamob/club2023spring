package imb.pr2.club.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.pr2.club.entity.Asistencia;
import imb.pr2.club.repository.AsistenciaRepository;
import imb.pr2.club.service.IAsistenciaService;

	@Service
	@Primary
	public class AsistenciaServiceImplJpa  implements IAsistenciaService{

		@Autowired
		AsistenciaRepository repo;

		@Override
		public List<Asistencia> buscarAsistencia() {		
			return repo.findAll();

		}

		@Override
		public void guardarAsistencia(Asistencia asistencia) {
			repo.save(asistencia);
			
		} 

		@Override
		public void eliminarAsistencia(Integer id) {
			repo.deleteById(id);		
		}

		@Override
		public Asistencia buscarAsistenciaPorId(Integer id) {
			Optional<Asistencia> optional = repo.findById(id);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				return null;
			}		
		}

		@Override
		public List<Asistencia> buscarAsistenciaPorClase(Integer id) {
			return repo.findByClase(id);
		}

		@Override
		public void marcarAusente(Integer asistenciaId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void cancelarInscripcion(Integer asistenciaId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void marcarPresente(Integer asistenciaId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getCountInscritosByClaseId(Integer claseId) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void inscribir(Integer claseId) {
			// TODO Auto-generated method stub
			
		}
}
