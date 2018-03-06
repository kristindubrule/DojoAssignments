public class Project {
    private String name;
    private String description;
    private double cost;

    public Project(String name, String description, double cost) {
        this.name = name;
        this.description = description;
    }

    public Project(String name) {
        this(name, "", 0);
    }

    public Project() {
        this("", "", 0);
    }

    public String elevatorPitch() {
        return name + ": " + description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
