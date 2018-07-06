package co.unir.oscardo.kenapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import co.unir.oscardo.kenapp.Constantes.Const;
import co.unir.oscardo.kenapp.Constantes.Tiempo;

public class Clase_log_System {

    BaseDeDatos BD;
    Context ctx;
    public boolean insertar_log(String Respuesta){
        try {

            Tiempo T = new Tiempo();

            Respuesta = Respuesta.replace("'", "");
            Respuesta = Respuesta.toLowerCase();
            Respuesta = Respuesta.replace("insert", "insertar");
            Respuesta = Respuesta.replace("select", "consultar");
            Respuesta = Respuesta.replace("update", "actualizar");
            Respuesta = Respuesta.replace("delete", "ELIMINAR");

            Respuesta = Respuesta.replace("create", "CREAR");
            Respuesta = Respuesta.replace("drop table", "ELIMINAR TABLA");


            ContentValues contentValues = new ContentValues();
            contentValues.put("respuesta", Respuesta);
            contentValues.put("fecha_hora", T.getCurrentDateandTime().toString());




            //long result = DB1.insert(Const.LOG, null, contentValues);
            //DB1.close();
            //if(result == -1){
            //    return false;
            //}

        }
        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
            //insertar_log(exception.getMessage().toString());
        }
        return true;
    }

}
