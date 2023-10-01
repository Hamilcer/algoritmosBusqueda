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

    public BusquedaInterna(int cantidadClaves) {
        System.out.println("Modo busqueda interna");
        this.clavesInsertadas = new int[cantidadClaves];
        this.estructuraClaves = new ListaEnlazada[cantidadClaves];
        funcionHash = new HashInterna(cantidadClaves, this.estructuraClaves);
        colision = new ColisionInterna(this.estructuraClaves);
        leerClaves();

    }

    public void leerClaves() {

        while (this.numClavesInsertadas < this.estructuraClaves.length) {
            System.out.println("Inserte la clave " + (this.numClavesInsertadas + 1) + ": ");
            int clave = leerClave();
            int posicion = this.funcionHash.aplicarHash(clave);
            this.insertarClave(posicion, clave);
        }
    }

    public int leerClave() {
        int clave;

        do {
            System.out.print("Por favor, ingresa un número de 4 cifras: ");
            clave = scanner.nextInt();

            // Limpiar el buffer (por el salto de línea que queda después de usar nextInt())
            scanner.nextLine();

        } while (clave < 1000 || clave > 9999);  // Repite mientras el número no tenga 4 cifras

        this.clavesInsertadas[this.numClavesInsertadas] = clave;
        return clave;
    }

    public void insertarClave(int posicion, int clave) {
        if (this.estructuraClaves[posicion] == null) {
            this.estructuraClaves[posicion] = new ListaEnlazada();
            this.estructuraClaves[posicion].insertarAlFinal(clave);
        } else {
            this.colision.solucionar(posicion, clave);
        }
        this.numClavesInsertadas++;
        this.imprimirArreglo();
    }

    public void imprimirArreglo() {
        for (int i = 0; i < this.estructuraClaves.length; i++) {
            System.out.print((i+1) + " - ");
            if (this.estructuraClaves[i] == null) {
                System.out.println("0");
            } else {
                this.estructuraClaves[i].imprimir();
                System.out.println();
            }
        }
    }

}
