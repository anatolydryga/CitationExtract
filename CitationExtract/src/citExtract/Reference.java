package citExtract;

import org.w3c.dom.*;                                                            
import java.net.*;                                                               
import javax.xml.parsers.*;                                                      
import java.util.ArrayList;                                                      
/**                                                                              
    parsed components for a reference.                                           
                                                                                 
    journal, author list, year, volume, issue, pages                                                 
*/                                                                               
public class Reference {                                                                
                                                                                                                                                                
    private int year;
    
    private String volume = "";                                                
    private String issue = "";                                                
                                                                                 
    private String journal = ""; // abbreviated name of the journal @wa not the full name
    private String pages = "";  // no need to parse it furtehr at present        
                                                                                 
    private ArrayList<String> authorLastNames = new ArrayList<>();     
    private ArrayList<String> authorInitials  = new ArrayList<>();     

    Reference() {
        this.year = 0;
    }
                                                                                 
    public void setYear(int year) {                                              
        this.year    = year;                                                     
    }                                                                            
                                                                                 
    public void setVolume(String volume) {                                          
        this.volume  = volume;                                                   
    }                                                                            
                                                                                 
    public void setIssue(String issue) {                                            
        this.issue   = issue;                                                    
    }                                                                            
                                                                                 
    public void setJournal(String journal) {                                     
        this.journal = journal;                                                  
    }                                                                            
                                                                                 
    public void setPages(String pages) {                                         
        this.pages = pages;                                                      
    }                                                                            
                                                                                 
    public void addAuthor(String lastName, String initials) {                    
        authorLastNames.add(lastName);                                           
        authorInitials .add(initials);                                           
    }                                                                            
                                                                                 
    public int getYear() {                                                       
        return year;                                                             
    }                                                                            
                                                                                 
    public String getVolume() {                                                     
        return volume;                                                           
    }                                                                            
                                                                                 
    public String getIssue() {                                                      
        return issue;                                                            
    }                                                                            
                                                                                 
    public String getJournal() {                                                 
        return journal;                                                          
    }                                                                            
                                                                                 
    public String getPages() {                                                   
        return pages;                                                            
    }                                                                            
                                                                                 
    public int getNumberOfAuthors() {                                            
        return authorLastNames.size();                                           
    }                                                                            
                                                                                 
    /// ordering is such that lastName[i] correponds to initials[i]              
    /// @sa getAuthorInitials                                                    
    public ArrayList< String > getAuthorLastNames() {                            
        return authorLastNames;                                                  
    }                                                                            
                                                                                 
    /// ordering is such that lastName[i] correponds to initials[i]              
    /// @sa getAuthorLastNames                                                   
    public ArrayList< String > getAuthorInitials() {                             
       return authorInitials;                                                    
    }                                                                            
}                                                          