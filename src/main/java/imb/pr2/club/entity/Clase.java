package imb.pr2.club.entity;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Clase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="socioId")
	private Socio socio;
	
	@ManyToOne
	@JoinColumn(name="profeId")
	private Profesor profe;
	
	/*@NotNull(message = "El id del profesor no puede estar vacío")
	private Integer id_Profesor;*/
	
	public Profesor getProfe() {
		return profe;
	}
	public void setProfe(Profesor profe) {
		this.profe = profe;
	}
	@NotNull(message = "El id de disciplina no puede estar vacío")
	private Integer Id_Disciplina;
	
	@NotNull(message = "El cupo no puede estar vacío")
	private Integer cupo;
	
	@NotBlank(message = "El día no puede estar vacío")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres") 
	private String dia;
	
	@NotNull(message = "El horario no puede estar vacío")
	private Integer horario;
	
	
	/*@NotNull(message = "Fecha y hora")
	private Date date;*/
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/*
	public Integer getId_Socio() {
		return id_Socio;
	}
	public void setId_Socio(Integer id_Socio) {
		this.id_Socio = id_Socio;
	}
	*/
	
	
	/*
	public Integer getId_Profesor() {
		return id_Profesor;
	}
	public void setId_Profesor(Integer id_Profesor) {
		this.id_Profesor = id_Profesor;
	}
	*/
	
	public Integer getId_Disciplina() {
		return Id_Disciplina;
	}
	public void setId_Disciplina(Integer id_Disciplina) {
		Id_Disciplina = id_Disciplina;
	}
	public Integer getCupo() {
		return cupo;
	}
	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public Integer getHorario() {
		return horario;
	}
	public void setHorario(Integer horario) {
		this.horario = horario;
	}
	
	
	/*public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}*/
	
	
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	
	
	
	

}
