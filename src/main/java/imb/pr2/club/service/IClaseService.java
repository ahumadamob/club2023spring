package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Clase;

public interface IClaseService {

	public List<Clase> buscarTodos();
	public Clase buscarPorId(Integer id);
	public Clase guardar(Clase clase);
	public void eliminarClase(Integer id);
	public boolean exists(Integer id);
	
	
	
}
