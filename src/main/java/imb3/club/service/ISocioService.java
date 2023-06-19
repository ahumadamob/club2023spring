package imb3.club.service;

import java.util.List;
import imb3.club.entity.Socio;

public interface ISocioService {
	
	public List<Socio> mostrarSocios();
	public void guardarSocio(Socio socio);
	public void eliminarSocio(Integer id);
	
}