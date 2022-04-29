package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Educator extends User{

    public Educator(int id, String name, String uname, String pass, String type) {
        super(id, name, uname, pass, "educator");
    }

    public static Educator getFetch(int id) {
        Educator obj =null;
        String query="Select * from public.\"user\" WHERE id=? ";
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn= DBConnector.getInstance();
            prst= conn.prepareStatement(query);
            prst.setInt(1,id);
            rs= prst.executeQuery();
            if(rs.next()){
                String name=rs.getString("name");
                int newID=rs.getInt("id");
                String uname=rs.getString("uname");
                String pass=rs.getString("pass");



                obj=new Educator(newID,name,uname,pass,"educator");
            }
            prst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (prst != null) {
                    prst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
