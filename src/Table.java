/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Table {
    private int[][] table;
    private int fo;
    private int[][][] currDiasPeriodos;
    private int[][] usedRooms;
    private int[][] busyDays;

    public Table() {
    }

    public int[][] getTable() {
        return this.table;
    }

    public void setTable(int[][] table) {
        this.table = table;
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
