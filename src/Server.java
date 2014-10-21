
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A TCP server that runs on port 9090. When a client connects, it sends the
 * client the current date and time, then closes the connection with that
 * client. Arguably just about the simplest server you can write.
 */
public class Server {

	/**
	 * Runs the server.
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("Server Running");

		//int[] times = { 810, 815, 910, 915, 1010, 1025, 1035, 1125, 1135, 1225, 1300, 1305, 1400, 1405, 1500 };
		int[] times = { 952, 953, 1010 };
		
		String s = "";
		for(int i = 0; i < times.length; i++){
			s+=times[i];
			s+= ",";
		}
		
		s = s.substring(0,s.length()-1);
		
		ServerSocket listener = new ServerSocket(9090);
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream(),
							true);
					out.println(s);
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}
}
