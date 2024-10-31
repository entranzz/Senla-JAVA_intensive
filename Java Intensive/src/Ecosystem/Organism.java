package Ecosystem;



public abstract class Organism {
    protected String name;

    public Organism(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
