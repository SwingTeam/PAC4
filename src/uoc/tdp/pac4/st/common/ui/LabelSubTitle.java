package uoc.tdp.pac4.st.common.ui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelSubTitle extends JLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7865487937220641040L;

	public LabelSubTitle(String text)
	{	    
		super(text);
		this.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.setForeground(Color.BLUE);
		this.setBackground(Color.BLUE);
	}
}