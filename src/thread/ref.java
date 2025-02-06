package thread;
import java.lang.reflect.*;
public class ref {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class c=Class.forName("java.awt.Button");
			//Constructor cn[]=c.getConstructors();
			//Method cn[]=c.getMethods();
			Field cn[]=c.getFields();
			for(int i=0;i<cn.length;i++) {
				System.out.println(cn[i]);
			}
			System.out.println(cn.length);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
