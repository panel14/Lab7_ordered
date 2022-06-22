package itmo.requests;

import itmo.interfaces.SimpleRequest;

public class AuthenticationRequest implements SimpleRequest {
    public String[] userData;
    public Boolean isRegistration;

    public AuthenticationRequest() {}

    public AuthenticationRequest(String[] userData, Boolean isRegistration) {
        this.userData = userData;
        this.isRegistration = isRegistration;
    }
}
