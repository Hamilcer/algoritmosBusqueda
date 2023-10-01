package algoritmosbusqueda.busquedaInterna;

class ListaEnlazada {

    private Nodo cabeza = null;
    private int longitud = 0;

    public void insertarAlInicio(int dato) {
        Nodo nuevoNodo = new Nodo(dato, 0);
        if (cabeza != null) {
            Nodo actual = cabeza;
            while (actual != null) {
                actual.posicion++;
                actual = actual.siguiente;
            }
        }
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
        longitud++;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevoNodo = new Nodo(dato, longitud);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        longitud++;
    }

    public void imprimir() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato);
            actual = actual.siguiente;
            if(actual!=null){
                System.out.println(") -> ");
            }
        }
    }

    public int obtenerLongitud() {
        return longitud;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    
    
}
