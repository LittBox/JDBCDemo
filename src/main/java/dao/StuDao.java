package dao;

import entity.Student;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuDao implements IStuDao{

    @Override
    public int AddStuInf(Student stu) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;
        try {
            String sql = "insert into Stu_Info(stu_Id,stuName,Age,Sex,Pwd) values (?,?,?,?,?)";
            preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, stu.getStuId());
            preStatement.setString(2, stu.getStuName());
            preStatement.setInt(3, stu.getAge());
            preStatement.setString(4, String.valueOf(stu.getSex()));
            preStatement.setString(5, stu.getStuPwd());
            int result = preStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, null, preStatement, null);
        }
        return 0;
    }

    @Override
    public int DeleteStuInf(String id) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;
        try {
            String sql = "delete from Stu_Info where stu_id=?";
            preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, id);
            int result = preStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, null, preStatement, null);
        }
        return 0;
    }

    @Override
    public int UpdateStuInf(Student stu) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;
        try {
            String sql = "update Stu_Info set StuName=?,Age=?,Sex=?,Pwd=? where stu_id=?";
            preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, stu.getStuName());
            preStatement.setInt(2, stu.getAge());
            preStatement.setString(3, String.valueOf(stu.getSex()));
            preStatement.setString(4, stu.getStuPwd());
            preStatement.setString(5, stu.getStuId());
            int result = preStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, null, preStatement, null);
        }
        return 0;
    }

    @Override
    public List<Student> QueryAllStuInf() {
        Connection conn = JDBCUtil.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            String sql = "select * from Stu_Info";
            rs = statement.executeQuery(sql);
            List<Student> stuInfList = new ArrayList<>();
            while (rs.next()) {
                Student stuInf = new Student();
                stuInf.setStuId(rs.getString("stu_Id"));
                stuInf.setStuName(rs.getString("StuName"));
                stuInf.setAge(rs.getInt("Age"));
                stuInf.setSex(Student.Sex.valueOf(rs.getString("Sex")));
                stuInf.setStuPwd(rs.getString("Pwd"));

                stuInfList.add(stuInf);
            }
            return stuInfList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, statement, null, rs);
        }
        return null;
    }

    @Override
    public Student QueryStuInfById(String id) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        try {
            String sql = "select * from Stu_Info where stu_id=?";
            preStatement = conn.prepareStatement(sql);
            preStatement.setString(1, id);
            rs = preStatement.executeQuery();

            while (rs.next()) {
                Student stuInf = new Student();
                stuInf.setStuId(rs.getString("stu_Id"));
                stuInf.setStuName(rs.getString("StuName"));
                stuInf.setAge(rs.getInt("Age"));
                stuInf.setSex(Student.Sex.valueOf(String.valueOf(rs.getString("Sex"))));
                stuInf.setStuPwd(rs.getString("Pwd"));
                return stuInf;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, null, preStatement, rs);
        }
        System.out.println("hello world");
        return null;
    }
}
