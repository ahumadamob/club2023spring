package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Disciplina;

public interface IDisciplinaService {

	public List<Disciplina> buscarTodos();
	public Disciplina buscarPorId(Integer id);
	public Disciplina guardar(Disciplina disciplina);
	public void eliminar(Integer id);
	public boolean exists (Integer id);
	
	public boolean exists(Integer id);
	
}
