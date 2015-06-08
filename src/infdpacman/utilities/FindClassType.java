package infdpacman.utilities;

import java.util.List;

/**
 *
 * @author CVD
 */

//wat is E, ff opzoeken 
public class FindClassType {
    public static <E> boolean containsInstance(List<E> list, Class<? extends E> classType) {
        for (E e : list) {
            if (classType.isInstance(e)) {
                return true;
            }
        }
        return false;
    }
}