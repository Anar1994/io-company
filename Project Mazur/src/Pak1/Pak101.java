package Pak1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
 
public class Pak101 {
	public static void main(String[] args){
		MainWin mainwin = new MainWin();
		mainwin.setVisible(true);
		mainwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
}
class MainWin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField logintxt;
	private JPasswordField pwdtxt;
	public MainWin(){
		setSize(250, 200);
		setTitle("Enter Login/Pass");
		logintxt = new JTextField();
		pwdtxt = new JPasswordField();
		JLabel loginlbl = new JLabel("Login:");
		JLabel pwdlbl = new JLabel("Password:");
		JButton submitbtn = new JButton("Submit");
		
		JPanel formPanel = new JPanel(new GridLayout(5,1));
		formPanel.add(loginlbl);
		formPanel.add(logintxt);
		formPanel.add(pwdlbl);
		formPanel.add(pwdtxt);
		formPanel.add(submitbtn);
		add(formPanel);
		submitbtn.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//��� ��������� ������ � ������
				//���� ��� ������� �� ��������� ��������� ����
				//��� ����: NextWindow nw = new NextWindow();
				
			}
			
		});
	}
}
