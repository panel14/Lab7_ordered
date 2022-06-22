package itmo.commands;

import itmo.io.Printable;
import itmo.utility.CommandFormatHeader;

import java.io.IOException;

/**
 * command that helps the user with commands
 */
public class HelpCommand implements Command {
    private final Printable printable;
    public HelpCommand(Printable printable) {
        this.printable = printable;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws IOException {
        printable.println(new CommandFormatHeader(50, this).toString());
        printable.println(
                "help : вывести справку по доступным командам\n" +
                        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                        "add {element} : добавить новый элемент в коллекцию\n" +
                        "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                        "remove_by_id id : удалить элемент из коллекции по его id\n" +
                        "clear : очистить коллекцию\n" +
                        "save : сохранить коллекцию\n" +
                        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                        "exit : завершить программу (без сохранения в файл)\n" +
                        "remove_last : удалить последний элемент из коллекции\n" +
                        "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                        "sort : отсортировать коллекцию в естественном порядке\n" +
                        "remove_any_by_expelled_students expelledStudents : удалить из коллекции один элемент, значение поля expelledStudents которого эквивалентно заданному\n" +
                        "sum_of_should_be_expelled : вывести сумму значений поля shouldBeExpelled для всех элементов коллекции\n" +
                        "min_by_group_admin : вывести любой объект из коллекции, значение поля groupAdmin которого является минимальным\n" +
                        "server_close: выключает сервер"
        );
    }
}
