import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by iBrunoTome on 6/19/16.
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
    private int[][] AA;
    private int[][] AI;
    private int nRooms;
    private int nDays;
    private int nPeriodsPerDay;
    private int idxLine = 1;

    public Problem() {

    }

    public Problem(String nameFileIn) throws IOException {
        this.fileIn = new FileReader(nameFileIn);
        this.reader = new BufferedReader(this.fileIn);
        this.line = reader.readLine();
        this.instantiateProblem();

    }

    /**
     * Preenche o vetor de cursos
     *
     * @throws IOException
     */
    private void fillCourses() throws IOException {
        String aux[];
        while (!Character.isWhitespace(this.line.charAt(0))) {
            aux = this.line.split(" ");
            Course course = new Course();
            course.setCourseName(aux[0]);
            course.setTeacherName(aux[1]);
            course.setnStudents(Integer.parseInt(aux[2]));
            course.setMinClassDays(Integer.parseInt(aux[3]));
            course.setnStudents(Integer.parseInt(aux[4]));
            this.line = reader.readLine();
        }
    }

    private void fillRooms() throws IOException {
        String aux[];
        while (!Character.isWhitespace(this.line.charAt(0))) {
            aux = this.line.split("\t");
            Room room = new Room();
            room.setRoomName(aux[0]);
            room.setCapacity(Integer.parseInt(aux[1]));
            this.line = reader.readLine();
        }
    }

    /**
     * Lê o texto e preenche os vetores
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
            } else if (line.startsWith("Days: ")) {
                this.setnDays(Integer.parseInt(line.substring(6)));
            } else if (line.startsWith("Periods_per_day: ")) {
                this.setnPeriodsPerDay(Integer.parseInt(line.substring(17)));
            } else if (line.startsWith("Curricula: ")) {
                this.curriculas = new Curricula[Integer.parseInt(line.substring(11))];
            } else if (line.startsWith("Constraints: ")) {
                this.constraints = new Constraint[Integer.parseInt(line.substring(13))];
            } else if (this.line.startsWith("COURSES: ")) {
                this.line = this.reader.readLine();
                fillCourses();
            } else if (this.line.startsWith("ROOMS: ")) {
                this.line = this.reader.readLine();
                fillRooms();
            }

            System.out.println(line);
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

    public int[][] getAA() {
        return this.AA;
    }

    public void setAA(int[][] AA) {
        this.AA = AA;
    }

    public int[][] getAI() {
        return this.AI;
    }

    public void setAI(int[][] AI) {
        this.AI = AI;
    }
}
