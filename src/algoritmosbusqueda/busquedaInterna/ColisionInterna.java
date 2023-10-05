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
public class ColisionInterna {

    Scanner scanner = new Scanner(System.in);
    ListaEnlazada[] estructuraClaves;
    int opcionColision;
    int rango;

    public ColisionInterna(ListaEnlazada[] estructuraClaves, int rango) {
        this.estructuraClaves = estructuraClaves;
        this.elegirColision();
        this.rango = rango;
    }

    public void elegirColision() {
        System.out.println("Elige el metodo para solucionar colisiones: \n1.Lienal\n2.Cuadratica\n3.Doble hash\n4.Arreglos anidados\n5.Listas Enlazadas");
        this.opcionColision = this.scanner.nextInt();
        if (this.opcionColision == 4) {
            this.llenarCeros();
        }
    }

    public void llenarCeros() {
        for (int i = 0; i < this.estructuraClaves.length; i++) {
            this.estructuraClaves[i] = new ListaEnlazada();
            for (int j = 0; j < this.estructuraClaves.length; j++) {
                this.estructuraClaves[i].insertarAlFinal(0);
            }
        }
    }

    public void solucionar(int posicion, int clave) {
        System.out.println("Colision !!!");

        switch (this.opcionColision) {
            case 1:
                this.solucionarLineal(posicion, clave);
                break;
            case 2:
                this.solucionarCuadratica(posicion, clave);
                break;
            case 3:
                this.solucionarHash(posicion, clave);
                break;
            case 4:
                this.solucionarArreglosAnidados(posicion, clave);
                break;
            case 5:
                this.solucionarListasEnlazadas(posicion, clave);
                break;

        }
    }

    public void solucionarLineal(int posicion, int clave) {
        while (this.estructuraClaves[posicion] != null) {
            posicion = (posicion + 1 >= this.estructuraClaves.length) ? 0 : ++posicion;
            System.out.println("Nueva posicion: " + (posicion + 1));
        }
        this.estructuraClaves[posicion] = new ListaEnlazada();
        this.estructuraClaves[posicion].insertarAlFinal(clave);
    }

    public void solucionarCuadratica(int posicion, int clave) {
        int i = 1;
        while (this.estructuraClaves[posicion] != null) {
            posicion = (posicion + i * i >= this.estructuraClaves.length) ? 0 : posicion + i * i;
            System.out.println("Nueva posicion: " + (posicion + 1));
            i++;
        }
        this.estructuraClaves[posicion] = new ListaEnlazada();
        this.estructuraClaves[posicion].insertarAlFinal(clave);
    }

    public void solucionarHash(int posicion, int clave) {
        while (this.estructuraClaves[posicion] != null) {
            int nuevaPosicion = (posicion + 1) % this.estructuraClaves.length;
            posicion = (nuevaPosicion >= this.estructuraClaves.length) ? 0 : nuevaPosicion;
            System.out.println("Nueva posicion: " + (posicion + 1));

        }
        this.estructuraClaves[posicion] = new ListaEnlazada();
        this.estructuraClaves[posicion].insertarAlFinal(clave);
    }

    public void solucionarArreglosAnidados(int posicion, int clave) {
        Nodo actual = this.estructuraClaves[posicion].getCabeza();
        while (actual.dato != 0) {
            actual = actual.siguiente;
        }
        actual.dato = clave;
    }

    public void solucionarListasEnlazadas(int posicion, int clave) {
        this.estructuraClaves[posicion].insertarAlFinal(clave);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ListaEnlazada[] getEstructuraClaves() {
        return estructuraClaves;
    }

    public void setEstructuraClaves(ListaEnlazada[] estructuraClaves) {
        this.estructuraClaves = estructuraClaves;
    }

    public int getOpcionColision() {
        return opcionColision;
    }

    public void setOpcionColision(int opcionColision) {
        this.opcionColision = opcionColision;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }
    
}
