package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Clase;

public interface IClaseService {

	public List<Clase> buscarClase();
	public Clase buscarClasePorId(Integer id);
	public void guardarClase(Clase clase);
	public void eliminarClase(Integer id);
	
	
}
