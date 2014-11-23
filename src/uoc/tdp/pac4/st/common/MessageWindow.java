package uoc.tdp.pac4.st.common;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe utilitzada per a mostrar
 * missatges d'avís a l'usuari.
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class MessageWindow extends JDialog {

	private static final long serialVersionUID = 7892360249563105918L;
	private final JPanel contentPanel = new JPanel();
	
	/***
	 * Contructor
	 */
	public MessageWindow(){
		this(null);
	}

	/***
	 * Constructor
	 * 
	 * @param message Text del missatge a mostrar
	 */
	public MessageWindow(String message) {
		this(message, Enums.MessageType.Error);
	}

	/***
	 * Constructor
	 * 
	 * @param message Text del missatge a mostrar
	 * @param title Títol de la pantalla del missatge
	 */
	public MessageWindow(String message, String title) {
		this(message, title, Enums.MessageType.Error);
	}
	
	/***
	 * Constructor
	 * 
	 * @param message Text del missatge a mostrar
	 * @param messageType Tipus de missatge que mostrará
	 */
	public MessageWindow(String message, Enums.MessageType messageType) {
		this(message, TokenKeys.TITLE_WINDOW_MESSAGE, messageType);
	}
	
	/***
	 * Constructor
	 *  
	 * @param message Text del missatge a mostrar
	 * @param title Títol de la pantalla del missatge
	 * @param messageType Tipus de missatge que mostrará
	 */
	public MessageWindow(String message, String title, Enums.MessageType messageType) {
		this.setName(title);
		setBounds(100, 100, 450, 213);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 136, 448, 35);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton cancelButton = new JButton("BUTTON_ACCEPT");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(264, 0, 148, 25);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		if (messageType == Enums.MessageType.Info){
			JLabel lblInfo = new JLabel("");
			lblInfo.setIcon(new ImageIcon(MessageWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
			lblInfo.setBounds(12, 12, 32, 32);
			contentPanel.add(lblInfo);
		}
		
		if (messageType == Enums.MessageType.Error){
			JLabel lblError = new JLabel("");
			lblError.setIcon(new ImageIcon(MessageWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
			lblError.setBounds(12, 12, 32, 32);
			contentPanel.add(lblError);
		}
		
		if (messageType == Enums.MessageType.Warning){
			JLabel lblWarning = new JLabel("");
			lblWarning.setIcon(new ImageIcon(MessageWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/warning.png")));
			lblWarning.setBounds(12, 12, 32, 32);
			contentPanel.add(lblWarning);
		}
		
		if (message.length() > 100){
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(70, 34, 353, 77);
			scrollPane.setViewportBorder(null);
			contentPanel.add(scrollPane);
			
			JTextPane txtMessage = new JTextPane();
			txtMessage.setEditable(false);
			txtMessage.setBackground(UIManager.getColor("Button.background"));
			txtMessage.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtMessage.setText(message);
	
			scrollPane.setViewportView(txtMessage);
		
		} else {
			JTextPane txtMessage = new JTextPane();
			txtMessage.setEditable(false);
			txtMessage.setBackground(UIManager.getColor("Button.background"));
			txtMessage.setFont(new Font("Dialog", Font.PLAIN, 18));
			txtMessage.setBounds(70, 34, 353, 77);
			txtMessage.setText(message);
			contentPanel.add(txtMessage);
		}
		
		//Tradueix els tokens
		try {
			Methods.setDialogLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException (e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
	}
}
