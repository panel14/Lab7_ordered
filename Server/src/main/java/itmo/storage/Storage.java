package itmo.storage;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.service.DataBase;
import itmo.service.User;

/**
 * class singleton for working with collection
 */
//Класс синглтон - хранилище коллекции
//Чтобы множество раз не обращаться к бд мы храним коллекцию в этом классе, обращаясь к бд
//только при инициализации этого класса

public class Storage {
    public static Storage storage = new Storage();
    private static VectorCollection<StudyGroup> collection;

    /**
     * private constructor
     */
    private Storage() {
        collection = DataBase.getCollection();
    }

    /**
     * get all collection
     * @return full collection of groups
     */
    public synchronized VectorCollection<StudyGroup> getAllCollection() {
        return collection;
    }

    /**
     * get only current user's collection
     * @param user is a current owner
     * @return user's collection
     */
    public synchronized VectorCollection<StudyGroup> getUserCollection(User user){
        VectorCollection<StudyGroup> userCol = new VectorCollection<>();
        userCol.setCreationDate(collection.getCreationDate());
        for (StudyGroup group : collection){
            if (group.getOwner().equals(user.login))
                userCol.add(group);
        }
        return userCol;
    }

    public static synchronized void saveCollection(){
        DataBase.saveCollection(collection);
    }

    /**
     * remove a part from collection
     * @param all is a part that need to be removed
     */
    public static synchronized void removeAll(VectorCollection<StudyGroup> all) {
        if (!collection.isEmpty())
            collection.removeAll(all);
    }
}
