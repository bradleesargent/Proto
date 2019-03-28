package proto.reader;


import junit.framework.TestCase;
import org.junit.Test;
import proto.logger.HtmlLogger;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by bradl on 3/22/2019
 */
public class HeaderTest extends TestCase {
    Proto proto = null;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        HtmlLogger.initialize("test");
        proto = new Proto();
        proto.getAll();
    }

    @Test
    public void testFile() {
        String theFileDoesNotExist = String.format("The file %s does not exist", proto.getFile());
        assertTrue(theFileDoesNotExist, proto.getFile());
    }
    @Test
    public void testMagicString() {
        String theMagicString = proto.getMagicString();
        String outputString = String.format("The magic string is",theMagicString);
        String theFileIsShort = String.format("The length of the magic string is %d",theMagicString.length());
        assertTrue(theFileIsShort,theMagicString.length() ==4);
    }
    @Test
    public void testVersion() {
        int version = proto.getVersion();
        String outputString = String.format("The version is",version);
        String theFileIsShort = String.format("The value of the version is %d",version);
        assertTrue(theFileIsShort,version != 0);
    }
    @Test
    public void testNumberOfRecords() {
        int numberOfRecords = proto.getNumberOfRecords();
        String outputString = String.format("The number of records are",numberOfRecords);
        assertTrue(outputString,numberOfRecords > 0);
    }
    @Test
    public void testMaxNumberOfRecords() {
        int numberOfRecords = proto.getNumberOfRecords();
        String outputString = String.format("The number of records are",numberOfRecords);
        assertTrue(outputString,numberOfRecords < 100);
    }

}