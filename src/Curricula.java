/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Curricula {
    private int idx;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    private String curriculaName;
    private String[] courses;
    private int nCourses;

    public Curricula() {

    }

    public boolean containCourse(Course course) {
        for (String c : this.courses) {
            if (c.equals(course.getCourseName())) {
                return true;
            }
        }
        return false;
    }

    public String getCurriculaName() {
        return this.curriculaName;
    }

    public void setCurriculaName(String curriculaName) {
        this.curriculaName = curriculaName;
    }

    public int getnCourses() {
        return this.nCourses;
    }

    public void setnCourses(int nCourses) {
        this.nCourses = nCourses;
    }

    public String[] getCourses() {
        return this.courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public boolean equals(Curricula obj) {
        if ((this.curriculaName.equals(obj.getCurriculaName())) && ((this.nCourses == obj.getnCourses()))) {
            return true;
        } else {
            return false;
        }
    }
}
