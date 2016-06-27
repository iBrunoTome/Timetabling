import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Problem {
    private FileReader fileIn;
    private BufferedReader reader;
    private String instanceName;
    private String line;
    private Curricula[] curriculas;
    private Course[] courses;
    private Room[] rooms;
    private Constraint[] constraints;
    private int[][] classClass;
    private int[][] classSchedules;
    private int nRooms;
    private int nDays;
    private int nPeriodsPerDay;

    /**
     * Instantiate the problem, read the filein and fill all the necessary matrix
     *
     * @param nameFileIn
     * @throws IOException
     */
    public Problem(String nameFileIn) throws IOException {
        this.fileIn = new FileReader(nameFileIn);
        this.reader = new BufferedReader(this.fileIn);
        this.line = reader.readLine();
        this.instantiateProblem();
        this.classClass = new int[this.getTotalClass()][this.getTotalClass()];
        this.classSchedules = new int[this.getTotalClass()][this.getTotalSchedules()];
        this.fillClassClassMatrix();
        this.fillClassSchedulesMatrix();
    }

    /**
     * Return a course from an integer line or column from a matrix
     *
     * @param idxLine
     * @return Course
     */
    private Course getCourseFromInt(int idxLine) {

        int total = 0;
        for (Course c : this.courses) {
            total += c.getnClass();
            if (idxLine <= total) {
                return c;
            }
        }

        return null;
    }

    /**
     * Check if the the 2 courses have the same teacher
     *
     * @param line
     * @param column
     * @return boolean
     */
    private boolean courseSameTeacher(int line, int column) {
        if (getCourseFromInt(line) != null && getCourseFromInt(column) != null) {
            return getCourseFromInt(line).getTeacherName().equals(getCourseFromInt(column).getTeacherName()) ? true : false;
        } else {
            return false;
        }
    }

    /**
     * Check if the the 2 courses are in the same curricula
     *
     * @param line
     * @param column
     * @return boolean
     */
    private boolean courseSameCurricula(int line, int column) {
        Course aux1;
        Course aux2;

        aux1 = this.getCourseFromInt(line);
        aux2 = this.getCourseFromInt(column);

        for (Curricula c : this.curriculas) {
            if (c.containCourse(aux1) && c.containCourse(aux2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Fill the classClass matrix
     * <p>
     * <p>Fill with 2 when the classes are in the same course</p>
     * <p>Fill with 1 when the classes has the same teacher or the classes are in the same curricula</p>
     * <p>Fill with zero if had no one of constraints above</p>
     */
    private void fillClassClassMatrix() {
        for (int l = 0; l < this.getTotalClass(); l++) {
            for (int c = 0; c < this.getTotalClass(); c++) {
                if (getCourseFromInt(l) != null && getCourseFromInt(c) != null && getCourseFromInt(l).equals(getCourseFromInt(c))) {
                    this.classClass[l][c] = 2;
                } else if (courseSameTeacher(l, c) || (courseSameCurricula(l, c))) {
                    this.classClass[l][c] = 1;
                } else {
                    this.classClass[l][c] = 0;
                }
            }
        }
    }

    // abs(totalSchedules / periodsPerDay) + 1 == dia atual
    // abs(totalSchedules / day) + 1 == turno atual

    /**
     * Check if constraint exist in the array of constraints
     *
     * @param line
     * @param column
     * @return boolean
     */
    private boolean constraintExist(int line, int column) {
        Constraint constraint = unavailableSchedule(line, column);

        for (Constraint c : this.constraints) {
            if (constraint.equals(c)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if a class is available in the schedule
     *
     * @param line
     * @param column
     * @return Constraint
     */
    private Constraint unavailableSchedule(int line, int column) {
        int day = Math.abs(this.getTotalSchedules() / this.getnPeriodsPerDay()) + 1;
        int turn = Math.abs(this.getTotalSchedules() / this.getnDays()) + 1;
        System.out.println("Dia: " + day);
        System.out.println("Truno: " + turn);

        Course auxCourse = getCourseFromInt(line);
        Constraint auxConstraint = new Constraint();
        auxConstraint.setCourseName(auxCourse.getCourseName());
        auxConstraint.setDay(day);
        auxConstraint.setPeriod(turn);

        return auxConstraint;
    }

    /**
     * Fill the classSchedules matrix
     * <p>
     * <p>Fill with 1 when the classes is unavailable in a schedule</p>
     * <p>Fill with 0 when the classes is available in a schedule</p>
     */
    private void fillClassSchedulesMatrix() {
        for (int l = 0; l < this.getTotalClass(); l++) {
            for (int c = 0; c < this.getTotalSchedules(); c++) {
                if (constraintExist(l, c)) {
                    this.classClass[l][c] = 1;
                } else {
                    this.classClass[l][c] = 0;
                }
            }
        }
    }

    /**
     * Get the schedule number (H) for the matrix
     *
     * @return
     */
    private int getTotalSchedules() {
        return this.getnDays() * this.getnPeriodsPerDay();
    }

    /**
     * Sum num of students to fill matrix (N)
     *
     * @return
     */
    private int getTotalClass() {
        int total = 0;
        for (int i = 0; i < this.courses.length; i++) {
            total += this.courses[i].getnStudents();
        }
        return total;
    }

    /**
     * Fill the array of courses
     *
     * @throws IOException
     */
    private void fillCourses() throws IOException {
        String aux[];
        int idx = 0;
        while (!this.line.isEmpty()) {
            aux = this.line.split(" ");
            Course course = new Course();
            course.setCourseName(aux[0]);
            course.setTeacherName(aux[1]);
            course.setnClass(Integer.parseInt(aux[2]));
            course.setMinClassDays(Integer.parseInt(aux[3]));
            course.setnStudents(Integer.parseInt(aux[4]));
            this.courses[idx] = course;
            idx++;
            this.line = reader.readLine();
        }
    }

    /**
     * Fill the array of rooms
     *
     * @throws IOException
     */
    private void fillRooms() throws IOException {
        String aux[];
        int idx = 0;
        while (!this.line.isEmpty()) {
            aux = this.line.split("\t");
            Room room = new Room();
            room.setRoomName(aux[0]);
            room.setCapacity(Integer.parseInt(aux[1]));
            this.rooms[idx] = room;
            idx++;
            this.line = reader.readLine();
        }
    }

    /**
     * Fill the array of curriculas
     *
     * @throws IOException
     */
    private void fillCurriculas() throws IOException {
        String auxLeft[];
        String auxRight[];
        int idx = 0;
        while (!this.line.isEmpty()) {
            auxLeft = this.line.split("  ");
            auxRight = auxLeft[1].split(" ");
            Curricula curricula = new Curricula();
            curricula.setCurriculaName(auxLeft[0]);
            curricula.setnCourses(Integer.parseInt(auxRight[0]));
            String auxCourses[] = new String[curricula.getnCourses()];
            for (int i = 0; i < curricula.getnCourses(); i++) {
                auxCourses[i] = auxRight[i + 1];
            }
            curricula.setCourses(auxCourses);
            this.curriculas[idx] = curricula;
            idx++;
            this.line = reader.readLine();
        }
    }

    /**
     * Fill the array of constraints
     *
     * @throws IOException
     */
    private void fillConstraints() throws IOException {
        String aux[];
        int idx = 0;
        while (!this.line.isEmpty()) {
            aux = this.line.split(" ");
            Constraint constraint = new Constraint();
            constraint.setCourseName(aux[0]);
            constraint.setDay(Integer.parseInt(aux[1]));
            constraint.setPeriod(Integer.parseInt(aux[2]));
            this.constraints[idx] = constraint;
            idx++;
            this.line = reader.readLine();
        }
    }

    /**
     * Read the text and fill the arrays
     *
     * @throws IOException
     */
    private void instantiateProblem() throws IOException {
        while (line != null) {
            if (line.startsWith("Name: ")) {
                this.setInstanceName(line.substring(7));
            } else if (line.startsWith("Courses: ")) {
                this.courses = new Course[Integer.parseInt(this.line.substring(9))];
            } else if (line.startsWith("Rooms: ")) {
                this.setnRooms(Integer.parseInt(line.substring(7)));
                this.rooms = new Room[this.getnRooms()];
            } else if (line.startsWith("Days: ")) {
                this.setnDays(Integer.parseInt(line.substring(6)));
            } else if (line.startsWith("Periods_per_day: ")) {
                this.setnPeriodsPerDay(Integer.parseInt(line.substring(17)));
            } else if (line.startsWith("Curricula: ")) {
                this.curriculas = new Curricula[Integer.parseInt(line.substring(11))];
            } else if (line.startsWith("Constraints: ")) {
                this.constraints = new Constraint[Integer.parseInt(line.substring(13))];
            } else if (this.line.startsWith("COURSES:")) {
                this.line = this.reader.readLine();
                fillCourses();
            } else if (this.line.startsWith("ROOMS:")) {
                this.line = this.reader.readLine();
                fillRooms();
            } else if (this.line.startsWith("CURRICULA:")) {
                this.line = this.reader.readLine();
                fillCurriculas();
            } else if (this.line.startsWith("UNAVAILABILITY_CONSTRAINTS:")) {
                this.line = this.reader.readLine();
                fillConstraints();
            } else if (this.line.startsWith("END.")) {
                break;
            }

            // System.out.println(line);
            line = this.reader.readLine();
        }
    }

    public String getInstanceName() {
        return this.instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public int getnRooms() {
        return this.nRooms;
    }

    public void setnRooms(int nRooms) {
        this.nRooms = nRooms;
    }

    public int getnDays() {
        return this.nDays;
    }

    public void setnDays(int nDays) {
        this.nDays = nDays;
    }

    public int getnPeriodsPerDay() {
        return this.nPeriodsPerDay;
    }

    public void setnPeriodsPerDay(int nPeriodsPerDay) {
        this.nPeriodsPerDay = nPeriodsPerDay;
    }

    public Curricula[] getCurriculas() {
        return this.curriculas;
    }

    public void setCurriculas(Curricula[] curriculas) {
        this.curriculas = curriculas;
    }

    public Course[] getCourses() {
        return this.courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public Room[] getRooms() {
        return this.rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public Constraint[] getConstraints() {
        return this.constraints;
    }

    public void setConstraints(Constraint[] constraints) {
        this.constraints = constraints;
    }

    public int[][] getClassClass() {
        return this.classClass;
    }

    public void setClassClass(int[][] classClass) {
        this.classClass = classClass;
    }

    public int[][] getClassSchedules() {
        return this.classSchedules;
    }

    public void setClassSchedules(int[][] classSchedules) {
        this.classSchedules = classSchedules;
    }
}
