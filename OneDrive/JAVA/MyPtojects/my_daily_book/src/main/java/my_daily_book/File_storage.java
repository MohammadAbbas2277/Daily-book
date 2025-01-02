package my_daily_book;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File_storage {
    private String projects_file_name = "src/prjects.json";
    private String notes_file_name = "src/notes.json";

    public static List<Project> projects = new ArrayList<>();
    public static List <Note> notes = new ArrayList();

    Project[] project_gson;
    Note[] note_gson;
    public void saveProjectsFille(List<Project> projects) throws IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter(projects_file_name);
        gson.toJson(projects, fw);
        fw.close();
    }
    public List loadProjectsFile() throws IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader(projects_file_name);
        project_gson = gson.fromJson(fr, Project[].class);
        fr.close();
        for (Project p : project_gson) {
            projects.add(p);
        }
        return projects;
    }


    public void saveNotesFile(List<Note> notes) throws IOException {
        Gson gson = new Gson();
        FileWriter fw = new FileWriter(notes_file_name);
        gson.toJson(notes, fw);
        fw.close();
    }



    public List<Note> loadNotesFile() throws IOException {
        Gson gson = new Gson();
        FileReader fr = new FileReader(notes_file_name);
        note_gson = gson.fromJson(fr, Note[].class);
        fr.close();
        for (Note n : note_gson) {
            notes.add(n);
        }
        return notes;
    }


}

