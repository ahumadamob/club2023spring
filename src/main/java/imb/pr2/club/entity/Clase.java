package imb.pr2.club.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Clase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "El id del socio no puede estar vacío") 
	private Integer id_Socio;
	
	@NotNull(message = "El id del profesor no puede estar vacío")
	private Integer id_Profesor;
	
	@NotNull(message = "El id de disciplina no puede estar vacío")
	private Integer Id_Disciplina;
	
	@NotNull(message = "El cupo no puede estar vacío")
	private Integer cupo;
	
	@NotBlank(message = "El día no puede estar vacío")
	private String dia;
	
	@NotNull(message = "El horario no puede estar vacío")
	private Integer horario;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_Socio() {
		return id_Socio;
	}
	public void setId_Socio(Integer id_Socio) {
		this.id_Socio = id_Socio;
	}
	public Integer getId_Profesor() {
		return id_Profesor;
	}
	public void setId_Profesor(Integer id_Profesor) {
		this.id_Profesor = id_Profesor;
	}
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

}
