import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {
    @Test
    public void searchShouldFindMatchingTasks() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Купить хлеб");
        Epic task2 = new Epic(2, new String[]{"Молоко", "Яйца"});
        Meeting task3 = new Meeting(3, "Выкатка", "НетоБанк", "Завтра");

        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        // Поиск по заголовку SimpleTask
        Task[] result1 = todos.search("хлеб");
        assertArrayEquals(new Task[]{task1}, result1);

        // Поиск по подзадаче Epic
        Task[] result2 = todos.search("Яйца");
        assertArrayEquals(new Task[]{task2}, result2);

        // Поиск по теме Meeting
        Task[] result3 = todos.search("Выкатка");
        assertArrayEquals(new Task[]{task3}, result3);
    }

    @Test
    public void searchShouldReturnEmptyArrayIfNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Позвонить"));
        todos.add(new Epic(2, new String[]{"Задача"}));
        todos.add(new Meeting(3, "Тема", "Проект", "Сейчас"));

        Task[] result = todos.search("несуществующий");
        assertEquals(0, result.length);
    }

    @Test
    public void searchShouldFindMultipleMatches() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Купить хлеб");
        Epic task2 = new Epic(2, new String[]{"Хлеб", "Масло"});
        Meeting task3 = new Meeting(3, "Обсуждение хлеба", "Продовольствие", "Завтра");

        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        // Поиск по общему запросу "хлеб"
        Task[] result = todos.search("хлеб");
        assertArrayEquals(new Task[]{task1, task2, task3}, result);
    }

    @Test
    public void testAddTask() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Test"));
        assertEquals(1, todos.findAll().length);
    }

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );


        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
}