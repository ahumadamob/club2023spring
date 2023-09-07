package imb3.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@NotNull(message = "El horario no puede estar vacío")
	@Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "El horario debe tener el formato HH:mm de 24hs")
	String horario;
	
    @Min(value = 1, message = "El valor mínimo de cancha es 1")
	@Max(value = 20, message = "El valor máximo de cancha es 20")
    @NotNull(message = "El número de cancha no puede estar vacio")
	Integer numCancha; //se cambia int a Integer para poder verificar si es null
	
	
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
	public Integer getNumCancha() {
		return numCancha;
	}
	public void setNumCancha(Integer numCancha) {
		this.numCancha = numCancha;
	}
	
	
	
	

}