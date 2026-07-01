import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class TransactionExample {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/bankdb";
        String user = "root";
        String password = "root";

        try {

            Connection con = DriverManager.getConnection(url, user, password);

           
            con.setAutoCommit(false);

           
            PreparedStatement debit = con.prepareStatement(
                "UPDATE accounts SET balance = balance - ? WHERE id = ?"
            );

            debit.setInt(1, 500);
            debit.setInt(2, 1);

            debit.executeUpdate();

            
            PreparedStatement credit = con.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE id = ?"
            );

            credit.setInt(1, 500);
            credit.setInt(2, 2);

            credit.executeUpdate();

            
            con.commit();

            System.out.println("Transaction Successful");

            con.close();

        } catch (Exception e) {

            System.out.println("Transaction Failed");
        }
    }
}