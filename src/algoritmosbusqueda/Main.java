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
        System.out.println("Que tipo de busqueda desea implementar 1 interna 2 externa");
        int tipoBusqueda = scanner.nextInt();
        int caso = 2;
        while(caso != 0){
        switch (tipoBusqueda) {
            case 1 -> {
                BusquedaInterna interna = new BusquedaInterna();
            }
            case 2 -> {
                BusquedaExterna externa = new BusquedaExterna();
            }
            case 3 -> {
                System.out.println("escogio finalizar programa");
                caso = 0;
            }
            default -> System.out.println("no incerto un numero del menu");
        }
        System.out.println("Que tipo de busqueda desea implementar 1 interna 2 externa");
        tipoBusqueda = scanner.nextInt();
    }
    }
}
