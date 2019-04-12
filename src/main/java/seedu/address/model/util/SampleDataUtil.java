package seedu.address.model.util;

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
        return new Task[]{
            new Task(new Name("Revision"), new Module("CG2023"), new Due("16-04"),
                    new Priority("1"),
                    getTagSet("examNextDay")),
            new Task(new Name("Oral Presentation"), new Module("CS2101"), new Due("12-04"),
                    new Priority("2"),
                    getTagSet("classParticipation")),
            new Task(new Name("Tutorial 3"), new Module("GES1003"), new Due("20-04"),
                    new Priority("3"),
                    getTagSet("ungraded")),
            new Task(new Name("Lecture 6 Notes"), new Module("CG2023"), new Due("30-04"),
                    new Priority("3"),
                    getTagSet("")),
            new Task(new Name("Optimise Project"), new Module("CG2028"), new Due("19-04"),
                    new Priority("1"),
                    getTagSet("")),
            new Task(new Name("Plot Bode Plot"), new Module("CG2023"), new Due("12-04"),
                    new Priority("1"),
                    getTagSet("")),
            new Task(new Name("Rewatch Lecture 2"), new Module("CS2113T"), new Due("21-04"),
                    new Priority("2"),
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
