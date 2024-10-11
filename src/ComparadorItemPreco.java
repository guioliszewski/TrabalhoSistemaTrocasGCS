import java.util.Comparator;

public class ComparadorItemPreco implements Comparator<Item> {

    @Override
    public int compare(Item i1, Item i2) {
        return Double.compare(i1.getValor(), i2.getValor());
    }
}

