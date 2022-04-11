

import java.sql.*;

public class AllCount  {
       int coutTable;
        int coutWord=0;
    public  AllCount(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            /*
            *
            *
            *
            * 这里需要修改！！！！！！！！！！！！！
            *
            *
            *
            *
            *
            *
            * */
            Connection connection= DriverManager.getConnection("jdbc:mysql://6数据库地址/lytdatabase?characterEncoding=UTF-8","用户名","密码");
            Statement statement=connection.createStatement();
            String str="SELECT\n" +
                    "\tCOUNT(*) TABLES,\n" +
                    "\ttable_schema\n" +
                    "FROM\n" +
                    "\tinformation_schema. TABLES\n" +
                    "WHERE\n" +
                    "\ttable_schema = 'lytdatabase'\n" +
                    "GROUP BY\n" +
                    "\ttable_schema;";
            ResultSet resultSet=statement.executeQuery(str);
            resultSet.next();
            System.out.println(resultSet.getString(1));
             coutTable=Integer.parseInt(resultSet.getString(1));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            /*
            *
            * 这里需要修改！！！！！！！！！！！！！！！！！！！！！！！！！
            *
            *
            * */
            Connection connection= DriverManager.getConnection("jdbc:mysql://数据库地址/lytdatabase?characterEncoding=UTF-8","用户名","密码");
            Statement statement=connection.createStatement();
            for (int i = 0; i < coutTable; i++) {
                int j=i+1;
                String string="select * from 高频"+j;
                ResultSet resultSet=statement.executeQuery(string);
                while (resultSet.next()){
                    coutWord++;
                }
            }
            System.out.println("一共学习了："+coutWord+"个单词");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
