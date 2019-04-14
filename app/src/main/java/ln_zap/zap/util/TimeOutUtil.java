package ln_zap.zap.util;

public class TimeOutUtil {

    // 10 seconds time out
    private static long TIMEOUT_DELAY = 1000 * 10;

    private static long appClosed = 0L;
    private static TimeOutUtil instance = null;
    private boolean canBeRestarted = true;

    private TimeOutUtil() {
        ;
    }

    public static TimeOutUtil getInstance() {

        if (instance == null) {
            instance = new TimeOutUtil();
        }

        return instance;
    }

    public void restartTimer() {
        appClosed = System.currentTimeMillis();
    }

    public boolean isTimedOut() {
        boolean timedOut = (System.currentTimeMillis() - appClosed) > TIMEOUT_DELAY;
        // Do also not allow times prior to "appClosed".
        // This would allow to circumventing timeout check by setting the time of the device manually.
        boolean invalidTime = System.currentTimeMillis() < appClosed;
        return timedOut || invalidTime;
    }

    public boolean getCanBeRestarted() {
        return canBeRestarted;
    }

    public void setCanBeRestarted(boolean canBeRestarted) {
        this.canBeRestarted = canBeRestarted;
    }
}
