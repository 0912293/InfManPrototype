package com.datacorner.app.prototype;// Use the JDBC driver
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.sql.*;

//public class DatabaseConnector{

//    // Connect to your database.
//    // Replace server name, username, and password with your credentials
//    Connection connection = null;
//
//
//    @Override
//    protected Void doInBackground(String... strings) {
//        String connectionString =
//                "jdbc:jtds:sqlserver://localhost:1433;"
//                        + "database=INFMAN;"
//                        + "user=androidstudio;"
//                        + "password=coolboy123;"
//                        + "encrypt=true;"
//                        + "trustServerCertificate=false;"
//                        + "hostNameInCertificate=*.database.windows.net;"
//                        + "loginTimeout=30;";
//
//
//
//        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//
//            connection = DriverManager.getConnection(connectionString);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public ResultSet executeQuery() {
//        ResultSet rs = null;
//        try{
//            Statement stmt = connection.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM PostFacts;");
//        }
//        catch(Exception e){
//
//        }
//        return rs;
//    }

//}
import java.sql.*;

public class FirstExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/INFMAN";

    //  Database credentials
    static final String USER = "infman2";
    static final String PASS = "Yk7z3!cw2!p0";

    public ResultSet executeQuery() {
        String ConnURL = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            ConnURL = "jdbc:jtds:sqlserver://" + "den1.mssql5.gear.host" + ";"
                    + "databaseName=" + "infman2" + ";user=" + USER + ";password="
                    + PASS + ";";

            //STEP 2: Register JDBC driver
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            //STEP 3: Open a connection
            Log.d("Connecting", "Connecting to database...");
            conn = DriverManager.getConnection(ConnURL);

            //STEP 4: Execute a query
            Log.d("Creating", "Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT cs.CompanySector, vi.VisualType, pf.PostComments, pf.PostLikes FROM CompanySector cs, PostFacts pf, VisualInformation vi WHERE pf.VisualInformationID = vi.VisualInformationID AND pf.CompanySector_id = cs.CompanySector_id;";
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                int PostLikes  = rs.getInt("PostLikes");
//
//
//                //Display values
//                Log.d("It is", "ID: " + PostLikes);
//            }
            //STEP 6: Clean-up environment
            //rs.close();
            //stmt.close();
            //conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
//        }finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
        }//end try
        Log.d("Bye", "Goodbye :)!");
        return rs;//end main
    }
}//end FirstExample