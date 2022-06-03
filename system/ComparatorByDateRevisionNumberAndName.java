package system;

import java.util.Comparator;

/**
 * Class which implements the comparator of date, number and name, respectively,
 * between two objects.
 * 
 * @author Henrique Alípio / João Marques
 * @param <Revision> the type of objects to consider.
 */
public class ComparatorByDateRevisionNumberAndName implements Comparator<Revision> {

    public int compare(Revision o1, Revision o2) {
        if (!o1.getDate().equals(o2.getDate())) {
            return -o1.getDate().compareTo(o2.getDate()); // sinal menos para ordenar do mais recente para o mais antigo
        }
        if (o1.getRevisionNumber() < (o2.getRevisionNumber())) {
            return -(o1.getRevisionNumber() - (o2.getRevisionNumber()));
        }
        return o1.getProjectName().compareTo(o2.getProjectName());
    }

}