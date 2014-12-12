package uoc.tdp.pac4.st.common.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class STTable extends  JPanel {
	
	private JTable table;
	private DefaultTableModel model;
	private List<STColumn> columns;
	
	public Action deleteRow;
	public boolean showDeleteButton= true;
	public Action validateCell;
	 
	public STTable() {
		columns= new ArrayList<STColumn>();
	}
	
	public void addColumn(String _nom, Integer _amplada, boolean _editable, boolean _validarEnterPositiu)
	{
		STColumn column= new STColumn(_nom, _amplada, _editable, _validarEnterPositiu);
		columns.add(column);
	}
	
	public void drawTable()
	{
		createTableModel();
		
		addScroll();
		
		configureColumns();
	
		//delete button
		Action deleteTableRow= new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);		     
		        
		        if (deleteRow != null) 		    	
		        {
		        	deleteRow.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
		        }
		    
		    }
		};
		 
		if (showDeleteButton) 
		{
			ButtonColumn buttonColumn = new ButtonColumn(table, deleteTableRow, getDeleteButtonIndex());
		}
		
	}	
	
	public int getRowCount() {
		return table.getRowCount();
	}
	
	public void addRow(Object[] row) {
		model.addRow(row);
	}
	

	
	private void createTableModel() 
	{
		//Crea un table model no editable 
		model = new DefaultTableModel()  {
			@Override
		    public boolean isCellEditable(int row, int column) {
				if (showDeleteButton && column == getDeleteButtonIndex()) 
				{
					return true;
				}
				else 
				{
					if(columns.get(column).isEditable())
						return true;
					else 
						return false;
				}
		    }
			
		};
		
		for (STColumn  column: columns)
		{
			model.addColumn(column.getNom());			
		}
		if (this.showDeleteButton)
		{
			model.addColumn("");
		}

	}
	
	private void configureColumns() 
	{
		int columnIndex= 0;
		for (STColumn  column: columns)
		{
			if (column.getAmplada() != null)
			{
				table.getColumnModel().getColumn(columnIndex).setMinWidth(column.getAmplada());
				table.getColumnModel().getColumn(columnIndex).setMaxWidth(column.getAmplada());
			}
			if (column.isEditable() && column.isValidarEnterPositiu())
			{				
				JTextField textField = new JTextField(); 
				STPositiveIntegerCellEditor cellEditor= new STPositiveIntegerCellEditor(textField );
				cellEditor.validateCell = this.validateCell;				
				table.getColumnModel().getColumn(columnIndex).setCellEditor(cellEditor);
			}
			
			columnIndex++;			
		}
		
		if (this.showDeleteButton)
		{
			table.getColumnModel().getColumn(getDeleteButtonIndex()).setMinWidth(50);
			table.getColumnModel().getColumn(getDeleteButtonIndex()).setMaxWidth(50);
		}

	}
	
	public int getEditingColumn() 
	{
		return table.getEditingColumn();
	}

	public int getEditingRow() 
	{
		return table.getEditingRow();
	}
	
	private int getDeleteButtonIndex() {
		return table.getColumnCount() - 1;
	}
	
	
	private void addScroll() 
	{
  	    setLayout(null);
		table= new JTable(model); 
		
		table.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());			
		table.setFillsViewportHeight(true);		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(this.getX(), this.getY(), this.getWidth()- 50, this.getHeight() - 125);

		
		removeAll();
		add(scrollPane);	
		
	}

	public DefaultTableModel getModel() {
		return model ;
	}

	public Object getValueAt(int row, int i) {
		return table.getValueAt(row, i);
	}

	public void setValueAt(Object value, int row, int column) {
		table.setValueAt(value, row, column);		
	}

	
	public void removeRows() 
	{
		int rowCount = model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}		
	}

	/***
	 * Torna en quina fila es trova el valor passat per parametre a la columna 0 
	 * 
	 * @param value valor a buscar
	 */  
	public int getRowIndexByRowValue(Object value) 
	{
		for(int row = 0;row < table.getRowCount();row++) {
	        if (value == table.getValueAt(row, 0))
    		{
	        	return row;
    		}	        
		}
		return -1;
	}
}
