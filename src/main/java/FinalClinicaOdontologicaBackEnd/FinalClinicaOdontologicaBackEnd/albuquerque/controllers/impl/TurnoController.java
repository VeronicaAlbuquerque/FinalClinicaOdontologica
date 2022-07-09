package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.controllers.impl;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.controllers.IController;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.TurnoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.BadRequestException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turnos")
public class TurnoController implements IController<TurnoDto> {

    @Autowired(required = true)
    TurnoService service;

    @Override
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody TurnoDto turnoDto) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(service.insertar(turnoDto));
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<?> consultarTodos() throws ResourceNotFoundException, Exception {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) throws ResourceNotFoundException, Exception {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException, BadRequestException{
        return ResponseEntity.ok(service.actualizar(turnoDto));
    }

    @Override
    @GetMapping("/{id}")
    public TurnoDto obtenerPorId(Integer id) throws ResourceNotFoundException {
        return service.buscarPorId(id);
    }


}
