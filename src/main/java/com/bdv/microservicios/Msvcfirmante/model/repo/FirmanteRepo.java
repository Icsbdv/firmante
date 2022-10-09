package com.bdv.microservicios.Msvcfirmante.model.repo;



import com.bdv.microservicios.Msvcfirmante.model.entities.Firmante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmanteRepo extends JpaRepository<Firmante,Long> {

    @Procedure
    void sp_ICS_Microservices_FIRMANTE(@Param("Accion") String Accion,
                  @Param("iImagen") Long iImagen,
                  @Param("sProductoId") String sProductoId,
                  @Param("sPersonaId") String sPersonaId,
                  @Param("iTipo") Integer iTipo,
                  @Param("Imagen") byte[] Imagen
    );


    @Procedure
    void sp_SIGN(@Param("Accion") String Accion,
                 @Param("sProductoId") String sProductoId,
                 @Param("sPersonaId") String sPersonaId,
                 @Param("iTipo") Integer iTipo,
                 @Param("iImagen") Long iImagen,
                 @Param("sUser") String sUser
    );




}
