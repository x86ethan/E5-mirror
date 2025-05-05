public class Affectation {
    private double comptability;
    private Teenager host;
    private Teenager guest;

    public Affectation(Teenager host,Teenager guest){
        this.host=host;
        this.guest=guest;
    }

    public double Compability(){
        return host.compatibility(guest);
    }
}
