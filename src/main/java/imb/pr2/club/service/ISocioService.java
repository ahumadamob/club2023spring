package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Socio;

public interface ISocioService {
	
	public List<Socio> mostrarSocios();
	public Socio buscarSocioById(Integer id);
	public void guardarSocio(Socio socio);
	public void eliminarSocio(Integer id);
	boolean exists(Integer id);
	
}