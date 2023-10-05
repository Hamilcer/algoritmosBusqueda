package algoritmosbusqueda.busquedaExterna;

import static algoritmosbusqueda.busquedaExterna.EnumerarMatriz.enumerarMatriz;
import static algoritmosbusqueda.busquedaExterna.ExpandirMatrizColision.expandirMatrizColision;
import static algoritmosbusqueda.busquedaExterna.ExpandirMatrizColumna.expandirMatrizColumna;
import static algoritmosbusqueda.busquedaExterna.ReducirMatrizColumna.reducirMatrizColumna;
import java.util.Scanner;

public class BusquedaExterna {

    public BusquedaExterna() {
        menu();
    }

    public void menu() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de filas: ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese el número de columnas: ");
        int columnasIniciales = scanner.nextInt();
        int columnas = columnasIniciales;
        double porcentajeExp = 0;
        while (porcentajeExp < 50 ||porcentajeExp >= 100) {
            System.out.print("Ingrese el porcentaje de expansión por inserción, recuerde que este no puede ser menor al 50%, y no puede ser mayor a 100%: ");
            porcentajeExp = scanner.nextDouble();
        }
        double porcentajeReduccion = 200;
        while (porcentajeReduccion >= 200) {
            System.out.print("Ingrese el porcentaje de reduccion por eliminacion, recuerde que este no puede ser mayor al 200%: ");
            porcentajeReduccion = scanner.nextDouble();
        }
        String[][] matriz = enumerarMatriz(new String[filas + 1][columnas + 1], filas, columnas);
        int caso = 100;
        int numero = 0;
        int totalNumeros = 0;
        while (caso != 0) {
            System.out.print("seleccione una opcion para hacer 1 insertar 2 eliminar, 3 cerrar aplicacion: ");
            caso = scanner.nextInt();
            switch (caso) {
                case 1 -> {
                    numero = this.leerClave();

                    totalNumeros++;

                    int columna = numero % columnas + 1;
                    boolean encontrado = false;

                    // Verificar si el número ya está en la matriz
                    for (int i = 1; i <= filas; i++) {
                        if (matriz[i][columna].equals(String.valueOf(numero))) {
                            System.out.println("¡Número repetido! Este número ya se encuentra ingresado en la matriz.");
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        // Calcular el porcentaje de expansión
                        double expansion = (double) totalNumeros / (filas * columnas) * 100;

                        if (expansion >= 100) {
                            System.out.println("¡Imposible expandir esta estructura! La ocupación alcanzó el 100%");
                            break;
                        } else if (expansion >= porcentajeExp) {
                            System.out.println("Se alcanzó la ocupación máxima (" + expansion + "%). Continuando con la expansión.");
                            matriz = expandirMatrizColumna(matriz, filas, columnas);
                            columnas *= 2;
                        } else {
                            System.out.println("Porcentaje de expansión: " + expansion + "%");
                        }

                        // Encontrar la siguiente fila vacía en la columna correspondiente
                        int fila = 1;
                        while (!matriz[fila][columna].equals(" ")) {
                            fila++;
                            if (fila > filas) {
                                System.out.println("¡Colisión! Se encontró una colisión, se insertará en una nueva fila.");
                                filas++;
                                matriz = expandirMatrizColision(matriz, filas, columnas);
                                break;
                            }
                        }

                        // Insertar el número en la posición encontrada
                        matriz[fila][columna] = String.valueOf(numero);

                        // Imprimir la matriz actualizada
                        for (int i = 0; i <= filas; i++) {
                            for (int j = 0; j <= columnas; j++) {
                                System.out.print(matriz[i][j] + "\t");
                            }
                            System.out.println();
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Has seleccionado la opción 2 eliminar clave");
                    System.out.print("Ingrese la clave a remover: ");
                    int numeroRemover = scanner.nextInt();
                    boolean numeroEncontrado = false;
                    for (int i = 1; i <= filas; i++) {
                        for (int j = 1; j <= columnas; j++) {
                            if (matriz[i][j].equals(String.valueOf(numeroRemover))) {
                                matriz[i][j] = " ";
                                totalNumeros--;
                                numeroEncontrado = true;
                                System.out.println("Número removido exitosamente.");
                                break;
                            }
                        }
                        if (numeroEncontrado) {
                            break;
                        }
                    }

                    // Imprimir mensaje si el número no existe en la matriz
                    if (!numeroEncontrado) {
                        System.out.println("El número no existe en la matriz.");
                    }
                    double porcentajeReduccionActual = (double) totalNumeros / columnas * 100;
                    if (porcentajeReduccionActual <= porcentajeReduccion) {
                        System.out.println("Porcentaje de reducción alcanzado (" + porcentajeReduccionActual + "%). Reduciendo la matriz.");
                        matriz = reducirMatrizColumna(matriz, filas, columnas, columnasIniciales);
                        columnas /= 2;

                        // Imprimir la matriz reducida
                        for (int i = 0; i <= filas; i++) {
                            for (int j = 0; j <= columnas; j++) {
                                System.out.print(matriz[i][j] + "\t");
                            }
                            System.out.println();
                        }
                    }
                    break;
                }
                case 3 -> {
                    System.out.println("Has seleccionado la opción 3 cerrar el programa ");
                    caso = 0;
                }
                default ->
                    System.out.println("Opción inválida. Por favor, ingrese 1, 2 o 3. ");
                // Código a ejecutar para otras opciones
            }
        }

        System.out.println("Programa finalizado.");
    }

    public int leerClave() {
        Scanner scanner = new Scanner(System.in);
        int clave;

        do {
            System.out.print("Ingrese la clave a insertar debe tener 4 cifras: ");
            clave = scanner.nextInt();

            // Limpiar el buffer (por el salto de línea que queda después de usar nextInt())
            scanner.nextLine();
        } while (clave < 1000 || clave > 9999);  // Repite mientras el número no tenga 4 cifras

        return clave;
    }
}
