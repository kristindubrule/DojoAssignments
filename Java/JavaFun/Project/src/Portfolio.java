import javax.sound.sampled.Port;
import java.util.ArrayList;

public class Portfolio {
    private ArrayList<Project> projects;

    public Portfolio(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public Portfolio() {
        this.projects = new ArrayList<Project>();
    }

    public void addProject(Project p) {
        projects.add(p);
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public void print() {
        double sum = 0;
        for(Project p : this.projects) {
            System.out.println(p.elevatorPitch());
            sum += p.getCost();
        }
        System.out.println("Total portfolio cost: " + sum);
    }
}
