package model;

public enum Country {
    FR("France"),GE("Alemagne"),IT("Italie"),ES("Espagne");

    private final String longLabel;

    private Country(String longLabel){
        this.longLabel = longLabel;
    }

    public String getLongLabel(){
        return this.longLabel;
    }
}
