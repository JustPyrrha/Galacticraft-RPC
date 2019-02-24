package me.joezwet.galacticraft.rpc.discord;

public class Dimension {

    private final String state;
    private final String largeImageKey;
    private final String largeImageText;

    public Dimension(String state, String largeImageKey, String largeImageText) {
        this.state = state;
        this.largeImageKey = largeImageKey;
        this.largeImageText = largeImageText;
    }

    public String getState() {
        return state;
    }

    public String getLargeImageKey() {
        return largeImageKey;
    }

    public String getLargeImageText() {
        return largeImageText;
    }
}
