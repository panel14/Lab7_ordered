package itmo.servers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import itmo.deserializers.RequestDeserializer;
import itmo.requests.Request;

import java.util.concurrent.ExecutorService;

public class ReadingThread implements Runnable {

    private final ThreadInfo info;
    private final ExecutorService writingThread;

    public ReadingThread(ThreadInfo info, ExecutorService writingThread) {
        this.info = info;
        this.writingThread = writingThread;
    }

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule()
                .addDeserializer(Request.class, new RequestDeserializer()));

        try {
            Request request = objectMapper.readValue(info.request, Request.class);

            Thread taskThread = new TaskThread(info, writingThread, request);
            taskThread.start();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
