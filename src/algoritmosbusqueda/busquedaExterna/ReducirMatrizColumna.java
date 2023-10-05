package algoritmosbusqueda.busquedaExterna;

import static algoritmosbusqueda.busquedaExterna.EnumerarMatriz.enumerarMatriz;



public class ReducirMatrizColumna {

    public static String[][] reducirMatrizColumna(String[][] matriz, int filas, int columnas, int columnasIniciales) {
        int nuevasColumnas = columnas / 2;
        if (nuevasColumnas <= columnasIniciales) {
            System.out.println("No se puede reducir más la matriz inicial dada");
            nuevasColumnas = columnasIniciales;
        }

        String[][] nuevaMatriz = enumerarMatriz(new String[filas + 1][nuevasColumnas + 1], filas, nuevasColumnas);

        // Imprimir la matriz original
        for (int i = 0; i <= filas; i++) {
            for (int j = 0; j <= columnas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }

        for (int i = 1; i <= filas; i++) {
            for (int j = 1; j <= columnas; j++) {
                if (!matriz[i][j].equals(" ")) {
                    int numero = Integer.parseInt(matriz[i][j]);
                    int nuevaColumna = numero % nuevasColumnas+1;
                    nuevaMatriz[i][nuevaColumna] = String.valueOf(numero);
                }
            }
        }

        double ocupacion = 0;
        int totalNumeros = 0;
        for (int i = 1; i <= filas; i++) {
            for (int j = 0; j < nuevasColumnas; j++) {
                if (nuevaMatriz[i][j] != null) {
                    totalNumeros++;
                }
            }
        }
        ocupacion = (double) totalNumeros / (filas * nuevasColumnas) * 100;
        System.out.println("Nueva matriz con " + filas + " filas y " + nuevasColumnas + " columnas.");
        System.out.println("Porcentaje de ocupación después de reducción: " + ocupacion + "%");
        return nuevaMatriz;
    }
}