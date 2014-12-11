package uoc.tdp.pac4.st.common.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class STPositiveIntegerCellEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1L;
	
	private static final Border red = new LineBorder(Color.red);
    private static final Border black = new LineBorder(Color.black);
    private JTextField textField;
    public Action validateCell;
    
    public STPositiveIntegerCellEditor(JTextField textField) {
        super(textField);
        this.textField = textField;
        this.textField.setHorizontalAlignment(JTextField.RIGHT);
    }

    @Override
    public boolean stopCellEditing() {
        try {
            int v = Integer.valueOf(textField.getText());
            if (v < 0) {
                throw new NumberFormatException();
            }           
            if (validateCell != null )
            {
            	validateCell.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, textField.getText()));
            }
            
        } catch (NumberFormatException e) {
            textField.setBorder(red);
            return false;
        }
        return super.stopCellEditing();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
        Object value, boolean isSelected, int row, int column) {
        textField.setBorder(black);
        return super.getTableCellEditorComponent(
            table, value, isSelected, row, column);
    }
  }