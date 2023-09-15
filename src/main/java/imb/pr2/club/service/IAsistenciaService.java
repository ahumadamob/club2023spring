package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Asistencia;

public interface IAsistenciaService {

		public void guardar(Asistencia asistencia);
		public void eliminar(Integer id);
		public List<Asistencia> obtenerPorClase(Integer claseId);
		public List<Asistencia> obtenerTodos();
}
