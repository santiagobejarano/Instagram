import java.util.Random;
class Comparticion {
    private String link;
    private Usuario autor;


    public Comparticion(Usuario autor) {
        this.autor = autor;
        this.link = RandomLink();
        //autor.notificarComparticion(this);
    }

    public String getLink() {
        return link;
    }






        public static String RandomLink() {
            String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            int LINK_LENGTH = 10;
            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < LINK_LENGTH; i++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                char randomChar = CHARACTERS.charAt(randomIndex);
                sb.append(randomChar);
            }

            return sb.toString();
        }





}
