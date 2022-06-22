package itmo.requests;

import itmo.interfaces.SimpleRequest;

public class Request {
    public Class<? extends SimpleRequest> requestType;
    public SimpleRequest request;

    public Request() {}

    public Request(SimpleRequest request) {
        this.request = request;
        requestType = request.getClass();
    }
}
