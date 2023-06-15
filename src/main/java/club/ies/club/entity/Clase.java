package club.ies.club.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Clase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer id_Socio;
	private Integer id_Profesor;
	private Integer Id_Disciplina;
	private Integer cupo;
	private String dia;
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
