import java.util.Comparator;

public class ComparadorItemPreco implements Comparator {

    @Override
    public int compare(Object ob1, Object ob2) {
        Item i1, i2;
        i1 = (Item) ob1;
        i2 = (Item) ob2;
        if (i1.getValor() > i2.getValor()) {
            return -1;
        }
        if (i1.getValor() > i2.getValor()) {
            return +1;
        }
        return 0;
    }

}
