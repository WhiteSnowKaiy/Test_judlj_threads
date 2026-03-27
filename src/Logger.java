public class Logger {
    private static Logger logger;

    public synchronized void log(String msg) {
        System.out.println(msg);
    }

    public static Logger getLogger() {
        if  (logger == null) {
            logger = new Logger();
        }
        return logger;
    }
}
