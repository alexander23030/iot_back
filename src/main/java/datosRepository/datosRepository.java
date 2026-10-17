package datosRepository;
import java.util.List;
import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import datosEntity.datosEntity;

public interface datosRepository extends JpaRepository<datosEntity, Integer>{
   
    //Todos los datos en general
    @Query("SELECT d FROM datosEntity d ORDER BY d.id_medicion DESC")
    List<datosEntity> findallOrderedById();

    //Filtrado
    @Query("SELECT d FROM datosEntity d WHERE d.fechahora > :intervalo ORDER BY d.fechahora ASC")
    List<datosEntity> findFiltradoDatos(@Param("intervalo") Timestamp intervalo);}
