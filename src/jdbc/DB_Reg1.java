package jdbc;
import java.util.*;
public class DB_Reg1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
			System.out.println("Enter name: ");
			String nm=sc.nextLine();
			System.out.println("Enter password: ");
			String pass=sc.nextLine();
			System.out.println("Enter salary: ");
			double sal=sc.nextDouble();
			DbConn db= new DbConn();
			try {
				//String qry="insert into t1 values('"+nm+"','"+pass+"',"+sal+")"; //
				//System.out.println(qry);
				String qry="insert into t1 values(?,?,?)"; // Faster method comparatively
				db.pstat=db.conn.prepareStatement(qry);
				db.pstat.setString(1, nm);
				db.pstat.setString(2, pass);
				db.pstat.setDouble(3, sal);
				db.stat.executeUpdate(qry);
				System.out.println("Congratulations!!!");
			}
			catch(Exception e){
				System.out.println(e);
				
			}
			
			
		}

	}


