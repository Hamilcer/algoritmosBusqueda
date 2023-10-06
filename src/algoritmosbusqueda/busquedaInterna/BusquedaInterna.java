/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmosbusqueda.busquedaInterna;

import java.util.Scanner;

/**
 *
 * @author hamil
 */
public class BusquedaInterna {

    Scanner scanner = new Scanner(System.in);
    HashInterna funcionHash;
    ColisionInterna colision;
    ListaEnlazada[] estructuraClaves;
    int[] clavesInsertadas; //Se usa en el caso de cambiar funcion hash
    int numClavesInsertadas = 0;

    public BusquedaInterna() {
        System.out.println("Modo busqueda interna");
        menu();

    }

    public void menu() {
        int cantidadClaves = this.solicitarPotenciaDeDiez();
        this.clavesInsertadas = new int[cantidadClaves];
        this.estructuraClaves = new ListaEnlazada[cantidadClaves];
        funcionHash = new HashInterna(cantidadClaves, this.estructuraClaves);
        colision = new ColisionInterna(this.estructuraClaves, this.funcionHash.getRango());

        while (true) {
            System.out.println("1. Insertar Clave\n2. Cambiar funcion hash\n3. Realizar busqueda\n4. Imprimir estructura \n5. Salir");
            int opcionMenu = scanner.nextInt();
            if (opcionMenu == 1) {
                agregarClave();
            } else if (opcionMenu == 2) {
                this.cambiarFuncionHash();
            } else if (opcionMenu == 3) {
                this.buscarClave(leerClave());
            } else if (opcionMenu == 4) {
                this.imprimirArreglo();
            } else if (opcionMenu == 5) {
                break;
            }
        }
    }

    public int solicitarPotenciaDeDiez() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        boolean esPotenciaDeDiez;
        System.out.println("Primero se va a definir la cantidad de claves a insertar");
        do {
            System.out.println("Ingrese un número que sea potencia de 10:");
            try {
                numero = scanner.nextInt();
                esPotenciaDeDiez = esPotenciaDeDiez(numero);
                if (!esPotenciaDeDiez) {
                    System.out.println("El número ingresado no es una potencia de 10. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();  // Limpia el buffer
                esPotenciaDeDiez = false;
            }
        } while (!esPotenciaDeDiez);

        return numero;
    }

    public boolean esPotenciaDeDiez(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1) {
            if (n % 10 != 0) {
                return false;
            }
            n /= 10;
        }
        return true;
    }

    public void agregarClave() {

        if (this.numClavesInsertadas < this.estructuraClaves.length) {
            System.out.println("Inserte la clave " + (this.numClavesInsertadas + 1) + ": ");
            int clave = leerClave();
            int posicion = this.funcionHash.aplicarHash(clave);
            this.insertarClave(posicion, clave);
            this.numClavesInsertadas++;

        } else {
            System.out.println("El arreglo está lleno");
        }
    }

    public void agregarClave(int clave) {
        int posicion = this.funcionHash.aplicarHash(clave);
        this.insertarClave(posicion, clave);
    }

    public int leerClave() {
        int clave;

        boolean seguir = true;
        do {
            System.out.print("Por favor, ingresa un número de 4 cifras: ");
            clave = scanner.nextInt();

            // Limpiar el buffer (por el salto de línea que queda después de usar nextInt())
            scanner.nextLine();
            seguir = this.verificarExiste(clave);
        } while (clave < 1000 || clave > 9999 || seguir);  // Repite mientras el número no tenga 4 cifras
        if (!(this.numClavesInsertadas >= this.estructuraClaves.length)) {
            this.clavesInsertadas[this.numClavesInsertadas] = clave;
        }
        return clave;
    }

    public boolean verificarExiste(int clave) {
        for (int i : this.clavesInsertadas) {
            if (i == clave) {
                System.out.println("¡Número repetido! Este número ya se encuentra ingresado.");
                return true;
            }
        }
        return false;
    }

    public void insertarClave(int posicion, int clave) {
        if (this.estructuraClaves[posicion] == null) {
            this.estructuraClaves[posicion] = new ListaEnlazada();
            this.estructuraClaves[posicion].insertarAlFinal(clave);
        } else if (this.estructuraClaves[posicion].getCabeza().dato == 0) {
            this.estructuraClaves[posicion].getCabeza().dato = clave;
        } else {
            this.colision.solucionar(posicion, clave);
        }
        //this.imprimirArreglo();
    }

