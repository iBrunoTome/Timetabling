import java.util.ArrayList;

/**
 * Created by iBrunoTome on 6/29/16.
 */
public class Class {
    private int scheduleViability;
    private int idxClass;
    private ArrayList<Integer[]> viableSchedules = new ArrayList<>();


    public int getScheduleViability() {
        return this.scheduleViability;
    }

    public void setScheduleViability(int scheduleViability) {
        this.scheduleViability = scheduleViability;
    }

    public int getIdxClass() {
        return this.idxClass;
    }

    public void setIdxClass(int idxClass) {
        this.idxClass = idxClass;
    }

    public String toString() {
        return "idxClass: " + this.idxClass + "  ScheduleViability: " + this.scheduleViability + "\n";
    }

    public ArrayList<Integer[]> getViableSchedules() {
        return viableSchedules;
    }

    public void setViableSchedules(ArrayList<Integer[]> viableSchedules) {
        this.viableSchedules = viableSchedules;
    }
}
