package co.unir.oscardo.kenapp.Clase_Ingreso_Egreso;

import java.util.Date;

public class Clase_Ingreso {

    private int id;
    private String concepto;
    private float valor;
    private String dia;
    private String hora;

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Clase_Ingreso(String concepto, float valor, String dia, String hora) {
        this.concepto = concepto;
        this.valor = valor;
        this.dia = dia;
        this.hora = hora;
    }
    public Clase_Ingreso() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
