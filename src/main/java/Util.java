
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public class Util {

    private List<TimeRange> freeSlots = new ArrayList<>();

    public List<TimeRange> mergedEmptySlots(List<TimeRange> ... emptyslots) {

        List<TimeRange> mergedList = Stream.of(emptyslots)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        Collections.sort(mergedList);
        return mergedList;
    }

    public void setFreeSlots(List<TimeRange> mergedSlots,long duration) {
        for (int i = 0; i < mergedSlots.size() - 1; i++) {
            LocalTime endCurrent = mergedSlots.get(i).getEnd();
            LocalTime startNext = mergedSlots.get(i + 1).getStart();
            if(!startNext.isAfter(endCurrent)&& !(Duration.between(startNext,
                endCurrent).toMinutes() <duration))
            freeSlots.add(new TimeRange(startNext,endCurrent));
        }
    }
}
