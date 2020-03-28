package ro.unibuc.fmi.sortari;

import ro.unibuc.fmi.admin.Facultate;
import java.util.Comparator;

public class ProcFacComparator implements Comparator<Facultate> {

    @Override
    public int compare(Facultate o1, Facultate o2) {
        return (-1)* o1.getProcentajBac().compareTo(o2.getProcentajBac());
    }
}
