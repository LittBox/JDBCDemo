package util;

import java.sql.*;

public class JDBCUtil {
    //1.加载驱动程序
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //2. 获得数据库连接
    // 获得数据库连接
    public static Connection getConnection() {
        // 从配置工具类获取数据库连接信息
        Connection conn = null;
        String url = ConfigUtil.getProperty("dbURL");
        String user = ConfigUtil.getProperty("userName");
        String password = ConfigUtil.getProperty("password");
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            System.err.println("数据库连接失败：" + e.getMessage());
            throw new RuntimeException("数据库连接失败", e);
        }
        return conn;
    }
    //3. 关闭数据库连接
    public static void close(Connection conn, Statement stmt, PreparedStatement prestmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (prestmt != null) {
            try {
                prestmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
