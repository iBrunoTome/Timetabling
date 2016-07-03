/**
 * @author Bruno Tomé - 0011254
 * @author Cláudio Menezes - 0011255
 * @since 19/06/2016
 */
public class Constraint {
    private String courseName;
    private int day;
    private int period;

    public Constraint() {

    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public boolean equals(Constraint obj) {
        if ((this.courseName.equals(obj.getCourseName())) && ((this.day == obj.getDay()) && ((this.period == obj.getPeriod())))) {
            return true;
        } else {
            return false;
        }
    }
}
