public class DBHelper(){


public void setUp() throws Exception {

        try{

        // Make the database connection

            String dbClass = "com.mysql.jdbc.Driver";

            Class.forName(dbClass).newInstance();

        // Get connection to DB

            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        // Statement object to send the SQL statement to the Database

            stmt = con.createStatement();

        }

        catch (Exception e)

        {

             e.printStackTrace();

        }

   }

   private static void clearDB(){

        try{
        // Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        //Open a connection
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        //Execute a query
        stmt = conn.createStatement();
        String sql = "DELETE FROM tablename WHERE id = idno"
        stmt.executeUpdate(sql);

        rs.close();
        }


   }


   private static void InsertToDB() {
        try {
        // create a mysql Database connection
//            String myUrl = "jdbc:mysql://localhost:3306/testDB";
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

        // the mysql insert statement
            String customerInsert = " Insert into customer (custID, Salutation, LastName , PhoneNo, Company) values(?,?,?,?)";
            String accountInsert = " Insert into Account (AccountID, AccountName) values(?,?)";

        // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(customerInsert);
            preparedStmt.setString(1, "<ID>");
            preparedStmt.setString(2, "<Salutation>");
            preparedStmt.setString(3, "<LastName>");
            preparedStmt.setString(4, "<PhoneNo>");
            preparedStmt.setString(5, "<Company>");

            PreparedStatement preparedStmt = conn.prepareStatement(accountInsert);
            preparedStmt.setString(1, "<ID>");
            preparedStmt.setString(2, "<AccountName>");


        // execute the preparedstatement
            preparedStmt.execute();
            conn.close();
        }

        catch (Exception e) {
            System.out.println("Got an exception!");
            System.out.println(e.getMessage());
            }
       }




       public void closeDBConnection() throws Exception {

        // Close DB connection

        if (con != null) {

             con.close();

           }

        }



}