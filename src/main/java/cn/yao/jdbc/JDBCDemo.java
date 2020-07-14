package cn.yao.jdbc;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //注册驱动
//        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy", "root", "x5");
        //获取操作数据库的预处理对象
        PreparedStatement ps = conn.prepareStatement("select * from account");
        //执行sql得到结果集
        ResultSet resultSet = ps.executeQuery();
        //遍历结果集
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }


    }
}
