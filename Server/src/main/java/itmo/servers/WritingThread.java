package itmo.servers;

import java.io.IOException;

public class WritingThread implements Runnable {

    private final ThreadInfo info;
    private final String answer;

    public WritingThread(ThreadInfo info, String answer) {
        this.info = info;
        this.answer = answer;
    }

    @Override
    public void run() {
        try {
            info.serverIO.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
