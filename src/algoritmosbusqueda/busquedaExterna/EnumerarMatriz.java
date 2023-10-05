package algoritmosbusqueda.busquedaExterna;

public class EnumerarMatriz {

    public static String[][] enumerarMatriz(String[][] matriz, int filas, int columnas) {
        for (int i = 0; i <= filas; i++) {
            for (int j = 0; j <= columnas; j++) {
                if (i == 0 && j == 0) {
                    matriz[i][j] = " ";
                } else if (i == 0) {
                    matriz[i][j] = String.valueOf(j - 1);
                } else if (j == 0) {
                    matriz[i][j] = String.valueOf(i);
                } else {
                    matriz[i][j] = " ";
                }
            }
        }
        return matriz;
    }
}
