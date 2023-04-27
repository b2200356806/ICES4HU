package penguins.backend.Semester;

public class Semester {

    private static boolean started = false;
    private Semester() {}

    /**
     * Indicates whether the semester is started.
     * @return true if the semester is started, false otherwise
     */
    public static boolean isStarted() {
        return started;
    }


    /**
     * Starts and finishes the semester.
     * @param started true to start the semester, false otherwise
     */
    public static void setStarted(boolean started) {
        Semester.started = started;
    }



}
