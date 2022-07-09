package FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.controllers.impl;

import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.controllers.IController;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.dto.OdontologoDto;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.BadRequestException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.exceptions.ResourceNotFoundException;
import FinalClinicaOdontologicaBackEnd.FinalClinicaOdontologicaBackEnd.albuquerque.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController implements IController<OdontologoDto> {


    @Autowired(required = true)
    OdontologoService service;


    @PostMapping("/crear")
    public ResponseEntity<OdontologoDto> crear(@RequestBody OdontologoDto odontologo) {
        ResponseEntity<OdontologoDto> respuesta = ResponseEntity.badRequest().body(odontologo);;
        OdontologoDto odontologoInsertado = service.insertar(odontologo);
        if (odontologoInsertado != null){
            respuesta = ResponseEntity.ok(odontologoInsertado);
        }
        return respuesta;

    }

    @Override
    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDto>> consultarTodos() throws ResourceNotFoundException
    {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Override
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.eliminar(id));
    }

    @Override
    @PutMapping()
    public ResponseEntity<String> actualizar(@RequestBody OdontologoDto odontologo) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<String> respuesta;
        if (odontologo.getId() != null){
            respuesta = ResponseEntity.ok(service.actualizar(odontologo));
        } else{
            throw new BadRequestException("No están los datos necesarios");
        }
        return respuesta;
    }

    @Override
    @GetMapping("/{id}")
    public OdontologoDto obtenerPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        return service.buscarPorId(id);
    }






}
