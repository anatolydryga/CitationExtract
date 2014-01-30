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

        formatted.append(getAuthors(authors, initials));
        formatted.append(getCollectiveAuthor(authors, ref));
        formatted.append("; ");

        formatted.append(ref.getJournal());
        formatted.append(" ");
        if ( ! ref.getVolume().equals("")) {
            formatted.append(ref.getVolume());
        }
        if ( ! ref.getIssue().equals("")) {
            formatted.append("(");
            formatted.append(ref.getIssue());
            formatted.append(")");
        }
        formatted.append(":");
        if ( ! ref.getPages().equals("")) {
            formatted.append(" ");
            formatted.append(ref.getPages());
            formatted.append(" ");
        }
        formatted.append("(");
        formatted.append(ref.getYear());
        formatted.append(")");
        return formatted.toString();
    }

    private String getAuthors(List<String> authors, List<String> initials) {
        StringBuilder formatted = new StringBuilder();

        if (authors.size() == 0) {
            return "";
        }

        assert(authors.size() > 0);
        formatted.append(authors.get(0));
        formatted.append(" ");
        formatted.append(initials.get(0));

        if (authors.size() == 2) {
            formatted.append(", ");
            formatted.append(authors.get(1));
            formatted.append(" ");
            formatted.append(initials.get(1));
        }
        if (authors.size() >= 3) {
            formatted.append(" et al");
        }
        return formatted.toString();
    }

    private String getCollectiveAuthor(List<String> authors, Reference ref) {
        String result = "";
        if ( ref.getCollectiveName().equals("")) {
            return "";
        }

        if (authors.size() == 0) {
            result =  ref.getCollectiveName();
        }
        if (authors.size() == 1) {
            result =  ", " + ref.getCollectiveName();
        }
        return result;
    }
}
