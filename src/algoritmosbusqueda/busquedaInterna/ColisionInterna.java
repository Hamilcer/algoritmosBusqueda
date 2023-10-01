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

    public ColisionInterna(ListaEnlazada[] estructuraClaves) {
        this.estructuraClaves = estructuraClaves;
        this.elegirColision();
    }

    public void elegirColision() {
        System.out.println("Elige el metodo para solucionar colisiones: \n1.Lienal");
        this.opcionColision = this.scanner.nextInt();
    }

    public void solucionar(int posicion, int clave) {
        System.out.println("Colision !!!");

        switch (this.opcionColision) {
            case 1:
                this.solucionarLineal(posicion, clave);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
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
}
