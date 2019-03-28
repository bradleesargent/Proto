package proto.reader;

import proto.logger.HtmlLogger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradl on 3/22/2019
 */
public class ProtoRecords {


    private InputStream inputStream = null;
    private int numberOfRecords = 0;
    private ProtoRecord protoRecord = null;
    private List<ProtoRecord> protoRecords = new ArrayList<>();
    int numberOfRecordsRead = 0;

    /**
     * Here we initialize the constructor used to read all the records
     * @param inputStream This is the input stream for the data
     * @param numberOfRecords This is the number of records we want to read.
     */
    public ProtoRecords(InputStream inputStream, int numberOfRecords) {
        this.inputStream = inputStream;
        this.numberOfRecords = numberOfRecords;
    }

    /**
     * Here we are going to read all of the records
     */
    public void readAll() {
        // Loop through all of the records
        for (numberOfRecordsRead=0;numberOfRecordsRead<numberOfRecords;numberOfRecordsRead++) {
            // We get the next record using the protoRecord to parse the contents of each record
            // We start by initializing the protoRecord with the input stream
            protoRecord =  new ProtoRecord(this.inputStream);
            // then we get all the data for that record
            protoRecord.getAll();

            HtmlLogger.value("Number of records read",numberOfRecordsRead);
            // Here we can store the records for later parsing if we want.
            protoRecords.add(protoRecord);
        }

    }

    /**
     * This gets the number of records read from the file.
     * @return The number of records read
     */
    public int getNumberOfRecordsRead() {
        return numberOfRecordsRead;
    }
}
