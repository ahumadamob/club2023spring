package imb3.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb3.club.entity.Socio;

public interface SocioRepository extends JpaRepository<Socio, Integer> {
	
}