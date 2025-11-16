
import org.junit.jupiter.api.Test;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtilTest {

    @Test
    public void JDBCestQuery(){

        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;
        ResultSet rs = null;
        String sql = "select * from stu_info where sex = ?";
        try {
            preStatement = conn.prepareStatement(sql);
            preStatement.setString(1,"male");
            rs = preStatement.executeQuery();
            while(rs.next()){
                String id=rs.getString("stu_id");
                String name=rs.getString("stuname");
                int age=rs.getInt("age");
                String gender=rs.getString("sex");
                String pws=rs.getString("pwd");
                System.out.println("姓名："+name+" 学号:"+id+" 年龄: "+age+" 性别："+gender);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(conn,null,preStatement,rs);
        }
    }

    @Test
    public void JDBCestUpdate(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;
        ResultSet rsQuery = null;
        //打印修改前的值
        try{
            String sqlQuery = "select * from stu_info where stu_id= ?";
            preStatement = conn.prepareStatement(sqlQuery);
            preStatement.setString(1, "20221120001");
            rsQuery = preStatement.executeQuery();
            while(rsQuery.next()){
                System.out.println("修改前：");
                System.out.println("姓名："+rsQuery.getString("stuname")+
                        " 学号：" + rsQuery.getString("stu_id")+
                        " 性别："+ rsQuery.getString("sex") +
                        " 年龄"+ rsQuery.getInt("age") +
                        " 密码："+rsQuery.getString("pwd"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //进行修改
        String sqlUpdate = "update stu_info set pwd = ? , age = ? where stu_id = ?";
        try {
            preStatement = conn.prepareStatement(sqlUpdate);
            preStatement.setString(1,"123456");
            preStatement.setInt(2,22);
            preStatement.setString(3,"20221120001");

            int affectedRows = preStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("修改成功，影响行数："+affectedRows);
            }else{
                System.out.println("修改失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //打印修改后的值
        try{
            String sqlQuery = "select * from stu_info where stu_id= ?";
            preStatement = conn.prepareStatement(sqlQuery);
            preStatement.setString(1, "20221120001");
            rsQuery = preStatement.executeQuery();
            while(rsQuery.next()){
                System.out.println("修改后：");
                System.out.println("姓名："+rsQuery.getString("stuname")+
                        " 学号：" + rsQuery.getString("stu_id")+
                        " 性别："+ rsQuery.getString("sex") +
                        " 年龄"+ rsQuery.getInt("age") +
                        " 密码："+rsQuery.getString("pwd"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(conn,null,preStatement,rsQuery);
        }

    }


    @Test
    public void JDBCestDelete(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;

        try{
            String sqlDelete = "Delete from stu_info where stu_id= ?";
            preStatement=conn.prepareStatement(sqlDelete);
            preStatement.setString(1,"20221120001");
            int rsDelete = preStatement.executeUpdate();
            if(rsDelete>0){
                System.out.println("删除成功，影响行数："+rsDelete);
            }else {
                System.out.println("删除失败");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(conn,null,preStatement,null);
        }
    }

    @Test
    public void JDBCestInsert(){
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement preStatement = null;

        try{
            String sqlInsert = "Insert into stu_info(stu_id,stuName,age,sex,pwd) values(?,?,?,?,?) ";
            preStatement=conn.prepareStatement(sqlInsert);
            preStatement.setString(1,"20221120001");
            preStatement.setString(2,"Lucy");
            preStatement.setInt(3,22);
            preStatement.setString(4,"female");
            preStatement.setString(5,"123456");

            int affectedRows = preStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("新增"+affectedRows+"行");
            }else{
                System.out.println("新增失败");
            }

        }catch (SQLException e){
            throw new RuntimeException(e);

    }finally {
            JDBCUtil.close(conn,null,preStatement,null);
        }
    }
}
