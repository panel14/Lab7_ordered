package itmo.commands;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.io.Printable;
import itmo.service.User;
import itmo.storage.Storage;

import java.util.Optional;

/**
 * command that updates StudyGroup that has given Id
 */
public class UpdateIdCommand implements Command {
    private final Printable printable;
    private final Integer id;
    private final StudyGroup studyGroup;
    private final User user;

    /**
     * @param printable
     * @param id               id
     * @param studyGroup       studyGroup
     * @param user             data of current user
     */
    public UpdateIdCommand( Printable printable, Integer id, StudyGroup studyGroup, User user) {
        this.printable = printable;
        this.id = id;
        this.studyGroup = studyGroup;
        this.user = user;
    }

    /**
     * executes command
     */
    @Override
    public void execute() throws Exception {
        VectorCollection<StudyGroup> vectorCollection = Storage.storage.getUserCollection(user);
        Optional<StudyGroup> idElementOptional = vectorCollection.stream().filter(studyGroup -> studyGroup.getId().equals(id)).findAny();
        if (!idElementOptional.isPresent()) {
            printable.println("There is no element with this id");
            return;
        }

        StudyGroup removed = idElementOptional.get();
        Storage.storage.getAllCollection().remove(removed);
        studyGroup.setId(id);
        Storage.storage.getAllCollection().add(studyGroup);
    }
}
