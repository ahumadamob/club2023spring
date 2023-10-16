package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Profesor;

public interface IProfesorService {
	
	public List<Profesor> buscarTodos();
	public Profesor buscarPorId(Integer id);
	public Profesor guardar(Profesor profesor);
	public void eliminar(Integer id);
	public boolean exists(Integer id);
	
}
