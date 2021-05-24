package MODEL;

public class Patogen extends SimData {
    public double propability;

    public Patogen(String name, double propability) {
        super(name);
        this.propability = propability;
    }
}
