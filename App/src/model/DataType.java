package model;

public interface DataType {
    public abstract boolean importCSV(String filename,  boolean header);
    public abstract boolean exportCSV(String filename);
}
