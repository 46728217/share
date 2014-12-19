import java.sql.*;

public class Transfer {

	String user = "root";
	String password = "123.abc";
	String url = "jdbc:mysql://localhost:3306/mydb";
	String driver = "com.mysql.jdbc.Driver";

	public static void main(String[] argv) {
		Transfer obj = new Transfer();
		obj.yearAreaSplit();
	}

	//处理cars表中year_area字段到start_year和end_year字段中
	private void yearAreaSplit() {
		System.out.println("in yearAreaSplit function");
		String sql = "select * from cars limit 1";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int columnCount =  rs.getMetaData().getColumnCount();
			while(rs.next()) {
				for(int i=0;i<columnCount;i++) {
					System.out.print(rs.getString(i+1));
					System.out.print("\t");
				}
			}
		} catch(ClassNotFoundException e1) {
			System.out.println("数据库驱动不存在！");
			System.out.println(e1.toString());
		} catch(SQLException e2) {
			System.out.println("数据库存在异常！");
			System.out.println(e2.toString());
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch(SQLException e) {
				System.out.println(e.toString());
			}            
		}


	}
}
