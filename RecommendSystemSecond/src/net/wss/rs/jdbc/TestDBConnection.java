package net.wss.rs.jdbc;

public class TestDBConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBConnection db= new DBConnection();
		System.out.println(db.getConn());
//		System.out.println("�ر����ݿ�");
//		db.close(db.getConn());

	}

}
