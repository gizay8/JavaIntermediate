package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public class Content {
    private int id;
    private String title;
    private String info;
    private String url;

    private int course_id;
    private Course course;
    private User educator;

    public Content(int id, String title, String info, String url,  int course_id) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.url = url;
        this.course_id = course_id;
        this.course=Course.getFetch(course_id);
        this.educator=course.getEducator();
    }

    public static Content getFetch(int content_id) {
        Content content=null;
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn=DBConnector.getInstance();
            prst =conn.prepareStatement("SELECT * FROM public.\"content\" WHERE id=?;");
            prst.setInt(1,content_id);
            rs=prst.executeQuery();
            if(rs.next()){

                content=new Content(rs.getInt("id"), rs.getString("title"), rs.getString("info"),rs.getString("url"),rs.getInt("course_id"));
            }
            prst.close();
            rs.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally{
            try {
                if(conn!=null){conn.close();}
                if(prst!=null){prst.close();}
                if(rs!=null){rs.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return content;
    }

    public static Content getFetch(String text) {
        Content content=null;
        Connection conn=null;
        PreparedStatement prst=null;
        ResultSet rs=null;
        try {
            conn=DBConnector.getInstance();
            prst =conn.prepareStatement("SELECT * FROM public.\"content\" WHERE title =?;");
            prst.setString(1,text);
            rs=prst.executeQuery();
            if(rs.next()){

                content=new Content(rs.getInt("id"), rs.getString("title"), rs.getString("info"),rs.getString("url"),rs.getInt("course_id"));
            }
            prst.close();
            rs.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally{
            try {
                if(conn!=null){conn.close();}
                if(prst!=null){prst.close();}
                if(rs!=null){rs.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return content;
    }

    public static boolean add(String title, String info, String url,int course_id) {
        boolean key=false;
        Connection conn=null;
        PreparedStatement prst=null;
        try {
            conn=DBConnector.getInstance();
            prst=conn.prepareStatement("Insert into public.\"content\"(title,info,url,course_id) Values (?,?,?,?)");
            prst.setString(1,title);
            prst.setString(2,info);
            prst.setString(3,url);
            prst.setInt(4,course_id);
            key= prst.executeUpdate()!=-1;
            conn.close();
            prst.close();
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
            return key;
        }finally{
            try {
                if(conn!=null){conn.close();}
                if(prst!=null){prst.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean update(int id, String title, String info, String url) {
        boolean key=false;
        Connection conn=null;
        PreparedStatement prst=null;
        try {
            conn=DBConnector.getInstance();
            prst=conn.prepareStatement("update public.\"content\" set title=?,info=?,url=? where id=? ;");
            prst.setString(1,title);
            prst.setString(2,info);
            prst.setString(3,url);
            prst.setInt(4,id);
            key= prst.executeUpdate()!=-1;
            prst.close();
            conn.close();
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
            return key;
        }finally{
            try {
                if(conn!=null){conn.close();}
                if(prst!=null){prst.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static ArrayList<Content> getList() {
        ArrayList<Content> contentArrayList=new ArrayList<>();
        Statement st=null;
        Connection conn=null;
        Content obj;
        ResultSet rs=null;
        try {
            conn= DBConnector.getInstance();
            st = conn.createStatement();
            rs = st.executeQuery( "SELECT * FROM public.\"content\" ORDER BY id;" );
            while ( rs.next() ) {

                obj =new Content(rs.getInt("id"), rs.getString("title"),rs.getString("info"),rs.getString("url") ,rs.getInt("course_id"));
                contentArrayList.add(obj);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally{
            try {
                if(conn!=null){conn.close();}
                if(st!=null){st.close();}
                if(rs!=null){rs.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return contentArrayList;
    }

    public static ArrayList<Content> getListByCourseId(int course_id) {
        ArrayList<Content> contentList = new ArrayList<>();
        String query = "SELECT * FROM public.\"content\" WHERE course_id = " + course_id;
        Content obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String info = rs.getString("info");
                String url = rs.getString("url");
                int c_id = rs.getInt("course_id");
                obj = new Content(id,title,info,url,c_id);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentList;
    }


    public static boolean delete(int id) {
        boolean key=false;
        Connection conn=null;
        PreparedStatement prst=null;
        try {
            conn=DBConnector.getInstance();
            prst=conn.prepareStatement("Delete from public.\"content\" where id=? ;");
            prst.setInt(1,id);

            key= prst.executeUpdate()!=-1;
            conn.close();
            prst.close();
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
            return key;
        }finally{
            try {
                if(conn!=null){conn.close();}
                if(prst!=null){prst.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String searchQuery(String title, int course_id){
        String query = "SELECT * FROM public.\"content\" WHERE title LIKE '%{{title}}%' AND course_id = {{course_id}}";
        query = query.replace("{{title}}", title);
        query = query.replace("{{course_id}}",String.valueOf(course_id));

        return query;
    }

    public static ArrayList<Content> searchContentList(String query){
        ArrayList<Content> contentList = new ArrayList<>();
        Content obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Content(rs.getInt("id"),rs.getString("title"),rs.getString("info"),rs.getString("url"),rs.getInt("course_id"));
                contentList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

}
