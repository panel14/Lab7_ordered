package itmo.servers;

import java.io.IOException;

/**
 * class sending response to client
 */
public class WritingThread implements Runnable {

    private final ThreadInfo info;
    private final String answer;

    /**
     * @param info
     * @param answer
     */
    public WritingThread(ThreadInfo info, String answer) {
        this.info = info;
        this.answer = answer;
    }

    @Override
    public void run() {
        //Отправляем полученный ответ клиенту
        try {
            info.serverIO.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
