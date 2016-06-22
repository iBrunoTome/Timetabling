/**
 * Created by iBrunoTome on 6/19/16.
 */
public class Disciplina {
    private String nomeDisciplina;
    private String nomeProfessor;
    private int nAulas;
    private int minDiasAula;
    private int nAlunos;

    public Disciplina() {

    }

    public String getNomeDisciplina() {
        return this.nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getNomeProfessor() {
        return this.nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public int getnAulas() {
        return this.nAulas;
    }

    public void setnAulas(int nAulas) {
        this.nAulas = nAulas;
    }

    public int getMinDiasAula() {
        return this.minDiasAula;
    }

    public void setMinDiasAula(int minDiasAula) {
        this.minDiasAula = minDiasAula;
    }

    public int getnAlunos() {
        return this.nAlunos;
    }

    public void setnAlunos(int nAlunos) {
        this.nAlunos = nAlunos;
    }
}
