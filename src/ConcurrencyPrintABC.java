import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyPrintABC {
    // print ABCABC
    public static void main(String[] args) {
        Integer contentLock = 0;
        Thread A = new PrintCharThread(0);
        Thread B = new PrintCharThread(1);
        Thread C = new PrintCharThread(2);
        A.start();
        B.start();
        C.start();


    }



}
