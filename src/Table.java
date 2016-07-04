import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Table {
    private int objectiveFunction;
    private ArrayList<Class> listClassNonAllocated = new ArrayList<>();
    private ArrayList<Class> listClassAllocated = new ArrayList<>();
    private int[][] table;
    private int[][] usedRooms; // Retorna quantidade de aulas da disciplina  na sala na semana.verificar quantas salas estão ocupadas por disciplina
    private int[][] busyDays; // Retorna a quantidade de aulas da disciplina no dia. contar em quantos dias há aulas de uma disciplina
    private int[][][] curriculaDaysPeriods; // Retorna a quantidade de aulas do currículo c alocadas no dia d e horário p
    private Problem currentProblem;

    public Table(Problem currentProblem) {
        this.currentProblem = currentProblem;
        this.table = new int[this.currentProblem.getnRooms()][this.currentProblem.getTotalSchedules()];
        this.busyDays = new int[this.currentProblem.getCourses().length][this.currentProblem.getnDays()];
        this.usedRooms = new int[this.currentProblem.getCourses().length][this.currentProblem.getnRooms()];
        this.curriculaDaysPeriods = new int[this.currentProblem.getCurriculas().length][this.currentProblem.getnDays()][this.currentProblem.getnPeriodsPerDay()];
        this.initializeBusyUsedMatrix();
        this.initializeCurriculaDaysPeriodsMatrix();
        this.gerateInicialTable();
    }

    /**
     * Get the min and the max viable schedules
     *
     * @param c
     * @return
     */
    private int[] getMinMax(Class c) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Integer[] s : c.getViableSchedules()) {
            if (s[2] < min) {
                min = s[2];
            }

            if (s[2] < max) {
                max = s[2];
            }
        }

        return new int[]{min, max};
    }

    /**
     * Generate a inicial table. That is the inicial solution
     */
    public void gerateInicialTable() {
        this.fillTable();
        this.fillClassNonAllocated();

        int interval;
        int choosen;
        final Double alfa = 0.15;

        Class classAux = this.listClassNonAllocated.get(0);
        classAux = this.genereteViableSchedules(classAux);
        while (!this.listClassNonAllocated.isEmpty()) {
            int[] minMax = this.getMinMax(classAux);

            interval = (int) (minMax[0] + (alfa * (minMax[1] - minMax[0])));

            for (Integer[] s : classAux.getViableSchedules()) {
                if (s[2] > interval) {
                    classAux.getViableSchedules().remove(s);
                }
            }

            Random random = new Random();
            choosen = random.nextInt(classAux.getViableSchedules().size());
            int line = classAux.getViableSchedules().get(choosen)[0];
            int column = classAux.getViableSchedules().get(choosen)[1];
            System.out.println("linha  "+line+"\n coluna "+column+" \nchoosen "+choosen);
            this.table[line][column] = classAux.getIdxClass();

            classAux.getViableSchedules().removeAll(classAux.getViableSchedules());
            classAux.getViableSchedules().add(new Integer[]{line, column});

            this.listClassAllocated.add(classAux);
            this.listClassNonAllocated.remove(0);

            classAux = this.listClassNonAllocated.get(0);
            classAux = this.genereteViableSchedules(classAux);
        }
    }

    /**
     * Initialize the matrix with zeros.
     */
    private void initializeBusyUsedMatrix() {
        for (int i = 0; i < this.currentProblem.getCourses().length; i++) {
            for (int j = 0; j < this.currentProblem.getnRooms(); j++) {
                this.usedRooms[i][j] = 0;
            }

            for (int j = 0; j < this.currentProblem.getnDays(); j++) {
                this.busyDays[i][j] = 0;
            }
        }
    }

    /**
     * Initialize the curriculaDaysPeriods matrix with zeros
     */
    private void initializeCurriculaDaysPeriodsMatrix() {
        for (int i = 0; i < this.currentProblem.getCurriculas().length; i++) {
            for (int j = 0; j < this.currentProblem.getnDays(); j++) {
                for (int k = 0; k < this.currentProblem.getnPeriodsPerDay(); k++) {
                    this.curriculaDaysPeriods[i][j][k] = 0;
                }
            }
        }
    }


    /**
     * Fill the array with the unavailable schedules array
     */
    private void fillClassNonAllocated() {
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

            this.listClassNonAllocated.add(currentClass);

        }
        Collections.sort(this.listClassNonAllocated, (c1, c2) -> Double.compare(c1.getScheduleViability(), c2.getScheduleViability()));
    }

    /**
     * verifify isoleted class in curricula(weak constraint)
     *
     * @param curr
     * @return int of number of isolated classes
     */
    public int isolatedClassesPerCurricula(int curr) {
        int sumIsoletedClass = 0;
        for (int i = 0; i < this.currentProblem.getnDays(); i++) {
            for (int p = 0; p < this.currentProblem.getnPeriodsPerDay(); p++) {
                if((p > 0) && (p < currentProblem.getnPeriodsPerDay()-1)) {
                    if ((this.curriculaDaysPeriods[curr][i][p - 1] == 0) && (this.curriculaDaysPeriods[curr][i][p + 1] == 0)) {
                        sumIsoletedClass += 2;
                    } else if ((this.curriculaDaysPeriods[curr][i][p - 1] == 0) || (this.curriculaDaysPeriods[curr][i][p + 1] == 0)) {
                        sumIsoletedClass++;
                    }
                }else{
                    if((p == 0) && (this.curriculaDaysPeriods[curr][i][p + 1] == 0)){
                        sumIsoletedClass++;
                    }
                    if((p == currentProblem.getnPeriodsPerDay()) && ((this.curriculaDaysPeriods[curr][i][p - 1] == 0))){
                        sumIsoletedClass++;
                    }
                }
            }
        }
        return sumIsoletedClass;
    }

    /**
     * verify how many rooms a class use
     *
     * @param course
     * @return int number of rooms
     */
    private int stabilityRoom(Course course) {
        int stability = 0;
        for (int i = 0; i < this.currentProblem.getnRooms(); i++) {
            if (this.usedRooms[course.getIdx()][i] > 0) {
                stability++;
            }
        }
        return stability;
    }

    /**
     * verify how many days a course have
     *
     * @param course
     * @return workDays
     */

    public int daysOfWork(Course course) {
        int workDays = 0;
        for (int i = 0; i < this.currentProblem.getnDays(); i++) {
            if (this.busyDays[course.getIdx()][i] > 0) {
                workDays++;
            }
        }
        return workDays;
    }


    /**
     * Calculate the cost to allocated a class on the table, based on weak constraints
     *
     * @param c
     * @param schedule
     * @param room
     * @return cost
     */
    public int alocationClassCost(Class c, int schedule, int room) {
        int cost = 0;
        Course caux = new Course();
        caux = this.currentProblem.getCourseFromInt(c.getIdxClass());

        // 1 - weak constraint: room capacity
        int weak = caux.getnStudents();
        if (weak <= this.currentProblem.getRoomCapacity(room)) {
            cost = weak - this.currentProblem.getRoomCapacity(room);
        }
        // 2 - weak constraint: min days necessity for a class
        weak = caux.getMinClassDays();
        if (weak > this.daysOfWork(caux)) {
            cost += ((weak - daysOfWork(caux)) * 5);
        }
        // 3 - weak constraint: all class in the same room
        weak = this.stabilityRoom(caux);
        cost += weak;
        // 4 - weak constraint: isolateded classes
        Curricula curriculaAux = this.currentProblem.getCurriculaFromCourse(caux);
        if (curriculaAux != null) {
            weak = this.isolatedClassesPerCurricula(curriculaAux.getIdx());
            cost += (weak * 2);
        }

        return cost;
    }

    /**
     * Gerete a list of viable schedules for a class
     *
     * @param c
     * @return Class c
     */
    public Class genereteViableSchedules(Class c) {
        boolean flagSameCurricula;
        boolean flagSameClass;
        Integer[] viableSchedules = new Integer[3];

        for (int i = 0; i < this.currentProblem.getTotalSchedules(); i++) {
            flagSameCurricula = false;
            flagSameClass = false;
            if (this.currentProblem.getClassSchedules()[c.getIdxClass()][i] == 0) {
                for (int j = 0; j < this.currentProblem.getnRooms(); j++) {
                    if (this.table[j][i] != -1) {
                        if (this.currentProblem.courseSameCurricula(c.getIdxClass(), this.table[j][i])) {
                            flagSameCurricula = true;
                        }

                        if (this.currentProblem.sameCourse(c.getIdxClass(), this.table[j][i])) {
                            flagSameClass = true;
                        }
                    }
                }

                for (int j = 0; j < this.currentProblem.getnRooms(); j++) {
                    if ((this.table[j][i] == -1)) {
                        if ((flagSameClass == false) && (flagSameCurricula == false)) {
                            viableSchedules[0] = j;
                            viableSchedules[1] = i;
                            viableSchedules[2] = this.alocationClassCost(c, i, j);
                            c.getViableSchedules().add(viableSchedules);
                        }
                    }
                }
            }
        }

        return c;
    }

    /**
     * Fill table with -1
     */
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
