package ver02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import lombok.Data;

@Data
public class Client {

	private ClientFrame clientFrame;
	ChattingRoomFrame chattingRoomFrame;
	private JTextArea writeMessageBox;

	// 소켓, 입출력 장치
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private String userId;
	
	private JList<String> userList;
	private Vector<String> userIdList = new Vector<>();

	// 토크나이저
	private String protocol;
	private String from;
	private String message;

	public Client() {
		clientFrame = new ClientFrame(this);
	}
	
	public void loginBtnClick() {

	      try {
	         connectNetwork();
	         connectIO();
	         
	         writer.write(userId.trim());
	         writer.flush();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
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

	private void connectIO() {
		try {
			
			// 입출력 장
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			writer.write(userId.trim() + "\n");
			
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
	
	public void writer(String str) {
		try {
			writer.write(str + "\n");
			writer.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "클라이언트 출력 장치 에러 !", "알림", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void checkProtocol(String msg) {
		StringTokenizer tokenizer = new StringTokenizer(msg, "/");

		protocol = tokenizer.nextToken();
		from = tokenizer.nextToken();
		
		if(protocol.equals("ConnectedUser")) {
			connectedUser();
		}else if(protocol.equals("newUser")) {
			newUser();
		}else if(protocol.equals("Chatting")) {
			message = tokenizer.nextToken();
			chatting();

		} 
	}
	
	public void chatting() {
		if (userId.equals(from)) {
			writeMessageBox.append("[나] \n" + message + "\n");
		} else if (from.equals("입장")) {
			writeMessageBox.append("▶" + from + "◀" + message + "\n");
		} else if (from.equals("퇴장")) {
			writeMessageBox.append("▷" + from + "◁" + message + "\n");
		} else {
			writeMessageBox.append("[" + from + "] \n" + message + "\n");
		}
	}
	
	public void connectedUser() {
		userIdList.add(from);
		userList.setListData(userIdList);
	}
	
	public void newUser() {
		if (!from.equals(this.userId)) {
			userIdList.add(from);
			userList.setListData(userIdList);
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
	
	
	
	public JList<String> getUserList() {
		return userList;
	}

	public void setUserList(JList<String> userList) {
		this.userList = userList;
	}

	public Vector<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(Vector<String> userIdList) {
		this.userIdList = userIdList;
	}

	public static void main(String[] args) {
		new Client();
	}// end of main



}// end of class
