/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Table {
    private int objectiveFunction;
    private int[][] table;
    private int[][] usedRooms;
    private int[][] busyDays;
    private int[][][] currDiasPeriodos;
    private Problem currentProblem;

    public Table(Problem currentProblem) {
        this.currentProblem = currentProblem;
    }

    public int[][] getTable() {
        return this.table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    public int getObjective() {
        return this.objectiveFunction;
    }

    public void setObjective(int objective) {
        this.objectiveFunction = objective;
    }

    public int[][][] getCurrDiasPeriodos() {
        return this.currDiasPeriodos;
    }

    public void setCurrDiasPeriodos(int[][][] currDiasPeriodos) {
        this.currDiasPeriodos = currDiasPeriodos;
    }

    public int[][] getUsedRooms() {
        return this.usedRooms;
    }

    public void setUsedRooms(int[][] usedRooms) {
        this.usedRooms = usedRooms;
    }

    public int[][] getBusyDays() {
        return this.busyDays;
    }

    public void setBusyDays(int[][] busyDays) {
        this.busyDays = busyDays;
    }
}
