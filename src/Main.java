import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        String nameFileIn = "instancias/comp01.ctt";
        try {
            FileReader fileIn = new FileReader(nameFileIn);
            BufferedReader reader = new BufferedReader(fileIn);
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível ler o arquivo");
        }
    }
}