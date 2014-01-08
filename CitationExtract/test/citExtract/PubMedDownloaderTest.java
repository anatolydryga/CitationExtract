package citExtract;

import org.junit.*;
import static org.junit.Assert.*;


public class PubMedDownloaderTest{

    private static String base =
        "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&id=";

    @Test
    public void ctr() {
        int[] ids = {124};
        PubMedDownloader downloader =  new PubMedDownloader(ids);
        assertTrue(downloader.getUrl().equals(base+"124,"));
        assertNotNull(downloader.getXML());
    }

    @Test
    public void ctr2() {
        int[] ids = {21887247};
        PubMedDownloader downloader =  new PubMedDownloader(ids);
        assertTrue(downloader.getUrl().equals(base+"21887247,"));
        assertNotNull(downloader.getXML());
    }

    @Test
    public void twoIds() {
        int[] ids = {124, 21887247};
        PubMedDownloader downloader =  new PubMedDownloader(ids);
        assertTrue(downloader.getUrl().equals(base + "124,21887247,"));
        assertNotNull(downloader.getXML());
    }

    @Test(expected=IllegalArgumentException.class)
    public void empty() {
        int[] ids = {};
        PubMedDownloader downloader =  new PubMedDownloader(ids);
    }
}