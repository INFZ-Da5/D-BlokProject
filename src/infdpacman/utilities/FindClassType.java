package infdpacman.utilities;

import java.util.List;

/**
 *
 * @author CVD
 */
public class FindClassType {
    public static <E> boolean containsInstance(List<E> list, Class<? extends E> clazz) {
    for (E e : list) {
        if (clazz.isInstance(e)) {
            return true;
        }
    }
    return false;
}
}