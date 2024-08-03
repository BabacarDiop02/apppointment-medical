package sn.diop.babacar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.diop.babacar.api.entitie.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
