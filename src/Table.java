import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Table {
    private int objectiveFunction;
    private List<Class> schedulesNonAllocated = new ArrayList<>();
    private int[][] table;
    private int[][] usedRooms;
    private int[][] busyDays;
    private int[][][] curriculaDaysPeriods;
    private Problem currentProblem;

    public Table(Problem currentProblem) {
        this.currentProblem = currentProblem;
        this.fillSchedulesNonAllocated();
    }

    /**
     * Fill the array with the unavailable schedules array
     */
    public void fillSchedulesNonAllocated() {
        // Sum the unavailable schedules for the class
        for (int l = 0; l < this.currentProblem.getTotalClass(); l++) {
            Class currentClass = new Class();
            currentClass.setIdxClass(l);
            for (int c = 0; c < this.currentProblem.getTotalSchedules(); c++) {
                if (this.currentProblem.getClassSchedules()[l][c] == 1) {
                    if (currentClass == null) {
                        currentClass.setUnavailability(1);
                    } else {
                        currentClass.setUnavailability(currentClass.getUnavailability() + 1);
                    }
                }
            }
        }

        Collections.sort(this.schedulesNonAllocated, (c1, c2) -> Double.compare(c1.getUnavailability(), c2.getUnavailability()));
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

    public int[][][] getCurriculaDaysPeriods() {
        return this.curriculaDaysPeriods;
    }

    public void setCurriculaDaysPeriods(int[][][] curriculaDaysPeriods) {
        this.curriculaDaysPeriods = curriculaDaysPeriods;
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
