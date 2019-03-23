package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name name;
    private final Module module;
    private final Due due;

    // Data fields
    private final Priority priority;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Module module, Due due, Priority priority, Set<Tag> tags) {
        requireAllNonNull(name, module, due, priority, tags);
        this.name = name;
        this.module = module;
        this.due = due;
        this.priority = priority;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Module getModule() {
        return module;
    }

    public Due getDue() {
        return due;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getMonth() {
        return due.toString().substring(3, 5);
    }

    public String getDay() {
        return due.toString().substring(0, 2);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && (otherTask.getModule().equals(getModule()) || otherTask.getDue().equals(getDue()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
                && otherTask.getModule().equals(getModule())
                && otherTask.getDue().equals(getDue())
                && otherTask.getPriority().equals(getPriority())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, module, due, priority, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Module: ")
                .append(getModule())
                .append(" Due: ")
                .append(getDue())
                .append(" Priority: ")
                .append(getPriority())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
