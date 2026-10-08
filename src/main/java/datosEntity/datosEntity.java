package datosEntity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name="mediciones")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class datosEntity {
    
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicion")
    private Integer id_medicion;
    
    @Column(name = "co2")
    private Integer co2;

    @Column(name = "temperatura")
    private Float  temperatura;

    @Column(name = "humedad")
    private Double  humedad;

    @Column(name = "fechahora")
    private Timestamp fechahora;
}
