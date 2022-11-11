import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    static Scanner sc = new Scanner(System.in);
    static File vehiculostxt = new File("/Users/HP/Documents/Eafit/Fundamentos/Parcial/Vehiculos.txt");

    public static ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    public static int tamano = 10;
    public static int posAnadir = 0;
    public static int posAnadirSens = 0;
    public static ArrayList<Sensor> sensoresTotal = new ArrayList<Sensor>();
    // public static Sensor[] sensores = {new Sensor("temperatura", 6),new
    // Sensor("temperatura", 4),new Sensor("temperatura", 2),new
    // Sensor("temperatura", 1)};
    public static int tamanosens = 8;

    public static void main(String[] args) throws FileNotFoundException {
        mostrarMenu();
    }

    /*
     * public static String toStringVehiculos() {
     * String texto = "";
     * for (Vehiculo x: vehiculos) {
     * texto += x.toString();
     * }
     * return texto;
     * }
     */

    public static void mostrarMenu() throws FileNotFoundException {
        int input;
        do {
            showMenu();
            input = sc.nextInt();

            switch (input) {
                case 1:

                    if (posAnadir < 10) {
                        System.out.println("Ingrese modelo: ");
                        int mo = sc.nextInt();
                        System.out.println("Ingrese marca: ");
                        String ma = sc.next();
                        System.out.println("Ingrese valor: ");
                        Double va = sc.nextDouble();
                        System.out.println("Ingrese color: ");
                        String co = sc.next();
                        new Vehiculo(mo, ma, va, co);
                    } else {
                        System.out.println("Error base de datos llena");
                    }
                    break;

                case 2:
                    for (int i = 0; i < vehiculos.size(); i++) {
                        Vehiculo x = vehiculos.get(i);
                        System.out.println("Vehiculo: " + i /* + 1 */);
                        System.out.println(x.toString());
                    }
                    break;
                case 3:
                    System.out.println("Vehiculos: " + (vehiculos.size()));
                    break;
                case 4:
                    for (Vehiculo x : vehiculos) {
                        if ((x.getColor()).compareToIgnoreCase("verde") == 0) {
                            System.out.println(x.toString());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Ingrese un ID valido");
                    System.out.println(obtenerVehiculoPorID(sc.nextInt()).toString());
                    break;
                case 6:
                    preguntarSensor();
                    break;
                case 7:
                    mostrarSensores();
                    break;
                case 8:
                    mostrarSensoresTemp();
                    break;
                case 9:
                    System.out.println(mayorVehiculo().toString());
                    break;
                case 10:
                    Scanner importVehiculos = new Scanner(vehiculostxt);
                    while (importVehiculos.hasNext()) {

                        // System.out.println(importVehiculos.nextLine());
                        int mo = importVehiculos.nextInt();
                        String ma = importVehiculos.next();
                        Double va = importVehiculos.nextDouble();
                        String co = importVehiculos.next();
                        new Vehiculo(mo, ma, va, co);
                    }
                        Vehiculo x = vehiculos.get(1);
                        x.anadirSensor(new Sensor("temperatura", 6));

                        
                        x = vehiculos.get(1);
                        x.anadirSensor(new Sensor("temperatura", 4));
                        
                        x = vehiculos.get(1);
                        x.anadirSensor(new Sensor("distance", 3));

                        x = vehiculos.get(2);
                        x.anadirSensor(new Sensor("temperatura", 1));
                    
                    break;
                case 666:
                    recolectarSensores();
                    ArrayList<Sensor> ordenados = ordenarSensores(sensoresTotal);
                    int countTemp = 1;
                    for (Sensor sen : ordenados) {
                        if ((sen.getTipo()).compareToIgnoreCase("temperatura") == 0) {
                            // Sensor x = ordenados[i];
                            System.out.println("Sensor: " + countTemp);
                            countTemp += 1;
                            System.out.println(sen.toString());
                        }
                    }
                    break;
                default:
                    break;
            }
        } while (input != 0);
        System.exit(1);

    }

    private static Vehiculo mayorVehiculo() {
        int posMayor = 0;
        for (int i = 1; i < vehiculos.size(); i++) {
            Vehiculo x = vehiculos.get(i);
            if (x.cantidadSensores() > vehiculos.get(posMayor).cantidadSensores()) {
                posMayor = i;
            }
        }
        Vehiculo r =vehiculos.get(posMayor); 
        return r;
    }

    private static void preguntarSensor() {
        System.out.println("Ingrese un ID valido");
        int id = sc.nextInt();
        if (VehiculoIDExiste(id) == true) {
            System.out.println("Ingrese un tipo de sensor");
            String tipo = sc.next();
            System.out.println("Ingrese un valor");
            int valor = sc.nextInt();
            obtenerVehiculoPorID(id).anadirSensor(new Sensor(tipo, valor));
        } else {
            preguntarSensor();
        }
    }

    private static void mostrarSensores() {
        System.out.println("Ingrese un ID valido");
        int id = sc.nextInt();
        if (VehiculoIDExiste(id) == true) {
            System.out.println(vehiculos.get(id).mostrarSensoresVehiculo());
        } else {
            preguntarSensor();
        }
    }

    private static void mostrarSensoresTemp() {
        recolectarSensores();
        for (Sensor x : sensoresTotal) {
            if (x.getTipo().compareToIgnoreCase("Temperatura") == 0) {
                System.out.println(x.toString());
            }
        }
    }

    private static Vehiculo obtenerVehiculoPorID(int id) {
        for (Vehiculo x : vehiculos) {
            if (x.getId() == id) {
                return x;
            } /*
               * else {
               * System.out.println("Ese ID no existe, ingrese otro");
               * id = sc.nextInt();
               * obtenerVehiculoPorID(id);
               * }
               */
        }
        return null;
    }

    private static boolean VehiculoIDExiste(int id) {
        for (Vehiculo x : vehiculos) {
            if (x.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Sensor> ordenarSensores(ArrayList<Sensor> s) {
        ArrayList<Sensor> newSensArr = new ArrayList<Sensor>();
        for (int i = 0; i < s.size(); i++) {
            // if (isTemp(i) == true) {
            newSensArr.add(s.get(i));
            // System.out.println("addNewArr: " + addNewArr);
            // addNewArr += 1;
            // }
        }

        do {

            for (int i = 0; i < s.size() - 1; i++) {
                Sensor x = newSensArr.get(i);
                Sensor y = newSensArr.get(i + 1);
                /*
                 * System.out.println("Position: " + i);
                 * System.out.println("SensArrLength: " + newSensArr.length);
                 * System.out.println("Valor y: " + x.getValor());
                 */
                if ((x.getValor() > y.getValor())) {
                    newSensArr.set(i, y);
                    newSensArr.set(i + 1, x);
                }
            }
        } while (checkOrden(newSensArr) == false);
        return newSensArr;
    }

    public static boolean checkOrden(ArrayList<Sensor> s) {
        boolean r = true;
        for (int i = 0; i < s.size() - 1; i++) {
            Sensor x = s.get(i);
            Sensor y = s.get(i + 1);
            if (x.getValor() > y.getValor()) {
                r = false;
                return r;
            }
        }
        return r;
    }

    public static int numTemp() {
        int r = 0;
        for (int i = 0; i < posAnadirSens - 1; i++) {
            Sensor x = sensoresTotal.get(i);
            if ((x.getTipo()).compareToIgnoreCase("temperatura") == 0) {
                r += 1;
            }
        }
        return r;
    }

    public static boolean isTemp(int i) {
        boolean r = false;
        Sensor x = sensoresTotal.get(i);
        if (x.getTipo().compareToIgnoreCase("Temperatura") == 0) {
            r = true;
        }

        return r;
    }

    public static void showMenu() {
        System.out.println("Seleccione una opcion");
        System.out.println("0: Salir");
        System.out.println("1: Agregar Vehiculo");
        System.out.println("2: Mostrar Vehiculos");
        System.out.println("3: Mostrar numero de vehiculos");
        System.out.println("4: Mostrar vehiculos verdes");
        System.out.println("5: Mostrar informacion de vehiculo por ID");
        System.out.println("6: Agregar sensor a vehiculo por ID");
        System.out.println("7: Mostrar sensores de vehiculo por ID");
        System.out.println("8: Mostrar sensores temperatura");
        System.out.println("9: Mostrar informacion de vehiculo con más sensores");
        System.out.println("10: Importar carros desde archivo");
        System.out.println("666: Mostrar sensores de temperatura ordenados por valor");
    }

    public String toStringVehiculos() {
        String r = "";
        for (Vehiculo x : vehiculos) {
            r += x.toString();
        }
        return r;
    }

    public int cantidadVehiculos() {
        return this.vehiculos.size();
    }

    private static void recolectarSensores() {
        sensoresTotal.clear();
        for (Vehiculo x : vehiculos) {
            for (Sensor y : x.sensores) {
                sensoresTotal.add(y);
            }
        }
    }

}
