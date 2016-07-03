/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Course {
    private String courseName;
    private String teacherName;
    private int idx;
    private int nClass;
    private int minClassDays;
    private int nStudents;

    public Course() {

    }

    public boolean equals(Course obj) {
        if (this.courseName.equals(obj.getCourseName()) && this.teacherName.equals(obj.getTeacherName())
                && this.nClass == obj.getnClass() && this.minClassDays == obj.getMinClassDays()
                && this.nStudents == obj.getnStudents()) {
            return true;
        } else {
            return false;
        }
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

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
