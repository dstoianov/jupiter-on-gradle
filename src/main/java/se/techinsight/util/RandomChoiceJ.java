package se.techinsight.util;


import java.security.SecureRandom;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/*
 *      This Class is not about Java Performance, this is about the **choice**
 */
public class RandomChoiceJ {

    private final Random random = new SecureRandom();

    public <T> T choice(List<T> list) {
        if (list.isEmpty()) {
            throw new RandomChoiceException("List must not be empty");
        }
        return list.get(random.nextInt(list.size()));
    }

    public <T> T choice(T[] array) {
        if (array.length > 0) {
            throw new RandomChoiceException("Array must not be empty");
        }
        return array[random.nextInt(array.length)];
    }

    public <E> E choice(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            throw new RandomChoiceException("Collection must not be empty");
        }

        int index = random.nextInt(collection.size());
        if (collection instanceof List) { // optimization
            return ((List<? extends E>) collection).get(index);
        } else {
            Iterator<? extends E> iter = collection.iterator();
            for (int i = 0; i < index; i++) {
                iter.next();
            }
            return iter.next();
        }
    }

    public int choiceBetween(Number min, Number max) {
        return random.nextInt(max.intValue() - min.intValue() + 1) + min.intValue();
    }

/*    public <T extends Enum<T>> T randomFrom(Class<? extends Enum> e) {
        return (T) choice(EnumSet.allOf(e));

        T[] enumConstants = e.getEnumConstants();

        T enumConstant = enumConstants[random.nextInt(enumConstants.length)];

        return choice(Arrays.asList(EnumSet.allOf(e)));
    }
    */


    public boolean maybe() {
        return random.nextBoolean();
    }

    static class RandomChoiceException extends RuntimeException {

        RandomChoiceException(String message) {
            super(message);
        }
    }

}
