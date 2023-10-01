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
        System.out.println("Cantidad de claves a insertar: ");
        int cantidadClaves = scanner.nextInt();
        
        if(cantidadClaves>99)
        {
            BusquedaExterna externa = new BusquedaExterna(cantidadClaves);
        }
        else{
            BusquedaInterna interna = new BusquedaInterna(cantidadClaves);
        }
    }
    
}
