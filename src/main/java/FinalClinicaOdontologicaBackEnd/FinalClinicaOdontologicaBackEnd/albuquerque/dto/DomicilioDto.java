package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DomicilioDto {
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
}
