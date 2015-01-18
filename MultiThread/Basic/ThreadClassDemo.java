// 文件名 : ThreadClassDemo.java
public class ThreadClassDemo
{
  public static void main(String [] args)
  {
    Runnable hello = new DisplayMessage("Hello");
    Thread thread1 = new Thread(hello);
    thread1.setDaemon(true);  // 守护进程 JVM可以结束不需要等待其结束
    thread1.setName("hello");
    System.out.println("Starting hello thread...");
    thread1.start();

    Runnable bye = new DisplayMessage("Goodbye");
    Thread thread2 = new Thread(bye);
    thread2.setPriority(Thread.MIN_PRIORITY);
    thread2.setDaemon(true);  // 守护进程 JVM可以结束不需要等待其结束
    System.out.println("Starting goodbye thread...");
    thread2.start();

    try {
      Thread.sleep(5000);
    } catch(Exception e) {
      System.out.println("Thread interrupted.");
    }

    System.out.println("Starting thread3...");
    Thread thread3 = new GuessANumber(27);
    thread3.start();
    try {
      thread3.join();  // JOIN 另主线程等待子线程结束
    } catch(InterruptedException e) {
      System.out.println("Thread interrupted.");
    }
    System.out.println("Starting thread4...");
    Thread thread4 = new GuessANumber(7);

    thread4.start();
    System.out.println("main() is ending...");
  }
}
