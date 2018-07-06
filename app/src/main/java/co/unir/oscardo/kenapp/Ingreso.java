package co.unir.oscardo.kenapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Ingreso;
import co.unir.oscardo.kenapp.Constantes.Tiempo;
import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class Ingreso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        TextView mensaje = (TextView)findViewById(R.id.tv_mensaje);
        BaseDeDatos BD = new BaseDeDatos(this);
        ArrayList<Clase_Ingreso> temp = BD.Consulta_Ingresos(5, 0, 0, 0);
        String tempo = "Últimos cinco conceptos\n\n";
        for (Clase_Ingreso t: temp) {
            tempo += t.getConcepto() + " $: " + t.getValor() + " Día: " + t.getDia() + " " + t.getHora() + "\n";
        }
        mensaje.setText(tempo);
    }

    public void Evaluar(View v){

        EditText tempConcepto = (EditText)findViewById(R.id.et_concepto);
        EditText tempValor = (EditText)findViewById(R.id.et_ingreso);
        TextView mensaje = (TextView)findViewById(R.id.tv_mensaje);

        if(!tempConcepto.getText().toString().isEmpty() && !tempValor.getText().toString().isEmpty()){
            String concepto = tempConcepto.getText().toString();
            String egreso = tempValor.getText().toString();
            Clase_Ingreso CI = new Clase_Ingreso();
            CI.setConcepto(concepto);
            CI.setValor(Float.valueOf(egreso));
            BaseDeDatos BD = new BaseDeDatos(this);
            Boolean rpt = BD.insertar_ingreso(CI);
            if(rpt){
                mensaje.setText(R.string.exito_operacion);
                IrMenu();
            }else{
                mensaje.setText(R.string.no_exito_operacion);
            }
        }else {
            mensaje.setText(R.string.no_llenado_formulario);
        }
    }

    public void IrMenu(){
        Intent IrMenu = new Intent(this, MainActivity.class);
        startActivity(IrMenu);
    }
}
