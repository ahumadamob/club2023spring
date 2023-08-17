package club.ies.club.service;

import java.util.List;

import club.ies.club.entity.Clase;

public interface IClaseService {

	public List<Clase> buscarClase();
	public Clase buscarClasePorId(Integer id);
	public void guardarClase(Clase clase);
	public void eliminarClase(Integer id);
	
	
}
