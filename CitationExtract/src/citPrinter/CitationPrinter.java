package citPrinter;

import citExtract.CitationFormatted;
import java.util.List;
import substitution.Substitution;

/**
 * print citation to file
 * @author drygaay
 */
public class CitationPrinter {

    private static final String webLink = 
            "<br>PMID = <a href = \"http://www.ncbi.nlm.nih.gov/pubmed/?term="; 

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
        StringBuffer result =  new StringBuffer();
        result.append(printHeader());
        for (int i = 0; i < citations.size(); i++) {
            result.append(printCitation(i));
        }
        result.append(printTail());
        return result.toString();
    }

    /**
     * print a citation for a given index
     * @param index of citation added in ctor
     * @return HTML representation of citation
     * @throws ArrayIndexOutOfBoundsException if index is not in the range
     * @see  CitationPrinter ctor
     */
    public String printCitation(int index) {
        StringBuilder result = new StringBuilder();

        result.append(printPmidLink(index));
        result.append(printReference(index));
        result.append(printAbstract(index));

        return result.toString();
    }


    private String printPmidLink(int index) {
        StringBuilder pmidLink = new StringBuilder();
        pmidLink.append(webLink);
        pmidLink.append(citations.get(index).getPmid());
        pmidLink.append("\">");
        pmidLink.append(citations.get(index).getPmid());
        pmidLink.append("</a>");
        return pmidLink.toString();
    }


    private String printReference(int index) {
        StringBuilder reference =  new StringBuilder();
        reference.append("<br><br>");
        reference.append(citations.get(index).getRef());
        reference.append("<br><br>");
        return reference.toString();
    }


    private String printAbstract(int index) {
        StringBuilder abstractFormatted =  new StringBuilder();
        List<Substitution> substitutions  = citations.get(index).getSubstitutions();
        for(Substitution s: substitutions) {
            abstractFormatted.append(printSubstitution(s));
        }
        abstractFormatted.append("<br>");
        return abstractFormatted.toString();
    }

    private static String aBefore = "<a style=\"background-color:red\" title=\"";
    private static String aAfter  = "</a>";

    private String printSubstitution(Substitution s) {
        if (s.isSubstitution()) {
            String substitution = s.getSubstitute();
            if (substitution.equals("")) {
                substitution = "&nbsp;";
            }
            return aBefore + s.getOriginal() + "\">" + substitution + aAfter;
        } else {
            return s.getOriginal();
        }
    }


    /*
     * print header before all citations
     */
    public String printHeader() {
        return "<!DOCTYPE html>\n" +
               "<meta charset=\"UTF-8\">\n" + 
               "<html>\n" +
               "    <head>\n" +
               "        <title>Citation Formatter</title>\n" +
               "    </head>\n" +
               "    <body>\n";
    }
    /**
     * print last portion of the file, after all citations
     * @return 
     */
    public String printTail() {
           return "    </body>\n" +
                  "</html>\n";
    }

}
