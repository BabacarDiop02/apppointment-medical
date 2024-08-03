package sn.diop.babacar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.diop.babacar.api.entitie.Medecin;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    public List<Medecin> findBySpecialty(String specialty);
}
