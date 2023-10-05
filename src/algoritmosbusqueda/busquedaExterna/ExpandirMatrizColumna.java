package algoritmosbusqueda.busquedaExterna;

import static algoritmosbusqueda.busquedaExterna.EnumerarMatriz.enumerarMatriz;


public class ExpandirMatrizColumna {

    public static String[][] expandirMatrizColumna(String[][] matriz, int filas, int columnas) {
        // Duplicar el número de columnas
        int nuevasColumnas = columnas * 2;
        String[][] nuevaMatriz = enumerarMatriz(new String[filas + 1][nuevasColumnas + 1], filas, nuevasColumnas);

        // Insertar los números en la nueva matriz usando el mod para encontrar las nuevas columnas
        for (int i = 1; i <= filas; i++) {
            for (int j = 1; j <= columnas; j++) {
                if (!matriz[i][j].equals(" ")) {
                    int numero = Integer.parseInt(matriz[i][j]);
                    int nuevaColumna = numero % nuevasColumnas+1;
                    nuevaMatriz[i][nuevaColumna] = String.valueOf(numero);
                }
            }
        }

        // Calcular el nuevo porcentaje de expansión
        double expansion = 0;
        int totalNumeros = 0;
        for (int i = 1; i <= filas; i++) {
            for (int j = 0; j < nuevasColumnas; j++) {
                if (!nuevaMatriz[i][j].equals(" ")) {
                    totalNumeros++;
                }
            }
        }
        expansion = (double) totalNumeros / (filas * nuevasColumnas) * 100;
        System.out.println("Nueva matriz con " + filas + " filas y " + nuevasColumnas + " columnas.");
        System.out.println("Porcentaje de expansión: " + expansion + "%");

        return nuevaMatriz;
    }
}
