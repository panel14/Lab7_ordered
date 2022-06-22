package itmo.collection;

import java.time.LocalDateTime;
import java.util.Vector;


public class VectorCollection<T> extends Vector<T> {

    private LocalDateTime creationDate;

    public VectorCollection() {
        creationDate = LocalDateTime.now();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
