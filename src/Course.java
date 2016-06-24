/**
 * Created by iBrunoTome on 6/19/16.
 */
public class Course {
    private String courseName;
    private String teacherName;
    private int nClass;
    private int minClassDays;
    private int nStudents;

    public Course() {

    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getnClass() {
        return this.nClass;
    }

    public void setnClass(int nClass) {
        this.nClass = nClass;
    }

    public int getMinClassDays() {
        return this.minClassDays;
    }

    public void setMinClassDays(int minClassDays) {
        this.minClassDays = minClassDays;
    }

    public int getnStudents() {
        return this.nStudents;
    }

    public void setnStudents(int nStudents) {
        this.nStudents = nStudents;
    }
}
