package datosService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import datosDTO.datosDTO;
import datosRepository.datosRepository;
import lombok.RequiredArgsConstructor;

//Lombok detecta todos los campos marcados como final
@RequiredArgsConstructor
@Service 
public class datosServices {

    //Inyeccion por constructor usando lombok
    //la variable debe asignarse obligatoriamente al crearse la clase
    private final datosRepository dataRepo;

    
    // Consultar datos
    //Para hacer que la consulta se ordene por ID se llama a la funcion .findAllOrdenado del repositorio
    public List<datosDTO> getData(){
        return dataRepo.findallOrderedById().stream().
        map(datosDTO::fromentity). //Llamada directa al método estático del DTO ya no se usa Mapper
        collect(Collectors.toList()) ;
    }


}