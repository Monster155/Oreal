package ru.itlab.oreal.Utils;

public class DialogHolder {
    private String text;
    private boolean isPlayerTell;

    public DialogHolder(String text, boolean isPlayerTell) {
        this.text = text;
        this.isPlayerTell = isPlayerTell;
    }

    public String getText() {
        return text;
    }

    public boolean getIsPlayerTell() {
        return isPlayerTell;
    }
}
