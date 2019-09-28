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


   private static void InsertToDB() {
        try {
        // create a mysql Database connection
            String myUrl = "jdbc:mysql://localhost:3306/testDB";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(myUrl, "root", "root");

        // the mysql insert statement
            String customerInsert = " Insert into customer (custID, Salutation, LastName , PhoneNo, Company) values(?,?,?,?)";
            String AccountInsert = " Insert into Account (custID, Salutation, LastName , PhoneNo, Company) values(?,?,?,?)";

        // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(customerInsert);
            preparedStmt.setString(1, "<ID>");
            preparedStmt.setString(2, "<Salutation>");
            preparedStmt.setString(3, "<LastName>");
            preparedStmt.setString(4, "<PhoneNo>");
            preparedStmt.setString(5, "<Company>");

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