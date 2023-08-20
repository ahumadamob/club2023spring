package imb.pr2.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@NotBlank(message = "La actividad no puede estar vacía")
	@Size(max = 50, message = "La actividad no puede superar los 50 caracteres") 
	private String actividad;
	
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
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
}


