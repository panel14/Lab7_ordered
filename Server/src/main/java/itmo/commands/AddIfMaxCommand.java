package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.data.comparators.StudyGroupComparator;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;

import java.util.Optional;

/**
 * command that adds new StudyGroup if it is greater than all the elements that are already in collection
 */
public class AddIfMaxCommand implements Command {
    private final Printable printable;
    private final StudyGroup studyGroup;
    private final User user;

    /**
     * @param printable
     * @param studyGroup       group to add
     * @param user             data of current user
     */
    public AddIfMaxCommand(Printable printable, StudyGroup studyGroup, User user) {
        this.printable = printable;
        this.studyGroup = studyGroup;
        this.user = user;
    }

    /**
     * executes the command
     */
    @Override
    public void execute() throws Exception {
        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getUserCollection(user);
        Optional<StudyGroup> max = vectorCollection.stream().max(new StudyGroupComparator());
        if (!max.isPresent()) {
            Storage.storage.getAllCollection().add(studyGroup);
            return;
        }
        if (studyGroup.compareTo(max.get()) > 0) {
            Storage.storage.getAllCollection().add(studyGroup);
        }
    }
}
