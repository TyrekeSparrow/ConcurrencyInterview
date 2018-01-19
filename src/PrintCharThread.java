import java.util.concurrent.atomic.AtomicInteger;

public class PrintCharThread extends Thread {
    private static final int CASE1 = 0;
    private static final int CASE2 = 1;
    private static final int CASE3 = 2;

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";

    private final int what;
//    private Integer contentLock;
    private boolean shutdown;
//    private int lastContent = -1;

    private static volatile AtomicInteger whatContent = new AtomicInteger(CASE1);



    public void shutdown() {
        shutdown = true;
//        whatContent.in
    }

    public PrintCharThread(int what/*, Integer contentLock*/) {
        this.what = what;
//        this.contentLock = contentLock;
    }

    @Override
    public void run() {
        while (!shutdown) {
            synchronized (whatContent) {
                if (whatContent.intValue() ==  what) {

                    printContent(whatContent.intValue());
                    updateWhat();
                }

                try {
                    whatContent.notifyAll();
                    whatContent.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateWhat() {
        if (whatContent.intValue() == CASE3) {
            whatContent.set(CASE1);
        } else {
            whatContent.incrementAndGet();
        }
    }

//    public static int getNextContent(int what) {
//        switch (what){
//            case CASE1:
//                return CASE2;
//            case CASE2:
//                return CASE3;
//            case CASE3:
//                return CASE1;
//            default:
//                return CASE1;
//        }
//    }

    public void printContent(int what) {
        try {
            Thread.sleep(1 * 1000);
            System.out.println(whatContent.intValue());
            System.out.println(getContent(what));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getContent(int what) {
        String content;
        switch (what) {
            case CASE1:
                content = A;
                break;
            case CASE2:
                content = B;
                break;
            case CASE3:
                content = C;
                break;
            default:
                content = A;
                break;
        }
        return content;
    }




}
