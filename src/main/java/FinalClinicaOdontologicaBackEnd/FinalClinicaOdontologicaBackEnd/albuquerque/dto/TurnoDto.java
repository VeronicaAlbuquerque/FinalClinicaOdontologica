package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TurnoDto {
    private Integer id;
    private LocalDateTime fecha;
    private PacienteDto paciente;
    private OdontologoDto odontologo;

}
