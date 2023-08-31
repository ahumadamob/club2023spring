package imb.pr2.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // Identificador único de la disciplina
	private String nombre; // Nombre de la disciplina
	private String horario; // Horario en el que se lleva a cabo la disciplina
	private int numCancha;  // Número de la cancha asignada para la disciplina
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public int getNumCancha() {
		return numCancha;
	}
	public void setNumCancha(int numCancha) {
		this.numCancha = numCancha;
	}
	
	
	
	

}
