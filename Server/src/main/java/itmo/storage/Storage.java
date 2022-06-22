package itmo.storage;

import itmo.collection.VectorCollection;
import itmo.data.StudyGroup;
import itmo.service.DataBase;
import itmo.service.User;

public class Storage {
    public static Storage storage = new Storage();
    private static VectorCollection<StudyGroup> collection;

    private Storage() {
        collection = DataBase.getCollection();
    }

    public synchronized VectorCollection<StudyGroup> getAllCollection() {
        return collection;
    }

    public static synchronized void supplementAllCollection(VectorCollection<StudyGroup> all) {
        collection.addAll(all);
    }

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
    
    public static synchronized void removeAll(VectorCollection<StudyGroup> all) {
        if (!collection.isEmpty())
            collection.removeAll(all);
    }
}
