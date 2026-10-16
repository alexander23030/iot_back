package datosController;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import datosDTO.datosDTO;
import datosService.datosServices;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor 
@CrossOrigin (origins = "http://localhost:5173")
@RestController 

public class datosController {

    private final datosServices service;    

    @GetMapping
    public List <datosDTO>getService() {
        return service.getData();
    }
    

}
