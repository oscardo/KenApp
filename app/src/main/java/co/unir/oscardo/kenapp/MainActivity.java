package co.unir.oscardo.kenapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Log;
import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Egreso(View v){
        BaseDeDatos bd = new BaseDeDatos(this);
        bd.insertar_log("Entrada al Egreso");
        Intent irEgreso = new Intent(this, Egreso.class);
        startActivity(irEgreso);
    }

    public void Ingreso(View v){
        BaseDeDatos bd = new BaseDeDatos(this);
        bd.insertar_log("Entrada al Ingreso");
        Intent irIngreso = new Intent(this, Ingreso.class);
        startActivity(irIngreso);
    }

    public void Configuracion(View v){
        BaseDeDatos bd = new BaseDeDatos(this);
        bd.insertar_log("Entrada al Configuración");
        Intent irConfiguracion = new Intent(this, Configuracion.class);
        startActivity(irConfiguracion);
    }

    public void Balance(View v){
        BaseDeDatos bd = new BaseDeDatos(this);
        bd.insertar_log("Entrada al Balance");
        Intent irBalance = new Intent(this, Balance.class);
        startActivity(irBalance);
    }
}
