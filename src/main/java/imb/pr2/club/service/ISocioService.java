package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Socio;

public interface ISocioService {
	
	public List<Socio> buscarTodos();
	public Socio buscarPorId(Integer id);
	public Socio guardar(Socio socio);
	public void eliminar(Integer id);
	public boolean existe(Integer id);
	
}