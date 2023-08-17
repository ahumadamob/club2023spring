package imb3.club.service;

import java.util.List;

import imb3.club.entity.Disciplina;

public interface IDisciplinaService {

	public List<Disciplina> buscarDisciplinas();
	public Disciplina buscarDisciplinaPorId(Integer id);
	public void guardarDisciplina(Disciplina disciplina);
	public void eliminarDisciplina(Integer id);
	
}
