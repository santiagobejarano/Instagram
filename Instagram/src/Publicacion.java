import java.util.ArrayList;

class Publicacion {
    private Usuario autor;
    private String Rutafoto;
    private String descripcion;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Reaccion> reacciones;
    private ArrayList<Comparticion> comparticiones;

    public Publicacion(Usuario autor, String fotos, String descripcion) {
        this.autor = autor;
        this.Rutafoto = fotos;
        this.descripcion = descripcion;
        this.comentarios = new ArrayList<>();
        this.reacciones = new ArrayList<>();
        this.comparticiones = new ArrayList<>();

    }

    public Usuario getAutor() {
        return autor;
    }

    public String getRutaFoto() {
        return Rutafoto;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void mostrarComentarios() {
        System.out.println("Comentarios de la publicación:");

        for (Comentario comentario : comentarios) {
            System.out.println(comentario.toString());
        }
    }

    public void agregarReaccion(Reaccion reaccion) {
        reacciones.add(reaccion);
    }

    public void agregarComparticion(Comparticion comparticion) {
        comparticiones.add(comparticion);
    }


    public void mostrarReaccionadores() {

        for (Reaccion reaccion : reacciones) {
            System.out.println(reaccion.toString());
        }

    }

    @Override
    public String toString() {

        return "Publicación de " + autor.getNombre() +
                "\nFoto: " +  Rutafoto+
                "\nDescripción: " + descripcion +
                "\nComentarios: " + comentarios.size() +
                "\nReacciones: " + reacciones.size() +
                "\nComparticiones: " + comparticiones.size();
    }
}
