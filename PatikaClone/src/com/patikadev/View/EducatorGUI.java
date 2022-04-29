package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Course;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EducatorGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JLabel lbl_welcome;
    private JTable tbl_course_list;
    private JButton backwardButton;
    private int id;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private JPopupMenu contentMenu;

    public EducatorGUI(int id) {
        this.id = id;

        add(wrapper);
        setSize(400, 400);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_welcome.setText("Welcome " + User.getFetch(id).getName());

        mdl_course_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_course_list = {"ID", "Path", "Name", "Language"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();

        contentMenu = new JPopupMenu();
        JMenuItem cMenu = new JMenuItem("Contents");
        contentMenu.add(cMenu);

        cMenu.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());
            ContentGUI c = new ContentGUI(selected_id);
        });

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.setComponentPopupMenu(contentMenu);

        tbl_course_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_course_list.rowAtPoint(point);
                tbl_course_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        backwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : Course.getListByUser(User.getFetch(id).getId())) {
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getPath().getName();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLanguage();

            mdl_course_list.addRow(row_course_list);
        }
    }
}


