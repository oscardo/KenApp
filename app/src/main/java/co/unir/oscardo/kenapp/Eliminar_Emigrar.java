package co.unir.oscardo.kenapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Egreso;
import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class Eliminar_Emigrar extends AppCompatActivity {

    private ArrayList<Clase_Egreso> Egreso = null;
    private Clase_Egreso CE;
    private BaseDeDatos DB;
    private int idResp;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar__emigrar);
    }

    public void Eliminar_Egreso(View view) {
        Spinner Spinner_Dia = (Spinner)findViewById(R.id.sp_dias);
        Spinner Spinner_Mes = (Spinner)findViewById(R.id.sp_Mes);
        Spinner Spinner_Annio = (Spinner)findViewById(R.id.sp_Annio);

        String Dia = String.valueOf(Spinner_Dia.getSelectedItem());
        String Mes = String.valueOf(Spinner_Mes.getSelectedItem());
        Long mes_i = Long.valueOf(Spinner_Mes.getSelectedItemId()) + 1;
        String Annio = String.valueOf(Spinner_Annio.getSelectedItem());

        DB = new BaseDeDatos(this);
        Egreso = DB.Consulta_Egreso(0, Integer.valueOf(mes_i.toString()), Integer.valueOf(Annio), Integer.valueOf(Dia));
        String Mensaje = "";
        if(Integer.valueOf(Dia) == 0){
            Mensaje += "Egresos del Mes: " + Mes + " " + Annio + "\n";
        }else{
            Mensaje += "Egresos del Día: " + Dia + " " + Mes + " " + Annio + "\n";
        }

        RadioGroup rb_datos_lista = (RadioGroup)findViewById(R.id.rb_datos);

        RadioButton[] rb = new RadioButton[Egreso.size()];
        int Nro = 0;
        for (Clase_Egreso CE:
                Egreso) {
            rb[Nro] = new RadioButton(this);
            int val = Nro + 1;
            rb[Nro].setText("Nro: " + val + " ID:" + CE.getId() +  " Concepto: " + CE.getConcepto() + " Valor: " + CE.getValor() + "\n");
            rb[Nro].setId(Nro);
            rb_datos_lista.addView(rb[Nro]);
            Nro++;
        }
        Button btn_fin_proceso_actualiza = (Button)findViewById(R.id.btn_fin_proceso_actualiza);
        btn_fin_proceso_actualiza.setVisibility(View.VISIBLE);
        DB.insertar_log("Actualizar_Egresos");
    }

    public void Eliminar(View view) {
        RadioGroup rb_datos_lista = (RadioGroup)findViewById(R.id.rb_datos);
        idResp = rb_datos_lista.getCheckedRadioButtonId();
        CE = new Clase_Egreso();
        if(idResp != -1) {
            RadioButton val = (RadioButton) findViewById(idResp);
            int valorEgreso = val.getId();
            CE.setConcepto(Egreso.get(valorEgreso).getConcepto());
            CE.setValor(Egreso.get(valorEgreso).getValor());
            idResp = Integer.valueOf(Egreso.get(valorEgreso).getId());
            CE.setId(idResp);
            DB.insertar_log("Actualiza_Egreso");
        }else{
            Toast.makeText(this, R.string.Actualiza_egreso_faltaRadio, Toast.LENGTH_LONG).show();
        }

        String msg = null;
        String titulo = null;
        titulo =   "Deseas Eliminar este \n";
        msg =   " Concepto: " + CE.getConcepto() + "\n" +
                " Valor $:" + CE.getValor() + "\n" +
                " Creada: " + CE.getDia() + " " + CE.getHora();

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(titulo);
        adb.setMessage(msg);
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Boolean rpt = DB.Eliminar_egreso(CE);
                DB.insertar_log("Eliminar");
                IrConfiguracion();
            } });
        adb.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                DB.insertar_log("Cancelar Opción de eliminar");
            } });
        adb.show();
    }

    public void IrConfiguracion(){
        Intent IrConfiguracion = new Intent(this, Configuracion.class);
        startActivity(IrConfiguracion);
    }
}

