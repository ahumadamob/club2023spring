package imb3.club.service;

import java.util.List;

import imb3.club.entity.Profesor;

public interface IProfesorService {
	
	public List<Profesor> buscarProfesor();
	public Profesor buscarProfesorPorId(Integer id);
	public void guardarProfesor(Profesor profesor);
	public void eliminarProfesor(Integer id);
	

}
