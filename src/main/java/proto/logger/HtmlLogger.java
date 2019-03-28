package proto.logger;

import java.awt.*;
import java.io.*;

/**
 * This is the html logger which is used for debugging and storing the results
 * of the analysis in the forms of questions and answers.
 * Created by bradlee sargent on 3/22/2019
 */
public class HtmlLogger {
    /**
     * Here we store the contents of the html logging
     */
    private static StringBuilder htmlContents = null;

    /**
     * Here we store the contents for the answers to the questions
     */
    private static StringBuilder htmlFinalReport = null;
    /**
     * The file that holds the logging information
     */
    private static File htmlFile = null;
    /**
     * The file that holds the answers to the questions
     */
    private static File htmlFileFinalReport = null;

    /**
     * Initialize the files and string builders for logging
     * @param title The title of the logging page
     */
    public static void initialize(String title) {
        try {
            htmlFile = File.createTempFile("proto",".html");
            htmlFileFinalReport = File.createTempFile("Final Report",".html");
            htmlContents = new StringBuilder();
            htmlContents.append(String.format("<!DOCTYPE HTML>\n<html lang='eng'>\n<header>\n<title>%s</title></header><body><h1 align='center'>%s</h1><table border='1'>",title,title));
            htmlFinalReport = new StringBuilder();
            htmlFinalReport.append("<!DOCTYPE HTML>\n<html lang='eng'>\n<header>\n<title>Answers to Questions</title></header><body><h1 align='center'>Answers to Questions</h1><table border='1'>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logging a string value
     * @param label The label to use for the value
     * @param value The string value to be displayed
     */
    public static void value(String label,String value) {
        htmlContents.append(String.format("<tr><td>%s</td><td>%s</td></tr>\n",label,value));
    }
    /**
     * Logging an integer value
     * @param label The label to use for the value
     * @param value The integer value to be displayed
     */
    public static void value(String label,int value) {
        htmlContents.append(String.format("<tr><td>%s</td><td>%d</td></tr>\n",label,value));
    }
    /**
     * Logging a float value
     * @param label  The label to use for the value
     * @param value The float value to be displayed
     */
    public static void value(String label,float value) {
        htmlContents.append(String.format("<tr><td>%s</td><td>$%,f</td></tr>\n",label,value));
    }

    /**
     * Display the stack trace on the logging web page
     * @param label  The label to use for the error message
     * @param theError The error
     */
    public static void error(String label, Throwable theError) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        theError.printStackTrace(pw);
        htmlContents.append(String.format("<tr><td>%s</td><td>%s</td></tr>",label,sw.toString()));

    }

    /**
     * Logging a long value
     * @param label  The label to use for the value
     * @param value The long value to be displayed
     */
    public static void value(String label,long value) {
        htmlContents.append(String.format("<tr><td>%s</td><td>%d</td></tr>\n",label,value));
    }

    /**
     * Logging a byte value as an integer
     * @param label  The label to use for the value
     * @param value The byte value to be displayed as an integer
     */
    public static void value(String label,byte value) {
        htmlContents.append(String.format("<tr><td>%s</td><td>%d</td></tr>\n",label,value));
    }

    /**
     * Show logging in hex
     * @param label The label to use for the value
     * @param value The byte value to be displayed as hex
     */
    public static void valuex(String label,byte value) {
        htmlContents.append(String.format("<tr><td>%s</td><td>0x%x</td></tr>\n",label,value));
    }

    /**
     * Show logging in octal format
     * @param label The label to use for the value
     * @param value The byte value to display in octal format
     */
    public static void valueo(String label,byte value) {
        htmlContents.append(String.format("<tr><td>%s</td><td>%o</td></tr>\n",label,value));
    }

    /**
     * Answer question 1 What is the total amount in dollars of debits?
     * @param value The answer to question 1
     */
    public static void question1TotalAmountOfDollarsInDebits(float value) {
        htmlFinalReport.append(String.format("<tr><td>What is the total amount in dollars of debits?\n</td><td>$%,f</td></tr>\n",value));
    }

    /**
     * Answer question 2 What is the total amount in dollars of credits?
     * @param value The answer to question 2
     */
    public static void question2TotalAmountOfDollarsInCredits(float value) {
        htmlFinalReport.append(String.format("<tr><td>What is the total amount in dollars of credits?\n</td><td>$%,f</td></tr>\n",value));
    }

    /**
     * Answer question 3 How many autopays were started?
     * @param value The answer to question 3
     */
    public static void question3HowManyAutoPaysStarted(int value) {
        htmlFinalReport.append(String.format("<tr><td>How many autopays were started?\n</td><td>%d</td></tr>\n",value));
    }

    /**
     * Answer question 4 How many autopays were ended?
     * @param value the answer to question 4
     */
    public static void question4HowManyAutoPaysEnded(int value) {
        htmlFinalReport.append(String.format("<tr><td>How many autopays were ended?\n</td><td>%d</td></tr>\n",value));
    }

    /**
     * Answer question 5 What is the balance of the special user id?
     * @param value The value of the balance for the special user
     */
    public static void question5BalanceOfSpecialUserId(float value) {
        htmlFinalReport.append(String.format("<tr><td>What is the balance of the special user id?\n</td><td>%.2f</td></tr>\n",value));
    }

    /**
     * Launch a browser with the logging text
     */
    public static void launch() {
        try {
            FileWriter fw = new FileWriter(htmlFile);
            BufferedWriter bw = new BufferedWriter(fw);
            htmlContents.append("</table>\n</body>\n</html>\n");
            bw.write(htmlContents.toString());
            bw.close();
            fw.close();
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Launch a browser with the answers to the questions
     */
    public static void launchQuestions() {
        try {
            FileWriter fw = new FileWriter(htmlFileFinalReport);
            BufferedWriter bw = new BufferedWriter(fw);
            htmlFinalReport.append("</table>\n</body>\n</html>\n");
            bw.write(htmlFinalReport.toString());
            bw.close();
            fw.close();
            Desktop.getDesktop().browse(htmlFileFinalReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
