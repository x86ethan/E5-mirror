package App;

public abstract class DataType {
    public abstract boolean importCSV(String filename);
    public abstract boolean exportCSV(String filename);
}