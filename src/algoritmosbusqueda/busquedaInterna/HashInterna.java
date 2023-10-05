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
    }

    public void elegirFuncionHash() {
        System.out.println("Elige la funcion hash:\n1. Modulo\n2. Cuadrado\n3. Truncamiento\n4. Plegamiento");
        do {
            while (!scanner.hasNextInt()) { // Si el input no es un número entero
                System.out.println("Eso no es un número válido. Inténtalo de nuevo.");
                scanner.next(); // Descarta el input anterior
            }
            this.opcionHash = scanner.nextInt();
        } while (this.opcionHash < 1 || this.opcionHash > 4);

        if (this.opcionHash == 1) {
            this.rango = this.calcularRango(this.estructuraClaves.length);
            System.out.println("Valor para funcion modulo: " + rango);
        }
        System.out.println("En el caso de un valor hash mayor al tamaño de la estructura \nse aplicara la funcion modulo utilizando el tamaño de la estructura");
    }

    public void elegirFuncionHash(int opcion) {
        this.opcionHash = opcion;

    }

    public int aplicarHash(int clave) {
        switch (this.opcionHash) {
            case 1:
                return hashModulo(clave);
            case 2:
                return this.hashCuadrado(clave);
            case 3:
                return this.hashTruncamiento(clave);
            case 4:
                return this.hashPlegamiento(clave);

        }
        return 0;
    }

    public int calcularRango(int cantidadClaves) {
        /*if (cantidadClaves < 2) {
            return -1; // No hay números primos menores que 2
        }

        int numero = cantidadClaves - 1; // Empezamos buscando desde el número anterior al dado
        while (numero > 1) {
            if (esPrimo(numero)) {
                return numero; // Devolvemos el primer primo encontrado
            }
            numero--;
        }
        return -1;*/
        int numero = cantidadClaves + 1;
        while (!esPrimo(numero)) {
            numero++;
        }
        return numero;
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
        int hashValue = (clave % rango);
        hashValue = (hashValue >= this.estructuraClaves.length) ? hashValue % this.estructuraClaves.length : hashValue;
        return hashValue;
    }

    public int hashCuadrado(int clave) {
        String cuadrado = String.valueOf(clave * clave);
        int hashValue = 0;

        if (cuadrado.length() > 7) {
            hashValue = Integer.parseInt(cuadrado.substring(3, 4) + cuadrado.subSequence(4, 5));
        } else {
            hashValue = Integer.parseInt(cuadrado.substring(2, 3) + cuadrado.subSequence(3, 4));
        }

        hashValue = (hashValue >= this.estructuraClaves.length) ? hashValue % this.estructuraClaves.length : hashValue;
        return hashValue;
    }

    public int hashPlegamiento(int clave) {
        int hashValue = (clave / 100) + (clave % 100);
        hashValue = (hashValue >= this.estructuraClaves.length) ? hashValue % this.estructuraClaves.length : hashValue;
        return hashValue;
    }

    public int hashTruncamiento(int clave) {
        int hashValue = (clave / 10) % 100;
        hashValue = (hashValue >= this.estructuraClaves.length) ? hashValue % this.estructuraClaves.length : hashValue;
        return hashValue;
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
