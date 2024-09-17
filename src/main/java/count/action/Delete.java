package count.action;

import count.TaskList;
import count.exception.CountException;
import count.task.Task;

/**
 * The Delete class is used to delete tasks to the TaskList
 */
public class Delete extends Action {
    private TaskList ls;
    private int index;
    private String filePath;

    /**
     * Constructs Delete object
     * @param ls TaskList to delete the task from
     * @param index int index of the Task in TaskList to delete
     * @param filePath String of the file path to create Save objects
     */
    public Delete(TaskList ls, int index, String filePath) {
        this.ls = ls;
        this.index = index;
        this.filePath = filePath;
    }

    /**
     * Deletes the Task with the index of index from TaskList and saves the TaskList afterward
     * @return String for Count's UI to print
     * @throws CountException if the index selected is out of bounds for the TaskList or if save fails
     */
    @Override
    public String run() throws CountException {
        try {
            Task taskRemoved = ls.getTaskList().get(this.index - 1);
            ls.getTaskList().remove(this.index - 1);
            String saveString = new Save(this.ls, this.filePath).run();
            return "Ribbit, I have removed this task from the list:\n"
                    + taskRemoved.toString() + "\nYou now have " + ls.getTaskList().size() + " task(s) in your list";
        } catch (IndexOutOfBoundsException e) {
            throw new CountException("Croak! Invalid list index chosen! Choose a number from 1 to "
                    + ls.getTaskList().size() + "\nType 'help' to see correct formatting examples");
        }
    }
}
