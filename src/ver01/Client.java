package ver01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import lombok.Data;

@Data
public class Client {

	private ClientFrame clientFrame;
	ChattingRoomFrame chattingRoomFrame;

	// 소켓, 입출력 장치
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private String userId;

	// 토크나이저
	private String protocol;
	private String from;
	private String message;

	public Client() {
		clientFrame = new ClientFrame(this);
	}

	// 소켓 연결
	private void connectNetwork() {
	    try {
	        socket = new Socket("localhost", 10001);
	        System.out.println("서버에 접속 성공");
	    } catch (UnknownHostException e) {
	        e.printStackTrace();
	        System.out.println("호스트를 찾을 수 없습니다: " + e.getMessage());
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("입출력 오류: " + e.getMessage());
	    }
	}
	
	private void loginBtnClick(String userId) {
		this.userId = userId;

		try {
			connectNetwork();
			connectIO();
			
			writer.write(userId.trim());
			clientFrame.setTitle("[ BROMATIANS " + userId + "님 ]");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connectIO() {
		try {
			
			// 입출력 장
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			// 입력 스레드
			readThread();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						String msg = reader.readLine();

						checkProtocol(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	private void checkProtocol(String msg) {
		StringTokenizer tokenizer = new StringTokenizer(msg, "/");

		protocol = tokenizer.nextToken();
		from = tokenizer.nextToken();
		
		if(protocol.equals("ConnectedUser")) {
			
		}
	}

	// Getter, Setter

	public ClientFrame getClientFrame() {
		return clientFrame;
	}

	public ChattingRoomFrame getChattingRoomFrame() {
		return chattingRoomFrame;
	}

	public void setChattingRoomFrame(ChattingRoomFrame chattingRoomFrame) {
		this.chattingRoomFrame = chattingRoomFrame;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setClientFrame(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
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
	
	public static void main(String[] args) {
		new Client();
	}// end of main



}// end of class
