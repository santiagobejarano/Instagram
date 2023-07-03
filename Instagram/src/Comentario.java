class Comentario {
    private String contenido;
    private Usuario autor;


    public Comentario(Usuario autor,  String contenido) {
        this.autor = autor;
        this.contenido = contenido;

    }

    public String getContenido() {
        return contenido;
    }

    public Usuario getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return
                "\nContenido del comentario : " + contenido+
         "\nAutor del comentario : " + autor.getNombre();

    }
}