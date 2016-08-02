import java.util.Random;

/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 27/06/2016
 */
public class Grasp {

    private Table table;
    private final int MaxIter = 100;

    public Grasp(Table table) {
        this.table = table;
    }

    public void run() {
        Table tableNeighbor;
        for (int i = 0; i < this.MaxIter; i++) {
            tableNeighbor = this.localSearch();
            if (tableNeighbor.getObjectiveFunction() < this.table.getObjectiveFunction()) {
                this.table = tableNeighbor;
            }
        }
    }

    private Table localSearch() {
        int iter = 10;
        int deltaF;
        Table tableAux;
        Table bestTable = this.table;
        for (int i = 0; i < iter; i++) {
            tableAux = this.generateNeighbor();
            deltaF = bestTable.getObjectiveFunction() - tableAux.getObjectiveFunction();

            if (deltaF < 0) {
                bestTable = tableAux;
                i = 0;
            }
        }

        return bestTable;
    }

    private Table generateNeighbor() {
        Table[] tables = new Table[]{this.swap(this.table), this.swap(this.table), this.swap(this.table), this.swap(this.table)};

        Table bestTable = tables[0];
        for (int i = 1; i < 4; i++) {
            if (tables[i].getObjectiveFunction() <= bestTable.getObjectiveFunction()) {
                bestTable = tables[i];
            }
        }

        return bestTable;

    }

    private void move(Table table) {
    }

    private Table swap(Table table) {
        Random rand = new Random();
        int choose1 = rand.nextInt(table.getListClassAllocated().size());
        int choose2 = rand.nextInt(table.getListClassAllocated().size());

        Class c1 = table.getListClassAllocated().get(choose1);
        Class c2 = table.getListClassAllocated().get(choose2);

        if ((table.getCurrentProblem().getClassSchedules()[c1.getIdxClass()][c2.getViableSchedules().get(0)[1]] == 0)
                && (table.getCurrentProblem().getClassSchedules()[c2.getIdxClass()][c1.getViableSchedules().get(0)[1]] == 0)) {
            table.getTable()[c1.getViableSchedules().get(0)[0]][c1.getViableSchedules().get(0)[1]] = c2.getIdxClass();
            table.getTable()[c2.getViableSchedules().get(0)[0]][c2.getViableSchedules().get(0)[1]] = c1.getIdxClass();
        } else {
            this.swap(table);
        }

        table.calculateObjetiveFunction();
        return table;

    }

    public Table getTable() {
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}
