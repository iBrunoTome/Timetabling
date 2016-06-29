import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String nameFileIn = "instancias/comp01.ctt";
        try {
            Problem currentProblem = new Problem(nameFileIn);
            Table currentTable = new Table(currentProblem);
        } catch (IOException e) {
            System.out.println("Problem ao ler arquivo");
        }
    }
}