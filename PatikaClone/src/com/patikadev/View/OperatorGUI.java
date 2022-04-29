package com.patikadev.View;

import com.patikadev.Helper.*;
import com.patikadev.Model.Course;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Path;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTextField fld_sh_user_name;
    private JTextField fld_sh_user_uname;
    private JComboBox cmb_sh_user_type;
    private JButton btn_user_sh;
    private JPanel pnl_path_list;
    private JScrollPane scrl_path_list;
    private JTable tbl_path_list;
    private JPanel pnl_path_add;
    private JTextField fld_path_name;
    private JButton btn_path_add;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_path;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private DefaultTableModel mdl_path_list;
    private Object[] row_path_list;
    private final Operator operator;
    private JPopupMenu pathMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;
    private JPopupMenu courseMenu;

    public OperatorGUI(Operator operator){
        this.operator = operator;

        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome " + operator.getName());

        // UserList
        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Name Surname", "Username", "Password", "Membership Type"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try{
                    String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                    fld_user_id.setText(select_user_id);
                }catch (Exception exception){

                }
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
                String user_name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
                String user_uname = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
                String user_pass = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
                String user_type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();

                if(User.update(user_id, user_name,user_uname,user_pass,user_type)){
                    Helper.showMsg("done");
                }
                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });
        // ## UserList

        // PathList
        pathMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        pathMenu.add(updateMenu);
        pathMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_path_list.getValueAt(tbl_path_list.getSelectedRow(), 0).toString());
            UpdatePathGUI updateGUI = new UpdatePathGUI(Path.getFetch(selected_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPathModel();
                    loadPathCombo();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selected_id = Integer.parseInt(tbl_path_list.getValueAt(tbl_path_list.getSelectedRow(), 0).toString());
                if(Path.delete(selected_id)){
                    Helper.showMsg("done");
                    loadPathModel();
                    loadPathCombo();
                    loadCourseModel();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        mdl_path_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 1){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_path_list = {"ID", "Path Name"};
        mdl_path_list.setColumnIdentifiers(col_path_list);
        row_path_list = new Object[col_path_list.length];
        loadPathModel();

        tbl_path_list.setModel(mdl_path_list);
        tbl_path_list.setComponentPopupMenu(pathMenu);
        tbl_path_list.getTableHeader().setReorderingAllowed(false);
        tbl_path_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_path_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_path_list.rowAtPoint(point);
                tbl_path_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        // ## PathList

        // CourseList
        courseMenu = new JPopupMenu();
        JMenuItem updMenu = new JMenuItem("Update");
        JMenuItem delMenu = new JMenuItem("Delete");
        courseMenu.add(updMenu);
        courseMenu.add(delMenu);

        updMenu.addActionListener(e -> {
            int selected_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());
            UpdateCourseGUI updGUI = new UpdateCourseGUI(Course.getFetch(selected_id));
            updGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCourseModel();
                }
            });
        });

        delMenu.addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selected_id = Integer.parseInt(tbl_course_list.getValueAt(tbl_course_list.getSelectedRow(), 0).toString());
                if(Course.delete(selected_id)){
                    Helper.showMsg("done");
                    loadCourseModel();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 1 || column == 2 || column == 3 || column == 4){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_course_list = {"ID", "Course Name", "Programming Language", "Path", "Educator"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.setComponentPopupMenu(courseMenu);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadPathCombo();
        loadEducatorCombo();

        tbl_course_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_course_list.rowAtPoint(point);
                tbl_course_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        // ## CourseList

        btn_user_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)){
                    Helper.showMsg("fill");
                }else{
                    String name = fld_user_name.getText();
                    String uname = fld_user_uname.getText();
                    String pass = fld_user_pass.getText();
                    String type = cmb_user_type.getSelectedItem().toString();
                    if(User.add(name,uname,pass,type)){
                        Helper.showMsg("done");
                        loadUserModel();
                        loadEducatorCombo();
                        fld_user_name.setText(null);
                        fld_user_uname.setText(null);
                        fld_user_pass.setText(null);
                    }else{
                        Helper.showMsg("error");
                    }
                }
            }
        });
        btn_user_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldEmpty(fld_user_id)){
                    Helper.showMsg("fill");
                }else{
                    if(Helper.confirm("sure")){
                        int user_id = Integer.parseInt(fld_user_id.getText());
                        if(User.delete(user_id)){
                            Helper.showMsg("done");
                            loadUserModel();
                            loadEducatorCombo();
                            loadCourseModel();
                            fld_user_id.setText(null);
                        }else{
                            Helper.showMsg("error");
                        }
                    }
                }
            }
        });
        btn_user_sh.addActionListener(e -> {
            String name = fld_sh_user_name.getText();
            String uname = fld_sh_user_uname.getText();
            String type = cmb_sh_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name,uname,type);
            ArrayList<User> searchingUser = User.searchUserList(query);
            loadUserModel(searchingUser);
        });
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });
        btn_path_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_path_name)){
                Helper.showMsg("fill");
            }else{
                if(Path.add(fld_path_name.getText())){
                    Helper.showMsg("done");
                    loadPathModel();
                    loadPathCombo();
                    fld_path_name.setText(null);
                }else{
                    Helper.showMsg("error");
                }
            }
        });
        btn_course_add.addActionListener(e -> {
            Item pathItem = (Item) cmb_course_path.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if(Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)){
                Helper.showMsg("fill");
            }else{
                if(Course.add(userItem.getKey(), pathItem.getKey(), fld_course_name.getText(),fld_course_lang.getText())){
                    Helper.showMsg("done");
                    loadCourseModel();
                    fld_course_name.setText(null);
                    fld_course_lang.setText(null);
                }else{
                    Helper.showMsg("error");
                }
            }
        });
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Course obj : Course.getList()){
            i = 0;
            row_course_list[i++] = obj.getId();
            row_course_list[i++] = obj.getName();
            row_course_list[i++] = obj.getLanguage();
            row_course_list[i++] = obj.getPath().getName();
            row_course_list[i++] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }
    }

    private void loadPathModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_path_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Path obj : Path.getList()){
            i = 0;
            row_path_list[i++] = obj.getId();
            row_path_list[i++] = obj.getName();
            mdl_path_list.addRow(row_path_list);
        }
    }

    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(User obj : User.getList()){
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);
        for(User obj : list){
            int i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadPathCombo(){
        cmb_course_path.removeAllItems();
        for(Path obj : Path.getList()){
            cmb_course_path.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadEducatorCombo(){
        cmb_course_user.removeAllItems();
        for(User obj : User.getListOnlyEducator()){
                cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
    /*public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("Gizay Cin");
        op.setPass("1234");
        op.setType("operator");
        op.setUname("gizay");


        OperatorGUI opGUI = new OperatorGUI(op);
    }*/
}
