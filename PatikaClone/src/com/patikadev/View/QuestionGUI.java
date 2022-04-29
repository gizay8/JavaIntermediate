package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Content;
import com.patikadev.Model.Question;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuestionGUI extends JFrame{
    private int content_id;
    private JPanel wrapper;
    private JPanel wtop;
    private JLabel lbl_questions;
    private JPanel wleft;
    private JPanel wright;
    private JScrollPane scrl_question_list;
    private JTable tbl_question_list;
    private JTextField fld_question;
    private JTextField fld_o1;
    private JTextField fld_o2;
    private JTextField fld_o3;
    private JTextField fld_o4;
    private JTextField fld_answer;
    private JButton add_question;
    private JComboBox cmb_answer;
    private JButton backwardButton;
    private DefaultTableModel mdl_question_list;
    private Object[] row_question_list;
    private JPopupMenu menu;

    public QuestionGUI(int content_id){
        this.content_id = content_id;

        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()), Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setResizable(false);

        lbl_questions.setText(Content.getFetch(content_id).getTitle() + " Questions");

        mdl_question_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0 || column == 6){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_question_list = {"ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer"};
        mdl_question_list.setColumnIdentifiers(col_question_list);
        row_question_list = new Object[col_question_list.length];
        loadQuestionModel();

        menu = new JPopupMenu();
        JMenuItem deleteMenu = new JMenuItem("Delete");
        menu.add(deleteMenu);
        deleteMenu.addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selected_id = Integer.parseInt(tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 0).toString());
                if(Question.deleteData(selected_id)){
                    Helper.showMsg("done");
                    loadQuestionModel();
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        tbl_question_list.setModel(mdl_question_list);
        tbl_question_list.getTableHeader().setReorderingAllowed(false);
        tbl_question_list.setComponentPopupMenu(menu);

        tbl_question_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                int id = Integer.parseInt(tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 0).toString());
                String question = tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 1).toString();
                String o1 = tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 2).toString();
                String o2 = tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 3).toString();
                String o3 = tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 4).toString();
                String o4 = tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(), 5).toString();
                int answer = Integer.parseInt(tbl_question_list.getValueAt(tbl_question_list.getSelectedRow(),6).toString());

                if(Question.updateData(id,question,o1,o2,o3,o4,answer)){
                    Helper.showMsg("done");
                }
                loadQuestionModel();
            }
        });

        tbl_question_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_question_list.rowAtPoint(point);
                tbl_question_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        add_question.addActionListener(e -> {
            int answer = Integer.parseInt(cmb_answer.getSelectedItem().toString());
            if(Helper.isFieldEmpty(fld_question)||Helper.isFieldEmpty(fld_o1)||Helper.isFieldEmpty(fld_o2)||Helper.isFieldEmpty(fld_o3)||Helper.isFieldEmpty(fld_o4)){
                Helper.showMsg("fill");
            }else{
                if(Question.insertData(fld_question.getText(),fld_o1.getText(),fld_o2.getText(),fld_o3.getText(),fld_o4.getText(),answer,content_id)){
                   Helper.showMsg("done");
                   loadQuestionModel();
                   fld_question.setText(null);
                   fld_o1.setText(null);
                   fld_o2.setText(null);
                   fld_o3.setText(null);
                   fld_o4.setText(null);

                }
            }

        });
        backwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadQuestionModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_question_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Question q : Question.getList(content_id)){
            i = 0;
            row_question_list[i++] = q.getId();
            row_question_list[i++] = q.getQuest();
            row_question_list[i++] = q.getO1();
            row_question_list[i++] = q.getO2();
            row_question_list[i++] = q.getO3();
            row_question_list[i++] = q.getO4();
            row_question_list[i++] = q.getAnswer();
            mdl_question_list.addRow(row_question_list);
        }
    }
}
