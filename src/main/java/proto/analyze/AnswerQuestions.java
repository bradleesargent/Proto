package proto.analyze;

import proto.logger.HtmlLogger;

/**
 * Answer the questions by collecting values during the run
 * Created by bradlee sargent on 3/22/2019
 */
public class AnswerQuestions {
    /**
     * What is the value of the dollar credits
     */
    private static float dollarCredits = 0;
    /**
     * What is the value of the dollar debits
     */
    private static float dollarDebits = 0;
    /**
     * Increment whenever autopays are started
     */
    private static int startedAutoPays = 0;
    /**
     * Increment whenever autopays are ended
     */
    private static int endedAutoPays = 0;

    /**
     * Get the balance for the special user
     * @return The amount for the special user
     */
    public static float getBalanceSpecialUserId() {
        return balanceSpecialUserId;
    }

    /**
     * What is the special users id
     */
    private static float balanceSpecialUserId = 0;

    /**
     * Get the value of the credits
     * @return The value of the credits
     */
    public static float getDollarCredits() {

        return dollarCredits;
    }

    /**
     * Get the value of the dollar debits
     * @return The value of the dollar debits
     */
    public static float getDollarDebits() {

        return dollarDebits;
    }

    /**
     * Get the value of the autopays that were started
     * @return The value of the autopays that were started
     */
    public static int getStartedAutoPays() {

        return startedAutoPays;
    }

    /**
     * Get the value of the autopays that ended
     * @return The value of the autopays that ended
     */
    public static int getEndedAutoPays() {

        return endedAutoPays;
    }


    /**
     * Add an amount to the credit
     * @param inputCredit The amount to increase the credit by
     */
    public static void addCredit(float inputCredit) {
        dollarCredits += inputCredit;
        HtmlLogger.value("Current Dollar Credits",dollarCredits);

    }

    /**
     * Add an amount to the special users balance
     * @param inputCredit The amount to add to the special users balance
     */
    public static void addSpecialUserDollarAmount(float inputCredit) {
        balanceSpecialUserId += inputCredit;
        HtmlLogger.value("Special Dollar Amount",balanceSpecialUserId);

    }

    /**
     * Decrease the special users balance
     * @param inputCredit Subtract from the special users balance
     */
    public static void subtractSpecialUserDollarAmount(float inputCredit) {
        balanceSpecialUserId -= inputCredit;
        HtmlLogger.value("Special Dollar Amount",balanceSpecialUserId);

    }

    /**
     * Increment the autopays that are started
     */
    public static void addStartedAutoPay() {
        startedAutoPays += 1;
        HtmlLogger.value("Started Auto Pays",startedAutoPays);

    }

    /**
     * Increment the autopays that are ended
     */
    public static void addEndedAutoPay() {
        endedAutoPays += 1;
        HtmlLogger.value("Ended Auto Pays",endedAutoPays);

    }

    /**
     * Add an amount to the debit
     * @param inputDebit Amount to add to the debit
     */
    public static void addDebit(float inputDebit) {
        dollarDebits += inputDebit;
        HtmlLogger.value("Current Dollar Debits",dollarDebits);

    }


}
