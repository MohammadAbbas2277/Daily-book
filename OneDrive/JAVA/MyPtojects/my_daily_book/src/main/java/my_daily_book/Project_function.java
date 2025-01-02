package my_daily_book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my_daily_book.File_storage.projects;

public class Project_function {
    Map account = new HashMap();
    public Project_function() {

    }

    public void add_project(int id, String name,String date, String description) {
        Project project= new Project(id,name,date,description);
        projects.add(project);
    }



}
