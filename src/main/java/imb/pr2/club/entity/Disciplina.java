package imb.pr2.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NotBlank(message="El nombre no puede quedar vacio")
	@Size(max = 20, message = "El nombre no puede superar los 20 caracteres")
	String nombre;
	
	/*Entidad -------   Verificar que en tu entidad asignada tenga las relaciones @ManyToOne correspondientes*/
	
	@ManyToOne
	@JoinColumn(name="profesorId")
	private Profesor profesorId;
	
	@ManyToOne
	@JoinColumn(name="claseId")
	private Clase claseId;
	
	@ManyToOne
	@JoinColumn(name="socioId")
	private Socio socioId;
	
	public Profesor getProfesorId() {
		return profesorId;
	}
	public void setProfesorId(Profesor profesorId) {
		this.profesorId = profesorId;
	}
	public Clase getClaseId() {
		return claseId;
	}
	public void setClaseId(Clase claseId) {
		this.claseId = claseId;
	}
	public Socio getSocioId() {
		return socioId;
	}
	public void setSocioId(Socio socioId) {
		this.socioId = socioId;
	}
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
	
}