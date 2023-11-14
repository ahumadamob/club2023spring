package imb.pr2.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Profesor {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;//
	
	@NotBlank(message = "El nombre del profesor no puede estar vacío")
	@Size(max = 50, message = "El nombre no puede superar los 50 caracteres") 
	private String nombre;
	
	@NotBlank(message = "El apellido del profesor no puede estar vacío")
	@Size(max = 50, message = "El apellido no puede superar los 50 caracteres") 
	private String apellido;
	
	@NotBlank(message = "El DNI del profesor no puede estar vacío")
	@Size(max = 10, message = "El DNI no puede superar los 10 caracteres") 
	private String dni;
	
	@ManyToOne
	@JoinColumn(name="disciplinaId")
	private Disciplina disciplina;
	
	
	
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
}


