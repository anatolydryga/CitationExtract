package citPrinter;

import citExtract.CitationFormatted;
import java.util.List;

/**
 * print citation to file
 * @author drygaay
 */
public class CitationPrinter {

    List<CitationFormatted> citations;

    /**
     * create HTML with citations with links to PubMed and showing where substitutions
     * were performed.
     * @param formattedCitations 
     */
    public CitationPrinter(List<CitationFormatted> formattedCitations) {
        this.citations = formattedCitations;
    }

    /**
     * create print representation for the list of citations
     */
    public String print() {
        return "";
    }

    /**
     * print a citation
     * @param citation
     * @return 
     */
    public String printCitation(CitationFormatted citation) {
        return "";
    }

    /*
     * print header before all citations
     */
    public String printHeader() {
        return "";
    }
    /**
     * print last portion of the file, after all citations
     * @return 
     */
    public String printTail() {
           return "";
    }
    
}
