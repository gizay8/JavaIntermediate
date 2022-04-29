package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Course;
import com.patikadev.Model.Path;
import com.patikadev.Model.User;

import javax.swing.*;

public class UpdateCourseGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_course_name;
    private JButton btn_update;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_path;
    private JComboBox cmb_course_user;
    private Course course;

    public UpdateCourseGUI(Course course){
        this.course = course;
        add(wrapper);
        setSize(300,300);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_course_name.setText(course.getName());
        fld_course_lang.setText(course.getLanguage());
        loadPathCombo();
        loadEducatorCombo();

        btn_update.addActionListener(e -> {
            Item pathItem = (Item) cmb_course_path.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if(Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang) || Helper.isComboNull(cmb_course_user) || Helper.isComboNull(cmb_course_path)){
                Helper.showMsg("fill");
            }else{
                if(Course.update(course.getId(), userItem.getKey(), pathItem.getKey(), fld_course_name.getText(),fld_course_lang.getText())){
                    Helper.showMsg("done");
                }
                dispose();
            }
        });
    }
    public void loadPathCombo(){
        cmb_course_path.removeAllItems();
        cmb_course_path.addItem(new Item(""));
        for(Path obj : Path.getList()){
            cmb_course_path.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadEducatorCombo(){
        cmb_course_user.removeAllItems();
        cmb_course_user.addItem(new Item(""));
        for(User obj : User.getListOnlyEducator()){
            cmb_course_user.addItem(new Item(obj.getId(), obj.getName()));
        }
    }
}
