package co.unir.oscardo.kenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Log;
import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class ListarLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_logs);
        Listar();
    }

    public void Listar(){
        BaseDeDatos DB = new BaseDeDatos(this);
        ArrayList<Clase_Log> listado_log = DB.Consulta_log();
        String Listado = "LISTADO DE LOGS\n\n\n";
        int valorI = 1;
        for (Clase_Log CL:
                listado_log) {
            Listado += "Log " + valorI + ": "+ CL.getRespuesta() + " Fecha: " + CL.getFecha_hora() + "\n";
            valorI += 1;
        }
        TextView ListadoPantalla = (TextView)findViewById(R.id.tv_logs);
        ListadoPantalla.setText(Listado);
    }
}
