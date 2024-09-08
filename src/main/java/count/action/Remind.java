package count.action;

import java.time.LocalDate;
import java.util.ArrayList;

import count.TaskList;
import count.task.Deadline;
import count.task.Event;
import count.task.Task;

/**
 * The Reminder class handles giving reminders
 * whenever Count is opened and there are tasks
 * stored close to the date of completion
 * @author Kieran Koh Jun Wei
 */
public class Remind extends Action {
    protected TaskList taskList;
    protected int days;
    protected LocalDate currentTime;

    /**
     * Constructor for remind
     * @param taskList TaskList being searched
     * @param days Int for tasks with due dates within number of days
     */
    public Remind(TaskList taskList, int days) {
        this.taskList = taskList;
        this.days = days;
        this.currentTime = LocalDate.now();
    }

    @Override
    public String run() {
        String ans = "Here are the tasks in your list due in " + this.days + " days\n";
        ArrayList<Task> list = this.taskList.getList();
        LocalDate dueByTime = currentTime.plusDays(this.days);
        int numberOfSearchHits = 0;

        for (Task t: list) {
            if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                if (deadline.getEndTime().isBefore(dueByTime)) {
                    numberOfSearchHits++;
                    ans += numberOfSearchHits + ". " + deadline.toString() + "\n";
                }
            }

            if (t instanceof Event) {
                Event event = (Event) t;
                if (event.getEndTime().isBefore(dueByTime)) {
                    numberOfSearchHits++;
                    ans += numberOfSearchHits + ". " + event.toString() + "\n";
                }
            }
        }
        return ans;
    }
}
