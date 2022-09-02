package de.unordentlich.streamchatplus.core.utils.customcommands.objects;

public class CustomCommand {

    String keyword;
    String response;

    public CustomCommand(String keyword, String response) {
        this.keyword = keyword;
        this.response = response;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
