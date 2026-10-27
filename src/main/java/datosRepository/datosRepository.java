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
}