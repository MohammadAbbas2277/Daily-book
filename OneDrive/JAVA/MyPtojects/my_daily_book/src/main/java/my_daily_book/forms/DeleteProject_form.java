package my_daily_book.forms;

import my_daily_book.File_storage;
import my_daily_book.Note;
import my_daily_book.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static my_daily_book.forms.Main_form.projectID;

import static my_daily_book.File_storage.projects;
import static my_daily_book.File_storage.notes;



public class DeleteProject_form extends JFrame {
     File_storage fs = new File_storage();

    JPanel panel, btnPanel;
    JButton btn_add, btn_cancel;
    JLabel lb_id, lb_name,lb_date,lb_discription;
    TextField tf_id, tf_name, tf_date;
    JTextArea tf_discription;

    DeleteProject_form() throws IOException {


        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5,5,390,480);
        panel.setBackground(new Color(0, 236, 253, 242));
        add(panel);
        lb_id = new JLabel("ID");
        lb_id.setEnabled(false);
        lb_id.setBounds(5,5,100,40);
        panel.add(lb_id);
        tf_id =  new TextField();
        tf_id.setEnabled(false);
        tf_id.setBounds(110,5,270,40);
        int id = 0;
        if(projects == null) {
            id = 1;
        }else {
            id = projects.size()+1;
        }
        tf_id.setText(String.valueOf(id));
        panel.add(tf_id);
        lb_name = new JLabel("Name");
        lb_name.setBounds(5,50,100,40);
        panel.add(lb_name);
        tf_name =  new TextField();
        tf_name.setEnabled(true);
        tf_name.setBounds(110,50,270,40);
        tf_name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_name);
        lb_date = new JLabel("Date");
        lb_date.setBounds(5,100,100,40);
        panel.add(lb_date);
        tf_date =  new TextField();
        tf_date.setEnabled(false);
        tf_date.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tf_date.setBounds(110,100,270,40);
        panel.add(tf_date);
        lb_discription = new JLabel("Discription");
        lb_discription.setBounds(5,150,100,40);
        panel.add(lb_discription);
        tf_discription =  new JTextArea();
        tf_discription.setEnabled(true);
        tf_discription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tf_discription.setBounds(110,150,270,320);
        tf_discription.setLineWrap(true);
        panel.add(tf_discription);



        btnPanel = new JPanel();
        btnPanel.setLayout(null);
        btnPanel.setBackground(new Color(0, 236, 253, 242));
        btnPanel.setBounds(5,500,380,95);
        add(btnPanel);
        btn_add = new JButton("Delete");
        btn_add.setBackground(new Color(55, 236, 253, 242));
        btn_add.setForeground(Color.white);
        btn_add.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn_add.setBounds(50,25,100,40);
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int response =  JOptionPane.showConfirmDialog(null,"do you to remove this project?","Delete project",JOptionPane.YES_NO_OPTION);
               if(response == JOptionPane.YES_OPTION) {
                   try {
                       delete();
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
                   dispose();
               } else if (response == JOptionPane.NO_OPTION) {
                   try {
                       new Main_form();
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
                   dispose();
               }

            }
        });
        btnPanel.add(btn_add);
        btn_cancel = new JButton("Cancel");
        btn_cancel.setBackground(new Color(55, 236, 253, 242));
        btn_cancel.setForeground(Color.white);
        btn_cancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn_cancel.setBounds(200,25,100,40);
        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                try {
                    new Main_form();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnPanel.add(btn_cancel);


    getProject();



        setLayout(null);
        setUndecorated(true);
        setLocation( 200,200);
        setSize(400,600);
        this.setTitle("Add New Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    private void delete() throws IOException {
        for (Project p : projects) {
            if(projectID == p.getId()) {
                projects.remove(p);
            }
        }

        for (Note note :notes ){
            if(projectID == note.getProject_id()){
                notes.remove(note);
            }
        }


        fs.saveNotesFile(notes);

        fs.saveProjectsFille(projects);
    }



private void getProject(){
        for (Project p : projects) {
            if (p.getId() == projectID){
                tf_id.setText(String.valueOf(p.getId()));
                tf_name.setText(p.getName());
                tf_date.setText(p.getDate());
                tf_discription.setText(p.getDescription());
            }
        }
}
}
