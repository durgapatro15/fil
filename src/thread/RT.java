package thread;

public class RT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("MSPaint");
			//Process p = r.exec("calc");

		}
		catch(Exception e){
			System.out.println(e);
		}

	}

}
