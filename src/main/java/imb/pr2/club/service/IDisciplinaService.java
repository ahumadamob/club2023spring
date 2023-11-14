package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Disciplina;

public interface IDisciplinaService {

	
	/*Servicios --- Cada servicio va atado a una entidad, se anula Disciplina en los metodos por considerarse redundante renombrarlo asi*/
	public List<Disciplina> buscarTodos();
	public Disciplina buscarPorId(Integer id);
	public Disciplina guardar(Disciplina disciplina);
	public void eliminar(Integer id);
	public boolean exists (Integer id);
	
	
}
