package uoc.tdp.pac4.st.client.cx;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.*;
import uoc.tdp.pac4.st.client.cf.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.client.e.*;
import uoc.tdp.pac4.st.client.m.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

@SuppressWarnings("unused")
public class ClientWindow extends JFrame {

	private static final long serialVersionUID = -4301396368624900151L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ClientWindow() {
		setTitle("TITLE_CLIENT_WINDOW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LABEL_EXAMPLE_CONNEXIO_RMI");
		lblNewLabel.setBounds(33, 26, 398, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("BUTTON_EXECUTE");
		btnNewButton.setBounds(480, 21, 142, 25);
		contentPane.add(btnNewButton);
	}
}
