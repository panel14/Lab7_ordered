package itmo.manager;

import itmo.collection.VectorCollection;
import itmo.commands.*;
import itmo.data.StudyGroup;
import itmo.data.builders.StudyGroupBuilder;
import itmo.exceptions.CollectionException;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandArguments;
import itmo.utility.CommandInfo;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class helps to read commands
 */
public class CommandManager {

    private static final VectorCollection<StudyGroup> vectorCollection = Storage.storage.getAllCollection();

    public static CommandArguments readCommandArguments(String[] words, CommandInfo commandInfo, Scannable scannable, boolean isConsole, String[] user) throws Exception {

        if (words.length < commandInfo.argumentCount + 1)
            throw new CollectionException("there is not enough argumentsf");

        CommandArguments commandArguments = new CommandArguments();
        switch (commandInfo.commandName) {
            case "info":
                break;
            case "clear":
                commandArguments.arguments = new Object[] {new User(user)};
                break;
            case "show":
                break;
            case "save":
                break;
            case "server_close":
                break;
            case "add": {
                StudyGroup studyGroup = new StudyGroupBuilder(isConsole).build(scannable, user[0]);
                commandArguments.arguments = new Object[]{studyGroup, new User(user)};
                break;
            }
            case "help":
                break;
            case "remove_last":
                commandArguments.arguments = new Object[] {new User(user)};
                break;
            case "sum_of_should_be_expelled":
                break;
            case "execute_script":
                String filename = words[1];
                List<String> fileContent = Files.readAllLines(Paths.get(filename));
                StringBuilder stringBuilder = new StringBuilder();
                fileContent.forEach(s -> stringBuilder.append(s).append("\n"));

                commandArguments.arguments = new Object[]{words[1], stringBuilder.toString(), new User(user)};
                break;
            case "exit":
                break;
            case "remove_by_id":
                commandArguments.arguments = new Object[]{Integer.parseInt(words[1]), new User(user)};
                break;
            case "sort":
                break;
            case "remove_any_by_expelled_students":
                commandArguments.arguments = new Object[]{Integer.parseInt(words[1]), new User(user)};
                break;
            case "min_by_group_admin":
                commandArguments.arguments = new Object[]{new User(user)};
                break;
            case "update": {
                StudyGroup studyGroup = new StudyGroupBuilder(isConsole).build(scannable, user[0]);
                commandArguments.arguments = new Object[]{Integer.parseInt(words[1]), studyGroup, new User(user)};
                break;
            }
            case "add_if_max": {
                StudyGroup studyGroup = new StudyGroupBuilder(isConsole).build(scannable, user[0]);
                commandArguments.arguments = new Object[]{Integer.parseInt(words[1]), studyGroup, new User(user)};
                break;
            }
            default:
                throw new CollectionException("No command");
        }

        commandArguments.types = new Class[commandArguments.arguments.length];
        for (int i = 0; i < commandArguments.types.length; i++) {
            commandArguments.types[i] = commandArguments.arguments[i].getClass();
        }

        return commandArguments;
    }
    public static Command constructCommand(CommandInfo commandInfo, CommandArguments commandArguments, Printable printable) throws InvocationTargetException, InstantiationException, IllegalAccessException, CollectionException {

        List<Object> finalArguments = new ArrayList<>();
        switch (commandInfo.commandName) {
            case "info":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) InfoCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "clear":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) ClearCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "show":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) ShowCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "save":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) SaveCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "add": {
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
//                System.out.println(Arrays.toString(commandArguments.types));
//                finalArguments.forEach(elem -> System.out.println(elem.getClass()));
                return (Command) AddCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            }
            case "help":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) HelpCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "remove_last":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) RemoveLastCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "sum_of_should_be_expelled":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) SumOfShouldBeExpelledCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "execute_script":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) ExecuteScriptCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "exit":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) ExitCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "remove_by_id":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) RemoveByIdCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "sort":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) SortCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "remove_any_by_expelled_students":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) RemoveAnyByExpelledCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "min_by_group_admin":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) MinByGroupAdminCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "server_close":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) ServerShutdownCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "update":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) UpdateIdCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            case "add_if_max":
                finalArguments.add(printable);
                finalArguments.addAll(Arrays.asList(commandArguments.arguments));
                return (Command) AddIfMaxCommand.class.getConstructors()[0].newInstance(finalArguments.toArray());
            default:
                throw new CollectionException("No command");
        }
    }

    public static CommandInfo getCommandInfo(String name){
        CommandInfo commandInfo = new CommandInfo();
        commandInfo.commandName = name;
        switch (name) {
            case "info":
                commandInfo.argumentCount = 0;
                break;
            case "server_close":
                commandInfo.argumentCount = 0;
                break;
            case "clear":
                commandInfo.argumentCount = 0;
                break;
            case "show":
                commandInfo.argumentCount = 0;
                break;
            case "save":
                commandInfo.argumentCount = 0;
                break;
            case "add": {
                commandInfo.argumentCount = 0;
                break;
            }
            case "help":
                commandInfo.argumentCount = 0;
                break;
            case "remove_last":
                commandInfo.argumentCount = 0;
                break;
            case "sum_of_should_be_expelled":
                commandInfo.argumentCount = 0;
                break;
            case "execute_script":
                commandInfo.argumentCount = 1;
                break;
            case "exit":
                commandInfo.argumentCount = 0;
                break;
            case "remove_by_id":
                commandInfo.argumentCount = 1;
                break;
            case "sort":
                commandInfo.argumentCount = 0;
                break;
            case "remove_any_by_expelled_students":
                commandInfo.argumentCount = 1;
                break;
            case "min_by_group_admin":
                commandInfo.argumentCount = 0;
                break;
            case "update":
                commandInfo.argumentCount = 1;
                break;
            case "add_if_max":
                commandInfo.argumentCount = 1;
                break;
            default:
                commandInfo.error = true;
                commandInfo.errorMessage = "There is no such command";
        }

        return commandInfo;
    }
}
