import java.util.ArrayList;

public class TeenagerInventory extends DataType{
    private ArrayList<Teenager> teenagers;

    TeenagerInventory(){
        this.teenagers = new ArrayList<>();
    }

    public boolean importCSV(String filename){
        return false;
    }

    public boolean exportCSV(String filename){
        return false;
    }

}