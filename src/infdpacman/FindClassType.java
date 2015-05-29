package infdpacman;

import java.util.Collection;

/**
 *
 * @author CVD
 */
public class FindClassType {
    public static <T> T find(Collection<?> list, Class<T> classType)
    {
        for(Object o : list)
        {
            if (o != null && o.getClass() == classType)
            {
                return classType.cast(o);
            }
        }

        return null;    
    }
}
