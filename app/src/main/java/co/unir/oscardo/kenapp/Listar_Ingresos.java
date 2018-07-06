package co.unir.oscardo.kenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Egreso;
import co.unir.oscardo.kenapp.Clase_Ingreso_Egreso.Clase_Ingreso;
import co.unir.oscardo.kenapp.Constantes.Const;
import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class Listar_Ingresos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar__ingresos);
    }

    public void ListarIngresos(View v){

        RadioButton enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre;
        enero = (RadioButton)findViewById(R.id.rb_enero);
        febrero = (RadioButton)findViewById(R.id.rb_febrero);
        marzo = (RadioButton)findViewById(R.id.rb_marzo);
        abril = (RadioButton)findViewById(R.id.rb_abril);
        mayo = (RadioButton)findViewById(R.id.rb_mayo);
        junio = (RadioButton)findViewById(R.id.rb_junio);
        julio = (RadioButton)findViewById(R.id.rb_julio);
        agosto = (RadioButton)findViewById(R.id.rb_agosto);
        septiembre = (RadioButton)findViewById(R.id.rb_septiembre);
        octubre = (RadioButton)findViewById(R.id.rb_octubre);
        noviembre = (RadioButton)findViewById(R.id.rb_noviembre);
        diciembre = (RadioButton)findViewById(R.id.rb_diciembre);
        RadioButton c_2018, c_2019, c_2020, c_2021, c_2022, c_2023, c_2024, c_2025, c_2026, c_2027, c_2028;
        c_2018 = (RadioButton)findViewById(R.id.rb_2018);
        c_2019 = (RadioButton)findViewById(R.id.rb_2019);
        c_2020 = (RadioButton)findViewById(R.id.rb_2020);
        c_2021 = (RadioButton)findViewById(R.id.rb_2021);
        c_2022 = (RadioButton)findViewById(R.id.rb_2022);
        c_2023 = (RadioButton)findViewById(R.id.rb_2023);
        c_2024 = (RadioButton)findViewById(R.id.rb_2024);
        c_2025 = (RadioButton)findViewById(R.id.rb_2025);
        c_2026 = (RadioButton)findViewById(R.id.rb_2026);
        c_2027 = (RadioButton)findViewById(R.id.rb_2027);
        c_2028 = (RadioButton)findViewById(R.id.rb_2028);

        int mes = 0;
        String mes_ms = "";
        if(enero.isChecked() == true) { mes = 1; mes_ms = "Enero";}
        if(febrero.isChecked() == true) { mes = 2; mes_ms = "Febrero"; }
        if(marzo.isChecked() == true) { mes = 3; mes_ms = "Marzo";}
        if(abril.isChecked() == true) { mes = 4; mes_ms = "Abril"; }
        if(mayo.isChecked() == true) { mes = 5; mes_ms = "Mayo"; }
        if(junio.isChecked() == true) { mes = 6; mes_ms = "Junio";}
        if(julio.isChecked() == true) { mes = 7; mes_ms = "Julio";}
        if(agosto.isChecked() == true) { mes = 8; mes_ms = "Agosto";}
        if(septiembre.isChecked() == true) { mes = 9; mes_ms = "Septiembre";}
        if(octubre.isChecked() == true) { mes = 10; mes_ms = "Octubre";}
        if(noviembre.isChecked() == true) { mes = 11; mes_ms = "Noviembre";}
        if(diciembre.isChecked() == true) { mes = 12; mes_ms = "Diciembre";}

        String annio = "";
        if(c_2018.isChecked() == true) { annio = c_2018.getText().toString(); }
        if(c_2019.isChecked() == true) { annio = c_2019.getText().toString(); }
        if(c_2020.isChecked() == true) { annio = c_2020.getText().toString(); }
        if(c_2021.isChecked() == true) { annio = c_2021.getText().toString(); }
        if(c_2022.isChecked() == true) { annio = c_2022.getText().toString(); }
        if(c_2023.isChecked() == true) { annio = c_2023.getText().toString(); }
        if(c_2024.isChecked() == true) { annio = c_2024.getText().toString(); }
        if(c_2025.isChecked() == true) { annio = c_2025.getText().toString(); }
        if(c_2026.isChecked() == true) { annio = c_2026.getText().toString(); }
        if(c_2027.isChecked() == true) { annio = c_2027.getText().toString(); }
        if(c_2028.isChecked() == true) { annio = c_2028.getText().toString(); }

        String Mensaje = "Mes " + mes_ms + " de " + annio + " \n";
        BaseDeDatos BD = new BaseDeDatos(this);
        ArrayList<Clase_Ingreso> CI = new ArrayList<>();
        CI = BD.Consulta_Ingresos(0, mes, Integer.valueOf(annio), 0);
        if(!CI.isEmpty()) {
            for (Clase_Ingreso i :
                    CI) {
                Mensaje += "Concepto: " + i.getConcepto() + " $:" + i.getValor() + " Dia: " + i.getDia() + " " + i.getHora() + "\n";
            }
        }else{
            Mensaje += "No tiene ningún valor";
        }
        BD.insertar_log(Const.INGRESO + " ### " + Mensaje.substring(0, 100));
        BD.close();
        TextView mensaje_egreso = (TextView)findViewById(R.id.tv_mensaje_egreso);
        mensaje_egreso.setText(Mensaje);
    }
}
