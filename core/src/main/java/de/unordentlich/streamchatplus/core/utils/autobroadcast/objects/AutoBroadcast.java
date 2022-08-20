package de.unordentlich.streamchatplus.core.utils.autobroadcast.objects;

public class AutoBroadcast {

    int interval;
    String message;

    public AutoBroadcast(int interval, String message) {
        this.interval = interval;
        this.message = message;
    }

    public int getInterval() {
        return interval;
    }

    public String getMessage() {
        return message;
    }
}
