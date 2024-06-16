package ver02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ChattingRoomFrame extends JFrame{
	
	Client mContext;
	ClientFrame clientFrame;
	ChattingRoomFrame chattingRoomFrame;
	
	private JLabel waitBackground;
	private JScrollPane friendsScroll;
	private JScrollPane chatScroll;

	private JTextField writeMessageBox;
	
	private JList<String> userList;
	private Vector<String> userIdVector = new Vector<>();

	
	// 텍스트 전송
	private JLabel sendBtn;
	
	Font font = new Font("Noto Sans KR", Font.BOLD, 18);

	public ChattingRoomFrame(Client mContext) {
		this.mContext = mContext;
		mContext.loginBtnClick();
		initData();
		setInitLayout();
		addEventListener();
	}
	
	public void initData() {
		waitBackground = new JLabel(new ImageIcon("img/waitingBg.jpg"));
		
		// 추가
		userList = new JList<>();
		friendsScroll = new JScrollPane(userList);
		chatScroll = new JScrollPane();

		sendBtn = new JLabel("보내기");
		
		writeMessageBox = new JTextField();
		setTitle(mContext.getUserId() + "님 환영합니다 !");
		setSize(400, 666);
		setContentPane(waitBackground);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/favRoom.png"));
	}
	
	public void setInitLayout() {

		// 메인패널 컴포넌트

		setLayout(null);
		setResizable(false); // 사이즈 조절 불가
		setLocationRelativeTo(null); // 가운데 배치
		setVisible(true);
		
		// 밑으로 이동
		friendsScroll.setBorder(new LineBorder(Color.BLACK, 3));
		friendsScroll.setBounds(15, 90, 360, 200);
		add(friendsScroll);

		chatScroll.setBorder(new LineBorder(Color.BLACK, 3));
		chatScroll.setBounds(15, 380, 360, 200);
		add(chatScroll);
	
		writeMessageBox.setBorder(new LineBorder(Color.BLACK, 2));
		writeMessageBox.setBounds(15, 590, 300, 30);
		add(writeMessageBox);

		sendBtn.setBorder(new LineBorder(Color.BLACK, 2));
		sendBtn.setBounds(600, 600, 30, 30);
		add(sendBtn);
	}
	
	public void addEventListener() {
		sendBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendMessage();
			}
		});

		writeMessageBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});
	}

	private void sendMessage() {
		if (!writeMessageBox.getText().equals(null)) {
			String msg = writeMessageBox.getText();
			writeMessageBox.setText("");
			writeMessageBox.requestFocus();
		}
	}

	public Client getmContext() {
		return mContext;
	}

	public void setmContext(Client mContext) {
		this.mContext = mContext;
	}

	public ClientFrame getClientFrame() {
		return clientFrame;
	}

	public void setClientFrame(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
	}

	public ChattingRoomFrame getChattingRoomFrame() {
		return chattingRoomFrame;
	}

	public void setChattingRoomFrame(ChattingRoomFrame chattingRoomFrame) {
		this.chattingRoomFrame = chattingRoomFrame;
	}

	public JLabel getWaitBackground() {
		return waitBackground;
	}

	public void setWaitBackground(JLabel waitBackground) {
		this.waitBackground = waitBackground;
	}

	public JScrollPane getFriendsscroll() {
		return friendsScroll;
	}

	public void setFriendsscroll(JScrollPane friendsscroll) {
		this.friendsScroll = friendsscroll;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public JList<String> getUserList() {
		return userList;
	}

	public void setUserList(JList<String> userList) {
		this.userList = userList;
	}

	public Vector<String> getUserIdVector() {
		return userIdVector;
	}

	public void setUserIdVector(Vector<String> userIdVector) {
		this.userIdVector = userIdVector;
	}

	public JScrollPane getFriendsScroll() {
		return friendsScroll;
	}

	public void setFriendsScroll(JScrollPane friendsScroll) {
		this.friendsScroll = friendsScroll;
	}

	public JScrollPane getChatScroll() {
		return chatScroll;
	}

	public void setChatScroll(JScrollPane chatScroll) {
		this.chatScroll = chatScroll;
	}

	public JTextField getWriteMessageBox() {
		return writeMessageBox;
	}

	public void setWriteMessageBox(JTextField writeMessageBox) {
		this.writeMessageBox = writeMessageBox;
	}

	public JLabel getSendBtn() {
		return sendBtn;
	}

	public void setSendBtn(JLabel sendBtn) {
		this.sendBtn = sendBtn;
	}
	
	
	
	
}
