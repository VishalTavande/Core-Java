
public class InfiniteThread {

	public static void main(String[] args) {
		
		final Thread firstThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					System.out.println(Thread.currentThread().getId() + " running..");
				}
			}
		});
		
		final Thread secondThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(!Thread.interrupted()) {
					System.out.println(Thread.currentThread().getId() + " running..");
				}
			}
		});
		
		firstThread.start();
		secondThread.start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10; i++) {
					if(i == 5) {
						firstThread.interrupt();
						secondThread.interrupt();
					}
				}				
			}
		}).start();
	}
}
