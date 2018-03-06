import java.util.ArrayList;

public class ProjectTest {
    public static void main(String[] args) {
        Project p1 = new Project("Infinity", "A very long project",10);
        Project p2 = new Project("Secret");
        Project p3 = new Project();

        System.out.println(p1.elevatorPitch());
        System.out.println(p2.elevatorPitch());
        System.out.println(p3.elevatorPitch());

        p2.setDescription("Not a secret anymore");
        p2.setCost(20.50);
        System.out.println(p2.elevatorPitch());
        System.out.println("p2 name: " + p2.getName());
        System.out.println("p2 description: " + p2.getDescription());

        p3.setName("Great name!");
        p3.setDescription("The best description!");
        System.out.println(p3.elevatorPitch());

        p1.setName("Infinity+1");
        p1.setDescription("This project got even longer");
        System.out.println(p1.elevatorPitch());

        Portfolio pList = new Portfolio();
        pList.addProject(p1);
        pList.addProject(p2);
        pList.addProject(p3);

        System.out.println("***Try #1***");
        pList.getProjects();
        pList.print();

        System.out.println("***Try #2***");
        ArrayList<Project> pList2 = new ArrayList<Project>(3);
        pList2.add(p3);
        pList2.add(p2);
        pList2.add(p1);
        pList.print();

    }
}
