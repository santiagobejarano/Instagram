import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Usuario {
    private String nombre;
    private String contraseña;
    private Perfil perfil;
    private static List<Usuario> listaUsuarios = new ArrayList<>();

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.perfil = new Perfil(this);
        listaUsuarios.add(this);
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void agregarPublicacion() throws IOException {
        Scanner scanner = new Scanner(System.in);

        File[] archivos = obtenerArchivosDirectorio("C:\\Users\\Dell\\OneDrive - Escuela Politécnica Nacional\\Escritorio\\EPN\\SEMESTRE 4\\ING. SOFTWARE\\Instagram\\Images"); // (Editable) Directorio de imágenes

        if (archivos.length == 0) {
            System.out.println("No se encontraron imágenes en el directorio especificado.");
            return;
        }

        System.out.println("Rutas de imágenes disponibles:");

        for (int i = 0; i < archivos.length; i++) {
            System.out.println((i + 1) + ". " + archivos[i].getPath());
        }

        System.out.print("Seleccione el número de la imagen que desea publicar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el búfer

        if (opcion < 1 || opcion > archivos.length) {
            System.out.println("Opción inválida. No se realizó la publicación.");
            return;
        }

        String rutaFoto = archivos[opcion - 1].getPath();

        System.out.print("Ingrese su descripción: ");
        String descripcion = scanner.nextLine();

        perfil.agregarPublicacion(new Publicacion(this, rutaFoto, descripcion));
        System.out.println("-------Publicación realizada con éxito----");
        this.verPublicaciones();
    }


    public void realizarComentario(Usuario usuario) throws IOException {
        Scanner indeee = new Scanner(System.in);
        usuario.getPerfil().mostrarContenidoPublicaciones();

        System.out.print("Seleccione la publicación:");
        int indicePublicacion = indeee.nextInt();
        // Verificar si el índice ingresado es válido
        ArrayList<Publicacion> publicaciones = usuario.getPerfil().getPublicaciones();
        if (verificarIndicePublicacion(indicePublicacion, publicaciones)) return;

        Publicacion publicacion = publicaciones.get(indicePublicacion - 1);
        String textoComentario = redactarComentario();

        enviarComentario(textoComentario, publicacion);


    }

    private String redactarComentario() {
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Ingrese su comentario: ");
        String textoComentario = scanner2.nextLine();
        return textoComentario;
    }

    private boolean verificarIndicePublicacion(int indicePublicacion, ArrayList<Publicacion> publicaciones) {
        if (!(indicePublicacion >= 1 && indicePublicacion <= publicaciones.size())) {
            System.out.println("¡El número de la publicación ingresado no es válido!");
            return true;
        }
        return false;
    }




    public void realizarReaccion(Usuario usuario) throws IOException {
        Scanner scanner = new Scanner(System.in);
        usuario.getPerfil().mostrarContenidoPublicaciones();

        System.out.print("Seleccione la publicación");
        int indicePublicacion = scanner.nextInt();
        // Verificar si el índice ingresado es válido
        ArrayList<Publicacion> publicaciones = usuario.getPerfil().getPublicaciones();
        if (verificarIndicePublicacion(indicePublicacion, publicaciones)) return;

        Publicacion publicacion = publicaciones.get(indicePublicacion - 1);

        // Publicacion publicacion = usuario.getPerfil().getPublicaciones().get(0);
        enviarReaccion(this, publicacion);
        // Opcional: Notificar al autor del comentario

    }

    private void enviarReaccion(Usuario autor, Publicacion publicacion) {
        Reaccion reaccion = new Reaccion(autor, publicacion);
        publicacion.agregarReaccion(reaccion);
    }

    public void enviarComentario(String texto, Publicacion publicacion) {

        Comentario comentario = new Comentario(this, texto);
        publicacion.agregarComentario(comentario);
        //comentario.getAutor().notificarComentario(comentario);

    }

    public void verComentarios() throws IOException {
        Scanner scanner = new Scanner(System.in);
        this.getPerfil().mostrarContenidoPublicaciones();

        System.out.print("Seleccione la publicación");
        int indicePublicacion = scanner.nextInt();
        // Verificar si el índice ingresado es válido
        ArrayList<Publicacion> publicaciones = this.getPerfil().getPublicaciones();
        if (verificarIndicePublicacion(indicePublicacion, publicaciones)) return;

        Publicacion publicacion = publicaciones.get(indicePublicacion - 1);

        //Publicacion publicacion = this.getPerfil().getPublicaciones().get(0);

        publicacion.mostrarComentarios();
    }


    public void verPublicaciones() throws IOException {
        perfil.mostrarContenidoPublicaciones();

    }


    public void verReaccion() throws IOException {

        Scanner scanner = new Scanner(System.in);
        this.getPerfil().mostrarContenidoPublicaciones();

        System.out.print("Seleccione la publicación");
        int indicePublicacion = scanner.nextInt();
        // Verificar si el índice ingresado es válido
        ArrayList<Publicacion> publicaciones = this.getPerfil().getPublicaciones();
        if (verificarIndicePublicacion(indicePublicacion, publicaciones)) return;

        Publicacion publicacion = publicaciones.get(indicePublicacion - 1);
        //Publicacion publicacion = this.getPerfil().getPublicaciones().get(0);

        publicacion.mostrarReaccionadores();
    }

    public void realizarComparticion(Usuario usuario1) throws IOException {

        Scanner scanner = new Scanner(System.in);
        usuario1.getPerfil().mostrarContenidoPublicaciones();

        System.out.print("Seleccione la publicación");
        int indicePublicacion = scanner.nextInt();
        // Verificar si el índice ingresado es válido
        ArrayList<Publicacion> publicaciones = usuario1.getPerfil().getPublicaciones();
        if (verificarIndicePublicacion(indicePublicacion, publicaciones)) return;

        Publicacion publicacion = publicaciones.get(indicePublicacion - 1);


        Comparticion comparticion = new Comparticion(this);
        perfil.agregarPublicacion(new Publicacion(publicacion.getAutor(), comparticion.getLink(), publicacion.getDescripcion()));
        publicacion.agregarComparticion(comparticion);
        System.out.println("FOTO COMPARTIDA EXITOSAMENTE");
        System.out.println("Link: " + comparticion.getLink());


    }


    public void seguirUsuario() {

        System.out.println("-----USUARIOS DISPONIBLES PARA SEGUIR-----");
        int indice = verPosibleUsuarios();
        Usuario usuarioSeleccionado = listaUsuarios.get(indice - 1);
        if (usuarioSeleccionado == this) {
            System.out.println("No puedes seguirte a ti mismo");
            return;
        }
        Perfil perfil = usuarioSeleccionado.getPerfil();
        perfil.agregarSeguidor(usuarioSeleccionado);
    }


    public Usuario verPerfil() throws IOException {
        int eleccion = this.verPosibleUsuarios();
        Usuario usuarioSeleccionado = listaUsuarios.get(eleccion - 1);
        if (!(usuarioSeleccionado == this)) {
            System.out.println(usuarioSeleccionado.perfil.toString());
            usuarioSeleccionado.verPublicaciones();
            return usuarioSeleccionado;
        }
        System.out.println(this.perfil.toString());
        this.verPublicaciones();
        return this;
    }

    public void verSeguidores() {
        List<Usuario> seguidores = this.getPerfil().getSeguidores();
        perfil.mostrarSeguidores(seguidores);
    }

    public int verPosibleUsuarios() {
        Scanner scanner = new Scanner(System.in);
        int eleccion;
        System.out.println("Usuarios disponibles:");
        int contador = 1;
        for (Usuario usuario : listaUsuarios) {
            if (usuario == this) {
                System.out.println(contador + ". " + usuario.getNombre() + " (Mi usuario ");
                contador++;

            } else {
                System.out.println(contador + ". " + usuario.getNombre());
                contador++;
            }
        }


        System.out.println("Seleccione uno:");
        int i = 0;
        eleccion = scanner.nextInt();
        do {
            if (!(eleccion >= 1 && eleccion <= listaUsuarios.size())) {
                System.out.println("Eleccion no valida, ingrese de nuevo");
            }
            scanner.nextLine();
            i++;
            return eleccion;
        } while (i != 1);

    }





    private File[] obtenerArchivosDirectorio(String directorio) {
        File folder = new File(directorio);
        return folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
    }




            @Override
            public String toString () {
                return "Usuario{" +
                        "nombre='" + nombre + '\'' +
                        ", perfil=" + perfil +
                        '}';
            }


        }





