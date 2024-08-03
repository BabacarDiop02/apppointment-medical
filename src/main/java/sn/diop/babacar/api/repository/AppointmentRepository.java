package sn.diop.babacar.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.diop.babacar.api.entitie.Appointment;
import sn.diop.babacar.api.entitie.Patient;

import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public Set<Appointment> findByPatient(Patient patient);
}
