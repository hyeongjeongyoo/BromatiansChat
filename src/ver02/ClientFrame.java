package ver02;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lombok.Data;

public class ClientFrame extends JFrame{
	
	private Client mContext;
	
	ClientFrame clientFrame;
	
	// background
	private JLabel clientBackground;
	
	// 컴포넌트
	private JLabel mainDogImg;
	private JTextField userId;
	private JLabel logo;
	private JLabel logoBtn;
	
	// 버튼 크기
	private final int BTN_WIDTH = 249;
	private final int BTN_HEIGHT = 44;
	
	// 폰트 설정
	Font font = new Font("New Walt Disney UI", Font.BOLD, 20);
	Font font2 = new Font("Noto Sans KR", Font.BOLD, 18);
	Font font3 = new Font("Wicked Mouse", Font.BOLD, 18);
	
	public ClientFrame(Client mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {
		clientFrame = this;
		clientBackground = new JLabel(new ImageIcon("img/backgroundBg.jpg"));
		mainDogImg = new JLabel(new ImageIcon("img/mainDogImg.png"));

		logo = new JLabel(new ImageIcon("img/mainLogo.png"));
		userId = new JTextField("ID",5);
		logoBtn = new JLabel(new ImageIcon("img/mainLogin.png"));
		

		setTitle("BROMATIANS CHAT");
		setSize(400, 666);
		setContentPane(clientBackground);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/fav.png"));
	}
	
	public void setInitLayout() {
		setLayout(null);
		setResizable(false); 			// 사이즈 조절 불가
		setLocationRelativeTo(null); 	// 가운데 배치
		setVisible(true);

		clientBackground.add(mainDogImg);
		mainDogImg.setSize(161, 161);
		mainDogImg.setLocation(115, 68);

		clientBackground.add(logo);
		logo.setSize(301, 118);
		logo.setLocation(45, 270);

		clientBackground.add(logoBtn);
		logoBtn.setSize(BTN_WIDTH, BTN_HEIGHT);
		logoBtn.setLocation(70, 490);
		logoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 모양 변경

		clientBackground.add(userId);
		userId.setFont(font2);
		userId.setBorder(null);
		userId.setSize(BTN_WIDTH - 10, BTN_HEIGHT - 10);
		userId.setLocation(70, 435);
	}
	
	public void addEventListener() {
		
		logoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mContext.setUserId(userId.getText());
				clientFrame.setVisible(false);
				ChattingRoomFrame chattingRoomFrame = new ChattingRoomFrame(mContext);
				
				try {
					mContext.getWriter().write(userId + "\n");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				mContext.setChattingRoomFrame(chattingRoomFrame);
			}
		});

	}


	
}
