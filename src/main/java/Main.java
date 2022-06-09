import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "kirillkirill";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            statement.execute("UPDATE Courses SET name = 'Веб-разработчик c 0 до PRO' WHERE id = 2");
            statement.execute("UPDATE Courses SET name = 'Мобильный разработчик с нуля' WHERE id = 1");
            ResultSet resultSet = statement.executeQuery("select course_name as COURSE, (COUNT(subscription_date)/8) AS SALE_IN_MONTH from PurchaseList GROUP BY COURSE");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int column = resultSetMetaData.getColumnCount();
//            System.out.println(column);
            for (int i = 1; i <= column; i++) {
                if (i != column) System.out.print(resultSetMetaData.getColumnName(i) + "  ||  ");
                else System.out.println(resultSetMetaData.getColumnName(i));
            }
            System.out.println("=======================================================");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "  =  \t" + resultSet.getString(2));
            }

            //SELECT pl.course_name, pl.subscription_date FROM PurchaseList pl WHERE pl.course_name = "Веб-разработчик c 0 до PRO" ORDER BY pl.subscription_date;
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("name"));
//            }



            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
