package proto.reader;

import proto.analyze.AnswerQuestions;
import proto.logger.HtmlLogger;

import java.io.*;
import java.math.BigInteger;

/**
 * This is the main class which reads the txnlog.dat file and processes the records
 * Created by bradlee sargent on 3/22/2019
 */
public class Proto {

    /**
     * The file to read
     */
    private final static String INPUTFILE = "proto/txnlog.dat";
    /**
     * The magic string
     */
    private String magicString = null;
    /**
     * The buffer to hold the magic string
     */
    private byte [] magicStringBytes = new byte[4];
    /**
     * The buffer to hold the version
     */
    private byte [] versionBytes = new byte[1];
    /**
     * The integer version number
     */
    private int version = 0;
    /**
     * The buffer to hold the number of records in the file
     */
    private byte [] numberOfRecordsBytes = new byte[4];
    /**
     * the number of records to read from the txnlog.dat file
     */
    private int numberOfRecords = 0;


    /**
     * Hold the file to read
     */
    private File txnLogFile = null;

    /**
     * Run the proco program, read the file, process the values
     * and produce two reports, one for logging and another with the answers to the questions
     *
     * @param args Arguments to run the file really aren't used
     */
    public static void main(String [] args) {
        // First, we initialize the logging file
        HtmlLogger.initialize("proto");
        // Then we instantiate the new Proto object which will do all the work
        Proto proto = new Proto();
        // Then we read the entire file and process the records to get
        // the answers to the questions
        proto.getAll();
        // then we launch the logging in a browser
        HtmlLogger.launch();
        // now we enter the answers to each one of the questions that is requested
        HtmlLogger.question1TotalAmountOfDollarsInDebits(AnswerQuestions.getDollarDebits());
        HtmlLogger.question2TotalAmountOfDollarsInCredits(AnswerQuestions.getDollarCredits());
        HtmlLogger.question3HowManyAutoPaysStarted(AnswerQuestions.getStartedAutoPays());
        HtmlLogger.question4HowManyAutoPaysEnded(AnswerQuestions.getEndedAutoPays());
        HtmlLogger.question5BalanceOfSpecialUserId(AnswerQuestions.getBalanceSpecialUserId());
        // and we launch a browser with the answer to the questions
        HtmlLogger.launchQuestions();
    }

    /**
     * Used to get the file and return if its there or not
     * @return is the file there
     */
    public boolean getFile() {
        boolean returnValue = false;
        txnLogFile = new File(INPUTFILE);
        returnValue = txnLogFile.isFile();
        if (!returnValue) {
            System.out.println("Place txnlog.dat file in proto directory...");
            System.exit(1);
        }
        return returnValue;
    }

    /**
     * Return the path of the file
     * @return the path of the file
     */
    public String getNameOfFile() {
        return INPUTFILE;
    }

    /**
     * Convert the magic string and put it into the logging
     * @param inputMagicStringInBytes the magic string
     */
    public void setMagicString(byte [] inputMagicStringInBytes) {
        magicString = new String(inputMagicStringInBytes);
        HtmlLogger.value("Magic String",magicString);
    }

    /**
     * Convert the number of records to an integer and put it into the logging file
     * @param inputNumberOfRecords the byte buffer holding the number of records
     */
    public void setNumberOfRecords(byte [] inputNumberOfRecords) {
        try {
            numberOfRecords = new BigInteger(inputNumberOfRecords).intValue();
            HtmlLogger.value("Number of Records",numberOfRecords);
        } catch (Exception e) {
            HtmlLogger.error("Set number of records",e);
        }
    }

    /**
     * Convert the version to an integer and add it to the logging file
     * @param inputVersion The buffer holding the version
     */
    public void setVersion(byte [] inputVersion) {
        try {
            version = new BigInteger(inputVersion).intValue();
            HtmlLogger.value("Version",version);
        } catch (Exception e) {
            HtmlLogger.error("Set version",e);
        }
    }

    /**
     * What is the version?
     * @return The version number
     */
    public int getVersion() {
        return version;
    }

    /**
     * Return the magic string
     * @return The magic string
     */
    public String getMagicString() {
        return magicString;
    }

    /**
     * Get the number of records
     * @return The number of records
     */
    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * Read all the values from the txnlog.dat file
     * @return The magic string
     */
    public String getAll() {
        try {
            if (txnLogFile == null) {
                getFile();
            }
            InputStream inputStream = new FileInputStream(txnLogFile);
            int byteRead = inputStream.read(magicStringBytes);
            HtmlLogger.value("byteread",byteRead);
            byteRead = inputStream.read(versionBytes);
            byteRead = inputStream.read(numberOfRecordsBytes);
            setVersion(versionBytes);
            setMagicString(magicStringBytes);
            setNumberOfRecords(numberOfRecordsBytes);
            ProtoRecords protoRecords = new ProtoRecords(inputStream,numberOfRecords);
            protoRecords.readAll();
            inputStream.close();
        } catch (FileNotFoundException e) {
            HtmlLogger.error("getall file not found",e);
        } catch (IOException e) {
            HtmlLogger.error("getall ioexception",e);
        }
        return magicString;
    }
}
