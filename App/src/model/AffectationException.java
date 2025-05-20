package model;

public class AffectationException extends Exception {
    public final String reason;

    public AffectationException (String reason) {
        this.reason = reason;
    }

    public void printTrace() {
        System.err.println("Exception: Affectation: " + this.reason);
    }
}
