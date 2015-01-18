// 文件名 : DisplayMessage.java
// 通过实现 Runnable 接口创建线程
public class DisplayMessage implements Runnable
{
  private String message;
  public DisplayMessage(String message)
  {
    this.message = message;
  }
  public void run()
  {
    while(true)
    {
      System.out.println(message);
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        System.out.println("Thread interrupted.");
      }
    }
  }
}
