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
public class HashInterna {

    Scanner scanner = new Scanner(System.in);
    ListaEnlazada[] estructuraClaves;

    int opcionHash;
    int rango;

    public HashInterna(int cantidadClaves, ListaEnlazada[] estructuraClaves) {
        this.estructuraClaves = estructuraClaves;
        this.elegirFuncionHash();
        this.rango = this.calcularRango(cantidadClaves);
        System.out.println("Rango: " + rango);
    }

    public void elegirFuncionHash() {
        System.out.println("Elige la funcion hash:\n1. Modulo");
        this.opcionHash = scanner.nextInt();
    }

    public int aplicarHash(int clave) {
        switch (this.opcionHash) {
            case 1:
                return hashModulo(clave);
            case 2:
                return 0;

        }
        return 0;
    }

    public int calcularRango(int cantidadClaves) {
        if (cantidadClaves < 2) {
            return -1; // No hay números primos menores que 2
        }

        int numero = cantidadClaves - 1; // Empezamos buscando desde el número anterior al dado
        while (numero > 1) {
            if (esPrimo(numero)) {
                return numero; // Devolvemos el primer primo encontrado
            }
            numero--;
        }
        return -1;
    }

    public static boolean esPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }

    public int hashModulo(int clave) {
        int posicion = (clave % rango);
        System.out.println("Posicion en la estructura: " + (posicion+1));
        return posicion;
    }

    public int getOpcionHash() {
        return opcionHash;
    }

    public void setOpcionHash(int opcionHash) {
        this.opcionHash = opcionHash;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

}
