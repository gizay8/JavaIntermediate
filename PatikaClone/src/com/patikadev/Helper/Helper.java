package com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setLayout(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public static int screenCenterPoint(String axis, Dimension size){
        int point = 0;
        switch(axis){
            case "x" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y" :
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default :
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static boolean isComboNull(JComboBox comboBox){
        return comboBox.getSelectedItem().toString().trim().isEmpty();
    }

    public static void showMsg(String str){
        optionPageChange();
        String msg;
        String title;
        switch(str){
            case "fill":
                msg = "Please fill in all fields!";
                title = "Error";
                break;
            case "done":
                msg = "Successful.";
                title = "Result";
                break;
            case "error":
                msg = "Something went wrong!";
                title = "Error";
                break;
            default:
                msg = str;
                title = "Message";
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        String msg;
        switch(str){
            case "sure":
                msg = "Are you sure?";
                break;
            default :
                msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Is it your final decision?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPageChange(){
        UIManager.put("OptionPane.okButtonText", "OK!");
    }
}
