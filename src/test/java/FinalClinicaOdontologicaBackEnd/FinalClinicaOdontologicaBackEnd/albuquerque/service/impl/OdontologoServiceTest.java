package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.OdontologoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Odontologo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    void insertar() throws ResourceNotFoundException {
        //odontologo 1
        OdontologoDto odontologo = new OdontologoDto();
        odontologo.setNombre("Romualdo");
        odontologo.setApellido("Ravena");
        odontologo.setId(1);
        //odontologo 2
        OdontologoDto odontologo2 = new OdontologoDto();
        odontologo2.setNombre("Sergio");
        odontologo2.setApellido("Lopez");
        odontologo2.setId(2);
        //creamos
        odontologoService.insertar(odontologo);
        odontologoService.insertar(odontologo2);

        OdontologoDto odontologoDtoRomualdo= odontologoService.buscarPorId(1);
        OdontologoDto odontologoDtoSergio= odontologoService.buscarPorId(2);

        assertTrue(odontologoDtoRomualdo != null);
        assertTrue(odontologoDtoSergio != null);
    }

    @Test
    void obtenerTodos() throws ResourceNotFoundException {


    }
    @Test
    void actualizar() {
    }

    @Test
    void eliminar() throws ResourceNotFoundException {
        OdontologoDto odontologo = new OdontologoDto();
        odontologo.setNombre("Romualdo");
        odontologo.setApellido("Ravena");
        odontologo.setId(1);

        odontologoService.eliminar(1);
        assertTrue(odontologo == null);

        }
    }



