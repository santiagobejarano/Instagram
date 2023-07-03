import java.io.IOException;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String RESET = "\u001B[0m";
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static Usuario usuarioActual;

    public static void main(String[] args) throws IOException {
        // Crear usuarios

        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println(RED + "######  ##    ##  ###### ########     ##       ###########  ##########        ##       ####    ####" + RESET);
        System.out.println(PURPLE + "  ##    ###   ##  ##        ##       ####      ##           ##      ##       ####      ####    ####  " + RESET);
        System.out.println(YELLOW + "  ##    ## #  ##  ##        ##      ##  ##     ##           ##      ##      ##  ##     ## ##  ## ##   " + RESET);
        System.out.println(WHITE + "  ##    ##  # ##  ######    ##     ##    ##    ##    ####   ##   #####     ##    ##    ##   ##   ##   " + RESET);
        System.out.println(PURPLE + "  ##    ##   ###      ##    ##    ##########   ##      ##   ##    ##      ##########   ##        ##   " + RESET);
        System.out.println(RED + "  ##    ##    ##      ##    ##   ##        ##  ##      ##   ##     ##    ##        ##  ##        ##   " + RESET);
        System.out.println(YELLOW + "######  ##    ##  ######    ##  ##          ## ##########   ##       ## ##          ## ##        ##   " + RESET);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("----- MENÚ PRINCIPAL -----");
            System.out.println("1. Agregar nuevo usuario");
            System.out.println("2. Elegir usuario");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el búfer

            switch (opcion) {
                case 1:
                    crearUsuario(scanner);
                    break;
                case 2:
                    cambiarUsuario(scanner);
                    if (usuarioActual != null) {
                        menuSecundario(scanner);
                    }
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }

        System.out.println("¡Hasta luego!");
    }

    public static void crearUsuario(Scanner scanner) {
        System.out.println("----- CREAR USUARIO -----");
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();

        Usuario usuario = new Usuario(nombreUsuario, contrasena);
        listaUsuarios.add(usuario);
        System.out.println("Usuario creado exitosamente.");
    }

    public static void cambiarUsuario(Scanner scanner) {
        System.out.println("----- CAMBIAR DE USUARIO -----");

        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios disponibles.");
            return;
        }

        System.out.println("Usuarios disponibles:");
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuario = listaUsuarios.get(i);
            System.out.println((i + 1) + ". " + usuario.getNombre());
        }

        System.out.print("Ingrese el número de usuario al que desea cambiar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el búfer

        if (opcion < 1 || opcion > listaUsuarios.size()) {
            System.out.println("Opción inválida. No se realizó el cambio de usuario.");
            usuarioActual = null;
        } else {
            usuarioActual = listaUsuarios.get(opcion - 1);
            System.out.println("¡Bienvenido, " + usuarioActual.getNombre() + "!");
        }
    }

    public static void menuSecundario(Scanner scanner) throws IOException {
        boolean salir = false;

        while (!salir) {
            System.out.println("----- MENÚ SECUNDARIO -----");
            System.out.println("1. Agregar publicación");
            System.out.println("2. Ver perfil");
            System.out.println("3. Ver seguidores");
            System.out.println("4. RealizarReaccion");
            System.out.println("5. Seguir Usuarios");
            System.out.println("6. Realizar Comentario");
            System.out.println("7. Realizar Comparticion");
            System.out.println("8. Ver comentarios");
            System.out.println("9. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el búfer

            switch (opcion) {
                case 1:
                    usuarioActual.agregarPublicacion();
                    break;
                case 2:
                 //   Scanner scanner1 = new Scanner();
                    //usuarioActual.verPerfil();
                    usuarioActual.verComentarios();

                    break;
                case 3:
                    usuarioActual.verSeguidores();
                    break;
                case 4:
                    System.out.println("A qué usuario desea realizar la reacción");
                    int opcion2 = usuarioActual.verPosibleUsuarios();
                    Usuario usuarioReaccion = listaUsuarios.get(opcion2 - 1);
                    usuarioActual.realizarReaccion(usuarioReaccion);
                    break;
                case 5:
                    usuarioActual.seguirUsuario();
                    break;
                case 6:
                    System.out.println("A qué usuario desea realizar un comentario");
                    int opcion3 = usuarioActual.verPosibleUsuarios();
                    Usuario usuarioComentario = listaUsuarios.get(opcion3 - 1);
                    usuarioActual.realizarComentario(usuarioComentario);
                    break;
                case 7:
                    System.out.println("A qué usuario desea realizar una compartida");
                    int opcion5 = usuarioActual.verPosibleUsuarios();
                    Usuario usuarioCompartir = listaUsuarios.get(opcion5 - 1);
                    usuarioActual.realizarComparticion(usuarioCompartir);
                    break;
                case 8:
                    usuarioActual.verComentarios();
                    break ;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

        /*
        Usuario usuario1 = new Usuario("Mario", "123");
        Usuario usuario2 = new Usuario("Juan", "123");
        Usuario usuario3 = new Usuario("Jose", "123");
        usuario1.agregarPublicacion();
        usuario2.realizarComentario(usuario1);

        usuario1.verPublicaciones();

        usuario1.verComentarios();
        usuario2.realizarReaccion(usuario1);
        usuario3.realizarReaccion(usuario1);
        usuario1.verReaccion();

        usuario2.realizarComparticion(usuario1);


        usuario1.seguirUsuario();
        usuario3.verPefil();
        usuario3.verSeguidores();


    }

 */


}



