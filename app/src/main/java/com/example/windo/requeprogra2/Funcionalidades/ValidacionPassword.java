package com.example.windo.requeprogra2.Funcionalidades;

import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class ValidacionPassword {

    public static boolean esPasswordValido(byte[] encriptado, byte[] sal, String candidato) throws Exception{
        try{
            byte[] encriptadoCandidato = generarHash(candidato, sal, 1000);
            if(Arrays.equals(encriptado, encriptadoCandidato))
                return true;
            return false;
        }catch (Exception e){
            throw new Exception("No se pudo generar un encriptado para el password ingresado\n" +
                    "Razon: " + e.getMessage());
        }
    }

    private static byte[] generarHash(String password, byte[] sal, int iteraciones) throws Exception{
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), sal, iteraciones, 32 * 8);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        return keyFactory.generateSecret(spec).getEncoded();
    }

}
