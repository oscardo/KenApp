package co.unir.oscardo.kenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
    }

    public void ListarLog(View v){
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a ListarLogs");
        Intent IrListarLog = new Intent(this, ListarLogs.class);
        startActivity(IrListarLog);
    }

    public void Listar_Egreso(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Listar_Egresos");
        Intent IrListarEgresos = new Intent(this, Listar_Egreso.class);
        startActivity(IrListarEgresos);
    }

    public void Listar_Ingreso(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Listar_Ingreso");
        Intent IrListarIngresos = new Intent(this, Listar_Ingresos.class);
        startActivity(IrListarIngresos);
    }

    public void Actulizar_Egreso(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Actualizar_Egreso");
        Intent IrActualizar_Egreso = new Intent(this, Actualizar_Egreso.class);
        startActivity(IrActualizar_Egreso);
    }

    public void Actualiza_Ingresos(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Actualizar_Ingresos");
        Intent IrActualizar_Ingresos = new Intent(this, Actualizar_Ingresos.class);
        startActivity(IrActualizar_Ingresos);
    }

    public void Balance_Mensual(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Balance_Mensual");
        Intent IrBalanceMensual = new Intent(this, Balance_Mensual.class);
        startActivity(IrBalanceMensual);
    }

    public void Eliminar_Egreso(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Eliminar_Emigrar");
        Intent IrEliminar_Emigrar = new Intent(this, Eliminar_Emigrar.class);
        startActivity(IrEliminar_Emigrar);
    }

    public void Eliminar_Ingresos(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Eliminar_Ingresos");
        Intent IrEliminar_Ingresos = new Intent(this, Eliminar_Ingresos.class);
        startActivity(IrEliminar_Ingresos);
    }

    public void Sobre(View view) {
        BaseDeDatos DB = new BaseDeDatos(this);
        DB.insertar_log("Entro a Sobre");
        Intent IrSanto = new Intent(this, About.class);
        startActivity(IrSanto);

    }
}
