/**
 * Created by iBrunoTome on 6/19/16.
 */
public class Curricula {
    private String curriculaName;
    private String[] courses;
    private int nCourses;

    public Curricula() {

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
}
