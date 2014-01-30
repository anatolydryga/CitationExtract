package citPrinter;

import citExtract.Citation;
import citExtract.CitationFactory;
import citExtract.CitationFormatted;
import citExtract.FileReader;
import citFormatter.AbstractFormatter;
import citFormatter.CitationFormatterFactory;
import citFormatter.ReferenceFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;

/**
 *
 * @author drygaay
 */
public class CitationPrinterTest {

    CitationPrinter printer;
    
    public CitationPrinterTest() {
        Document doc1 = null; // structured abstract, no subsititutions
        Document doc2 = null; // abstract with \mu
        Document doc3 = null; // 2 abstracts: nonthing in 1st, h, greek and approx in 2nd 
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc1 = db.parse("./test/citExtract/16545112.xml");
            doc2 = db.parse("./test/citExtract/24015326.xml");
            doc3 = db.parse("./test/citExtract/21887247_124.xml");
        } catch (Exception e) {
            assertTrue(false);
        }
        List<Citation> citations1 = CitationFactory.createCitations(doc1);
        List<Citation> citations2 = CitationFactory.createCitations(doc2);
        List<Citation> citations3 = CitationFactory.createCitations(doc3);

        List<Citation> citations =  new ArrayList<>();

        citations.addAll(citations1);
        citations.addAll(citations2);
        citations.addAll(citations3);

        ReferenceFormatter refFormatter = new ReferenceFormatter();
        AbstractFormatter absFormatter = new AbstractFormatter(); 
        CitationFormatterFactory instance = 
                new CitationFormatterFactory(refFormatter, absFormatter);
        List<CitationFormatted>  result = instance.format(citations);
        printer = new CitationPrinter(result);
    }

    @Test
    public void testPrint() {
        String result = printer.print();
        assertTrue(result.contains("<html>"));
        assertTrue(result.contains("PMID"));
        assertTrue(result.contains("16545112"));
        assertTrue(result.contains("24015326"));
        assertTrue(result.contains("21887247"));
        assertTrue(result.contains("124"));
        assertTrue(result.contains("http://www.ncbi.nlm.nih.gov/pubmed/?term="));
        assertTrue(result.contains("</html>"));
    }

    @Test
    public void testPrintCitation() {
        String expResult = "<br>PMID = <a href = " +
                "\"http://www.ncbi.nlm.nih.gov/pubmed/?term=16545112\">16545112</a>" +
                "<br><br>Snyder KA et al;" +
                " BMC Bioinformatics 7: 152 (2006)<br><br>";
        String abstractFormatted = ""; 
        try { 
            abstractFormatted = FileReader.readFile(
             "./test/citExtract/16545112_abstractFormatted.dat");
        } catch (IOException ex) {
            fail("cannot open 16545112_abstractFormatted.dat");
        }
        expResult += abstractFormatted;
        expResult += "<br>";
        String result = printer.printCitation(0);
        assertEquals(expResult, result);
    }

    @Test
    public void testPrintHeader() {
        String expResult = "<!DOCTYPE html>\n" +
                           "<meta charset=\"UTF-8\">\n" + 
                           "<html>\n" +
                           "    <head>\n" +
                           "        <title>Citation Formatter</title>\n" +
                           "    </head>\n" +
                           "    <body>\n";
        String result = printer.printHeader();
        assertEquals(expResult, result);
    }

    @Test
    public void testPrintTail() {
        String expResult = "    </body>\n" +
                           "</html>\n";
        String result = printer.printTail();
        assertEquals(expResult, result);
    }
}