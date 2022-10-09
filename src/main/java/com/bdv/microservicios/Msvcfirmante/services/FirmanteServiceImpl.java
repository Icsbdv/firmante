package com.bdv.microservicios.Msvcfirmante.services;


import com.bdv.microservicios.Msvcfirmante.model.entities.Firmante;
import com.bdv.microservicios.Msvcfirmante.model.repo.FirmanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class FirmanteServiceImpl implements FirmanteService{

    @Autowired
    FirmanteRepo firmanteRepo;


    @Override
    public Firmante guardarFirmante(Firmante firmante) throws IOException {

        Long iImagen=firmante.getIimagen();
        String productId=firmante.getSproductoid();
        String personaId=firmante.getSpersonaid();
        Integer tipo=firmante.getItipo();
        String im=(firmante.getImagen()).substring(2);

        byte[] ans = new byte[im.length() / 2];
        for (int i = 0; i < ans.length; i++)
        {
            int index = i * 2;
            // Using parseInt() method of Integer class
            int val = Integer.parseInt(im.substring(index, index + 2), 16);
            ans[i] = (byte)val;
        }

        byte[] im2=ans;

        firmanteRepo.sp_ICS_Microservices_FIRMANTE("INSERT",iImagen,productId,personaId,tipo,im2);



        return firmante;
    }

    @Override
    public void eliminarFirmante(Firmante firmante) {
        Long iImagen=firmante.getIimagen();
        String productId=firmante.getSproductoid();
        String personaId=firmante.getSpersonaid();
        Integer tipo=firmante.getItipo();
        String user="PLATAFORMA";
        firmanteRepo.sp_SIGN("DELETE",productId,personaId,tipo,iImagen,user);
    }
}
