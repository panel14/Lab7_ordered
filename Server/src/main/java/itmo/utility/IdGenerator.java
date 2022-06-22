package itmo.utility;

import java.util.Random;

/**
 * generates id
 */
public class IdGenerator {

    private static final Random random = new Random();

    public static Integer generateId() {
        Integer id = random.nextInt();
        return (id <= 0 ? generateId() : id);
    }
}
