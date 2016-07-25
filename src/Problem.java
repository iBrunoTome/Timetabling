import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Problem {
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
     * @param nameFileIn Is the name of ITC instance
     * @throws IOException Exception of file reader
     */
    public Problem(String nameFileIn) throws IOException {
        FileReader fileIn = new FileReader(nameFileIn);
        this.reader = new BufferedReader(fileIn);
        this.line = reader.readLine();
        this.instantiateProblem();
        this.classClass = new int[this.getTotalClass()][this.getTotalClass()];
        this.classSchedules = new int[this.getTotalClass()][this.getTotalSchedules()];
        this.fillClassClassMatrix();
        this.fillClassSchedulesMatrix();
    }

    /**
     * Check if two curses are equals
     *
     * @param c1 idx of course 1
     * @param c2 idx of course 2
     * @return true if the both courses are the same
     */
    public boolean sameCourse(int c1, int c2) {
        Course aux1 = this.getCourseFromInt(c1);
        Course aux2 = this.getCourseFromInt(c2);

        return aux1.equals(aux2);
    }

    /**
     * Return a course from an integer line or column from a matrix
     *
     * @param idx idx of course
     * @return Course from int parameter
     */
    public Course getCourseFromInt(int idx) {
        int total = 0;

        for (Course c : this.courses) {
            total += c.getnClass();
            if (idx <= total) {
                return c;
            }
        }

        return null;
    }

    /**
     * Return a curricula from an integer line or column from a matrix
     *
     * @param course for compare if curricula have this course
     * @return Curricula from the parameter course
     */
    public Curricula getCurriculaFromCourse(Course course) {
        for (Curricula c : this.curriculas) {
            if (c.containCourse(course)) {
                return c;
            }
        }

        return null;
    }

    /**
     * Return the quantity of rooms from an integer line or column from a matrix
     *
     * @param idx of the room
     * @return int indicating the capacity of the room
     */
    public int getRoomCapacity(int idx) {
        return this.rooms[idx].getCapacity();
    }

    /**
     * Check if the 2 courses have the same teacher
     *
     * @param line   is an int indicating the idx of a course
     * @param column is an int indicating the idx of a course
     * @return boolean true if courses have the same teacher, otherwise return false
     */
    private boolean courseSameTeacher(int line, int column) {
        if (this.getCourseFromInt(line) != null && this.getCourseFromInt(column) != null) {
            return this.getCourseFromInt(line).getTeacherName().equals(this.getCourseFromInt(column).getTeacherName());
        } else {
            return false;
        }
    }

    /**
     * Check if the 2 courses are in the same curricula
     *
     * @param line   is an int indicating the idx of a course
     * @param column is an int indicating the idx of a course
     * @return boolean if courses have same curricula, otherwise return false
     */
    public boolean courseSameCurricula(int line, int column) {
        Course aux1 = this.getCourseFromInt(line);
        Course aux2 = this.getCourseFromInt(column);

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

    /**
     * Check if constraint exist in the list of constraints
     *
     * @param line is the idx of a course
     * @param column is the total of days x periods
     * @return boolean true if the constraint exists, otherwise return false
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
     * @param line is the idx of a course
     * @param column is the total of days x periods
     * @return Constraint based on line and column of schedules
     */
    private Constraint unavailableSchedule(int line, int column) {
        int day = Math.abs(column / this.getnPeriodsPerDay());
        int turn = Math.abs(column / this.getnDays());

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
                if (this.constraintExist(l, c)) {
                    this.classSchedules[l][c] = 1;
                } else {
                    this.classSchedules[l][c] = 0;
                }
            }
        }
    }

    /**
     * Get the schedule number (H) for the matrix
     *
     * @return int is the total of days x periods
     */
    public int getTotalSchedules() {
        return this.getnDays() * this.getnPeriodsPerDay();
    }

    /**
     * Sum of classes
     *
     * @return int the sum of classes
     */
    public int getTotalClass() {
        int total = 0;
        for (Course course : this.courses) {
            total += course.getnClass();
        }
        return total;
    }

    /**
     * Fill the array of courses
     *
     * @throws IOException exception of file reader
     */
    private void fillCourses() throws IOException {
        String aux[];
        int idx = 0;
        while (!this.line.isEmpty()) {
            aux = this.line.split(" ");
            Course course = new Course();
            course.setIdx(idx);
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
     * @throws IOException expection of file reader
     */
    private void fillRooms() throws IOException {
        String aux[];
        int idx = 0;
        while (!this.line.isEmpty()) {
            aux = this.line.split(" ");
            Room room = new Room();
            room.setRoomName(aux[0]);
            room.setCapacity(Integer.parseInt(aux[1]));
            room.setIdx(idx);
            this.rooms[idx] = room;
            idx++;
            this.line = reader.readLine();
        }
    }

    /**
     * Fill the array of curriculas
     *
     * @throws IOException exception of file reader
     */
    private void fillCurriculas() throws IOException {
        String aux[];
        int idx = 0;
        while (!this.line.isEmpty()) {
            aux = this.line.split(" ");
            Curricula curricula = new Curricula();
            curricula.setIdx(idx);
            curricula.setCurriculaName(aux[0]);
            curricula.setnCourses(Integer.parseInt(aux[1]));
            String auxCourses[] = new String[curricula.getnCourses()];
            System.arraycopy(aux, 2, auxCourses, 0, curricula.getnCourses());
            curricula.setCourses(auxCourses);
            this.curriculas[idx] = curricula;
            idx++;
            this.line = reader.readLine();
        }
    }

    /**
     * Fill the array of constraints
     *
     * @throws IOException exception of file reader
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
     * @throws IOException exception of file reader
     */
    private void instantiateProblem() throws IOException {
        while (line != null) {
            if (line.startsWith("Name: ")) {
                this.setInstanceName(line.substring(7));
            } else if (line.startsWith("Course: ")) {
                this.courses = new Course[Integer.parseInt(this.line.substring(8))];
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

    public int[][] getClassSchedules() {
        return this.classSchedules;
    }
}
