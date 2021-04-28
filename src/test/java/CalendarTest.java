import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class CalendarTest {

    Calendar calendar1;
    Calendar calendar2;
    Calendar calendar3;
    Util util;

    @Before
    public void init() {
        util = new Util();
        calendar1 = new Calendar(LocalTime.of(9, 00), LocalTime.of(19, 55));
        calendar1.addMeeting(LocalTime.of(9, 00), LocalTime.of(10, 30));
        calendar1.addMeeting(LocalTime.of(12, 00), LocalTime.of(13, 00));
        calendar1.addMeeting(LocalTime.of(16, 00), LocalTime.of(18, 00));
        calendar1.setEmptySlots(calendar1.getMeetings(), 30);
        calendar2 = new Calendar(LocalTime.of(10, 00), LocalTime.of(18, 30));
        calendar2.addMeeting(LocalTime.of(10, 00), LocalTime.of(11, 30));
        calendar2.addMeeting(LocalTime.of(12, 30), LocalTime.of(14, 30));
        calendar2.addMeeting(LocalTime.of(14, 30), LocalTime.of(15, 00));
        calendar2.addMeeting(LocalTime.of(16, 00), LocalTime.of(17, 00));
        calendar2.setEmptySlots(calendar2.getMeetings(), 30);
        calendar3=new Calendar(LocalTime.of(8,30),LocalTime.of(17,30));
        calendar3.addMeeting(LocalTime.of(9,00),LocalTime.of(12,00));
        calendar3.addMeeting(LocalTime.of(13,20),LocalTime.of(14,15));
        calendar3.addMeeting(LocalTime.of(15,00),LocalTime.of(17,30));
        calendar3.setEmptySlots(calendar3.getMeetings(),25);
    }

    @Test
    public void findMutualEmptySlotsCalendar1And2() {
        //given
        List<TimeRange> mergedEmptySlots = util
            .mergedEmptySlots(calendar1.getEmptySlots(),
                calendar2.getEmptySlots());
        //when
        util.setFreeSlots(mergedEmptySlots,30);
        TimeRange timeRange = new TimeRange(LocalTime.of(11, 30),
            LocalTime.of(12, 00));
        //then
        assertEquals(timeRange, util.getFreeSlots().get(0));
    }
    @Test
    public void findMutualEmptySlotsCalendar2And3() {
        //given
        List<TimeRange> mergedEmptySlots = util
            .mergedEmptySlots(calendar2.getEmptySlots(),
                calendar3.getEmptySlots());
        //when
        util.setFreeSlots(mergedEmptySlots,25);

        TimeRange timeRange = new TimeRange(LocalTime.of(12, 00),
            LocalTime.of(12, 30));
        //then
        assertEquals(timeRange, util.getFreeSlots().get(0));
        assertEquals(util.getFreeSlots().size(),1);
    }
}
