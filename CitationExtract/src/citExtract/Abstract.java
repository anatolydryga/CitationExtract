package citExtract;

import java.util.ArrayList;
/**
    parsed abstract from PubMed XML.

    No substitutions are made and abstract is considered as structured abstract
    which has several sections.
*/
class Abstract {

    ArrayList< String > sectionsText  = new ArrayList< String >();
    ArrayList< String > sectionsLabel = new ArrayList< String >();

    /// if no label add "" string
    public void addAbstractText(String text, String label) {
        sectionsText.add(text);
        sectionsLabel.add(label);
    }

    public ArrayList< String > getSectionsText() {
        return sectionsText;
    }

    /// if no label we assign label to "" to keep
    /// size the same as size of text
    public ArrayList< String > getSectionsLabel() {
        return sectionsLabel;
    }
}