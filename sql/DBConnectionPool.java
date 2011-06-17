package sql;
import java.sql.*;
import java.util.LinkedList;

public class DBConnectionPool {
	private static final Integer maxCon = 10;
	private static LinkedList<Connection> FreeConnectionPool;
	private static DBConnectionPool instance = new DBConnectionPool();
	private DBConnectionPool() {
		initConnectionPool();
	}
	
	public static DBConnectionPool getInstance() {
		return instance;
	}
	
	private void initConnectionPool() {
		try
	     {
	         Class.forName( "org.sqlite.JDBC" );
	         FreeConnectionPool = new LinkedList<Connection>();
	         for(int i=0; i<maxCon; i++)
	        	 FreeConnectionPool.add(createConnection());
	     }
	     catch (Exception e)
	     {
	         e.printStackTrace();
	     }
	}
	
	private static Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:ressource/ff-verwaltung.sqlite", "", "" );
	}
	
	/** retrieves a connection from the connection pool,
	 * will wait for a free connection if none is left,
	 * a connection should be returned if it is not needed anymore */
	public static Connection getConnection() {
		synchronized(FreeConnectionPool) {
			while(FreeConnectionPool.isEmpty()) {
				try {
					FreeConnectionPool.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return FreeConnectionPool.getFirst();
		}
	}
	
	/** returns a connection to the connection pool
	 * any connection retrieved from this pool should be returned ASAP */
	public static void returnConnection(Connection cn) {
		if(cn == null)
			return;
		try {
			if(cn.isClosed())
				cn = createConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		synchronized(FreeConnectionPool) {
			FreeConnectionPool.add(cn);
			FreeConnectionPool.notify();
		}
	}
	
}