    public void cambiarFuncionHash() {
        this.estructuraClaves = new ListaEnlazada[this.estructuraClaves.length];
        this.colision.setEstructuraClaves(this.estructuraClaves);
        this.funcionHash = new HashInterna(this.estructuraClaves.length, this.estructuraClaves);
        System.out.println("1. Utilizar claves ya insertadas\n2. NO utilizar claves ya insertadas");
        int opcionHash = scanner.nextInt();
        System.out.println("----------------------------------------");
        System.out.println("---------------Cambiando...-------------");
        if (opcionHash == 1) {
            for (int clave : this.clavesInsertadas) {
                System.out.println("Clave: " + clave);
                if (clave == 0) {
                    break;
                }
                this.agregarClave(clave);
            }
        } else {
            this.clavesInsertadas = new int[this.estructuraClaves.length];
            this.numClavesInsertadas = 0;
        }

    }

    public void buscarClave(int clave) {
        int posicion = this.funcionHash.aplicarHash(clave);
        if (posicion >= this.estructuraClaves.length) {
            posicion = 0;
        }
        if (this.estructuraClaves[posicion] == null) {
            System.out.println("La clave no esta isnertada");
        } else if (this.colision.opcionColision == 1) { //secuencial
            int maxBusqueda = 0; //Con esto se controla el maximo de busquedas
            while (this.estructuraClaves[posicion] != null && this.estructuraClaves[posicion].getCabeza().dato != 0) {
                if (this.estructuraClaves[posicion].getCabeza().dato == clave) {
                    System.out.println("La clave se encuentra en la posicion: " + (posicion + 1));
                    return;
                }
                maxBusqueda++;
                if (maxBusqueda == this.estructuraClaves.length) {
                    break;
                }
                posicion = (posicion + 1 >= this.estructuraClaves.length) ? 0 : ++posicion;
            }
        } else if (this.colision.opcionColision == 2) { //cuadratica
            int maxBusqueda = 0; //Con esto se controla el maximo de busquedas
            int i = 1;
            while (this.estructuraClaves[posicion] != null && this.estructuraClaves[posicion].getCabeza().dato != 0) {
                if (this.estructuraClaves[posicion].getCabeza().dato == clave) {
                    System.out.println("La clave se encuentra en la posicion: " + (posicion + 1));
                    return;
                }
                maxBusqueda++;
                if (maxBusqueda == this.estructuraClaves.length) {
                    break;
                }
                posicion = (posicion + i * i >= this.estructuraClaves.length) ? 0 : posicion + i * i;
                i++;

            }
        } else if (this.colision.opcionColision == 3) { //doble hash
            int maxBusqueda = 0; //Con esto se controla el maximo de busquedas
            while (this.estructuraClaves[posicion] != null && this.estructuraClaves[posicion].getCabeza().dato != 0) {

                if (this.estructuraClaves[posicion].getCabeza().dato == clave) {
                    System.out.println("La clave se encuentra en la posicion: " + (posicion + 1));
                    return;
                }
                maxBusqueda++;
                if (maxBusqueda == this.estructuraClaves.length) {
                    break;
                }
                int nuevaPosicion = (posicion + 1) % this.estructuraClaves.length;
                posicion = (nuevaPosicion >= this.estructuraClaves.length) ? 0 : nuevaPosicion;

            }
        } else if (this.colision.opcionColision == 4 || this.colision.opcionColision == 5) {
            Nodo actual = this.estructuraClaves[posicion].getCabeza();
            while (actual != null) {
                if (actual.dato == clave) {
                    System.out.println("La clave se encuentra en la posicion: " + (posicion + 1));
                    return;
                }
                actual = actual.siguiente;
            }

        }

        System.out.println("Clave no encontrada.");
    }

    public void imprimirArreglo() {
        for (int i = 0; i < this.estructuraClaves.length; i++) {
            System.out.print((i + 1) + " - ");
            if (this.estructuraClaves[i] == null) {
                System.out.println("0");
            } else {
                this.estructuraClaves[i].imprimir();
                System.out.println();
            }
        }
    }

}
