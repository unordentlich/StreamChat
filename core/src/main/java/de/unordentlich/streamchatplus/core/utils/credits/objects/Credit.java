package de.unordentlich.streamchatplus.core.utils.credits.objects;

public class Credit {

    String uuid, contribution;

    public Credit(String uuid, String contribution) {
        this.uuid = uuid;
        this.contribution = contribution;
    }

    public String getUuid() {
        return uuid;
    }

    public String getContribution() {
        return contribution;
    }
}
