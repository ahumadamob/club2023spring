package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Asistencia;

public interface IAsistenciaService {

		public Asistencia guardar(Asistencia asistencia);
		public void eliminar(Integer id);
		public Asistencia buscarPorId(Integer id);
		public List<Asistencia> buscarTodos();	
		
		public boolean exists(Integer id);
}
