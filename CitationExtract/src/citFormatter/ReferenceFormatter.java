package citFormatter;

import citExtract.Reference;
import java.util.List;

/**
 * format reference as: author list. abbreviated journal, volume, issue, year;
 * pages
 *
 * @author drygaay
 */
public class ReferenceFormatter {

    public String format(Reference ref) {
        StringBuilder formatted = new StringBuilder();

        List<String> authors = ref.getAuthorLastNames();
        List<String> initials = ref.getAuthorInitials();

        for (int i = 0; i < authors.size(); i++) {
            formatted.append(authors.get(i));
            formatted.append(" ");
            formatted.append(initials.get(i));
            if (i != authors.size() - 1) {
                formatted.append(", ");
            }
        }
        formatted.append(". ");
        formatted.append(ref.getJournal());
        formatted.append(", ");
        if (!ref.getVolume().equals("")) {
            formatted.append(ref.getVolume());
            formatted.append(", ");
        }
        if (!ref.getIssue().equals("")) {
            formatted.append(ref.getIssue());
            formatted.append(", ");
        }
        formatted.append(ref.getYear());
        if (!ref.getPages().equals("")) {
            formatted.append("; ");
            formatted.append(ref.getPages());
        }
        return formatted.toString();
    }
}
