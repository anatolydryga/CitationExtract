package citExtract;

import java.util.ArrayList;
import org.w3c.dom.Document;
import citFormatter.*;
import citPrinter.CitationPrinter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**                                                                              
    main class for program that extract citation from NCBI                       
    and format it in txt and html                                                
*/                                                                               
public class CitationExtract {                                                   
    public static void main(String[] args) {                                     
        System.out.println("Citation extraction is started");                    
         
        IdsValidator idsVal = null;
        try {                                                                    
            idsVal = new IdsValidator(args);                        
        } catch (IllegalArgumentException e) {                                   
            System.out.println();
            System.out.println("FATAL ERROR:");
            System.out.println("Cannot process CMD line args: " + e.getMessage());            
            System.out.println("Citation Extract failed!");                       
            System.exit(1);                                                      
        }                                                                        
           
        PubMedDownloader downloader = null;
        try {
            downloader = new PubMedDownloader(idsVal.getIds());           
        } catch (IllegalArgumentException e) {                                          
            System.out.println();
            System.out.println("FATAL ERROR:");
            System.out.println(e.getMessage());
            System.out.println("Cannot Download PMIDs from NCBI!");                 
            System.out.println("Check that PMIDs exist in PubMed.");                 
            System.exit(1);                                                             
        }                                                                        
                                                                                 
        Document doc = downloader.getXML();                                   
                                                                                 
        List<Citation> citations = CitationFactory.createCitations(doc);
        System.out.println("All citation are extracted"); 
        
        List<CitationFormatted> formattedCitations = formatCitations(citations);   
        
        CitationPrinter htmlPrinter = new CitationPrinter(formattedCitations);
        String html = htmlPrinter.print();

        String htmlFile = "citations.html";
        writeToFile(htmlFile, html);
    }

    private static List<CitationFormatted> formatCitations(List<Citation> citations) {
        ReferenceFormatter refFormatter = new ReferenceFormatter();
        AbstractFormatter absFormatter = new AbstractFormatter();
        CitationFormatterFactory formatter = 
                new CitationFormatterFactory(refFormatter, absFormatter);
        return formatter.format(citations);                                
    }                                                                            

    private static void writeToFile(String htmlFile, String html) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(htmlFile, "UTF-8");
            writer.println(html);
            writer.close(); 
        } catch (FileNotFoundException ex) {
            System.out.println("");
            System.out.println("ERROR: CITATIONS ARE NOT SAVED!");
            System.out.println("Please close file \"" + htmlFile + "\"");
            System.out.println("(or delete/rename it) before running the program.");
            System.out.println("And rerun the program.");
            System.out.println("");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CitationExtract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}                       