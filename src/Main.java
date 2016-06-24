import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String nameFileIn = "instancias/comp01.ctt";

        try {
            Problema problem = new Problema(nameFileIn);
        } catch (IOException e) {
            System.out.println("Problema ao ler arquivo");
        }


    }
}