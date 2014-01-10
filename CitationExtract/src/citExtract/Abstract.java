package citExtract;

import java.util.ArrayList;
import java.util.List;
/**
    parsed abstract from PubMed XML.

    No substitutions are made and abstract is considered as structured abstract
    which has several sections.
*/
public class Abstract {

    List<String> sectionsText  = new ArrayList<>();
    List<String> sectionsLabel = new ArrayList<>();

    /// if no label add "" string
    public void addAbstractText(String text, String label) {
        sectionsText.add(text);
        sectionsLabel.add(label);
    }

    public List<String> getSectionsText() {
        return sectionsText;
    }

    /// if no label we assign label to "" to keep
    /// size the same as size of text
    public List<String> getSectionsLabel() {
        return sectionsLabel;
    }
}