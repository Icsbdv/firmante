package com.bdv.microservicios.Msvcfirmante.controller;



import com.bdv.microservicios.Msvcfirmante.model.entities.Firmante;
import com.bdv.microservicios.Msvcfirmante.services.FirmanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("app")
public class Firmantecontroller {


    @Autowired
    FirmanteService firmanteService;


    @PostMapping("/crearfirmante")
    public ResponseEntity<?> crearFirmante(@RequestBody Firmante firmante) throws IOException {
        Firmante firmanteguardado=firmanteService.guardarFirmante(firmante);
        return ResponseEntity.status(HttpStatus.CREATED).body(firmanteguardado);

    }


    @DeleteMapping("/eliminarfirmante")
    public ResponseEntity<?> eliminarFirmante(@RequestBody Firmante firmante) throws IOException {
        firmanteService.eliminarFirmante(firmante);
        return ResponseEntity.noContent().build();
    }















}
