import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

public class MySqlDBPoolExample {
    private String user;
    private String password;
    private String driverName;
    private String connectString;
    private int maxActive;
    private int maxWait;
    private Byte whenExhausted;
    private boolean autoCommit;

    public MySqlDBPoolExample (String user, String password, String driverName, String connectString, int maxActive,
			       int maxWait, Byte whenExhausted, boolean autoCommit ) {
	this.user=user;
	this.password = password;
	this.driverName = driverName;
	this.connectString = connectString;
	this.maxActive = maxActive;
	this.maxWait = maxWait;
	this.whenExhausted = whenExhausted;
	this.autoCommit = autoCommit;
    }


    public static void main ( String args [] ) {

	String connectURI = "jdbc:mysql://localhost:3306/nj_rail";
	String user = "motidev";
	String password = "motidev";
	int maxActive = 2;
	//Byte whenExhausted = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;
	Byte whenExhausted = GenericObjectPool.WHEN_EXHAUSTED_GROW;
	int maxWait = 5;
	
	try {

            System.out.println("Attempting to start DBCPCDatasourceComponent.");            
            Class.forName("com.mysql.jdbc.Driver");
            
            // Validate the connection before we go any further
            try {
		Connection conn = DriverManager.getConnection(connectURI, user, password);
		conn.close();
            } catch(Exception e) {
                System.out.println("Unable to obtain a connection database via URI: "+connectURI + e.toString());
                throw e;
            }
            
            ObjectPool connectionPool = new GenericObjectPool(null, maxActive, whenExhausted, maxWait);
            ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, user, password);
            PoolableConnectionFactory dsConnectionFactory = 
		new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);
            PoolingDataSource dataSource = new PoolingDataSource(connectionPool);            
            System.out.println("DBCPCDatasourceComponent successfuly started!");

	    Connection conn = dataSource.getConnection();
	    System.out.println (conn.toString() );
	    Connection conn1 = dataSource.getConnection();
	    System.out.println (conn1.toString() );
	    Connection conn2= dataSource.getConnection();
	    System.out.println (conn2.toString() );
	    conn.close();
	    conn2.close();
	    
	    int i=0;
	    while ( i<10 ) {
		Thread t = new Thread( new SelectQuery( "select count(*) from agency", dataSource ) );
		Thread t2 = new Thread( new SelectQuery( "select count(*) from routes", dataSource ) );
		
		t.start(); t2.start();
		i++;		   
	    }
        }
        catch (Throwable e) {
            String msg = "Unable to start DBCPCDatasourceComponent: "+e.toString();
            System.out.println(msg);
            throw new IllegalStateException(msg);
        }
    }
}

