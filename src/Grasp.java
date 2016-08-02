import java.util.Random;

/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 27/06/2016
 */
public class Grasp {

    private Table table;
    private final int MaxIter = 200;

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
        int iter = 100;
        int deltaF;
        Table tableAux;
        Table bestTable = this.table;
        for (int i = 0; i < iter; i++) {
            tableAux = this.generateNeighbor();
            deltaF = tableAux.getObjectiveFunction() - bestTable.getObjectiveFunction();

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

            /*
                Update the dynamic matrix busyDays
             */
            int day = Math.floorDiv(c1.getViableSchedules().get(0)[1], table.getCurrentProblem().getnPeriodsPerDay());
            table.getBusyDays()[table.getCurrentProblem().getCourseFromInt(c1.getIdxClass()).getIdx()][day] -= 1;
            day = Math.floorDiv(c2.getViableSchedules().get(0)[1], table.getCurrentProblem().getnPeriodsPerDay());
            table.getBusyDays()[table.getCurrentProblem().getCourseFromInt(c1.getIdxClass()).getIdx()][day] += 1;
            table.getBusyDays()[table.getCurrentProblem().getCourseFromInt(c2.getIdxClass()).getIdx()][day] -= 1;
            day = Math.floorDiv(c1.getViableSchedules().get(0)[1], table.getCurrentProblem().getnPeriodsPerDay());
            table.getBusyDays()[table.getCurrentProblem().getCourseFromInt(c2.getIdxClass()).getIdx()][day] += 1;

            /*
                Update the dynamic matrix usedRooms
             */
            table.getUsedRooms()[table.getCurrentProblem().getCourseFromInt(c1.getIdxClass()).getIdx()][c1.getViableSchedules().get(0)[0]] -= 1;
            table.getUsedRooms()[table.getCurrentProblem().getCourseFromInt(c1.getIdxClass()).getIdx()][c2.getViableSchedules().get(0)[0]] += 1;
            table.getUsedRooms()[table.getCurrentProblem().getCourseFromInt(c2.getIdxClass()).getIdx()][c2.getViableSchedules().get(0)[0]] -= 1;
            table.getUsedRooms()[table.getCurrentProblem().getCourseFromInt(c2.getIdxClass()).getIdx()][c1.getViableSchedules().get(0)[0]] += 1;

            /*
                Update the dynamic matrix currDaysPeriods
             */
            int period1 = c1.getViableSchedules().get(0)[1] % table.getCurrentProblem().getnPeriodsPerDay();
            int period2 = c2.getViableSchedules().get(0)[1] % table.getCurrentProblem().getnPeriodsPerDay();
            int day1 = Math.floorDiv(c1.getViableSchedules().get(0)[1], table.getCurrentProblem().getnPeriodsPerDay());
            int day2 = Math.floorDiv(c2.getViableSchedules().get(0)[1], table.getCurrentProblem().getnPeriodsPerDay());
            int curricula1 = table.getCurrentProblem().getCurriculaFromCourse(table.getCurrentProblem().getCourseFromInt(c1.getIdxClass())).getIdx();
            int curricula2 = table.getCurrentProblem().getCurriculaFromCourse(table.getCurrentProblem().getCourseFromInt(c2.getIdxClass())).getIdx();
            table.getCurriculaDaysPeriods()[curricula1][day1][period1] -= 1;
            table.getCurriculaDaysPeriods()[curricula1][day2][period2] += 1;
            table.getCurriculaDaysPeriods()[curricula2][day2][period2] -= 1;
            table.getCurriculaDaysPeriods()[curricula2][day1][period1] += 1;
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
