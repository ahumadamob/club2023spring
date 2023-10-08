package imb.pr2.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.club.entity.Asistencia;


public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
	List<Asistencia> findByClaseId(Integer claseId);
	// select * from asistencia where claseId = 4

}
