import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class GameRule extends JFrame {

	GameRule() {
		getContentPane();
		setTitle("�ٺ�ġ �ڵ� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);
		ImageIcon img = new ImageIcon("howtogame.png");

		JLabel j = new JLabel(img);
		JScrollPane sp = new JScrollPane(j);
		ImageIcon backImg = new ImageIcon("backBtn.png");
		JButton backBtn = new JButton(backImg);

		backBtn.setLocation(260, 120);
		backBtn.setSize(100, 25);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Davinchi();
			}
		});
		add(backBtn);
		sp.setLocation(0, 0);
		sp.setSize(390, 650);
		add(sp);
		
		setSize(400, 650);
		setVisible(true);

		// �ڷ� ��ư
		
		
		

	}

}