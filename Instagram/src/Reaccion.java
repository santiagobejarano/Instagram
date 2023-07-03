class Reaccion {
    private Tipo tipo;
    private Usuario autor;


    public Reaccion(Usuario autor, Publicacion publicacion) {
        this.autor = autor;
        tipo = Tipo.LIKE;
        //autor.notificarReaccion(this);
    }

    @Override
    public String toString() {
        return "Reacción: " +
                "\nTipo: " + tipo +
                "\nAutor: " + autor.getNombre();
    }


}
