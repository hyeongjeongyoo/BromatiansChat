package ver01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class ChattingRoomFrame extends JFrame{
	
	Client mContext;
	ClientFrame clientFrame;
	ChattingRoomFrame chattingRoomFrame;
	
	private JLabel waitBackground;
	private JScrollPane friendsscroll;
	
	Font font = new Font("Noto Sans KR", Font.BOLD, 18);

	public ChattingRoomFrame(Client mContext) {
		this.mContext = mContext;
		initData();
		setInitData();
		addEventListener();
	}
	
	public void initData() {
		waitBackground = new JLabel(new ImageIcon("img/waitingBg.jpg"));

		setTitle("WAITING ROOM");
		setSize(400, 666);
		setContentPane(waitBackground);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/favRoom.png"));
	}
	
	public void setInitData() {

		// 메인패널 컴포넌트
		add(friendsscroll);
		friendsscroll.setBorder(new LineBorder(Color.BLACK, 3));
		friendsscroll.setBounds(15, 170, 360, 170);

		setLayout(null);
		setResizable(false); // 사이즈 조절 불가
		setLocationRelativeTo(null); // 가운데 배치
		setVisible(true);
	}
	
	public void addEventListener() {
		
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
		return friendsscroll;
	}

	public void setFriendsscroll(JScrollPane friendsscroll) {
		this.friendsscroll = friendsscroll;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	
}
