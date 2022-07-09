package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.config.Config;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.OdontologoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.PacienteDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.TurnoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.BadRequestException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Turno;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.repository.TurnoRepository;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.iService.TurnoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class TurnoService implements TurnoServiceInterface{
    @Autowired
    TurnoRepository repository;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    Config mapper;

    @Override
    public TurnoDto insertar(TurnoDto entidad) throws BadRequestException, ResourceNotFoundException {
        TurnoDto respuesta;
        if (entidad.getPaciente().getId() != null && entidad.getOdontologo() != null){
            PacienteDto pacienteBuscado = pacienteService.buscarPorId(entidad.getPaciente().getId());
            OdontologoDto odontologoBuscado = odontologoService.buscarPorId(entidad.getOdontologo().getId());
            if (verificarDisponibilidadTurno(odontologoBuscado.getId(), entidad.getFecha())){
                //Creamos en BDD
                Turno turno = mapper.getModelMapper().map(entidad, Turno.class);
                respuesta = mapper.getModelMapper().map(repository.save(turno), TurnoDto.class);

                //Setemos el odntologo y paciente al JSON de la respuesta
                respuesta.setPaciente(pacienteBuscado);
                respuesta.setOdontologo(odontologoBuscado);
            } else {
                throw new ResourceNotFoundException("El odontologo con id " + odontologoBuscado.getId() + " ya tiene un turno agendado en la fecha "+ entidad.getFecha());
            }
        } else {
            throw new BadRequestException("Faltan introducir el id del paciente u odontologo para crear el turno");
        }
        return respuesta;
    }

    private Boolean verificarDisponibilidadTurno(Integer idOdontologo, LocalDateTime fechaTurno) throws ResourceNotFoundException {
        Boolean respuesta = true;
        List<Turno> listaTurnos = repository.findAll();
        for (Turno t: listaTurnos){
            TurnoDto turnoDto = mapper.getModelMapper().map(t,TurnoDto.class);
            if (turnoDto.getOdontologo().getId().equals(idOdontologo) && turnoDto.getFecha().equals(fechaTurno)){
                respuesta = false;
            }
        }
        return respuesta;
    }

    @Override
    public List<TurnoDto> obtenerTodos() throws Exception, ResourceNotFoundException {
        List<TurnoDto> turnos = mapper.getModelMapper().map(repository.findAll(), List.class);
        if (turnos.size()<=0){
            throw new ResourceNotFoundException ("No hay turnos cargados");
        }
        return turnos;
    }

    /*@Override
    public String actualizar(TurnoDto entidad) throws ResourceNotFoundException {
        String respuesta;
        if (entidad.getId() != null) {
            Optional<Turno> turnoAModificar = repository.findById(entidad.getId());
            if (turnoAModificar.isPresent()){
                repository.save(this.actualizarEnBDD(turnoAModificar.get(), entidad));
                respuesta = "Actualización con éxito del turno número: " + entidad.getId();
            } else {
                throw new ResourceNotFoundException("No fue posible encontrar el turno en la base de datos");
            }
        }return "Se actualizo correctamente";
    };*/


    public String actualizarEnBDD(Turno turno, TurnoDto turnoDto){
        if (turnoDto.getFecha()!=null){
            turno.setFecha(turnoDto.getFecha());
        }
        return "El turno fué otorgado para " + turno;
    }

    @Override
    public String eliminar(Integer id) throws Exception, ResourceNotFoundException {
        String respuesta;
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            respuesta = "Eliminado con éxito el turno número: " + id;
        }
        else
            throw new ResourceNotFoundException("No fue encontrado el turno en la base de datos");

        return respuesta;
    }

    @Override
    public TurnoDto buscarPorId(Integer id) throws ResourceNotFoundException {
        return null;
    }
}
