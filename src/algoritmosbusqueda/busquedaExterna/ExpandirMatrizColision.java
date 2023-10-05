package algoritmosbusqueda.busquedaExterna;

import java.util.Arrays;

public class ExpandirMatrizColision {

    public static String[][] expandirMatrizColision(String[][] matriz, int filas, int columnas) {
        matriz = Arrays.copyOf(matriz, filas + 1);
        matriz[filas] = new String[columnas + 1];
        for (int i = 0; i <= columnas; i++) {
            matriz[filas][i] = " ";
        }
        return matriz;
    }

}
