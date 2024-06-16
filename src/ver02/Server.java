package ver02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import lombok.Data;

@Data
public class Server {

	private Server mContext;
	ServerFrame serverFrame;
	
	private String userId;
	private String roomId;

	private JTextArea mainBoard;

	// 포트 번호 지정
	private static final int PORT = 10001;

	// 소켓 생성
	private ServerSocket serverSocket;
	private Socket socket;

	private BufferedReader reader;
	private BufferedWriter writer;

	// 프로토콜
	private String protocol;
	private String from;
	private String message;

	// 접속된 유저 벡터
	private Vector<ConnectedUser> connectedUsers = new Vector<>();
	
	public Server() {
		serverFrame = new ServerFrame(this);
		mainBoard = serverFrame.getMainBoard();
	
	}
	
	public void startServer() {
		try {
			// 서버 소켓 장치
			serverSocket = new ServerSocket(PORT);
			
			connectClient();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void connectClient() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {

						// 소켓 장치
						socket = serverSocket.accept();
						serverViewAppendWriter("[알림] 사용자 접속 대기\n");

						// 연결을 대기 하다가 유저가 들어오면 유저 생성, 소켓으로 유저 구분 가능.
						ConnectedUser user = new ConnectedUser(socket);
						user.start();
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("[에러] 접속 에러 ! !\n");
						
					}
				}
			}
		}).start();
	}
	
	private void broadCast(String msg) {
		for (int i = 0; i < connectedUsers.size(); i++) {
			ConnectedUser user = connectedUsers.elementAt(i);
			user.writer(msg);
		}
	}
	
	private void serverViewAppendWriter(String str) throws IOException {
		mainBoard.append(str);
	}
	

	
	@Data
	private class ConnectedUser extends Thread {

		// 소켓 장치
		private Socket socket;
		
		private String userId;

		// 입출력 장치
		private BufferedReader reader;
		private BufferedWriter writer;
		
		public ConnectedUser(Socket socket) {
			this.socket = socket;
			connectIO();
		}
		
		private void connectIO() {
			try {
				// 입출력 장치
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				sendInfo();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("[에러] 서버 입출력 장치 에러 !");
			}
		}

		private void sendInfo() {
			try {
				// 유저의 아이디를 가지고 온다.
				userId = reader.readLine();
				serverViewAppendWriter("[접속] " + userId + "님 \n");

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("[에러] 접속 에러");
			}
		}
		
		private void writer(String str) {
			try {
				writer.write(str + "\n");
				writer.flush();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "서버 출력 에러 !", "알림", JOptionPane.ERROR_MESSAGE);
			}
		}


	}
	
	
	// getter, setter
	public Server getmContext() {
		return mContext;
	}

	public void setmContext(Server mContext) {
		this.mContext = mContext;
	}

	public ServerFrame getServerFrame() {
		return serverFrame;
	}

	public void setServerFrame(ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public JTextArea getMainBoard() {
		return mainBoard;
	}

	public void setMainBoard(JTextArea mainBoard) {
		this.mainBoard = mainBoard;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static int getPort() {
		return PORT;
	}

	public static void main(String[] args) {
		new Server();
	}// end of main
	
	
}// end of class
