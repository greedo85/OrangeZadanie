import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@EqualsAndHashCode
public class TimeRange implements Comparable<TimeRange> {

    private LocalTime start;
    private LocalTime end;

    public TimeRange( LocalTime start, LocalTime end   ) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "\nTimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public int compareTo( TimeRange o ) {
       int starHour=this.start.compareTo(o.getStart());
       return starHour;
    }
}
