package co.unir.oscardo.kenapp.Clase_Ingreso_Egreso;

public class Clase_Balance {

    private int id;
    private float Ingreso;
    private float Egreso;
    private float Balance;
    private String Fecha;

    public float getIngreso() {
        return Ingreso;
    }

    public void setIngreso(float ingreso) {
        Ingreso = ingreso;
    }

    public float getEgreso() {
        return Egreso;
    }

    public void setEgreso(float egreso) {
        Egreso = egreso;
    }

    public float getBalance() {
        return Balance;
    }

    public void setBalance(float balance) {
        Balance = balance;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
