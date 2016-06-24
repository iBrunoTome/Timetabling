import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String nameFileIn = "instancias/comp01.ctt";
        try {
            Problem problem = new Problem(nameFileIn);
        } catch (IOException e) {
            System.out.println("Problem ao ler arquivo");
        }
    }
}