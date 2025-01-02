package my_daily_book.forms;

import my_daily_book.File_storage;

import my_daily_book.Project;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static my_daily_book.File_storage.projects;


public class test {
    static File_storage fs = new File_storage();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Project> projects1 = new ArrayList();

    public static void main(String[] args) throws IOException {
        while (true) {
            menu();
        }

    }

    public static void menu() {
        System.out.println("1. add project");
        System.out.println("2. delete project");
        System.out.println("3. update project");
        System.out.println("4. view project");
        System.out.println("5. list projects");
        System.out.println("--------------------------");
        System.out.println("6. add mote");
        System.out.println("7. delete mote");
        System.out.println("8. update mote");
        System.out.println("9. view motes");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                addProject();
                break;
            case 2:
                deleteProject();
                break;
            case 3:
                updateProject();
                break;
            case 4:
                viewProject();
                break;
            case 5:
                listProjects();


        }
    }


    public static void addProject() {
        System.out.println("oriject id");
        int id = sc.nextInt();
        System.out.println("project name");
        String projectName = sc.next();
        System.out.println("project description");
        String projectDescription = sc.next();
        System.out.println("project date");
        String projectDate = sc.next();
        projects1.add(new Project(id, projectName, projectDescription, projectDate));
    }

    public static void deleteProject() {
        System.out.println("project id");
        int id = sc.nextInt();
        Project project = new Project(1,"","","");
        for (Project p : projects1) {
            if (p.getId() == id) {
                project = p;
            }
        }
        projects1.remove(project);
    }

    public static void updateProject() {
        System.out.println("project id");
        int id = sc.nextInt();
        System.out.println("project name");
        String projectName = sc.next();
        System.out.println("project description");
        String projectDescription = sc.next();
        System.out.println("project date");
        String projectDate = sc.next();
        for (Project p : projects1) {
            if (p.getId() == id) {
                p.setName(projectName);
                p.setDescription(projectDescription);
                p.setDate(projectDate);
            }
        }
    }

    public static void viewProject() {
        System.out.println("project id");
        int id = sc.nextInt();
        for (Project p : projects1) {
            if (p.getId() == id) {
                System.out.println(p.getId());
            }
        }
    }

    public static void listProjects() {
        for (Project p : projects1) {
            System.out.println(p.getId());
        }
    }
}
