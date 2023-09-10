package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Asistencia;

public interface IAsistenciaService {

	
	public List<Asistencia> buscarAsistencia();
	public Asistencia buscarAsistenciaPorId(Integer id);
	public void guardarAsistencia(Asistencia asistencia);
	public void eliminarAsistencia(Integer id);
	public List<Asistencia> buscarAsistenciaPorClase(Integer id);
	public void marcarAusente(Integer asistenciaId);
	public void cancelarInscripcion(Integer asistenciaId);
	public void marcarPresente(Integer asistenciaId);
	public int getCountInscritosByClaseId(Integer claseId);
	public void inscribir(Integer claseId);

		
}

