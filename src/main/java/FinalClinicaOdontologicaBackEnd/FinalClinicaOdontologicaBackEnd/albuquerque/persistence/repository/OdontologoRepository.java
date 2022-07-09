package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.repository;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.persistence.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OdontologoRepository extends JpaRepository<Odontologo,Integer> {

    @Query("from Odontologo o where o.apellido like %:apellido%")
    Set<Odontologo> getOdontologoByApellido(@Param("apellido")String apellidoOdontologo);
}
