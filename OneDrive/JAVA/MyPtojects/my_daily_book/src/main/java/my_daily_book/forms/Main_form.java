package my_daily_book.forms;

import my_daily_book.File_storage;
import my_daily_book.Project;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static my_daily_book.File_storage.projects;
import static my_daily_book.File_storage.notes;
import static my_daily_book.File_storage.projectID;


public class Main_form extends JFrame {

    JPanel panet_buttons, panel_view_projects;
    JButton btn_new_project, btn_edit_project, btn_delete_project, btn_exit, btn_choose;
    int lb_Xposation = 15;
    int lb_Yposation = 15;

    File_storage file_storage = new File_storage();

    public Main_form() throws IOException {
        projects.clear();
        projects = file_storage.loadProjectsFile();
        notes.clear();
        notes = file_storage.loadNotesFile();

        panet_buttons = new JPanel();
        panet_buttons.setLayout(null);
        panet_buttons.setBounds(10, 10, 170, 580);
        panet_buttons.setBackground(Color.YELLOW);
        add(panet_buttons);

        btn_new_project = new JButton("New Project");
        btn_new_project.setForeground(Color.BLACK);
        btn_new_project.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn_new_project.setBackground(Color.YELLOW);
        btn_new_project.setBounds(10, 10, 150, 50);
        btn_new_project.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new Add_newProject_form();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panet_buttons.add(btn_new_project);
        btn_edit_project = new JButton("Edit Project");
        btn_edit_project.setForeground(Color.BLACK);
        btn_edit_project.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn_edit_project.setBackground(Color.YELLOW);
        btn_edit_project.setBounds(10, 70, 150, 50);
        btn_edit_project.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (projectID == -1) {
                    new SelectPoject_form();
                    dispose();
                } else {
                    try {
                        new EditProject_form();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();
                }
            }
        });


        panet_buttons.add(btn_edit_project);
        btn_delete_project = new JButton("Delete Project");
        btn_delete_project.setForeground(Color.BLACK);
        btn_delete_project.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn_delete_project.setBackground(Color.YELLOW);
        btn_delete_project.setBounds(10, 130, 150, 50);
        btn_delete_project.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (projectID == -1) {
                    new SelectPoject_form();
                } else {
                    try {
                        new DeleteProject_form();
                        dispose();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }
        });
        panet_buttons.add(btn_delete_project);
        btn_exit = new JButton("Exit");
        btn_exit.setForeground(Color.BLACK);
        btn_exit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn_exit.setBackground(new Color(253, 55, 137, 242));
        btn_exit.setBounds(10, 190, 150, 50);
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panet_buttons.add(btn_exit);

        panel_view_projects = new JPanel();
        panel_view_projects.setLayout(null);
        panel_view_projects.setBackground(new Color(0, 236, 253, 242));
        panel_view_projects.setBounds(170, 10, 620, 580);
        add(panel_view_projects);

        view_projects();


        setTitle("My Daily Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Main_form frm = new Main_form();
        frm.setVisible(true);
    }


    public void view_projects() throws IOException {

        for (Project p : projects) {
            JButton b = new JButton(p.getName());
            b.setName(p.getName());
            b.setBackground(Color.YELLOW);
            b.setBounds(lb_Xposation, lb_Yposation, 100, 40);
            lb_Xposation = +lb_Xposation + 106;
            if (lb_Xposation > 400) {
                lb_Xposation = 15;
                lb_Yposation = +lb_Yposation + 50;
            }
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (p.getName().equals(b.getText())) {
                        projectID = p.getId();
                        try {
                            new ProjectDetails_form();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
            panel_view_projects.add(b);
        }
    }
}

/*
        for (Project p : projects)
{
            btn_choose = new JButton(p.getName());
            if(lb_Xposation < 620){
                btn_choose.setBounds(lb_Xposation +=105, lb_Yposation , 100, 40);
                btn_choose.setFont(new Font("Tahoma", Font.BOLD, 20));
                btn_choose.setForeground(Color.BLACK);
                btn_choose.setBackground(Color.YELLOW);
                btn_choose.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(Project p : projects){
                            if (p.getName().equals(btn_choose.getText())) {}
                            projectID=p.getId();
                            try {
                                new ProjectDetails_form();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                panel_view_projects.add(btn_choose);
            } else {
                lb_Xposation = 5;
                btn_choose = new JButton(p.getName());
                btn_choose.setBounds(lb_Xposation , lb_Yposation + 50 , 100, 40);
                btn_choose.setFont(new Font("Tahoma", Font.BOLD, 20));
                btn_choose.setForeground(Color.BLACK);
                btn_choose.setBackground(Color.YELLOW);
                panel_view_projects.add(btn_choose);
            }
        }



 */
