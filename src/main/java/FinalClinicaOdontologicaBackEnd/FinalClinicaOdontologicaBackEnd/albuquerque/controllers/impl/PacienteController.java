package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.controllers.impl;


import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.controllers.IController;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.OdontologoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.PacienteDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.BadRequestException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController implements IController<PacienteDto> {

    @Autowired
    PacienteService service;

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Override
    @PostMapping("/crear")
    public ResponseEntity<PacienteDto> crear(@RequestBody PacienteDto paciente) {
        ResponseEntity<PacienteDto> response = null;
        paciente.setFechaIngreso(LocalDate.now());
        PacienteDto pacienteInsertado = service.insertar(paciente);
        if (pacienteInsertado != null) {
            response = ResponseEntity.ok(pacienteInsertado);
        }

        return response;
    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDto>> consultarTodos() throws BadRequestException {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody PacienteDto paciente) throws BadRequestException,ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        if(paciente.getId() != null ){
            respuesta = ResponseEntity.ok(service.actualizar(paciente));
        } else {
            throw new BadRequestException("Id del paciente o del domicilio faltantes");
        }
        return respuesta;
    }
    @Override
    @GetMapping("/{id}")
    public PacienteDto obtenerPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        return service.buscarPorId(id);
    }

}