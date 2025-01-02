package my_daily_book.forms;

import my_daily_book.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static my_daily_book.File_storage.*;


public class SelectNote_form extends JFrame {

    JPanel panel;
    JLabel lb_selectNote;
    JList noteList;
    JButton btn_selectNote, btn_cancel;
    SelectNote_form() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 290, 580);
        add(panel);
        lb_selectNote = new JLabel("Select Project");
        lb_selectNote.setBounds(0, 0, 200, 40);
        lb_selectNote.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(lb_selectNote);
        noteList = new JList();
        noteList.setBounds(0,45,590,480);
        noteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane =new JScrollPane(noteList);
        scrollPane.setBounds(0, 45, 290, 480);
        panel.add(scrollPane);
        btn_selectNote = new JButton("Select Project");
        btn_selectNote.setBounds(0, 540, 180, 40);
        btn_selectNote.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btn_selectNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteID = ids.get(noteList.getSelectedIndex());
                dispose();
            }
        });
        panel.add(btn_selectNote);
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
        for (Note n : notes){
            if (projectID == n.getProject_id() ){
            dlm.addElement(n.getNote_title());
            ids.add(n.getNote_id());
        }
        noteList.setModel(dlm);
    }
}
}
