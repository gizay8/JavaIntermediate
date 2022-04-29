package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Content;
import com.patikadev.Model.Course;
import com.patikadev.Model.Question;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ContentGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel pnl_content_list;
    private JScrollPane scrl_content_list;
    private JTable tbl_content_list;
    private JPanel pnl_content_form;
    private JTextField fld_content_title;
    private JTextField fld_content_description;
    private JTextField fld_content_url;
    private JComboBox cmb_content_course;
    private JButton btn_content_add;
    private JTextField fld_sh_title;
    private JButton btn_sh_content;
    private JButton btn_backward;
    private Object[] row_content_list;
    private DefaultTableModel mdl_content_list;
    private JPopupMenu menu;
    private JPopupMenu addQuesMenu;
    private int course_id;

    public ContentGUI(int course_id){
        this.course_id = course_id;

        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setTitle(Config.PROJECT_TITLE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        mdl_content_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 4){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"ID", "Title", "Info", "Url", "Course ID"};
        mdl_content_list.setColumnIdentifiers(col_user_list);
        row_content_list = new Object[col_user_list.length];
        loadContentModel();

        menu = new JPopupMenu();
        JMenuItem deleteMenu = new JMenuItem("Delete");
        menu.add(deleteMenu);
        deleteMenu.addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selected_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
                if(Content.delete(selected_id)&& Question.deleteQuestion(selected_id)){
                    Helper.showMsg("done");
                    loadContentModel();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        JMenuItem questions = new JMenuItem("Questions");
        menu.add(questions);
        questions.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
            QuestionGUI q = new QuestionGUI(selected_id);
        });

        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);
        tbl_content_list.setComponentPopupMenu(menu);

        tbl_content_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                int id = Integer.parseInt(tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 0).toString());
                String title = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 1).toString();
                String info = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 2).toString();
                String url = tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(), 3).toString();


                if(Content.update(id, title, info, url)){
                    Helper.showMsg("done");
                }else{
                    Helper.showMsg("error");
                }
                loadContentModel();
            }
        });

        tbl_content_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_content_list.rowAtPoint(point);
                tbl_content_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });

        btn_sh_content.addActionListener(e -> {
            String query = Content.searchQuery(fld_sh_title.getText(),course_id);
            ArrayList<Content> searchingContent = Content.searchContentList(query);
            loadContentModel(searchingContent);
        });
        btn_content_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_content_title)||Helper.isFieldEmpty(fld_content_description)||Helper.isFieldEmpty(fld_content_url)){
                Helper.showMsg("fill");
            }else{
                if(Content.add(fld_content_title.getText(),fld_content_description.getText(),fld_content_url.getText(),course_id)){
                    Helper.showMsg("done");
                    loadContentModel();
                    fld_content_title.setText(null);
                    fld_content_description.setText(null);
                    fld_content_url.setText(null);
                }else{
                    Helper.showMsg("error");
                }
            }
        });
        btn_backward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void loadContentModel(ArrayList<Content> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Content obj : list){
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getInfo();
            row_content_list[i++] = obj.getUrl();
            row_content_list[i++] = obj.getCourse_id();

            mdl_content_list.addRow(row_content_list);
        }
    }

    public void loadContentModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Content obj : Content.getListByCourseId(course_id)){
            i = 0;
            row_content_list[i++] = obj.getId();
            row_content_list[i++] = obj.getTitle();
            row_content_list[i++] = obj.getInfo();
            row_content_list[i++] = obj.getUrl();
            row_content_list[i++] = obj.getCourse_id();

            mdl_content_list.addRow(row_content_list);
        }
    }
}
