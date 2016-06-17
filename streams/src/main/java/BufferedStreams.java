import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mikhail Zaitsev
 */
public class BufferedStreams {

  public static void main(String[] args) throws IOException {
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try{
      bis = new BufferedInputStream(getInputStream());
      bos = new BufferedOutputStream(new FileOutputStream("res/out.txt"));
      int l = 0;
      byte[] buff = new byte[5];
      while((l = bis.read(buff)) != -1) {
        bos.write(buff, 0, l);
      }
      bos.flush();
    }finally {
      if (bis != null) {
        bis.close();
      }
      if (bos != null) {
        bos.close();
      }
    }

  }

  static InputStream getInputStream() throws IOException {
    ServerSocket serverSocket = new ServerSocket(8989);
    Socket socket = serverSocket.accept();
    return socket.getInputStream();
  }

}
