package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.controllers;


import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.OdontologoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.BadRequestException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IController <E>{
    @PostMapping("/crear")
    ResponseEntity<?> crear(@RequestBody E e) throws BadRequestException, ResourceNotFoundException;
    @GetMapping("/todos") ResponseEntity<?> consultarTodos() throws ResourceNotFoundException, Exception;
    @DeleteMapping("/eliminar/{id}") ResponseEntity<?> eliminar(@PathVariable Integer id) throws Exception, ResourceNotFoundException;
    @PutMapping
    ResponseEntity<?> actualizar(@RequestBody E e) throws BadRequestException, ResourceNotFoundException;
    @GetMapping("/{id}")
    E obtenerPorId(@PathVariable Integer id) throws ResourceNotFoundException;
}
