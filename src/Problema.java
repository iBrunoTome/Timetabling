import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by iBrunoTome on 6/19/16.
 */
public class Problema {
    private FileReader fileIn;
    private BufferedReader reader;
    private String nomeInstancia;
    private String line;
    private Curricula[] curriculas;
    private Course[] courses;
    private Sala[] salas;
    private Restricao[] restricoes;
    private int[][] AA;
    private int[][] AI;
    private int nSalas;
    private int nDias;
    private int nPeriodosPorDia;

    public Problema() {

    }

    public Problema(String nameFileIn) throws IOException {
        this.fileIn = new FileReader(nameFileIn);
        this.reader = new BufferedReader(this.fileIn);
        this.line = reader.readLine();
        instantiateProblem();

    }

    private void instantiateProblem() throws IOException {
        while (line != null) {
            if (line.startsWith("Name: ")) {
                this.setNomeInstancia(line.substring(7));
            } else if (line.startsWith("Courses: ")) {
                this.courses = new Course[Integer.parseInt(this.line.substring(9))];
            } else if (line.startsWith("Rooms: ")) {
                this.setnSalas(Integer.parseInt(line.substring(7)));
            } else if (line.startsWith("Days: ")) {
                this.setnDias(Integer.parseInt(line.substring(6)));
            } else if (line.startsWith("Periods_per_day: ")) {
                this.setnPeriodosPorDia(Integer.parseInt(line.substring(17)));
            } else if (line.startsWith("Curricula: ")) {
                this.curriculas = new Curricula[Integer.parseInt(line.substring(11))];
            }
            System.out.println(line);
            line = this.reader.readLine();
        }
    }

    public String getNomeInstancia() {
        return this.nomeInstancia;
    }

    public void setNomeInstancia(String nomeInstancia) {
        this.nomeInstancia = nomeInstancia;
    }

    public int getnSalas() {
        return this.nSalas;
    }

    public void setnSalas(int nSalas) {
        this.nSalas = nSalas;
    }

    public int getnDias() {
        return this.nDias;
    }

    public void setnDias(int nDias) {
        this.nDias = nDias;
    }

    public int getnPeriodosPorDia() {
        return this.nPeriodosPorDia;
    }

    public void setnPeriodosPorDia(int nPeriodosPorDia) {
        this.nPeriodosPorDia = nPeriodosPorDia;
    }

    public Curricula[] getCurriculas() {
        return this.curriculas;
    }

    public void setCurriculas(Curricula[] curriculas) {
        this.curriculas = curriculas;
    }

    public Course[] getCourses() {
        return this.courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public Sala[] getSalas() {
        return this.salas;
    }

    public void setSalas(Sala[] salas) {
        this.salas = salas;
    }

    public Restricao[] getRestricoes() {
        return this.restricoes;
    }

    public void setRestricoes(Restricao[] restricoes) {
        this.restricoes = restricoes;
    }

    public int[][] getAA() {
        return this.AA;
    }

    public void setAA(int[][] AA) {
        this.AA = AA;
    }

    public int[][] getAI() {
        return this.AI;
    }

    public void setAI(int[][] AI) {
        this.AI = AI;
    }
}
