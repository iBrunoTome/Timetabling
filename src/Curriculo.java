/**
 * Created by iBrunoTome on 6/19/16.
 */
public class Curriculo {
    private String nomeCurriculo;
    private int nDisciplinas;
    private String[] disciplinas;

    public Curriculo() {

    }

    public String getNomeCurriculo() {
        return this.nomeCurriculo;
    }

    public void setNomeCurriculo(String nomeCurriculo) {
        this.nomeCurriculo = nomeCurriculo;
    }

    public int getnDisciplinas() {
        return this.nDisciplinas;
    }

    public void setnDisciplinas(int nDisciplinas) {
        this.nDisciplinas = nDisciplinas;
    }

    public String[] getDisciplinas() {
        return this.disciplinas;
    }

    public void setDisciplinas(String[] disciplinas) {
        this.disciplinas = disciplinas;
    }
}
