 package datosDTO;
import java.sql.Timestamp;
import datosEntity.datosEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //Permite construir instancias de forma limpia y legible
@Data //Genera automáticamente getters, setters etc
@NoArgsConstructor
@AllArgsConstructor
public class datosDTO {

    private Integer idMedicion;
    private Integer co2;
    private Float temperatura;
    private Double humedad;
    private Timestamp fechahora;

    // Constructor método estático en el mismo DTO
    //evita el uso de mapper si el proyecto es siple
    public static datosDTO fromentity(datosEntity entity) {

        datosDTO dto = new datosDTO();
        dto.setIdMedicion(entity.getId_medicion());
        dto.setCo2(entity.getCo2());
        dto.setTemperatura(entity.getTemperatura());
        dto.setHumedad(entity.getHumedad());
        dto.setFechahora(entity.getFechahora()); 
        return dto;
    }
    //Dto de los promedios
    public record resumenDTO(
    long total,
    Double co2Promedio, Double co2Max, Double co2Min,
    Double tempPromedio, Double tempMax, Double tempMin,
    Double humPromedio, Double humMax, Double humMin
    ) {}

} 

