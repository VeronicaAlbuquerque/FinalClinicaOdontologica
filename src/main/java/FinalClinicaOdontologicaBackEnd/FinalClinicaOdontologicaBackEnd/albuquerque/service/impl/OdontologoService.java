package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.config.Config;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.OdontologoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Odontologo;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.repository.OdontologoRepository;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.iService.OdontologoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public abstract class OdontologoService implements OdontologoServiceInterface{
    @Autowired
    OdontologoRepository repository;

    @Autowired
    Config mapper;


    @Override
    public OdontologoDto insertar(OdontologoDto entidad) {
        Odontologo odontologo = mapper.getModelMapper().map(entidad, Odontologo.class);
        return mapper.getModelMapper().map(repository.save(odontologo), OdontologoDto.class);

    }

    @Override
    public List<OdontologoDto> obtenerTodos() throws ResourceNotFoundException {
        List<Odontologo> lista = repository.findAll();
        if (lista.size() == 0) {
            throw new ResourceNotFoundException("No hay odontólogos disponibles");
        }
        return mapper.getModelMapper().map(lista, List.class);
    }

    @Override
    public String actualizar(OdontologoDto entidad) throws ResourceNotFoundException {
        String respuesta;
        Optional<Odontologo> odontologoAModificar = repository.findById(entidad.getId());
        if (odontologoAModificar.isPresent()){
            repository.save(this.actualizarEnBDD(odontologoAModificar.get(), entidad));
            respuesta = "Actualización con éxito del odontólogo con id " + entidad.getId();
        } else {
            throw new ResourceNotFoundException("No se logró actualizar el odontólogo. El odontólogo con id " + entidad.getId() + " no fue encontrado en la base de datos");
        }
        return respuesta;
    }


        private Odontologo actualizarEnBDD(Odontologo odontologo, OdontologoDto odontologoDto) throws ResourceNotFoundException {
            if (odontologoDto.getNombre() != null) {
                odontologo.setNombre(odontologoDto.getNombre());
            }
            if (odontologoDto.getApellido() != null) {
                odontologo.setApellido(odontologoDto.getApellido());
            }
            if (odontologoDto.getMatricula() != null) {
                odontologo.setMatricula(odontologoDto.getMatricula());
            }
            return odontologo;

        }

        public String eliminar(Integer id) throws ResourceNotFoundException {
            String respuesta;
            if (repository.findById(id).isPresent()) {
                repository.deleteById(id);
                respuesta = "Eliminado con éxito";
            } else {
                throw new ResourceNotFoundException("No se logró eliminar el odontologo de la base de datos. El id " + id + " no fue encontrado.");
            }
            return respuesta;
        }

        public OdontologoDto buscarPorId(Integer id) throws ResourceNotFoundException{
           Odontologo odontologoRespuesta = repository.findById(id).orElse(null);
            if (odontologoRespuesta != null){
               return mapper.getModelMapper().map(odontologoRespuesta, (Type) OdontologoDto.class);
            } else {
               throw new ResourceNotFoundException ("No fue encontrado el odontologo con id " + id);
          }
        }



}