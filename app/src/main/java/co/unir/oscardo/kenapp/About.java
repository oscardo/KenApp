package co.unir.oscardo.kenapp;

import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.TextView;

import co.unir.oscardo.kenapp.DB.BaseDeDatos;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView v = (TextView)findViewById(R.id.texto);
        PackageInfo pInfo = null;
        try {
            pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = "Versión: " + pInfo.versionName;
        v.setText(version);
    }

    public void Elimina_Configuracion(View view) {
        final BaseDeDatos BD = new BaseDeDatos(this);

        String msg = null;
        String titulo = null;
        titulo =   "Deseas Eliminar TODAS las tablas";
        msg =   "Desea eliminar\nTODAS las TABLAS de referencia\n";

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(titulo);
        adb.setMessage(msg);
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BD.Truncate_Table_Sistema();
                BD.insertar_log("ELIMINADAS PERO CREADAS LOS ARCHIVOS TEMPORALES");
            } });
        adb.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BD.insertar_log("Cancelar Opción de eliminar las tablas");
            } });
        adb.show();
    }
}

