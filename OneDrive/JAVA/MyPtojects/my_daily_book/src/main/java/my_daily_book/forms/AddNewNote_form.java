package my_daily_book.forms;

import my_daily_book.File_storage;
import my_daily_book.Note;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static my_daily_book.forms.Main_form.projectID;
import static my_daily_book.File_storage.notes;

public class AddNewNote_form extends JFrame {

         File_storage fs = new File_storage();

        JPanel panel, btnPanel;
        JButton btn_add, btn_cancel;
        JLabel lb_id, lb_name,lb_date,lb_discription;
        TextField tf_id, tf_name, tf_date;
        JTextArea tf_discription;

        AddNewNote_form() throws IOException {



            panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(5,5,390,480);
            panel.setBackground(new Color(0, 236, 253, 242));
            add(panel);
            lb_id = new JLabel("Note's ID");
            lb_id.setEnabled(false);
            lb_id.setBounds(5,5,100,40);
            panel.add(lb_id);
            tf_id =  new TextField();
            tf_id.setEnabled(false);
            tf_id.setBounds(110,5,270,40);
            int id = getNoteID();
            tf_id.setText(String.valueOf(id));
            panel.add(tf_id);
            lb_name = new JLabel("Note's Title");
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
            tf_date.setText(getDate());
            tf_date.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            tf_date.setBounds(110,100,270,40);
            panel.add(tf_date);
            lb_discription = new JLabel("Note");
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
            btn_add = new JButton("Add");
            btn_add.setBackground(new Color(55, 236, 253, 242));
            btn_add.setForeground(Color.white);
            btn_add.setFont(new Font("Tahoma", Font.BOLD, 14));
            btn_add.setBounds(50,25,100,40);
            btn_add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tf_name.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please enter a title");
                    }else{
                        String name = tf_name.getText();
                        boolean isExist = false;
                        do{
                            for(Note note : notes) {
                                if (name.equals(note.getNote_title()) && projectID == note.getProject_id()) {
                                    isExist = true;
                                }
                            }
                            if(isExist) {
                                JOptionPane.showMessageDialog(null, "Note already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                                isExist = false;
                            }else{
                                int project_id = projectID;
                                String date = getDate();
                                String time = getTime();
                                tf_date.setText(date + " " + time );
                                String disc = tf_discription.getText();
                                notes.add(new Note(id, name, project_id,date,time, disc));
                                dispose();
                                try {
                                    new ProjectDetails_form();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }while(isExist);
                        try {
                            fs.saveNotesFile(notes);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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
                        new ProjectDetails_form();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            btnPanel.add(btn_cancel);






            setLayout(null);
            setUndecorated(true);
            setLocation( 200,200);
            setSize(400,600);
            this.setTitle("Add New Project");
            setVisible(true);
        }

        public static void main(String[] args) throws IOException {
            new AddNewNote_form();
        }




        private String getDate(){
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter fr = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(fr);
        }
        private  String getTime(){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter fr1 = DateTimeFormatter.ofPattern("HH:mm:ss");
            return now.format(fr1);
        }

        Random random = new Random();
        private int getNoteID(){
            int id = random.nextInt(100000);
            boolean isTrue = false;
            do{
                for (Note note : notes) {
                    if (note.getNote_id() == id) {
                        id = random.nextInt(100000);
                        isTrue = true;
                    }else isTrue = false;
                }
            }while(isTrue);
            return id;
        }
    }


