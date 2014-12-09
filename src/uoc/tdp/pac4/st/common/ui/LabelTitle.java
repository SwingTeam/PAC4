package uoc.tdp.pac4.st.common.ui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelTitle extends JLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7865487937220641040L;

	public LabelTitle(String text)
	{	    
		super(text);
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.setForeground(Color.BLUE);
		this.setBackground(Color.BLUE);
	}
}