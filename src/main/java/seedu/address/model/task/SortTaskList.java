package seedu.address.model.task;

import java.util.Collections;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SortTaskList {

    public ObservableList<Task> sortTask(ObservableList<Task> internalList, String method) {

        FXCollections.sort(internalList, new Comparator<Task>() {
            @Override
            public int compare(Task self, Task other) {
                switch(method) {
                    case ("name"): {
                        return self.getName().toString().compareTo(other.getName().toString());
                    }
                    case ("module"): {
                        return self.getModule().toString().compareTo(other.getModule().toString());
                    }
                    //case ("date"): {
                    //    return self.getDate().compareTo(other.getDate());
                    //}
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
