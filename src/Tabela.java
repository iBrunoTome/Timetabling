/**
 * Created by iBrunoTome on 6/19/16.
 */
public class Tabela {
	private int[][] tabela;
	private int fo;
	private int[][][] currDiasPeriodos;
	private int[][] salasUsadas;
	private int[][] diasOcupados;

	public Tabela() {

	}

	public int[][] getTabela() {
		return this.tabela;
	}

	public void setTabela(int[][] tabela) {
		this.tabela = tabela;
	}

	public int getFo() {
		return this.fo;
	}

	public void setFo(int fo) {
		this.fo = fo;
	}

	public int[][][] getCurrDiasPeriodos() {
		return this.currDiasPeriodos;
	}

	public void setCurrDiasPeriodos(int[][][] currDiasPeriodos) {
		this.currDiasPeriodos = currDiasPeriodos;
	}

	public int[][] getSalasUsadas() {
		return this.salasUsadas;
	}

	public void setSalasUsadas(int[][] salasUsadas) {
		this.salasUsadas = salasUsadas;
	}

	public int[][] getDiasOcupados() {
		return this.diasOcupados;
	}

	public void setDiasOcupados(int[][] diasOcupados) {
		this.diasOcupados = diasOcupados;
	}
}
