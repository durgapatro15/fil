package jdbc;
public class Report1 {
	public static void main(String[] args) {
			DbConn db= new DbConn();
			try {
				db.rs=db.stat.executeQuery("select * from t1");
				db.md=db.rs.getMetaData();
				int c=db.md.getColumnCount();
				for(int i=1;i<=c;i++) {
					System.out.print("\t" + db.md.getColumnLabel(i));
				}
				System.out.println("\n-----------------------------------------------------------------");
				int n=0;
				while(db.rs.next()){
					n++;
					for(int i=1;i<=c;i++){
						System.out.print("\t" + db.rs.getString(i));				
					}
					System.out.println();					
				}
				System.out.println("\t" + n + "record(s)");
			}
			catch(Exception e){
				System.out.println(e);
				
			}

}}
