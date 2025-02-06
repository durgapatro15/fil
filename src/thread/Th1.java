package thread;

public class Th1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new Thread();
		t.setName("My 1st Thread");
		System.out.println(t.getName());
		t.start();

		System.out.println(t.isAlive());

	}

}
