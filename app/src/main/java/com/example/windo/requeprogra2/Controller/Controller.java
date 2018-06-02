package com.example.windo.requeprogra2.Controller;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.windo.requeprogra2.Funcionalidades.ValidacionPassword;

public class Controller {

    private static String usuarioActivo = null;
    private static Context contextoActivo = null;

    public static Connection GetConn(){
        Connection conexion = null;

        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.37;databaseName=Monitoreo;user:live;password=1234;");
            //172.19.53.200
        }catch(Exception e) {
            Toast.makeText(contextoActivo,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public static String getUsuarioActivo(){ return usuarioActivo; }

    // Función que valida el password para un identificador
    public static boolean verificarPassword(String identificador, String password){
        try{
            PreparedStatement stmn = GetConn().prepareStatement(
                    "SELECT sal, pass_hash FROM usuario WHERE usuario = ?");
            stmn.setString(0, identificador);
            ResultSet res = stmn.executeQuery();

            if(res.next()){
                byte[] sal = res.getBytes("sal");
                byte[] encriptado = res.getBytes("pass_hash");

                if(ValidacionPassword.esPasswordValido(encriptado, sal, password)){
                    usuarioActivo = identificador;
                    return true;
                }
                else{
                    throw new Exception("La contraseña no es correcta");
                }
            }else{
                throw new Exception("No se ha encontrador el usuario en la base de datos");
            }
        }
        catch (Exception e){
            Toast.makeText(contextoActivo,e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}
