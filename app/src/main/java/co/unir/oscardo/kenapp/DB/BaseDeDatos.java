package co.unir.oscardo.kenapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Balance;
import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Egreso;
import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Ingreso;
import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Log;
import co.unir.oscardo.kenapp.Constantes.Const;
import co.unir.oscardo.kenapp.Constantes.Tiempo;

public class BaseDeDatos extends SQLiteOpenHelper {

    public Context context;
    private SQLiteDatabase DB;
    public BaseDeDatos(Context context) {
        super(context, Const.NAME_DB, null, Const.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String QueryEgreso =   "CREATE TABLE IF NOT EXISTS " + Const.EGRESO +
                               " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                               "concepto	TEXT,"+
                               "valor NUMERIC,"+
                               " dia	TEXT,"+
                               " hora	TEXT)";

        String QueryIngreso =  "CREATE TABLE IF NOT EXISTS "+ Const.INGRESO +" (" +
                                "id	INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "concepto	TEXT," +
                                "valor	NUMERIC," +
                                "dia	TEXT,"+
                                "hora	TEXT);";

        String QueryIngreso_Egreso = "CREATE TABLE IF NOT EXISTS " + Const.INGRESO_EGRESO + " (" +
	                                "id	INTEGER PRIMARY KEY AUTOINCREMENT," +
	                                "total_ingreso	NUMERIC," +
	                                "total_egreso	NUMERIC," +
	                                "valor_ingreso_egreso	NUMERIC," +
	                                "fecha	TEXT," +
                                    "hora	TEXT);";

        String QueryLog = "CREATE TABLE IF NOT EXISTS "+ Const.LOG +" (" +
                          "id	INTEGER PRIMARY KEY AUTOINCREMENT," +
	                      "respuesta	TEXT, " +
                          "fecha_hora TEXT);";
        sqLiteDatabase.execSQL(QueryEgreso);
        sqLiteDatabase.execSQL(QueryIngreso);
        sqLiteDatabase.execSQL(QueryIngreso_Egreso);
        sqLiteDatabase.execSQL(QueryLog);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Const.EGRESO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Const.INGRESO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Const.INGRESO_EGRESO);
        onCreate(sqLiteDatabase);
    }

