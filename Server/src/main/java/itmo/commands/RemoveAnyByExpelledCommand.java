package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;
import itmo.utility.CommandFormatHeader;

import java.util.Optional;

/**
 * command that removes StudyGroup by the value of expelledStudents
 */
public class RemoveAnyByExpelledCommand implements Command {
    private final Printable printable;
    private final Integer expelledStudents;
    private final User user;

    /**
     * @param printable
     * @param expelledStudents the number of expelled students
     * @param user             data of current user
     */
    public RemoveAnyByExpelledCommand(Printable printable, Integer expelledStudents, User user) {
        this.printable = printable;
        this.expelledStudents = expelledStudents;
        this.user = user;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {

        printable.println(new CommandFormatHeader(50, this).toString());
        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getUserCollection(user);
        Optional<StudyGroup> optionalStudyGroup = vectorCollection.stream().filter(studyGroup -> studyGroup.getExpelledStudents().equals(expelledStudents)).findAny();
        if (!optionalStudyGroup.isPresent())
            return;

        StudyGroup studyGroupToRemove = optionalStudyGroup.get();
        Storage.storage.getAllCollection().remove(studyGroupToRemove);
    }
}
