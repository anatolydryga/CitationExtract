package citPrinter;

import citExtract.CitationFormatted;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author drygaay
 */
public class CitationPrinterTest {
    
    public CitationPrinterTest() {
    }

    @Test
    public void testPrint() {
        System.out.println("print");
        CitationPrinter instance = null;
        String expResult = "";
        String result = instance.print();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintCitation() {
        System.out.println("printCitation");
        CitationFormatted citation = null;
        CitationPrinter instance = null;
        String expResult = "";
        String result = instance.printCitation(citation);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintHeader() {
        System.out.println("printHeader");
        CitationPrinter instance = null;
        String expResult = "";
        String result = instance.printHeader();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPrintTail() {
        System.out.println("printTail");
        CitationPrinter instance = null;
        String expResult = "";
        String result = instance.printTail();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}