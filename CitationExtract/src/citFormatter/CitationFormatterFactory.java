/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package citFormatter;

import citExtract.Citation;
import citExtract.CitationFormatted;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drygaay
 */
public class CitationFormatterFactory {

    public CitationFormatterFactory(ReferenceFormatter refFormatter, AbstractFormatter absFormatter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param citations list of parsed citations
     * @return list of citations that are formatted according to rules
     */
    public List<CitationFormatted> format(List<Citation> citations) {
        return new ArrayList<>();
    }
}
