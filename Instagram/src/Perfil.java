import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
class Perfil {
    private Usuario usuario;
    private ArrayList<Publicacion> publicaciones;
    private int numSeguidores;
    private ArrayList<Usuario> seguidores;

    public Perfil(Usuario usuario) {
        this.usuario = usuario;
        this.publicaciones = new ArrayList<>();
        this.seguidores = new ArrayList<>();
        this.numSeguidores = 0;
    }

    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
    }

    public void mostrarContenidoPublicaciones() throws IOException {
        int i = 1;

        /*
        for (Publicacion publicacion : publicaciones) {
            publicacion.mostrar();
        }
        */
        for (Publicacion publicacion : publicaciones) {
            System.out.println("--------Publicación--------"+i);
             // Reemplaza con la ruta de la foto que se muestra en la consola
            String rutaFoto = publicacion.getRutaFoto();
            File foto = new File(rutaFoto);
            System.out.println(publicacion.toString());
            Desktop.getDesktop().open(foto);
            i++;
        }

    }

    public void incrementarSeguidores() {
        numSeguidores++;
    }

    public ArrayList<Usuario> getSeguidores() {
        return seguidores;
    }

    public int getCantidadPublicaciones() {
        return publicaciones.size();
    }



    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void agregarSeguidor(Usuario seguidor) {
        for (Usuario seguidores : seguidores) {
            if (seguidores.equals(seguidor)) {
                System.out.println("Ya sigues a "+ seguidor.getNombre());
                return;
            }



    }
        seguidores.add(seguidor);
        incrementarSeguidores();
        System.out.println("Has comenzado a seguir a " + seguidor.getNombre());

    }

    public String toString() {
        return "Perfil de " + usuario.getNombre() +
                "\nSeguidores: " + numSeguidores +
                "\nCantidad de publicaciones: " + publicaciones.size();
    }


    public void mostrarSeguidores(List<Usuario> seguidores) {
        System.out.println("-----SEGUIDORES-----");
        for (int i = 0; i < seguidores.size(); i++) {
            Usuario seguidor = seguidores.get(i);
            System.out.println("Seguidor " + (i + 1) + ": " + seguidor.getNombre());
        }
    }



}