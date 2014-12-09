package uoc.tdp.pac4.st.common;

import javax.swing.table.AbstractTableModel;

/***
 * Classe per a fer-la servir com a model
 * a les taules.
 * 
 * @author Swing Team - 2014
 *
 */
public class STTableModel extends AbstractTableModel {
    
	private static final long serialVersionUID = 460026333248002249L;

	Object _data[][] = null;
    String[] _columns = null;
    
    public STTableModel(Object[][] data, String[] columns) {
    	this._columns = columns;
        this._data = new Object[data.length][this._columns.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < columns.length; j++)
                this._data[i][j] = data[i][j];
        }
    }

    public int getColumnCount() {
        return (this._columns == null ? 0 : this._columns.length);
    }

    public String getColumnName(int column) {
        return (this._columns == null ? "" : this._columns[column]);
    }

    public int getRowCount() {
        return (this._data == null ? 0 : this._data.length);
    }

    public Object getValueAt(int row, int column) {
        return (this._columns == null ? new Object() : this._data[row][column]);
    }

    public void setValueAt(Object value, int row, int column) {
        this._data[row][column] = value;
    }

    public boolean isCellEditable(int row, int column) { return false;}
}
