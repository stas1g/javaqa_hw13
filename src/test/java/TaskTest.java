import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    // Тесты для SimpleTask
    @Test
    public void simpleTaskMatchesTitle() {
        SimpleTask task = new SimpleTask(1, "Купить хлеб");
        assertTrue(task.matches("хлеб"));
    }

    @Test
    public void simpleTaskNotMatchesTitle() {
        SimpleTask task = new SimpleTask(1, "Позвонить родителям");
        assertFalse(task.matches("молоко"));
    }

    // Тесты для Epic
    @Test
    public void epicMatchesSubtasks() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца"});
        assertTrue(epic.matches("Яйца"));
    }

    @Test
    public void epicNotMatchesSubtasks() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Яйца"});
        assertFalse(epic.matches("Хлеб"));
    }

    // Тесты для Meeting
    @Test
    public void meetingMatchesTopic() {
        Meeting meeting = new Meeting(3, "Выкатка", "НетоБанк", "Завтра");
        assertTrue(meeting.matches("Выкатка"));
    }

    @Test
    public void meetingMatchesProject() {
        Meeting meeting = new Meeting(3, "Обсуждение", "НетоБанк", "Завтра");
        assertTrue(meeting.matches("НетоБанк"));
    }

    @Test
    public void meetingNotMatches() {
        Meeting meeting = new Meeting(3, "Выкатка", "НетоБанк", "Завтра");
        assertFalse(meeting.matches("Обед"));
    }
}
