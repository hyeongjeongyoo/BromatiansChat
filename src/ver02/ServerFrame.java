package ver02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class ServerFrame extends JFrame{

private Server mContext;
	
	// backgrund
	private JLabel background;
	
	// 알림창
	private JPanel mainPanel;
	private JTextArea mainBoard;
	private JScrollPane scollPane;
	
	// 폰트 설정
	Font font = new Font("New Walt Disney UI", Font.BOLD, 20);
	Font font2 = new Font("Noto Sans KR", Font.BOLD, 18);
	Font font3 = new Font("Wicked Mouse", Font.BOLD, 18);
	
	// 추가
	private JButton btn;
	
	public ServerFrame(Server mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
		initListener();
	}
	
	public void initData() {
		background = new JLabel(new ImageIcon("img/serverBg.jpg"));
		mainBoard = new JTextArea();
		scollPane = new JScrollPane(mainBoard);
		
		// 추가
		btn = new JButton("서버 실행");
		
		setTitle("BROMATIANS SERVER");
		setSize(400, 500);
		setContentPane(background);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/fav.png"));
	}


	public void setInitLayout() {
		setLayout(null);
		setResizable(false); 			// 사이즈 조절 불가
		setLocationRelativeTo(null); 	// 가운데 배치
		setVisible(true);
		
		scollPane.setBorder(new LineBorder(Color.BLACK,3));

		
		mainBoard.setEnabled(false);
		add(scollPane);
		scollPane.setSize(300, 300);
		scollPane.setLocation(30, 100);
		
		// 추가
		btn.setSize(100, 50);
		btn.setLocation(100,400);
		add(btn);
	}
	
	public void initListener() {
		
		// 추가
		btn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				mContext.startServer();
				System.out.println("서버 실행");
			}
			
		});
		
	}

	public JTextArea getMainBoard() {
		return mainBoard;
	}

	public void setMainBoard(JTextArea mainBoard) {
		this.mainBoard = mainBoard;
	}
	
}
