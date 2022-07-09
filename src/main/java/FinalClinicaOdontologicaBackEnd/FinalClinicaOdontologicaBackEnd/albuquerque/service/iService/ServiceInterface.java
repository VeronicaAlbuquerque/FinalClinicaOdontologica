package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.iService;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.PacienteDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.BadRequestException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Paciente;

import java.util.List;

public interface ServiceInterface<E> {
    E insertar(E entidad) throws Exception, BadRequestException, ResourceNotFoundException;
    List<E> obtenerTodos() throws Exception, ResourceNotFoundException, BadRequestException;
    String actualizar(E entidad) throws ResourceNotFoundException;
    String actualizarEnBDD(E entidad) throws Exception;
    String eliminar(Integer id) throws Exception, ResourceNotFoundException;
    E buscarPorId(Integer id) throws ResourceNotFoundException;
}
