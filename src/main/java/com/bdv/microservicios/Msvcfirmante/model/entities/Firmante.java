package com.bdv.microservicios.Msvcfirmante.model.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Firmante",
                procedureName = "sp_ICS_Microservices_FIRMANTE",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Accion", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "iImagen", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sProductoId", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sPersonaId", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "iTipo", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Imagen", type = String.class)


                }),
        @NamedStoredProcedureQuery(
                name = "EliminarFirmante",
                procedureName = "sp_SIGN",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "Accion", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sProductoId", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sPersonaId", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "iTipo", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "iImagen", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sUser", type = String.class)


                })

})
public class Firmante implements Serializable {

    @Id
    Long iimagen;

    String sproductoid;

     String spersonaid;

    Integer itipo;

    String imagen;


}
