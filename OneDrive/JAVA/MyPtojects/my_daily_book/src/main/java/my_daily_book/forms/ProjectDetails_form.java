package my_daily_book.forms;

import my_daily_book.File_storage;
import my_daily_book.Note;
import my_daily_book.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static my_daily_book.File_storage.projectID;
import static my_daily_book.File_storage.notes;
import static my_daily_book.File_storage.projects;
import static my_daily_book.File_storage.noteID;

public class ProjectDetails_form extends JFrame {



    JLabel lb_progectName;
    JPanel panelList, panelDetails, panel_buttons;
    JList listNotes;
    JTextArea txtDetails;
    JButton btnAddNote, btnDeleteNote, btnEditNote, btnExit, btnPrint;

    ProjectDetails_form() throws IOException {

        panelList = new JPanel();
        panelList.setLayout(null);
        panelList.setBounds(5, 5, 200, 488);
        add(panelList);
        lb_progectName = new JLabel("Project Name");
        lb_progectName.setFont(new Font("", Font.BOLD, 20));
        lb_progectName.setBounds(5, 5, 200, 30);
        panelList.add(lb_progectName);
        listNotes = new JList();
        listNotes.setBounds(2, 52, 195, 438);
        listNotes.setFont(new Font("", Font.BOLD, 20));
        addValuesTolist();
        listNotes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listNotes.getSelectedIndex();
                for (Note note : notes) {
                    if (projectID == note.getProject_id() ){
                        noteID = ids.get(index);
                    }
                }
            }
        });
        panelList.add(listNotes);
        txtDetails = new JTextArea();
        txtDetails.setFont(new Font("", Font.BOLD, 20));
        txtDetails.setEditable(true);
        txtDetails.setLineWrap(true);
        txtDetails.setBounds(5, 5, 575, 480);
        JScrollPane scrollPane = new JScrollPane(txtDetails);
        scrollPane.setBounds(210, 5, 585, 490);
        add(scrollPane);

        viewDetails();
        panel_buttons = new JPanel();
        panel_buttons.setLayout(null);
        panel_buttons.setBounds(10, 500, 785, 100);
        add(panel_buttons);
        btnAddNote = new JButton("Add Note");
        btnAddNote.setFont(new Font("", Font.BOLD, 20));
        btnAddNote.setBounds(5, 25, 140, 50);
        btnAddNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new AddNewNote_form();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel_buttons.add(btnAddNote);
        btnDeleteNote = new JButton("Delete Note");
        btnDeleteNote.setFont(new Font("", Font.BOLD, 20));
        btnDeleteNote.setBounds(150, 25, 140, 50);
        btnDeleteNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (noteID ==  -1){
                   new SelectNote_form();
               }else {
                   dispose();
                   try {
                       new DetleteNote_form();
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
               }
            }
        });
        panel_buttons.add(btnDeleteNote);
        btnEditNote = new JButton("Edit Note");
        btnEditNote.setFont(new Font("", Font.BOLD, 20));
        btnEditNote.setBounds(300, 25, 140, 50);
        btnEditNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(noteID == -1){
                    new SelectNote_form();
                }else {
                    dispose();
                    try {
                        new EditNote_form();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        panel_buttons.add(btnEditNote);
        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("", Font.BOLD, 20));
        btnExit.setBounds(450, 25, 100, 50);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new Main_form();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel_buttons.add(btnExit);
        btnPrint = new JButton("Print");
        btnPrint.setFont(new Font("", Font.BOLD, 20));
        btnPrint.setBounds(680, 25, 100, 50);
        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtDetails.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel_buttons.add(btnPrint);


        setTitle("Project Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new ProjectDetails_form();
    }
    List<Integer> ids = new ArrayList<>();
    public void addValuesTolist() throws IOException {
        DefaultListModel dlm = new DefaultListModel();
        for (Note note : notes) {
            if (note.getProject_id() == projectID) {

                dlm.addElement(note.getNote_title());
                ids.add(note.getNote_id());
            }
        }
        listNotes.setModel(dlm);
    }

    public void viewDetails() throws IOException {


        String str = "";
        for (Note note : notes) {
            if (note.getProject_id() == projectID) {
                str = str + note.getNote_title() + "\n"
                        + note.getNote_date()
                        + "\n" + note.getNote()
                        + "\n\n";
            }
        }
        txtDetails.setText(str);

        for (Project project : projects) {
            if (projectID == project.getId()) {
                lb_progectName.setText(project.getName());
            }
        }
    }

}