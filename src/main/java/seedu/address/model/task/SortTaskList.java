package seedu.address.model.task;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Sorts the task list according to the attribute
 */
public class SortTaskList {
    /**
     * @param internalList
     * @param attribute
     * @return sorted internalList
     */
    public ObservableList<Task> sortTask(ObservableList<Task> internalList, String attribute) {

        FXCollections.sort(internalList, new Comparator<Task>() {
            @Override
            public int compare(Task self, Task other) {
                switch(attribute) {
                    case ("name"): {
                        return self.getName().toString().compareTo(other.getName().toString());
                    }
                    case ("module"): {
                        return self.getModule().toString().compareTo(other.getModule().toString());
                    }
                    case ("date"): {
                        return self.getDue().getDate().compareTo(other.getDue().getDate());
                    }
                    case ("priority"): {
                        return Integer.parseInt(self.getPriority().toString())
                                - Integer.parseInt(other.getPriority().toString());
                    }
                    default:
                        return 0;
                }
            }

        });
        return internalList;
    }
}
