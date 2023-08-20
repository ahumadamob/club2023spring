package imb.pr2.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Socio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	private String nombre;
	
	@NotBlank(message = "El apellido no puede estar vacio")
	@Size(max = 50, message = "El nombre no debe superar los 40 caracteres")
	private String apellido;
	
	@NotNull
	@Min(16)
	@Max(80)
	private int edad;
	
	@NotNull
	private int dni;
	
	@NotNull
	private boolean estadoDeCuenta;
	
	
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
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public boolean isEstadoDeCuenta() {
		return estadoDeCuenta;
	}
	public void setEstadoDeCuenta(boolean estadoDeCuenta) {
		this.estadoDeCuenta = estadoDeCuenta;
	}
	
	
	
}
