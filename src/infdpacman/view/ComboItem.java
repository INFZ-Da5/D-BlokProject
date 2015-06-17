
package infdpacman.view;

/**
 *
 * @author Lenovo
 */
public class ComboItem {
    private String key;
    private Board value;

    public ComboItem(String key, Board value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public Board getValue()
    {
        return value;
    }
}
