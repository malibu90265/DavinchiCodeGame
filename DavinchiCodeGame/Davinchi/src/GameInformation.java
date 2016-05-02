import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//뒤로 버튼 만들기!!


class GameInformation extends JFrame {
	GameInformation() {
		getContentPane();
		setTitle("다빈치 코드 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);
		ImageIcon img = new ImageIcon("bgGameInfo.png");
		JLabel bg = new JLabel(img);
		bg.setLocation(0,0);
		bg.setSize(400,650);
		
		
		ImageIcon backImg = new ImageIcon("backBtn.png");
		JButton backBtn = new JButton(backImg);

		backBtn.setLocation(260, 140);
		backBtn.setSize(100, 25);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Davinchi();
			}
		});
		add(backBtn);
		add(bg);
		setSize(400, 650);
		setVisible(true);
		
		
	}
}
