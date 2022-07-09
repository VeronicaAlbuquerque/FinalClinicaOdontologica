package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.config.Config;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.DomicilioDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.PacienteDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.BadRequestException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Domicilio;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Odontologo;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Paciente;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.repository.PacienteRepository;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.iService.PacienteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class PacienteService implements PacienteServiceInterface{
    @Autowired
    PacienteRepository repository;

    @Autowired
    Config mapper;

    @Autowired
    DomicilioService domicilioService;

    @Override
    public PacienteDto insertar(PacienteDto entidad){
        Paciente paciente = mapper.getModelMapper().map(entidad, Paciente.class);
        repository.save(paciente);
        return mapper.getModelMapper().map(paciente, PacienteDto.class);

    }

    @Override
    public List<PacienteDto> obtenerTodos() throws BadRequestException {
        List<Paciente> lista = repository.findAll();
        if (lista.size() == 0){
            throw new BadRequestException("No hay pacientes");
        }
        return mapper.getModelMapper().map(lista, List.class);
    }

    /*@Override
    public String actualizar(PacienteDto entidad) throws ResourceNotFoundException {
        String respuesta;
        Optional<Paciente> pacienteAModificar = repository.findById(entidad.getId());
        if (pacienteAModificar.isPresent()){
            repository.save(this.actualizarEnBDD(pacienteAModificar.get(), entidad));
            respuesta = "Actualización con éxito del odontólogo con id " + entidad.getId();
        } else {
            throw new ResourceNotFoundException("No se logró actualizar el odontólogo. El odontólogo con id " + entidad.getId() + " no fue encontrado en la base de datos");
        }
        return respuesta;
    };*/


    public String actualizarEnBDD(Paciente paciente, PacienteDto pacienteDto) throws ResourceNotFoundException{
        if (pacienteDto.getNombre() != null) {
            paciente.setNombre(pacienteDto.getNombre());
        }
        if (pacienteDto.getApellido() != null) {
            paciente.setApellido(pacienteDto.getApellido());
        }
        if (pacienteDto.getDni() != null) {
            paciente.setDni(pacienteDto.getDni());
        }
        if (pacienteDto.getDomicilio() != null) {
            DomicilioDto domicilioActualizado = domicilioService.actualizar(pacienteDto.getDomicilio());
            paciente.setDomicilio(mapper.getModelMapper().map(domicilioActualizado, Domicilio.class));
        }
        return "Se actualizó correctamente el paciente";
    }

    @Override
    public String eliminar(Integer id) throws ResourceNotFoundException {
        String respuesta;
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = "Eliminado con éxito";
        } else {
            throw new ResourceNotFoundException("No se logró eliminar el paciente. El id " + id +" no fue encontrado en la base de datos.");
        }
        return respuesta;
    }

    public PacienteDto buscarPorId(Integer id) throws ResourceNotFoundException {
        Paciente pacienteRespuesta = repository.findById(id).orElse(null);
        if (pacienteRespuesta != null){
            return mapper.getModelMapper().map(pacienteRespuesta, PacienteDto.class);
        } else {
            throw new ResourceNotFoundException ("No fue encontrado el paciente con id " + id);
        }
    }
}
