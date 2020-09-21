import java.io.OutputStream;
import java.net.Socket;

/**
 * PACKAGE_NAME
 *
 * @Author Administrator
 * @date 12:01
 */
public class IOClient {

    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            WriteData writeData = new WriteData();
            writeData.start();
        }
    }

    public static class WriteData extends Thread{
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
            try {
                Socket socket = new Socket("127.0.0.1", 9002);
                OutputStream os = socket.getOutputStream();
                while (true){
                    os.write(("测试数据").getBytes());
                    os.flush();
                    Thread.sleep(2000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
