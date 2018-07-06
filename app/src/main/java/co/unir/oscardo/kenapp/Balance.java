package co.unir.oscardo.kenapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Balance;
import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class Balance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
    }

    @SuppressLint("ResourceAsColor")
    public void CrearBalance(View v){
        Spinner Spinner_Dia = (Spinner)findViewById(R.id.sp_dias);
        Spinner Spinner_Mes = (Spinner)findViewById(R.id.sp_Mes);
        Spinner Spinner_Annio = (Spinner)findViewById(R.id.sp_Annio);

        String Dia = String.valueOf(Spinner_Dia.getSelectedItem());
        String Mes = String.valueOf(Spinner_Mes.getSelectedItem());
        Long mes_i = Long.valueOf(Spinner_Mes.getSelectedItemId()) + 1;
        String Annio = String.valueOf(Spinner_Annio.getSelectedItem());

        BaseDeDatos DB = new BaseDeDatos(this);
        ArrayList<Clase_Balance> Balance = DB.Consulta_Ingreso_vs_Egreso(Integer.valueOf(Dia), Integer.valueOf(mes_i.toString()), Integer.valueOf(Annio));
        String Mensaje = "";
        if(Integer.valueOf(Dia) == 0){
            Mensaje += "Balance del Mes: " + Mes + " " + Annio + "\n";
        }else{
            Mensaje += "Balance del Día: " + Dia + " " + Mes + " " + Annio + "\n";
        }
        TextView tv_mensaje_balance = (TextView)findViewById(R.id.tv_mensaje_balance);

        boolean color_balance = true;
        for (Clase_Balance CB:
             Balance) {
                Mensaje += "Ingreso: $ " + CB.getIngreso() + "\n" +
                           "Egreso:  $ " + CB.getEgreso() + "\n" +
                           "__________________" + "\n" +
                           "Balance: $ " + CB.getBalance();
            if(CB.getBalance() < 0) color_balance = false;
        }
        if(color_balance) {
            tv_mensaje_balance.setTextColor(R.color.colorVerde);
        }else{
            tv_mensaje_balance.setTextColor(R.color.colorRojo);
        }
        tv_mensaje_balance.setText(Mensaje);
    }
}
