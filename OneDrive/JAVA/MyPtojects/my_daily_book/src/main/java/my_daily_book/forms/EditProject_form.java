package my_daily_book.forms;
import my_daily_book.File_storage;
import my_daily_book.Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static my_daily_book.File_storage.*;

public class EditProject_form extends JFrame {
    File_storage fs = new File_storage();

    JPanel panel, btnPanel;
    JButton btn_add, btn_cancel;
    JLabel lb_id, lb_name, lb_date, lb_discription;
    TextField tf_id, tf_name, tf_date;
    JTextArea tf_discription;

    EditProject_form() throws IOException {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 390, 480);
        panel.setBackground(new Color(0, 236, 253, 242));
        add(panel);
        lb_id = new JLabel("Note's ID");
        lb_id.setEnabled(false);
        lb_id.setBounds(5, 5, 100, 40);
        panel.add(lb_id);
        tf_id = new TextField();
        tf_id.setEnabled(false);
        tf_id.setBounds(110, 5, 270, 40);
        int id = noteID;

        tf_id.setText(String.valueOf(id));
        panel.add(tf_id);
        lb_name = new JLabel("Note's Title");
        lb_name.setBounds(5, 50, 100, 40);
        panel.add(lb_name);
        tf_name = new TextField();
        tf_name.setEnabled(true);
        tf_name.setBounds(110, 50, 270, 40);
        tf_name.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(tf_name);
        lb_date = new JLabel("Date");
        lb_date.setBounds(5, 100, 100, 40);
        panel.add(lb_date);
        tf_date = new TextField();
        tf_date.setEnabled(false);
        tf_date.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tf_date.setBounds(110, 100, 270, 40);
        panel.add(tf_date);
        lb_discription = new JLabel("Note");
        lb_discription.setBounds(5, 150, 100, 40);
        panel.add(lb_discription);
        tf_discription = new JTextArea();
        tf_discription.setEnabled(true);
        tf_discription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tf_discription.setBounds(110, 150, 270, 320);
        tf_discription.setLineWrap(true);
        panel.add(tf_discription);


        btnPanel = new JPanel();
        btnPanel.setLayout(null);
        btnPanel.setBackground(new Color(0, 236, 253, 242));
        btnPanel.setBounds(5, 500, 380, 95);
        add(btnPanel);
        btn_add = new JButton("Edit");
        btn_add.setBackground(new Color(55, 236, 253, 242));
        btn_add.setForeground(Color.white);
        btn_add.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn_add.setBounds(50, 25, 100, 40);
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (projectID == -1){
                    new SelectPoject_form();
                }else {
                    try {
                        editDate();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();
                    try {
                        new Main_form();
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
        btn_cancel.setBounds(200, 25, 100, 40);
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


        getData();


        setLayout(null);
        setUndecorated(true);
        setLocation(200, 200);
        setSize(400, 600);
        this.setTitle("Add New Project");
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new EditNote_form();
    }

    public void editDate() throws IOException {
        for (Project p : projects) {
            if (projectID == p.getId()) {
                p.setDate(tf_date.getText());
                p.setId(Integer.parseInt(tf_id.getText()));
                p.setName(tf_name.getText());
                p.setDescription(tf_discription.getText());
            }
        }
        fs.saveProjectsFille(projects);
    }


    public void getData() {
        for (Project project : projects) {
            if (project.getId() == projectID) {
                tf_id.setText(String.valueOf(project.getId()));
                tf_name.setText(project.getName());
                tf_date.setText(project.getDate());
                tf_discription.setText(project.getDescription());
            }
        }
    }
}
