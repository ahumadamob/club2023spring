package imb.pr2.club.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;



@Entity
public class Clase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="socioId")
	private Socio socioId;
	
	@ManyToOne
	@JoinColumn(name="profeId")
	private Profesor profeId;
	
	@ManyToOne
	@JoinColumn(name="disciplinaId")
	private Disciplina discId;
	
	@NotNull
	private Integer cupo;
	
	
	@NotNull
	@Min(1)
	@Max(6)
	private Integer dia;
	
	@NotNull
	@Min(9)
	@Max(22)
	private Integer hora;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Socio getSocioId() {
		return socioId;
	}

	public void setSocioId(Socio socioId) {
		this.socioId = socioId;
	}

	public Profesor getProfeId() {
		return profeId;
	}

	public void setProfeId(Profesor profeId) {
		this.profeId = profeId;
	}

	public Disciplina getDiscId() {
		return discId;
	}

	public void setDiscId(Disciplina discId) {
		this.discId = discId;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	
	
	

		

}
