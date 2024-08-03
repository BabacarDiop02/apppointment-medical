package sn.diop.babacar.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.diop.babacar.api.dto.AppointmentDTO;
import sn.diop.babacar.api.entitie.Appointment;
import sn.diop.babacar.api.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping(path = "api/appointment")
@CrossOrigin("*")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(path = "/patients/{patientId}")
    public ResponseEntity<Set<AppointmentDTO>> getAllAppointmentsByPatient(@PathVariable Long patientId) {
        Set<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByPatient(patientId);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestParam String objective,
                                                         @RequestParam String description,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                                         @RequestParam Long patientId,
                                                         @RequestParam Long medecinId) {
        return this.appointmentService.createAppointment(objective, description, dateTime, patientId, medecinId);
    }
}