    /**
     * @param NroOpciones número de datos que se veras en pantalla
     * @return arrayList de Clase Ingreso
     */
    public ArrayList<Clase_Ingreso> Consulta_Ingresos(int NroOpciones, int mes, int annio, int dia){

        String Condiciones = "";
        String mes_cero = "";
        if(annio != 0 && mes != 0){
            Condiciones += " where substr(dia, 0, 5) = '" + annio + "'";
            mes_cero = (mes <= 9 ? "0" : "");
            Condiciones += " and substr(dia, 6, 2) = '" + mes_cero + mes + "'";
        }
        if(dia != 0){
            mes_cero = (dia <= 9 ? "0" : "");
            Condiciones += " and substr(TI.dia, 9, 2) = '" + mes_cero + dia + "'";
            Condiciones += " and substr(TE.dia, 9, 2) = '" + mes_cero + dia + "'";
        }

        if(NroOpciones != 0){
            Condiciones += " limit " + NroOpciones;
        }

        ArrayList<Clase_Ingreso> Ingreso = new ArrayList<>();
        String query = "select concepto, valor, dia, hora, id from " + Const.INGRESO + Condiciones;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);
        while(registro.moveToNext()){
            Clase_Ingreso CI = new Clase_Ingreso();
            CI.setConcepto(registro.getString(0));
            CI.setValor(registro.getFloat(1));
            CI.setDia(registro.getString(2));
            CI.setHora(registro.getString(3));
            CI.setId(registro.getInt(4));
            Ingreso.add(CI);
        }
        DB.close();
        return Ingreso;
    }

    public ArrayList<Clase_Ingreso> Consulta_Ingresos(){

        ArrayList<Clase_Ingreso> Ingreso = new ArrayList<>();
        String query = "select concepto, valor, dia, hora, id from " + Const.INGRESO;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);
        while(registro.moveToNext()){
            Clase_Ingreso CI = new Clase_Ingreso();
            CI.setConcepto(registro.getString(0));
            CI.setValor(registro.getFloat(1));
            CI.setDia(registro.getString(2));
            CI.setHora(registro.getString(3));
            CI.setId(registro.getInt(4));
            Ingreso.add(CI);
        }
        DB.close();
        return Ingreso;
    }

    public ArrayList<Clase_Ingreso> Consulta_Ingresos_dia(){
        ArrayList<Clase_Ingreso> ingreso = new ArrayList<>();
        String query = "select concepto, valor, id from " + Const.INGRESO;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);
        while(registro.moveToNext()){
            Clase_Ingreso CI = new Clase_Ingreso();
            CI.setConcepto(registro.getString(0));
            CI.setValor(registro.getFloat(1));
            CI.setId(registro.getInt(2));
            ingreso.add(CI);
        }
        DB.close();
        return ingreso;
    }

    public ArrayList<Clase_Egreso> Consulta_Egreso(int NroOpciones, int mes, int annio, int dia){

        String Condiciones = "";
        String mes_cero = "";
        if(annio != 0 && mes != 0){
            Condiciones += " where substr(dia, 0, 5) = '" + annio + "'";
            mes_cero = (mes <= 9 ? "0" : "");
            Condiciones += " and substr(dia, 6, 2) = '" + mes_cero + mes + "'";
        }
        if(dia != 0){
            mes_cero = (dia <= 9 ? "0" : "");
            Condiciones += " and substr(dia, 9, 2) = '" + mes_cero + dia + "'";
        }

        if(NroOpciones != 0){
            Condiciones += " limit " + NroOpciones;
        }

        ArrayList<Clase_Egreso> Egreso = new ArrayList<>();
        String query = "select concepto, valor, dia, hora, id from " + Const.EGRESO + Condiciones;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);

        while(registro.moveToNext()){
            Clase_Egreso CI = new Clase_Egreso();
            CI.setConcepto(registro.getString(0));
            CI.setValor(registro.getFloat(1));
            CI.setDia(registro.getString(2));
            CI.setHora(registro.getString(3));
            CI.setId(registro.getInt(4));
            Egreso.add(CI);
        }

        DB.close();
        return Egreso;
    }

    public ArrayList<Clase_Egreso> Consulta_Egreso(){
        ArrayList<Clase_Egreso> Egreso = new ArrayList<>();
        String query = "select concepto, valor, id from " + Const.EGRESO;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);
        while(registro.moveToNext()){
            Clase_Egreso CI = new Clase_Egreso();
            CI.setConcepto(registro.getString(0));
            CI.setValor(registro.getFloat(1));
            CI.setId(registro.getInt(2));
            Egreso.add(CI);
        }
        DB.close();
        return Egreso;
    }

    public ArrayList<Clase_Balance> Consulta_Ingreso_vs_Egreso(int dia, int mes, int annio){
        String Condiciones_e = "";
        String Condiciones_i = "";
        float total_i = 0;
        float total_e = 0;
        String mes_cero = "";
        if(annio != 0 && mes != 0){
            Condiciones_i += " where substr(TI.dia, 0, 5) = '" + annio + "'";
            Condiciones_e += " where substr(TE.dia, 0, 5) = '" + annio + "'";
            mes_cero = (mes <= 9 ? "0" : "");
            Condiciones_i += " and substr(TI.dia, 6, 2) = '" + mes_cero + mes + "'";
            Condiciones_e += " and substr(TE.dia, 6, 2) = '" + mes_cero + mes + "'";
        }
        if(dia != 0){
            mes_cero = (dia <= 9 ? "0" : "");
            Condiciones_i += " and substr(TI.dia, 9, 2) = '" + mes_cero + dia + "'";
            Condiciones_e += " and substr(TE.dia, 9, 2) = '" + mes_cero + dia + "'";
        }

        ArrayList<Clase_Balance> Balance_Mensual = new ArrayList<>();

        //String query = "select SUM(TI.valor) as '1', SUM(TE.valor) as '2', SUM(TI.valor)-SUM(TE.valor) as '3'" +
        //               " from Tabla_ingreso TI join Tabla_Egreso TE " + Condiciones;

        String query = "select SUM(TI.valor) from " + Const.INGRESO + " TI " + Condiciones_i;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);
        while(registro.moveToNext()){
            total_i = registro.getFloat(0);
            //Clase_Balance CB = new Clase_Balance();
            //CB.setIngreso(registro.getFloat(0));
            //CB.setEgreso(registro.getFloat(1));
            //CB.setBalance(registro.getFloat(2));
            //Balance_Mensual.add(CB);
        }

        query = "select SUM(TE.valor) from " + Const.EGRESO + " TE " + Condiciones_e;
        DB = this.getWritableDatabase();
        registro = DB.rawQuery(query, null);
        while(registro.moveToNext()){
            total_e = registro.getFloat(0);
            //Clase_Balance CB = new Clase_Balance();
            //CB.setIngreso(registro.getFloat(0));
            //CB.setEgreso(registro.getFloat(1));
            //CB.setBalance(registro.getFloat(2));
            //Balance_Mensual.add(CB);
        }
        Clase_Balance CB = new Clase_Balance();
        CB.setIngreso(total_i);
        CB.setEgreso(total_e);
        CB.setBalance(total_i - total_e);
        Balance_Mensual.add(CB);
        DB.close();
        return Balance_Mensual;
    }

    public ArrayList<Clase_Balance> Consulta_Ingreso_vs_Egreso(){

        ArrayList<Clase_Balance> Balance_Mensual = new ArrayList<>();
        String query = "select SUM(TI.valor) as '1', SUM(TE.valor) as '2', SUM(TI.valor) - SUM(TE.valor) as '3'" +
                " from Tabla_ingreso TI, Tabla_Egreso TE ";
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);
        while(registro.moveToNext()){
            Clase_Balance CB = new Clase_Balance();
            CB.setIngreso(registro.getFloat(0));
            CB.setEgreso(registro.getFloat(1));
            CB.setBalance(registro.getFloat(2));
            Balance_Mensual.add(CB);
        }
        DB.close();
        return Balance_Mensual;
    }

    public ArrayList<Clase_Log> Consulta_log(){
        ArrayList<Clase_Log> Log = new ArrayList<>();
        String query = "select  respuesta,fecha_hora from " + Const.LOG;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor registro = DB.rawQuery(query, null);
            while (registro.moveToNext()) {
                Clase_Log CL = new Clase_Log();
                CL.setRespuesta(registro.getString(0));
                CL.setFecha_hora(registro.getString(1));
                Log.add(CL);
            }
            DB.close();
            return Log;
    }

    /**
     * @param CI Clase Ingreso
     * @return true si se hizo la operación o false si no se realizo
     */
    public boolean insertar_ingreso(Clase_Ingreso CI){
        try {

            Tiempo T = new Tiempo();
            String rtp;
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("concepto", CI.getConcepto());
            contentValues.put("valor", CI.getValor());
            contentValues.put("dia", T.getCurrentDate().toString());
            contentValues.put("hora", T.getCurrentTime().toString());
            long result = DB.insert(Const.INGRESO, null, contentValues);
            DB.close();
            if(result == -1){
                rtp = "Error: " + T.getCurrentDateandTime();
                return false;
            }else{
                rtp =   "concepto: " + CI.getConcepto() +
                        " valor: " + CI.getValor() +
                        " Fecha y Hora: " + CI.getDia() + CI.getHora();
            }
            insertar_log(rtp);
        }
        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
            insertar_log(exception.getMessage().toString());
            return false;
        }
        return true;
    }

    /**
     * @param CE Clase Egreso
     * @return true si se realiza la operación o false si fracasa
     */
    public boolean insertar_egreso(Clase_Egreso CE){
        try {
            Tiempo T = new Tiempo();
            String rtp;
            ContentValues contentValues = new ContentValues();
            contentValues.put("concepto", CE.getConcepto());
            contentValues.put("valor", CE.getValor());
            contentValues.put("dia", T.getCurrentDate().toString());
            contentValues.put("hora", T.getCurrentTime().toString());
            SQLiteDatabase DB = this.getWritableDatabase();
            long result = DB.insert(Const.EGRESO, null, contentValues);
            DB.close();
            if(result == -1){
                rtp = "Error: " + T.getCurrentDateandTime();
                return false;
            }else{
                rtp = "concepto: " + CE.getConcepto() +
                        " valor: " + CE.getValor() +
                        " Fecha y Hora: " + CE.getDia() + CE.getHora();
            }
            insertar_log(rtp);
        }
        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
            insertar_log(exception.getMessage().toString());
            return false;
        }
        return true;
    }

    public boolean insertar_log(String Respuesta){
        try {
            Tiempo T = new Tiempo();
            Respuesta = Respuesta.toLowerCase();
            Respuesta = Respuesta.replace("'", "");
            Respuesta = Respuesta.replace("´", "");

            Respuesta = Respuesta.replace("insert", "insertar");
            Respuesta = Respuesta.replace("select", "consultar");
            Respuesta = Respuesta.replace("update", "actualizar");
            Respuesta = Respuesta.replace("delete", "ELIMINAR");

            Respuesta = Respuesta.replace("1=1", "uno=uno");
            Respuesta = Respuesta.replace("--", "separados");

            Respuesta = Respuesta.replace("create", "CREAR");
            Respuesta = Respuesta.replace("drop table", "ELIMINAR TABLA");

            ContentValues contentValues = new ContentValues();
            contentValues.put("respuesta", Respuesta);
            contentValues.put("fecha_hora", T.getCurrentDateandTime().toString());

            DB = this.getWritableDatabase();
            long result = DB.insert(Const.LOG, null, contentValues);
            DB.close();

            if(result == -1){
                return false;
            }
        }
        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
        }
        return true;
    }

    public boolean actualizar_egreso(Clase_Egreso CE){
        try {
            Tiempo T = new Tiempo();
            String rtp;
            ContentValues contentValues = new ContentValues();
            contentValues.put("concepto", CE.getConcepto());
            contentValues.put("valor", CE.getValor());
            SQLiteDatabase DB = this.getWritableDatabase();
            //myDB.update(TableName, cv, "_id="+id, null);
            long result = DB.update(Const.EGRESO, contentValues, "id="+CE.getId(), null);
            DB.close();
            if(result == -1){
                rtp = "Error: " + T.getCurrentDateandTime();
                return false;
            }else{
                rtp = "concepto: " + CE.getConcepto() +
                        " valor: " + CE.getValor() +
                        " Fecha y Hora: " + CE.getDia() + CE.getHora();
            }
            insertar_log("Update: " + rtp);
        }
        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
            insertar_log(exception.getMessage().toString());
            return false;
        }
        return true;
    }

    public boolean actualizar_ingresos(Clase_Ingreso CI){
        try {
            Tiempo T = new Tiempo();
            String rtp;
            ContentValues contentValues = new ContentValues();
            contentValues.put("concepto", CI.getConcepto());
            contentValues.put("valor", CI.getValor());
            SQLiteDatabase DB = this.getWritableDatabase();
            //myDB.update(TableName, cv, "_id="+id, null);
            long result = DB.update(Const.INGRESO, contentValues, "id="+CI.getId(), null);
            DB.close();
            if(result == -1){
                rtp = "Error: " + T.getCurrentDateandTime();
                return false;
            }else{
                rtp = "concepto: " + CI.getConcepto() +
                        " valor: " + CI.getValor() +
                        " Fecha y Hora: " + CI.getDia() + CI.getHora();
            }
            insertar_log("Update: " + rtp);
        }
        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
            insertar_log(exception.getMessage().toString());
            return false;
        }
        return true;
    }

    public boolean Eliminar_egreso(Clase_Egreso CE){
        try {
            Tiempo T = new Tiempo();
            String rtp;
            ContentValues contentValues = new ContentValues();

            SQLiteDatabase DB = this.getWritableDatabase();
            String table = Const.EGRESO;
            String whereClause = "id=" + CE.getId();
            long result = DB.delete(table, whereClause, null);
            DB.close();
            if(result == -1){
                rtp = "Error: " + T.getCurrentDateandTime();
                return false;
            }else{
                rtp = "concepto: " + CE.getConcepto() +
                        " valor: " + CE.getValor() +
                        " Fecha y Hora: " + CE.getDia() + CE.getHora();
            }
            insertar_log("Delete Egreso: " + rtp);
        }

        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
            insertar_log(exception.getMessage().toString());
            return false;
        }
        return true;
    }

    public boolean Eliminar_Ingresos(Clase_Ingreso CI){
        try {
            Tiempo T = new Tiempo();
            String rtp;
            ContentValues contentValues = new ContentValues();

            SQLiteDatabase DB = this.getWritableDatabase();
            String table = Const.INGRESO;
            String whereClause = "id=" + CI.getId();
            long result = DB.delete(table, whereClause, null);
            DB.close();
            if(result == -1){
                rtp = "Error: " + T.getCurrentDateandTime();
                return false;
            }else{
                rtp = "concepto: " + CI.getConcepto() +
                        " valor: " + CI.getValor() +
                        " Fecha y Hora: " + CI.getDia() + CI.getHora();
            }
            insertar_log("Delete Ingreso: " + rtp);
        }
        catch (Exception exception){
            Log.d(exception.toString(), exception.getMessage(), null);
            insertar_log(exception.getMessage().toString());
            return false;
        }
        return true;
    }

    public void Creacion_txt(String sFileName) {
        try {
            File gpxfile = null;
            String Dia = new Tiempo().getCurrentDate().toString();
            Dia += ".txt";
            File root = new File(Environment.getExternalStorageDirectory(), "KenApp");
            if (!root.exists()) {
                root.mkdirs();
            }
            if(sFileName == Const.EGRESO) {
                sFileName = sFileName.concat("_" + Dia);
                gpxfile = new File(root, sFileName);
                FileWriter writer = new FileWriter(gpxfile);
                ArrayList<Clase_Egreso> Egresos = this.Consulta_Egreso();
                for (Clase_Egreso CE:
                        Egresos) {
                    String palabra = CE.getId() +","+CE.getConcepto() + "," + CE.getValor() +"," + CE.getDia() +"," + CE.getHora() + "\n";
                    writer.append(palabra);
                    writer.flush();
                }
                writer.close();
            }
            if(sFileName == Const.INGRESO) {
                sFileName = sFileName.concat("_" + Dia);
                gpxfile = new File(root, sFileName);
                FileWriter writer = new FileWriter(gpxfile);
                ArrayList<Clase_Ingreso> Ingresos = this.Consulta_Ingresos();
                for (Clase_Ingreso CI:
                        Ingresos) {
                    String palabra = CI.getId() +","+CI.getConcepto() + "," + CI.getValor() +"," + CI.getDia() +"," + CI.getHora() + "\n";
                    writer.append(palabra);
                    writer.flush();
                }
                writer.close();
            }
            if(sFileName == Const.INGRESO_EGRESO) {
                sFileName = sFileName.concat("_" + Dia);
                gpxfile = new File(root, sFileName);
                FileWriter writer = new FileWriter(gpxfile);
                ArrayList<Clase_Balance> Balances = this.Consulta_Ingreso_vs_Egreso();
                for (Clase_Balance CB:
                        Balances) {
                    String palabra = "ID: " + CB.getId() + " Ingreso: $" + CB.getIngreso() + " - Egreso: $" + CB.getEgreso() + " = " + CB.getBalance() + "\n";
                    writer.append(palabra);
                    writer.flush();
                }
                writer.close();
            }
            if(sFileName == Const.LOG) {
                sFileName = sFileName.concat("_" + Dia);
                gpxfile = new File(root, sFileName);
                FileWriter writer = new FileWriter(gpxfile);
                ArrayList<Clase_Log> Logs = this.Consulta_log();
                for (Clase_Log Log:
                        Logs) {
                    String palabra = Log.getRespuesta() +" - Fecha: "+ Log.getFecha_hora() + "\n";
                    writer.append(palabra);
                    writer.flush();
                }
                writer.close();
            }
            Toast.makeText(context, "Saved...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Truncate_Table_Sistema(){
        Creacion_txt(Const.INGRESO_EGRESO);
        Creacion_txt(Const.INGRESO);
        Creacion_txt(Const.EGRESO);
        Creacion_txt(Const.LOG);
        DB = getWritableDatabase();
        onUpgrade(DB, 0, 0);
    }

}


