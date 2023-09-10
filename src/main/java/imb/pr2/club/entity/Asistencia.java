package imb.pr2.club.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Asistencia {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;//
	
	@NotBlank(message = "La clase puede estar vacía")
	private Clase clase;
	@NotBlank(message = "La fecha no puede estar vacía")
	private Date fechaInscripcion;
	
	private boolean presente;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	 // Método para marcar como presente
    public void marcarPresente() {
        this.presente = true;
    }

    // Método para marcar como ausente
    public void marcarAusente() {
        this.presente = false;
    }
    
    @PrePersist
    @PreUpdate
    private void validatePresente() {
    	if(!presente  && !marcadoComoAusente()) {
    		//excepción estándar en Java que se utiliza cuando se pasa un argumento inválido a un método
    		throw new IllegalArgumentException("Para marcar como ausente use marcarAusente()");
    	}
    	 if (presente && !marcadoComoPresente()) {
             throw new IllegalArgumentException("Para marcar como presente, use marcarPresente()");
         }
     }

     private boolean marcadoComoPresente() {
         return presente == true;
     }

     private boolean marcadoComoAusente() {
         return presente == false;
     }    
	 
	  }
