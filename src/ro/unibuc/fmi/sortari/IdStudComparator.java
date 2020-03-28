package ro.unibuc.fmi.sortari;
import ro.unibuc.fmi.inscriere.Student;
import java.util.Comparator;

public class IdStudComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
