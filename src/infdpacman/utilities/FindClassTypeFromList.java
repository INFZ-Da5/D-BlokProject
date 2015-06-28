package infdpacman.utilities;

import java.util.List;

/**
 *
 * @author CVD
 */

//E = generic element
public class FindClassTypeFromList {
    public static <E> boolean containsInstance(List<E> list, Class<? extends E> classType) {
        for (E e : list) {
            if (classType.isInstance(e)) {
                return true;
            }
        }
        return false;
    }    
}