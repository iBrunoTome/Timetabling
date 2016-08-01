import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {

        String nameFileIn = "comp01.ctt";
        try {
            Problem currentProblem = new Problem("instancias/" + nameFileIn);
            Table currentTable = new Table(currentProblem);
            PrintWriter writer = new PrintWriter("solucoes/" + nameFileIn + ".txt");
            writer.println(currentTable.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problema ao ler ou escrever arquivo ");
        }
    }
}