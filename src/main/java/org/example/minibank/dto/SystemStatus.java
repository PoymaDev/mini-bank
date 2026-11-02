package org.example.minibank.dto;

public class SystemStatus {
    private String message;
    private String version;

    public SystemStatus(String message, String version){
        this.message = message;
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public String getVersion() {
        return version;
    }
}
