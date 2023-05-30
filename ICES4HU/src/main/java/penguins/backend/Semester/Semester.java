package penguins.backend.Semester;

public class Semester {

    private static boolean semesterStarted = false;
    private static boolean evaluationStarted = false;
    private static boolean addOrDropStarted = false;

    /**
     * Indicates whether the semester is started.
     *
     * @return true if the semester is started, false otherwise
     */
    public static boolean isSemesterStarted() {
        return semesterStarted;
    }


    /**
     * Starts and finishes the semester.
     *
     * @param started true to start the semester, false otherwise
     */
    public static void setSemesterStarted(boolean started) {
        Semester.semesterStarted = started;
    }


    /**
     * Indicates whether the evaluation is started.
     *
     * @return true if the semester is started, false otherwise
     */
    public static boolean isEvaluationStarted() {
        return evaluationStarted && semesterStarted;
    }


    /**
     * Starts and finishes the evaluation.
     *
     * @param started true to start the semester, false otherwise
     */
    public static void setEvaluationStarted(boolean started) {
        Semester.evaluationStarted = started;
    }


    /**
     * Indicates whether the add or drop is started.
     *
     * @return true if the add or drop is started, false otherwise
     */
    public static boolean isAddOrDropStarted() {
        return addOrDropStarted;
    }


    /**
     * Starts and finishes the add or drop.
     *
     * @param started true to start the add or drop, false otherwise
     */
    public static void setAddOrDropStarted(boolean started) {
        Semester.addOrDropStarted = started;
    }


}
