package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;

public class StudentGUI extends JFrame{
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private User student;

    public StudentGUI(User student){
        this.student = student;
        add(wrapper);
        lbl_welcome.setText("Welcome " + student.getName());
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x", getSize()),Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        //Student Panel Codes like Educator Panel and Operator Panel
    }
}
