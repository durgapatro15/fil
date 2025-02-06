package jdbc;
import java.util.*;
public class DB_Reg3 {

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
				//String qry="insert into t1 values('"+nm+"','"+pass+"',"+sal+")"; 
				//System.out.println(qry);
				
				db.rs=db.stat.executeQuery("select emp_id from t2 order by emp_id desc"); 
				String emp_id=""; if(!db.rs.next()) 
				{ 
					emp_id="E0001"; 
				} 
				else {
					emp_id=db.rs.getString("emp_id"); emp_id=emp_id.substring(1);  
					int n=Integer.parseInt(emp_id); 
					n++; 
					if(n>=0 && n<10) emp_id="E000" + n; 
					else if(n>=10 && n<100) emp_id="E00" + n; 
					else if (n>=100 && n<1000) emp_id="E0" + n; 
					else if (n>=1000 && n<10000) emp_id="E" + n; 
					} 
				String qry="insert into t2 values(?,?,?,?)"; // Faster method comparatively
				db.pstat=db.conn.prepareStatement(qry);
				db.pstat.setString(1, emp_id);
				db.pstat.setString(2, nm);
				db.pstat.setString(3, pass);
				db.pstat.setDouble(4, sal);
				db.pstat.executeUpdate();
				System.out.println("Congratulations!!!");
				System.out.println("Remember EID - "+emp_id);

			}
			catch(Exception e){
				System.out.println(e);
				
			}
			
			
		}

	}


