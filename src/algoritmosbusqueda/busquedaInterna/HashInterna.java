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
public class HashInterna {

    Scanner scanner = new Scanner(System.in);
    ListaEnlazada[] estructuraClaves;

    int opcionHash;
    int rango;

    public HashInterna(int cantidadClaves, ListaEnlazada[] estructuraClaves) {
        this.estructuraClaves = estructuraClaves;
        this.elegirFuncionHash();
        this.rango = estructuraClaves.length;
    }

    public void elegirFuncionHash() {
        System.out.println("Elige la funcion hash:\n1. Modulo\n2. Cuadrado\n3. Truncamiento\n4. Plegamiento");
        do {
            while (!scanner.hasNextInt()) { // Si el input no es un número entero
                System.out.println("Eso no es un número válido. Inténtalo de nuevo.");
                scanner.next(); // Descarta el input anterior
            }
            this.opcionHash = scanner.nextInt();
        } while (this.opcionHash < 1 || this.opcionHash > 4);

        if (this.opcionHash == 1) {
            this.rango = this.calcularRango(this.estructuraClaves.length, this.leerRango());
        } else {
            this.rango = this.calcularRango(this.estructuraClaves.length, 3);
        }

        System.out.println("Valor rango: " + rango);

    }

    public int leerRango() {
        int numero;

        while (true) {
            System.out.println("Ahora elige el valor del rango: \n1. Numero primo mas cercano menor\n2. Numero primo mas cercano mayor\n3. Usar cantidad de claves a insertar");
            try {
                numero = scanner.nextInt();

                if (numero == 1 || numero == 2 || numero == 3) {
                    return numero;  // Si es válido, retorna el número
                } else {
                    System.out.println("Número no válido. Por favor, ingrese 1, 2 o 3.");
                }

            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número entero válido.");
                scanner.nextLine();  // Limpia el buffer
            }
        }
    }

    public void elegirFuncionHash(int opcion) {
        this.opcionHash = opcion;

    }

    public int aplicarHash(int clave) {
        switch (this.opcionHash) {
            case 1:
                return hashModulo(clave);
            case 2:
                return this.hashCuadrado(clave);
            case 3:
                return this.hashTruncamiento(clave);
            case 4:
                return this.hashPlegamiento(clave);

        }
        return 0;
    }

    public int calcularRango(int cantidadClaves, int opcion) {
        int numero = 0;
        switch (opcion) {
            case 1:
                if (cantidadClaves < 2) {
                    return -1; // No hay números primos menores que 2
                }

                numero = cantidadClaves - 1; // Empezamos buscando desde el número anterior al dado
                while (numero > 1) {
                    if (esPrimo(numero)) {
                        return numero; // Devolvemos el primer primo encontrado
                    }
                    numero--;
                }
                return -1;

            case 2:
                numero = cantidadClaves + 1;
                while (!esPrimo(numero)) {
                    numero++;
                }
                break;
            case 3:
                numero = cantidadClaves;
                break;
        }
        return numero;
    }

    public static boolean esPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }

    public int hashModulo(int clave) {
        int hashValue = (clave % rango);
        hashValue = (hashValue >= this.estructuraClaves.length) ? 0 : hashValue;

        System.out.println("Posicion en la estructura: " + (hashValue + 1));
        return hashValue;
    }

    public int hashCuadrado(int clave) {
        long cuadrado = (long) clave * clave;  // Usamos long para evitar desbordamiento

        int digitos = this.contarCeros(this.rango);
        String cuadradoStr = String.valueOf(cuadrado);

        // Comprobamos si el cuadrado tiene menos dígitos que lo requerido
        if (cuadradoStr.length() < digitos) {
            return (int) cuadrado;  // Simplemente retornamos el cuadrado si es demasiado corto
        }

        // Tomamos los dígitos del medio
        int inicio = (cuadradoStr.length() - digitos) / 2;
        int fin = inicio + digitos;
        String subconjunto = cuadradoStr.substring(inicio, fin);
        int hashValue = Integer.parseInt(subconjunto);
        System.out.println("Posicion en la estructura: " + (hashValue + 1));

        return Integer.parseInt(subconjunto);
    }

    public int hashPlegamiento(int c) {
        String clave = Integer.toString(c);
        int segmentoSize = this.contarCeros(this.rango);
        int hashValue = 0;

        for (int i = 0; i < clave.length(); i += segmentoSize) {
            String segmento;
            if (i + segmentoSize <= clave.length()) {
                segmento = clave.substring(i, i + segmentoSize);
            } else {
                segmento = clave.substring(i);
            }
            hashValue += Integer.parseInt(segmento);
        }
        hashValue = (hashValue >= this.rango) ? hashValue % this.rango : hashValue;
        System.out.println("Posicion en la estructura: " + (hashValue + 1));
        return hashValue;
    }

    public int hashTruncamiento(int clave) { 
        int digitos = this.contarCeros(this.rango);
        String strClave = Integer.toString(clave);
        if (strClave.length() < digitos) {
            System.out.println("Posicion en la estructura: " + (clave + 1));
            return clave;
        }

        int inicio = (strClave.length() - digitos) / 2;
        int fin = inicio + digitos;
        String subconjunto = strClave.substring(inicio, fin);
        int hashValue = Integer.parseInt(subconjunto);
        System.out.println("Posicion en la estructura: " + (hashValue + 1));

        return hashValue;
    }

    public int getOpcionHash() {
        return opcionHash;
    }

    public void setOpcionHash(int opcionHash) {
        this.opcionHash = opcionHash;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int contarCeros(int potenciaDeDiez) {
        int contador = 0;
        while (potenciaDeDiez > 1) {
            contador++;
            potenciaDeDiez /= 10;
        }
        return contador;
    }
}
