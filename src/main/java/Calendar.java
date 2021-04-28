import lombok.Getter;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Getter

public class Calendar {

    private LocalTime workStart;
    private List<TimeRange> meetings;
    private LocalTime workEnd;
    private List<TimeRange> emptySlots;

    public Calendar( LocalTime start, LocalTime end ) {
        workStart = start;
        workEnd = end;
        meetings = new ArrayList<>();
        emptySlots = new ArrayList<>();
    }

    public void addMeeting( LocalTime start, LocalTime end ) {
        if (!start.isBefore(workStart) && !end.isAfter(workEnd)) {
            TimeRange timeRange = new TimeRange(start, end);
            meetings.add(timeRange);
        }
    }

    public void setEmptySlots( List<TimeRange> meetings, long duration ) {
        int lastMeeting = meetings.size() - 1;
        if (!workStart.equals(meetings.get(0).getStart()) && Duration.between(workStart, meetings.get(0).getStart()).toMinutes() >= duration) {
            emptySlots.add(new TimeRange(workStart, meetings.get(0).getStart()));
        }
        for (int i = 0; i < meetings.size() - 1; i++) {
            if(!(meetings.get(i).getEnd().equals(meetings.get(i + 1).getStart())))
            {
                TimeRange timeRange=new TimeRange(meetings.get(i).getEnd(),
                    meetings.get(i + 1).getStart());
                if(Duration.between(timeRange.getStart(),timeRange.getEnd()).toMinutes()>=duration)
                emptySlots.add(timeRange);
            }
        }
        if (!workEnd.equals(meetings.get(lastMeeting).getEnd()) && Duration.between(meetings.get(lastMeeting).getEnd(), workEnd).toMinutes() >= duration) {
            emptySlots.add(new TimeRange(meetings.get(lastMeeting).getEnd(), workEnd));
        }
    }

    @Override
    public String toString() {
        return "\nCalendar{" +
            "workStart=" + workStart +
            "\n meetings=" + meetings +
            "\n workEnd=" + workEnd +
            "\n\n emptySlots=" + emptySlots +
            '}';
    }
}
