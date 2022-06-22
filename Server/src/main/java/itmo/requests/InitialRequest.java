package itmo.requests;

import itmo.interfaces.SimpleRequest;

public class InitialRequest implements SimpleRequest {
    public int number = -1;

    public InitialRequest() {}
    public InitialRequest(int number) {
        this.number = number;
    }
}
