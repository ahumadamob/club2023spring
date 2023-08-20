package imb.pr2.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr2.club.entity.Socio;

public interface SocioRepository extends JpaRepository<Socio, Integer> {
	
}