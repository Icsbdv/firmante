package com.bdv.microservicios.Msvcfirmante.services;




import com.bdv.microservicios.Msvcfirmante.model.entities.Firmante;

import java.io.IOException;

public interface FirmanteService {
    Firmante guardarFirmante(Firmante firmante) throws IOException;

    void eliminarFirmante(Firmante firmante);

}
