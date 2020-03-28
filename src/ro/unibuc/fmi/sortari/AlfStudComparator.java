package ro.unibuc.fmi.sortari;
import ro.unibuc.fmi.inscriere.Student;
import java.util.Comparator;

public class AlfStudComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getNume().compareTo(o2.getNume());
    }
}
