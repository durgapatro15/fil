package thread;

public class Th extends Thread {
	Th(){
		start();
	}
	public void run() {
		try {
			for(int i=1;i<=10;i++) {
				System.out.println(i);
				Thread.sleep(500);
			}
		}
		catch(InterruptedException e) {
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Th();
		try {
			for(int i=101;i<=110;i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e) {
			
		}
	}
		

	}

