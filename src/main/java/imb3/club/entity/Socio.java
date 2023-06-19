package imb3.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
	@Size(max = 40, message = "El apellido no debe superar los 40 caracteres")
	private String apellido;
	
	@NotBlank(message = "La edad no puede estar vacio")
	private int edad;
	
	@NotBlank(message = "El dni no puede estar vacio")
	private int dni;
	
	@NotBlank(message = "El estado de cuenta no puede estar vacio")
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
