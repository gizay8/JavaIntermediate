package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Path;

import javax.swing.*;

public class UpdatePathGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_path_name;
    private JButton btn_update;
    private Path path;

    public UpdatePathGUI(Path path){
        this.path = path;
        add(wrapper);
        setSize(300,150);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_path_name.setText(path.getName());
        btn_update.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_path_name)){
                Helper.showMsg("fill");
            }else{
                if(Path.update(path.getId(), fld_path_name.getText())){
                    Helper.showMsg("done");
                }
                dispose();
            }
        });
    }
}
