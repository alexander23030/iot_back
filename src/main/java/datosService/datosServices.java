package datosService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import datosDTO.datosDTO; 
import datosRepository.datosRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;
import datosEntity.datosEntity;

//Lombok detecta todos los campos marcados como final
@RequiredArgsConstructor
@Service 
public class datosServices {

    //Inyeccion por constructor usando lombok
    //la variable debe asignarse obligatoriamente al crearse la clase
    private final datosRepository dataRepo;

    // segundos de cada intervalo de promedio según el filtro (horas)
    private static final Map<Integer, Integer> SEGUNDOS_POR_RANGO = Map.of(
        6, 300,      // 5 min
        24, 1200,    // 20 min
        72, 3600,    // 1 h
        120, 7200    // 2 h
    );
    // Consultar datos
    //Para hacer que la consulta se ordene por ID se llama a la funcion .findAllOrdenado del repositorio
    public List<datosDTO> getData(){
        return dataRepo.findTopByOrderByFechahoraDesc().stream().
        map(datosDTO::fromentity). //Llamada directa al método estático del DTO ya no se usa Mapper
        collect(Collectors.toList()) ;
    }

    //Filtrado por tiempos determinadps
    public List<datosDTO> ObtenerFiltrado(int horas) {
        Timestamp intervalo = Timestamp.valueOf(LocalDateTime.now().minusHours(horas));
        return dataRepo.findFiltradoDatos(intervalo).stream()
        //Conversion de entidad a DTO
                .map(datosDTO::fromentity)
                .collect(Collectors.toList());
    }
    @Transactional
    public datosDTO guardar(Integer co2, Float temperatura, Double humedad) {
        datosEntity medicion = new datosEntity();

        medicion.setCo2(co2);
        medicion.setTemperatura(temperatura);
        medicion.setHumedad(humedad);
        medicion.setFechahora(new Timestamp(System.currentTimeMillis()));

        //convertir una entidad en un DTO
        datosEntity guardada = dataRepo.save(medicion);
        return datosDTO.fromentity(guardada);
    }





}