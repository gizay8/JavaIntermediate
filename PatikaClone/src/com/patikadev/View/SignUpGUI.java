package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame {

    private JPanel wrapper;
    private JTextField fld_signup_name;
    private JTextField fld_signup_pass;
    private JTextField fld_signup_uname;
    private JButton signUpButton;
    private JButton backwardButton;

    public SignUpGUI(){
        add(wrapper);
        setSize(500,500);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);
        signUpButton.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_signup_name)||Helper.isFieldEmpty(fld_signup_uname)||Helper.isFieldEmpty(fld_signup_pass)){
                Helper.showMsg("fill");
            }else{
                String name = fld_signup_name.getText();
                String uname = fld_signup_uname.getText();
                String pass = fld_signup_pass.getText();
                if(User.add(name,uname,pass,"student")) {
                    Helper.showMsg("done");
                    LoginGUI login=new LoginGUI();
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }
        });
        backwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI login=new LoginGUI();
                dispose();
            }
        });
    }
}
