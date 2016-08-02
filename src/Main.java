import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {

        String nameFileIn = "toy.ctt";
        try {
            Problem currentProblem = new Problem("instancias/" + nameFileIn);
            Table currentTable = new Table(currentProblem);
            Grasp grasp = new Grasp(currentTable);
            grasp.run();
            currentTable = grasp.getTable();
            System.out.println("##########################################");
            System.out.println("Função objetivo: " + currentTable.getObjectiveFunction());
            System.out.println(currentTable.toString());
            PrintWriter writer = new PrintWriter("solucoes/" + nameFileIn + ".txt");
            writer.println(currentTable.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problema ao ler ou escrever arquivo ");
        }
    }
}