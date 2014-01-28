/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package citFormatter;

import citExtract.Citation;
import citExtract.CitationFormatted;
import java.util.ArrayList;
import java.util.List;
import substitution.Substitution;

/**
 *
 * @author drygaay
 */
public class CitationFormatterFactory {

    private ReferenceFormatter refFormatter;
    private AbstractFormatter absFormatter;

    public CitationFormatterFactory(
            ReferenceFormatter refFormatter, 
            AbstractFormatter absFormatter
    ) {
       this.refFormatter = refFormatter; 
       this.absFormatter = absFormatter;
    }


    /**
     * @param citations list of parsed citations
     * @return list of citations that are formatted according to rules
     */
    public List<CitationFormatted> format(List<Citation> citations) {
        List<CitationFormatted> formatted = new ArrayList<>();
        for (Citation citation : citations) {
            String ref = refFormatter.format(citation.getReference());
            List<Substitution> subs = absFormatter.format(citation.getAbstract());
            CitationFormatted citationFomatted = 
                    new CitationFormatted(citation.getPmid(), ref, subs);
            formatted.add(citationFomatted);
        }
        return formatted;
    }
}
