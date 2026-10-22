package datosController;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import datosDTO.datosDTO;
import datosService.datosServices;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor 
@CrossOrigin (origins = "http://localhost:5173")
@RestController 

public class datosController {

    private final datosServices service;    

/* 
    @GetMapping("/all")
    public List <datosDTO>getService() {
        return service.getData();
    }
*/ 
    @GetMapping("/")
    public List<datosDTO> listar
    //Se usa una condicion para filtrar la cantidad de horas
    //Si no se envia un cantidad es null y devuelve todo
    (@RequestParam(required = false) Integer horas) {
        // Si se envia una cantidad (de horas) no se cumple la condicion
        //se llama al metodo ObtenerFiltrado con la cantidad de horas solicitadas
        if (horas == null) return service.getData(); 
        return service.ObtenerFiltrado(horas);
}

        @GetMapping("/actual")
        public datosDTO obtenerUltimaLectura() {
            return service.getData()
                    .stream()
                    .max(java.util.Comparator.comparing(datosDTO::getFechahora))
                    .orElse(null);
        }
}
