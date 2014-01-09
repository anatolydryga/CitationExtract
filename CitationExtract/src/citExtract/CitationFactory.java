package citExtract;

import org.w3c.dom.*;
import java.net.*;
import java.util.List;
import javax.xml.parsers.*;
import java.util.ArrayList;

/**
 * Factory for production of Citation(s) from PubMed XML file.
 *
 * @sa Citation.java for parsed information
 *
 * @sa CitationFormatted.java for parsed citation with substituition rules
 * applied and reference formated
 *
 */
public class CitationFactory {

    public static ArrayList<Citation> createCitations(Document doc) {
        ArrayList<Citation> citations = new ArrayList<>();
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("PubmedArticle");

        for (int i = 0; i < nList.getLength(); i++) {
            Element pubMedArticle = (Element) nList.item(i);
            int pmid = getFirstIntElement(pubMedArticle, "PMID");
            Abstract abs = createAbstract(pubMedArticle);
            Reference ref = createReference(pubMedArticle);
            Citation citation = new Citation(pmid, ref, abs);
            citations.add(citation);
        }
        return citations;
    }

    private static Abstract createAbstract(Element pubMedArticle) {
        Abstract abst = new Abstract();
        NodeList nList = pubMedArticle.getElementsByTagName("AbstractText");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            String label = ((Element) nNode).getAttribute("Label");
            abst.addAbstractText(nNode.getTextContent(), label);
        }
        return abst;
    }

    private static Reference createReference(Element pubMedArticle) {
        Reference reference = new Reference();

        int year = getPublicationYear(pubMedArticle);
        if (year != -1) {
            reference.setYear(year);
        }

        String volume = getFirstStringElement(pubMedArticle, "Volume");
        reference.setVolume(volume);

        String issue = getFirstStringElement(pubMedArticle, "Issue");
        reference.setIssue(issue);

        String journal = getFirstStringElement(pubMedArticle, "ISOAbbreviation");
        reference.setJournal(journal);

        String pages = getFirstStringElement(pubMedArticle, "MedlinePgn");
        reference.setPages(pages);

        NodeList nList = pubMedArticle.getElementsByTagName("Author");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            Element elemAuthor = (Element) nNode;

            String lastName = getFirstStringElement(elemAuthor, "LastName");
            String initials = getFirstStringElement(elemAuthor, "Initials");

            reference.addAuthor(lastName, initials);
        }

        return reference;
    }

    private static int getPublicationYear(Element pubMedArticle) {
        NodeList pubDate = pubMedArticle.getElementsByTagName("PubDate");
        int year;
        if (pubDate.getLength() == 1) {
            year = getFirstIntElement( (Element) pubDate.item(0), "Year");
        } else {
            year = -1;
        }
        return year;
    }

    /// return value if can extract it
    /// return -1 if cannot extract it
    private static int getFirstIntElement(Element elem, String name) {
        NodeList extracted = elem.getElementsByTagName(name);
        if (extracted.getLength() == 0) {
            return -1;
        } else {
            try {
                return Integer.parseInt(extracted.item(0).getTextContent());
            } catch (NumberFormatException e) {
                System.out.println("cannot extract in from " + name + " xml element");
                return -1;
            }
        }
    }

    /// return value if can extract it
    /// return "" if cannot extract it
    private static String getFirstStringElement(Element elem, String name) {
        NodeList extracted = elem.getElementsByTagName(name);
        if (extracted.getLength() == 0) {
            return "";
        } else {
            return extracted.item(0).getTextContent();
        }
    }
}