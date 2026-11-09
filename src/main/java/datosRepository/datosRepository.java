package datosRepository;
import java.util.List;
import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datosEntity.datosEntity;

public interface datosRepository extends JpaRepository<datosEntity, Integer>{
    
    //Se puede generar una consulta a partir del nombre
    //Todos los datos en general
   // List<datosEntity> findAllByOrderByFechahoraAsc();
   
    //Filtrado
    @Query("SELECT d FROM datosEntity d WHERE d.fechahora > :intervalo ORDER BY d.fechahora ASC")
    List<datosEntity> findFiltradoDatos(@Param("intervalo") Timestamp intervalo);
    
    //Ultimo id registrado
    List<datosEntity> findTopByOrderByFechahoraDesc();

    //Busquda por rangos
    List<datosEntity> findByFechahoraBetweenOrderByFechahoraAsc(Timestamp desde, Timestamp hasta);

    @Query("""
    SELECT COUNT(d) AS total,
           AVG(d.co2) AS co2Promedio, MAX(d.co2) AS co2Max, MIN(d.co2) AS co2Min,
           AVG(d.temperatura) AS tempPromedio, MAX(d.temperatura) AS tempMax, MIN(d.temperatura) AS tempMin,
           AVG(d.humedad) AS humPromedio, MAX(d.humedad) AS humMax, MIN(d.humedad) AS humMin
    FROM datosEntity d
    WHERE d.fechahora BETWEEN :desde AND :hasta
    """)
resumenInterface resumen(@Param("desde") Timestamp desde, @Param("hasta") Timestamp hasta);
}