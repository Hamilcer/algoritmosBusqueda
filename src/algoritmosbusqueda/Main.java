/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmosbusqueda;

import algoritmosbusqueda.busquedaExterna.BusquedaExterna;
import algoritmosbusqueda.busquedaInterna.BusquedaInterna;
import java.util.Scanner;

/**
 *
 * @author hamil
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que tipo de busqueda desea implementar \n1. interna \n2. externa\n3. Finalizar programa");
        int tipoBusqueda = scanner.nextInt();
        while (tipoBusqueda != 3) {
            switch (tipoBusqueda) {
                case 1 -> {
                    BusquedaInterna interna = new BusquedaInterna();
                }
                case 2 -> {
                    BusquedaExterna externa = new BusquedaExterna();
                }
                default ->
                    System.out.println("Ingrese un numero valido");
            }
            System.out.println("Que tipo de busqueda desea implementar \n1. interna \n2. externa\n3. Finalizar programa");
            tipoBusqueda = scanner.nextInt();
        }
    }
}
