package algoritmosbusqueda.busquedaInterna;

class Nodo {
    int dato;
    int posicion;
    Nodo siguiente;

    public Nodo(int dato, int posicion) {
        this.dato = dato;
        this.posicion = posicion;
        this.siguiente = null;
    }
}