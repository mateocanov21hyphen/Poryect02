import java.util.ArrayList;

public class Vehiculo {
    private int modelo;
    private String marca;
    private double valorComercial;
    private String color;
    private int id;
    public static int idActual = 0;
    public ArrayList<Sensor> sensores = new ArrayList<Sensor>();

    public Vehiculo() {
        // Principal.vehiculos[Principal.posAnadir] = this;
        this.id = idActual;
        idActual++;
        Principal.vehiculos.add(this);
    }

    public Vehiculo(int mo, String ma, double va) {
        this(mo, ma, va, "Verde");
    }

    public Vehiculo(int mo, String ma, double va, String co) {
        this.modelo = mo;
        this.marca = ma;
        this.valorComercial = va;
        this.color = co;
        // Principal.vehiculos[Principal.posAnadir] = this;
        this.id = idActual;
        idActual++;
        Principal.vehiculos.add(this);
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return Principal.vehiculos;
    }

    public int getTamano() {
        return Principal.tamano;
    }

    public int getPosAnadir() {
        return Principal.posAnadir;
    }

    public int getModelo() {
        return this.modelo;
    }

    public String getColor() {
        return this.color;
    }

    public String getMarca() {
        return this.marca;
    }

    public double getValorComercial() {
        return this.valorComercial;
    }

    public void setVehiculos(ArrayList<Vehiculo> x) {
        Principal.vehiculos = x;
    }

    public void setTamano(int x) {
        Principal.tamano = x;
    }

    public void setPosAnadir(int x) {
        Principal.posAnadir = x;
    }

    public void setModelo(int x) {
        this.modelo = x;
    }

    public void setColor(String x) {
        this.color = x;
    }

    public void setMarca(String x) {
        this.marca = x;
    }

    public void setValorComercial(double x) {
        this.valorComercial = x;
    }

    public String toString() {
        String texto = "Modelo: " + this.modelo + "\n";
        texto += "Marca: " + this.marca + "\n";
        texto += "Valor Comercial: " + this.valorComercial + "\n";
        texto += "Color: " + this.color + "\n";
        for (Sensor x : sensores) {
            texto += x.toString() + "\n";
        }
        return texto;
    }

    public static int cantidadVehiculos() {
        return Principal.posAnadir + 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdActual() {
        return idActual;
    }

    public static void setIdActual(int idActual) {
        Vehiculo.idActual = idActual;
    }

    public ArrayList<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(ArrayList<Sensor> sensores) {
        this.sensores = sensores;
    }

    public int cantidadSensores() {
        return sensores.size();
    }

    public void anadirSensor(Sensor s) {
        sensores.add(s);
    }

    public String mostrarSensoresVehiculo() {
        String r = "";
        for (Sensor x : sensores) {
            r += x.toString();
        }
        return r;
    }

    public String mostrarSensoresVehiculoTemp() {
        String r = "";
        for (Sensor x : sensores) {
            if ((x.getTipo()).compareToIgnoreCase("Temperatura") == 0) {
                r += x.toString();
            }
        }
        return r;
    }
}