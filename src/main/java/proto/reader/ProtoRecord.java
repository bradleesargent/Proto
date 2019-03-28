package proto.reader;

import proto.analyze.AnswerQuestions;
import proto.logger.HtmlLogger;

import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Handle reading each proto record
 * Created by bradlee sargent on 3/22/2019
 */
public class ProtoRecord {

    /**
     * Byte buffer for the record type
     */
    private byte [] recordTypeBytes = new byte[1];
    /**
     * Input stream to read the data from
     */
    private InputStream inputStream = null;
    /**
     * Buffer to hold the time stamp
     */
    private byte [] unixTimeStampBytes = new byte[4];
    /**
     * Buffer to hold the dollar amount
     */
    private byte [] amountInDollarsBytes = new byte[8];
    /**
     * Hold the value of the dollar amount
     */
    private float amountInDollars = 0.0F;
    /**
     * Buffer to hold the user id
     */
    private byte [] userIdBytes = new byte[8];
    /**
     * Hold the unix time stamp as an integer
     */
    private int unixTimeStamp = 0;
    /**
     * What is the user id
     */
    private long userId = 0;
    /**
     * What is the special id that we want to pay attention to
     */
    private final long SPECIAL_ID = 2456938384156277127L;

    private int recordType = 0;

    /**
     * Initialize the method for reading from the input stream
     *
     * @param inputStream What is the input stream to read from
     */
    public ProtoRecord(InputStream inputStream) {

        this.inputStream = inputStream;
    }

    /**
     * Set the record type
     *
     * @param inputRecordType The type of record as a byte buffer
     */
    public void setRecordType(byte [] inputRecordType) {
        try {
            recordType = new BigInteger(inputRecordType).intValue();
            HtmlLogger.value("Record Type",recordType);
        } catch (Exception e) {
            HtmlLogger.error("Set record type",e);
        }
    }

    /**
     * Set the amount of dolalrs using an input buffer array of bytes
     * Use BigInteger to do the convert the buffer byte array
     *
     * @param inputAmountInDollarsBytes The amount of dollars as a buffer array of bytes
     */
    public void setAmountInDollars(byte [] inputAmountInDollarsBytes) {
        try {
            BigInteger dollars = new BigInteger(inputAmountInDollarsBytes);
            amountInDollars = dollars.floatValue();
            HtmlLogger.value("Amount in Dollars",amountInDollars);
        } catch (Exception e) {
            HtmlLogger.error("Set amount in dollars",e);
        }
    }

