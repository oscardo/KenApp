package co.unir.oscardo.kenapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Balance;
import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Egreso;
import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class Actualizar_Egreso extends AppCompatActivity {

    private ArrayList<Clase_Egreso> Egreso = null;
    private Clase_Egreso CE;
    private BaseDeDatos DB;
    private int idResp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar__egreso);
    }

    public void Actualizar_Egresos(View view) {
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
        Button btn_Actualiza = (Button)findViewById(R.id.btn_Actualiza_egreso);
        btn_Actualiza.setVisibility(View.VISIBLE);
        DB.insertar_log("Actualizar_Egresos");
    }



    public void Actualiza_Egreso(View view) {
        RadioGroup rb_datos_lista = (RadioGroup)findViewById(R.id.rb_datos);
        idResp = rb_datos_lista.getCheckedRadioButtonId();
        if(idResp != -1) {
            RadioButton val = (RadioButton) findViewById(idResp);
            int valorEgreso = val.getId();

            TextView tv_concepto = (TextView) findViewById(R.id.et_actual_concepto);
            EditText concepto = (EditText) findViewById(R.id.et_actual_concepto);
            TextView tv_valor = (TextView) findViewById(R.id.et_actual_valor);
            EditText valor = (EditText) findViewById(R.id.et_actual_valor);

            concepto.setText((Egreso.get(valorEgreso).getConcepto()));
            concepto.setVisibility(View.VISIBLE);
            tv_concepto.setVisibility(View.VISIBLE);

            valor.setText((String.valueOf(Egreso.get(valorEgreso).getValor())));
            valor.setVisibility(View.VISIBLE);
            tv_valor.setVisibility(View.VISIBLE);

            Button Actualiza = (Button) findViewById(R.id.btn_fin_proceso_actualiza);
            Actualiza.setVisibility(View.VISIBLE);
            idResp = Integer.valueOf(Egreso.get(valorEgreso).getId());
            DB.insertar_log("Actualiza_Egreso");
        }else{
            Toast.makeText(this, R.string.Actualiza_egreso_faltaRadio, Toast.LENGTH_LONG).show();
        }

    }

    public void Actualizar(View view) {
       EditText et_actual_valor = (EditText)findViewById(R.id.et_actual_valor);
       EditText et_actual_concepto = (EditText)findViewById(R.id.et_actual_concepto);
       Clase_Egreso CE = new Clase_Egreso();
       CE.setId(idResp);
       CE.setConcepto(et_actual_concepto.getText().toString());
       CE.setValor(Float.valueOf(et_actual_valor.getText().toString()));
       Boolean rpt = DB.actualizar_egreso(CE);
        DB.insertar_log("Actualizar");
       if(rpt){
           Intent IrConfiguracion = new Intent(this, Configuracion.class);
           startActivity(IrConfiguracion);
       }else{
           Toast.makeText(this, R.string.Actualiza_error_Egreso, Toast.LENGTH_LONG).show();
       }
    }
}




