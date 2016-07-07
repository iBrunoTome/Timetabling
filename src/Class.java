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
        return "idxClass: " + this.idxClass + "  ScheduleViability: " + this.scheduleViability + " room: " + this.viableSchedules.get(0)[0] + " Schedule: " + this.viableSchedules.get(0)[1] + " cost: " + this.viableSchedules.get(0)[2] + "\n";
    }

    public ArrayList<Integer[]> getViableSchedules() {
        return this.viableSchedules;
    }


}
