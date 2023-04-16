package penguins.backend.Semester;

public class Semester {

    private static boolean started = false;
    private static boolean courseEvaluation = false;
    private static boolean courseRegistration = false;

    private Semester() {}

    public static boolean isStarted() {
        return started;
    }

    public static void setStarted(boolean started) {
        Semester.started = started;
    }

    public static boolean isCourseEvaluation() {
        return courseEvaluation;
    }

    public static void setCourseEvaluation(boolean courseEvaluation) {
        Semester.courseEvaluation = courseEvaluation;
    }

    public static boolean isCourseRegistration() {
        return courseRegistration;
    }

    public static void setCourseRegistration(boolean courseRegistration) {
        Semester.courseRegistration = courseRegistration;
    }

}