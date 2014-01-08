/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package citExtract;

import org.junit.Test;
import static org.junit.Assert.*;
                                                          
import java.util.Arrays;                                                         
                                            
                                                                                 
public class IdsValidatorTest {                                                                                                                                
                                                                                 
    @Test(expected=IllegalArgumentException.class)                               
    public void empty() {                                                        
        String[] ids = {};                                                       
        IdsValidator eutils =  new IdsValidator(ids);                            
    }                                                                            
                                                                                 
    @Test(expected=IllegalArgumentException.class)                               
    public void notInt() {                                                       
        String[] ids = {"bla"};                                                  
        IdsValidator eutils =  new IdsValidator(ids);                            
    }                                                                            
                                                                                 
    @Test(expected=IllegalArgumentException.class)                               
    public void notAllInt() {                                                    
        String[] ids = {"124", "bla", "3434"};                                   
        IdsValidator eutils =  new IdsValidator(ids);                            
    }                                                                            
                                                                                 
    @Test(expected=IllegalArgumentException.class)                               
    public void tooManyIds() {                                                   
        String[] ids = new String[100];                                          
        for (int i = 0; i < ids.length; i++) {                                   
            ids[i] = Integer.toString(i + 1);                                    
        }                                                                        
        IdsValidator eutils =  new IdsValidator(ids);                            
    }                                                                            
                                                                                 
    @Test                                                                        
    public void oneId() {                                                        
        String[] ids = {"124"};                                                  
        IdsValidator eutils =  new IdsValidator(ids);                            
        Arrays.sort(eutils.getIds());                                            
        int[] ans = {124};                                                       
        assertTrue(Arrays.equals(eutils.getIds(), ans));                         
    }                                                                            
                                                                                 
    @Test                                                                        
    public void severalIds() {                                                   
        String[] ids = {"124", "12341234", "234234"};                            
        IdsValidator eutils =  new IdsValidator(ids);                            
        Arrays.sort(eutils.getIds());                                            
        int[] ans = {124, 234234, 12341234};                                     
        assertTrue(Arrays.equals(eutils.getIds(), ans));                         
    }                                                                            
                                                                                 
    @Test                                                                        
    public void manyIds() {                                                      
        String[] ids = new String[10];                                           
        for (int i = 0; i < ids.length; i++) {                                   
            ids[i] = Integer.toString(i + 1);                                    
        }                                                                        
        IdsValidator eutils =  new IdsValidator(ids);                            
        Arrays.sort(eutils.getIds());                                            
        int[] ans = {1,2,3,4,5,6,7,8,9,10};                                      
        assertTrue(Arrays.equals(eutils.getIds(), ans));                         
    }                                                                            
                                                                                 
}                                                      