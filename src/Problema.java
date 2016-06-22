/**
 * Created by iBrunoTome on 6/19/16.
 */
public class Problema {
	private String nomeInstancia;
	private int nSalas;
	private int nDias;
	private int nPeriodosPorDia;
	private Curriculo[] curriculos;
	private Disciplina[] disciplinas;
	private Sala[] salas;
	private Restricao[] restricoes;
	private int[][] AA;
	private int[][] AI;

	public Problema() {

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

	public Curriculo[] getCurriculos() {
		return this.curriculos;
	}

	public void setCurriculos(Curriculo[] curriculos) {
		this.curriculos = curriculos;
	}

	public Disciplina[] getDisciplinas() {
		return this.disciplinas;
	}

	public void setDisciplinas(Disciplina[] disciplinas) {
		this.disciplinas = disciplinas;
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
