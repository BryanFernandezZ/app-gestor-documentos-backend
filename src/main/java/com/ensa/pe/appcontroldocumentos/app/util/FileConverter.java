package com.ensa.pe.appcontroldocumentos.app.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Base64;

@Component
public class FileConverter {

    //Para guardar en base de datos
    public String convertirAMultipartFile(MultipartFile file){
        if (file != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) System.out.println("Not a valid file");

            String imageEncoded = "";

            try{
                imageEncoded = Base64.getEncoder().encodeToString(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return imageEncoded;
        }

        return null;
    }

    //Para enviar al cliente (REPONSE)
    public byte[] convertirAByteArray(String imageEncoded){
        if(imageEncoded != null) {
            byte[] bytes = Base64.getDecoder().decode(imageEncoded);
            return bytes;
        }

        return null;
    }
}
