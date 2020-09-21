
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * PACKAGE_NAME
 *
 * @Author Administrator
 * @date 12:01
 */
public class IOServer {

    public static void main(String[] args) throws IOException {
        //
        ServerSocket serverSocket = new ServerSocket(9002);
        while (true){
            //用阻塞方法获取一个新连接
            final Socket accept = serverSocket.accept();
            //有新连接的时候创建一个线程，获取该连接中的数据
            new Thread(){
                /**
                 * If this thread was constructed using a separate
                 * <code>Runnable</code> run object, then that
                 * <code>Runnable</code> object's <code>run</code> method is called;
                 * otherwise, this method does nothing and returns.
                 * <p>
                 * Subclasses of <code>Thread</code> should override this method.
                 *
                 * @see #start()
                 * @see #stop()
                 * @see #Thread(ThreadGroup, Runnable, String)
                 */
                @Override
                public void run() {
                    //获取当前线程的名字
                    String currentName = Thread.currentThread().getName();
                    //开始读取该连接中的数据
                    try {
                        InputStream is = accept.getInputStream();
                        byte[] bytes = new byte[1024];
                       while (true){
                           int len;
                           while ((len = is.read(bytes))!=-1){
                               System.out.println("线程"+currentName+":"+new String(bytes, "utf-8"));
                           }
                       }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }



    }
}