    /**
     * Set the amount of dolalrs using an input buffer array of bytes
     * Convert the buffer byte array to a float value using ByteBuffer
     * @param inputAmountInDollarsBytes The amount of dollars as a buffer array of bytes
     */
    public void setAmountInDollarsUsingBuffer(byte [] inputAmountInDollarsBytes) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(inputAmountInDollarsBytes);
            Float theAmountInDollars = buffer.getFloat();
            HtmlLogger.value("The Amount in Dollars",theAmountInDollars);
        } catch (Exception e) {
            HtmlLogger.error("Set amount in dollars",e);
        }
    }

    /**
     * Set the amount of dolalrs using an input buffer array of bytes
     * Convert the buffer byte array to a float value using ByteBuffer and LITTLE_ENDIAN order
     * @param inputAmountInDollarsBytes The amount of dollars as a buffer array of bytes
     */
    public void setAmountInDollarsUsingBufferLittleEndian(byte [] inputAmountInDollarsBytes) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(inputAmountInDollarsBytes);
            Float theAmountInDollars = buffer.order(ByteOrder.LITTLE_ENDIAN).getFloat();
            HtmlLogger.value("The Amount in Dollars Little Endian",theAmountInDollars);
        } catch (Exception e) {
            HtmlLogger.error("Set amount in dollars",e);
        }
    }

    /**
     * Set the amount of dolalrs using an input byte buffer array
     * Convert the buffer byte array to a float value using ByteBuffer and BIG_ENDIAN order
     * @param inputAmountInDollarsBytes The amount of dollars as a buffer array of bytes
     */
    public void setAmountInDollarsUsingBufferBigEndian(byte [] inputAmountInDollarsBytes) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(inputAmountInDollarsBytes);
            Float theAmountInDollars = buffer.order(ByteOrder.BIG_ENDIAN).getFloat();
            HtmlLogger.value("<strong>The Amount in Dollars Big Endian</strong>",theAmountInDollars);
        } catch (Exception e) {
            HtmlLogger.error("Set amount in dollars",e);
        }
    }

    /**
     * Set the amount in dollars using a byte array buffer
     * Log the values
     *
     * @param inputAmountInDollarsBytes The amount of dollars as a buffer array of bytes
     */
    public void setAmountInDollarsSteps(byte [] inputAmountInDollarsBytes) {
        try {
            for (int i=0;i<inputAmountInDollarsBytes.length;i++) {
                HtmlLogger.valuex(String.format("Amount byte number %d",i),inputAmountInDollarsBytes[i]);
            }
        } catch (Exception e) {
            HtmlLogger.error("Set amount in dollars",e);
        }
    }

    /**
     * Set the user id using the byte array buffer
     * Convert the value to an integer using BigInteger
     * @param inputUserId the user id as a byte array buffer
     */
    public void setUserId(byte [] inputUserId) {
        try {
            BigInteger userIdBI = new BigInteger(inputUserId);
            userId = userIdBI.longValue();
            HtmlLogger.value("User Id",userId);
        } catch (Exception e) {
            HtmlLogger.error("Set user id",e);
        }
    }

    /**
     * Set the time stamp using a byte buffer array
     * @param inputTimeStamp The time stamp as a byte buffer array
     */
    public void setTimeStamp(byte [] inputTimeStamp) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(inputTimeStamp);
            unixTimeStamp = buffer.getInt();
            HtmlLogger.value("Unix time stamp",unixTimeStamp);
        } catch (Exception e) {
            HtmlLogger.error("Set time stamp",e);
        }

    }

    /**
     * This method does the heavy lifting of reading all the data for the current record
     */
    public void getAll() {
        try {
            // read the record type
            inputStream.read(recordTypeBytes);
            // Set the record type
            setRecordType(recordTypeBytes);
            // Read the time stamp
            inputStream.read(unixTimeStampBytes);
            // Set the time stamp
            setTimeStamp(unixTimeStampBytes);
            // Read the user id
            inputStream.read(userIdBytes);
            // set the user id
            setUserId(userIdBytes);
            // Processing based on the record type
            switch(recordType) {
                // Here we add a credit
                case 0:
                    inputStream.read(amountInDollarsBytes);
                    setAmountInDollars(amountInDollarsBytes);
                    setAmountInDollarsUsingBuffer(amountInDollarsBytes);
                    setAmountInDollarsUsingBufferLittleEndian(amountInDollarsBytes);
                    setAmountInDollarsUsingBufferBigEndian(amountInDollarsBytes);
                    setAmountInDollarsSteps(amountInDollarsBytes);
                    AnswerQuestions.addCredit(amountInDollars);
                    if (userId ==  SPECIAL_ID) {
                        AnswerQuestions.addSpecialUserDollarAmount(amountInDollars);
                    }
                    break;
                    // Here we add a debit
                case 1:
                    inputStream.read(amountInDollarsBytes);
                    setAmountInDollars(amountInDollarsBytes);
                    setAmountInDollarsUsingBuffer(amountInDollarsBytes);
                    setAmountInDollarsUsingBufferLittleEndian(amountInDollarsBytes);
                    setAmountInDollarsUsingBufferBigEndian(amountInDollarsBytes);
                    setAmountInDollarsSteps(amountInDollarsBytes);
                    if (userId ==  SPECIAL_ID) {
                        AnswerQuestions.subtractSpecialUserDollarAmount(amountInDollars);
                    }
                    AnswerQuestions.addDebit(amountInDollars);
                    break;
                    // Here we start an auto pay
                case 2:
                    AnswerQuestions.addStartedAutoPay();
                    break;
                    // Here we end an auto pay
                case 3:
                    AnswerQuestions.addEndedAutoPay();
                    break;
            }
        } catch (Exception e) {
            HtmlLogger.error("getAll",e);
        }

    }

}
