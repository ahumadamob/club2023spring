package imb.pr2.club.service;

import java.util.List;

import imb.pr2.club.entity.Espacio;



public interface IEspacioService {

	public List<Espacio> mostrarEspacios();
	public Espacio mostrarEspacioPorId(Integer id);
	public void modificarEspacio(Espacio espacio);
	public void eliminarEspacio(Integer id);
	
	
	
}
