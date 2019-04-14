package seedu.address.model.util;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTaskManager;
import seedu.address.model.TaskManager;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Due;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code TaskManager} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        String currMonth = Integer.toString(YearMonth.now().getMonthValue());
        if (currMonth.length() == 1) {
            currMonth = "0" + currMonth;
        }

        return new Task[]{
            new Task(new Name("Revision"), new Module("CG2023"), new Due("06-" + currMonth),
                    new Priority("1"),
                    getTagSet("examNextDay")),
            new Task(new Name("Oral Presentation"), new Module("CS2101"), new Due("12-" + currMonth),
                    new Priority("2"),
                    getTagSet("classParticipation")),
            new Task(new Name("Tutorial 3"), new Module("GES1003"), new Due("28-" + currMonth),
                    new Priority("3"),
                    getTagSet("ungraded")),
            new Task(new Name("Add Test Cases"), new Module("CS2113T"), new Due("14-" + currMonth),
                    new Priority("1"),
                    getTagSet("")),
            new Task(new Name("Product Demo"), new Module("CS2113T"), new Due("15-" + currMonth),
                    new Priority("1"),
                    getTagSet("graded")),
            new Task(new Name("Final Reflections"), new Module("CS2101"), new Due("15-" + currMonth),
                    new Priority("1"),
                    getTagSet("")),
            new Task(new Name("Rewatch Lecture 6"), new Module("CG2023"), new Due("17-" + currMonth),
                    new Priority("2"),
                    getTagSet("")),
            new Task(new Name("Finals"), new Module("CG2028"), new Due("20-" + currMonth),
                    new Priority("1"),
                    getTagSet("")),
            new Task(new Name("Finals"), new Module("GES1003"), new Due("27-" + currMonth),
                    new Priority("1"),
                    getTagSet("")),
            new Task(new Name("Redo Tutorial 2"), new Module("CG2028"), new Due("1-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Tutorial 4"), new Module("CG2028"), new Due("4-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Rewatch Lecture 4"), new Module("CS2113T"), new Due("11-" + currMonth),
                    new Priority("1"),
                    getTagSet("")),
            new Task(new Name("PPP Submission"), new Module("CS2101"), new Due("15-" + currMonth),
                    new Priority("2"),
                    getTagSet("")),
            new Task(new Name("Lab Report"), new Module("CG2028"), new Due("19-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("PE"), new Module("CS2113T"), new Due("17-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Watch Lecture 10"), new Module("GES1003"), new Due("28-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Consultation"), new Module("CG2023"), new Due("30-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Tutorial 1"), new Module("CG2023"), new Due("2-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Tutorial 2"), new Module("CG2023"), new Due("4-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Tutorial 3"), new Module("CG2023"), new Due("6-" + currMonth),
                    new Priority("3"),
                    getTagSet("")),
        };
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        TaskManager sampleTm = new TaskManager();
        for (Task sampleTask : getSampleTasks()) {
            sampleTm.addTask(sampleTask);
        }
        return sampleTm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
