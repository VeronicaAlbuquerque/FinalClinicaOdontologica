package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.repository;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
