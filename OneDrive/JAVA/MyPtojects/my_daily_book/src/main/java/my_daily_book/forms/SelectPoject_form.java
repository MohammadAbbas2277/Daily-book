package my_daily_book.forms;

import my_daily_book.Project;

import static my_daily_book.File_storage.projectID;
import static my_daily_book.File_storage.projects;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectPoject_form extends JFrame {
    int index1 = -1;
    int id = -1;

    JPanel panel;
    JLabel lb_selectProject;
    JList projectList;
    JButton btn_selectProject, btn_cancel;
    SelectPoject_form() {
    panel = new JPanel();
    panel.setLayout(null);
    panel.setBounds(5, 5, 290, 580);
    add(panel);
    lb_selectProject = new JLabel("Select Project");
    lb_selectProject.setBounds(0, 0, 200, 40);
    lb_selectProject.setFont(new Font("Tahoma", Font.PLAIN, 20));
    panel.add(lb_selectProject);
    projectList = new JList();
    projectList.setBounds(0,45,590,480);
    projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollPane =new JScrollPane(projectList);
    scrollPane.setBounds(0, 45, 290, 480);
    panel.add(scrollPane);
    btn_selectProject = new JButton("Select Project");
    btn_selectProject.setBounds(0, 540, 180, 40);
    btn_selectProject.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btn_selectProject.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            projectID = ids.get(projectList.getSelectedIndex());
            dispose();
        }
    });
    panel.add(btn_selectProject);
    btn_cancel = new JButton("Cancel");
    btn_cancel.setBounds(185, 540, 100, 40);
    btn_cancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btn_cancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            hide();
        }
    });
    panel.add(btn_cancel);


    setDataInList();

        setLayout(null);
        setSize(300,600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SelectPoject_form();
    }
    List<Integer> ids = new ArrayList<>();
    private void setDataInList(){
        DefaultListModel dlm = new DefaultListModel();
        for (Project p : projects){
            dlm.addElement(p.getName());
            ids.add(p.getId());
        }
        projectList.setModel(dlm);
    }
}
