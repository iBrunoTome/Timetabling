import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Table {
    private int objectiveFunction;
    private ArrayList<Class> listSchedulesNonAllocated = new ArrayList<>();
    private ArrayList<Integer> viableSchedules = new ArrayList<>();
    private int[][] table;
    private int[][] usedRooms;//retorna quantidade de aulas da disciplina  na sala na semana.verificar quantas salas estão ocupadas por disciplina
    private int[][] busyDays;//retorna a quantidade de aulas da disciplina no dia. contar em quantos dias há aulas de uma disciplina
    private int[][][] curriculaDaysPeriods;//retorna a quantidade de aulas do currículo c alocadas no dia d e horário p
    private Problem currentProblem;

    public Table(Problem currentProblem) {
        this.currentProblem = currentProblem;
        this.table = new int[this.currentProblem.getnRooms()][this.currentProblem.getTotalSchedules()];
        this.fillTable();
        this.fillSchedulesNonAllocated();
        this.busyDays = new int[this.currentProblem.getCourses().length][this.currentProblem.getnDays()];
        this.usedRooms = new int[this.currentProblem.getCourses().length][this.currentProblem.getnRooms()];
        this.curriculaDaysPeriods = new int[this.currentProblem.getCurriculas().length][this.currentProblem.getnDays()][this.currentProblem.getnPeriodsPerDay()];
    }


    /**
     * generate a inicial table. that is the inicial solution
     */
    public void gerateInicialTable() {

    }

    /**
     * Fill the array with the unavailable schedules array
     */
    public void fillSchedulesNonAllocated() {
        // Run the matrix, and catch inviability of same teacher or same curricula
        for (int l = 0; l < this.currentProblem.getTotalClass(); l++) {
            Class currentClass = new Class();
            currentClass.setIdxClass(l);
            currentClass.setScheduleViability(currentProblem.getTotalSchedules());
            for (int c = 0; c < this.currentProblem.getTotalSchedules(); c++) {
                if (this.currentProblem.getClassClass()[l][c] == 1) {
                    currentClass.setScheduleViability(currentClass.getScheduleViability() - 1);
                }
            }

            // Run the matrix classSchudele, and catch inviability Schedules for the currentClass
            for (int k = 0; k < currentProblem.getTotalSchedules(); k++) {
                if (this.currentProblem.getClassSchedules()[l][k] == 1) {
                    currentClass.setScheduleViability(currentClass.getScheduleViability() - 1);
                }
            }

            this.listSchedulesNonAllocated.add(currentClass);

        }
        Collections.sort(this.listSchedulesNonAllocated, (c1, c2) -> Double.compare(c1.getScheduleViability(), c2.getScheduleViability()));
    }

    /**
     * verifify isoleted class in curricula
     *
     * @return int of numbre of isolated classes
     */

    public int isolatedClassesPerCurricula(int curr) {
        int sumIsoletedClass = 0;
        for (int i = 0; i < this.curriculaDaysPeriods[0].length; i++) {
            for (int p = 0; p < this.curriculaDaysPeriods[0][0].length; p++) {
                if ((this.curriculaDaysPeriods[curr][i][p - 1] == 0) && (this.curriculaDaysPeriods[curr][i][p + 1] == 0)) {
                    sumIsoletedClass++;
                }
            }
        }
        return sumIsoletedClass;
    }

    /**
     * calculate the cost to allocated a class, based on weak constraints
     *
     * @param room
     * @param period
     * @return cost
     */

    public int alocationCost(Class c, int room, int period) {
        return 0;
    }

    /**
     * gerete a list of viable schedules for a class
     *
     * @return
     */

    public void genereteViableSchedules(Class c) {
        int empytSchedule;
        for (int i = 0; i < currentProblem.getClassSchedules()[0].length; i++) {
            if (currentProblem.getClassSchedules()[c.idxClass][i] == 0) {
                empytSchedule = 0;
                for (int j = 0; j < this.table.length; j++) {
                    if ((this.table[j][i] != -1) && (!currentProblem.courseSameCurricula(c.getIdxClass(), this.table[j][i])) && !currentProblem.sameCourse(c.getIdxClass(), this.table[j][i])) {
                        viableSchedules.add(i);
                    } else if (this.table[j][i] == -1) {
                        empytSchedule++;
                    }
                }
                // if not have any class allocated in this schedule
                if (empytSchedule == this.table.length) {
                    viableSchedules.add(i);
                }
            }
        }
    }

    private void fillTable() {
        for (int room = 0; room < this.table.length; room++) {
            for (int schedule = 0; schedule < this.table[0].length; schedule++) {
                this.table[room][schedule] = -1;
            }
        }
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
