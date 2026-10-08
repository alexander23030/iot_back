package datosRepository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import datosEntity.datosEntity;

public interface datosRepository extends JpaRepository<datosEntity, Integer>{

    @Query("SELECT d FROM datosEntity d ORDER BY d.id_medicion ASC")
    List<datosEntity> findallOrderedById();
}
