public class Sensor {
    
    private String tipo;
    private double valor;

    public Sensor() {
        this("Temperatura", 10.00);
    }

    public Sensor(String t, double v) {
        this.tipo = t;
        this.valor = v;
        Principal.posAnadirSens += 1;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String toString() {
        String texto = "";
        texto = "Tipo: " + this.tipo + "\n";
        texto = texto + "Valor: " + this.valor + "\n";
        return texto;
    }

    public static int cantidadSensores(){
        return Principal.posAnadirSens;
    }
}