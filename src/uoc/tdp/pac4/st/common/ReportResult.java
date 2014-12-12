package uoc.tdp.pac4.st.common;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import java.awt.SystemColor;

import uoc.tdp.pac4.st.common.dto.*;

/***
 * Classe base per a tots els formularis
 * que mostren el resultat dels informes
 * demanats.
 * 
 * @author Swing Team - 2014
 *
 * @param <T>
 */
public class ReportResult <T> extends STFrame {
	
	private static final long serialVersionUID = -597281336156358964L;
	private List<T> _reportLines = null;
	private ReportSelectorData _reportSelectorData = null;
	private STTable dataTable = null;
	private JScrollPane dataTableScrollPane = null;
	private JLabel lblStartDate = null;
	private JLabel lblEndDate = null;
	private JLabel lblEstablishment = null;
	private JLabel lblOrder = null;
	private JTextPane txtProducts = null;
	
	/***
	 * Constructor
	 * 
	 * @param reportLines Llista de ReportLine
	 * que conté les línies de l'informe
	 * @param reportSelectorData Instància de
	 * ReportSelectorData que conté la selecció
	 * de l'usuari. 
	 */
	public ReportResult(List<T> reportLines, ReportSelectorData reportSelectorData) {
		super(null, null, false);
		this._reportLines = reportLines;
		this._reportSelectorData = reportSelectorData;
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 934, 477);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnClose = new JButton("BUTTON_CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		panelBottom.add(btnClose);

		this.dataTableScrollPane = new JScrollPane();
		
//		splitPane.add(scrollPane, BorderLayout.SOUTH);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setLayout(null);
		
		JLabel lblDataDinici = new JLabel("LABEL_START_DATE_COLON");
		lblDataDinici.setBounds(12, 5, 135, 15);
		pnlHeader.add(lblDataDinici);
		
		this.lblStartDate = new JLabel("");
		lblStartDate.setBounds(152, 5, 119, 15);
		pnlHeader.add(lblStartDate);
		
		JLabel lblDataFinal = new JLabel("LABEL_END_DATE_COLON");
		lblDataFinal.setBounds(12, 32, 135, 15);
		pnlHeader.add(lblDataFinal);
		
		this.lblEndDate = new JLabel("");
		lblEndDate.setBounds(152, 32, 119, 15);
		pnlHeader.add(lblEndDate);
		
		JLabel lblLocal = new JLabel("LABEL_ESTAB_COLON");
		lblLocal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocal.setBounds(272, 5, 146, 15);
		pnlHeader.add(lblLocal);
		
		this.lblEstablishment = new JLabel("");
		lblEstablishment.setBounds(424, 5, 360, 15);
		pnlHeader.add(lblEstablishment);
		
		JLabel lblOrderTitle = new JLabel("LABEL_ORDER_COLON");
		lblOrderTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrderTitle.setBounds(272, 32, 146, 15);
		pnlHeader.add(lblOrderTitle);
		
		this.lblOrder = new JLabel("");
		lblOrder.setBounds(424, 32, 257, 15);
		pnlHeader.add(lblOrder);
		
		JLabel lblRecanvis = new JLabel("LABEL_PRODUCTS_COLON");
		lblRecanvis.setBounds(12, 59, 135, 15);
		pnlHeader.add(lblRecanvis);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(152, 59, 632, 58);
		pnlHeader.add(scrollPane_1);
		
		this.txtProducts = new JTextPane();
		txtProducts.setBorder(null);
		txtProducts.setBackground(SystemColor.text);
		txtProducts.setEditable(false);
		scrollPane_1.setViewportView(txtProducts);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pnlHeader, this.dataTableScrollPane);
		splitPane.setDividerLocation(130);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		//Omplim la selecció de l'usuari
		this.fillinSelectionData();
	}
	
	/***
	 * Posa els valors de la instància rebuda
	 * a les etiquetes de capçalera.
	 * 
	 * @param reportSelectorData Instància de
	 * ReportSelectorData amb la selecció que
	 * ha fet l'usuari.
	 */
	private void fillinSelectionData(){
		this.lblStartDate.setText(new SimpleDateFormat(Constants.LOCAL_DATE_FORMAT).format(this._reportSelectorData.getStartDate()));
		this.lblEndDate.setText(new SimpleDateFormat(Constants.LOCAL_DATE_FORMAT).format(this._reportSelectorData.getEndDate()));
		this.lblEstablishment.setText(this._reportSelectorData.getEstablishmentName());
		this.lblOrder.setText(Managers.i18n.getTranslation(Constants.ENUM_PREFIX + this._reportSelectorData.getOrder().toUpperCase()));
		
		StringBuilder products = new StringBuilder();
		for(STTreeNode node : this._reportSelectorData.getProducts()){
			if (products.length() > 0)
				products.append(" - ");
			
			if (node.getNodeType() == Enums.NodeType.Root)
				products.append(Managers.i18n.getTranslation(TokenKeys.ALL_PRODUCTS));
			else if (node.getNodeType() == Enums.NodeType.Group)
				products.append(Managers.i18n.getTranslation(TokenKeys.GROUP) + ": " + node.getDescription());
			else if (node.getNodeType() == Enums.NodeType.Subgroup)
				products.append(Managers.i18n.getTranslation(TokenKeys.SUBGROUP) + ": " + node.getDescription());
			else
				products.append(node.getIdAsString());
		}
		this.txtProducts.setText(products.toString());
	}
	
	/***
	 * Mètode que s'encarrega de crear la taula
	 * a partir de las línies de l'informe.
	 * 
	 * @param reportLines Lista d'objecte ReportLineInterface
	 * i que contenen la informació de l'informe.
	 */
	protected void showReport(String[] columnName, String[] columnField, int[] columnWidth, int[] columnAlign){

		String[] columnTraslatedName = this.translateColumns(columnName);
		Object[][] dataList = new Object[0][columnTraslatedName.length];
		
		if (this._reportLines != null
				&& this._reportLines.size() > 0){
			dataList = new Object[this._reportLines.size()][columnTraslatedName.length];

			int i = 0;
            for (T item : this._reportLines) {
            	for (int n = 0; n < columnTraslatedName.length; n++){
	                dataList[i][n] = ((ReportLine)item).getValue(columnField[n]);
            	}
                i++;
            }
		}

        if (this.dataTable == null){
		    this.dataTable = new STTable(dataList, columnTraslatedName);
		    this.dataTable.setFillsViewportHeight(true);
		    this.dataTableScrollPane.setViewportView(this.dataTable);
        }
		
		STTableModel tableModel = new STTableModel(dataList, columnTraslatedName);
        this.dataTable.setModel(tableModel);
        tableModel.fireTableDataChanged();

	    //Amplada de les columnes i alineació
	    this.dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	    TableColumn column = null;
	    for (int j = 0; j < columnWidth.length; j++) {
	    	//Amplada
	        column = this.dataTable.getColumnModel().getColumn(j);
	        //El valor de l'amplada de les columnes
	        //és porcentual... per tant, hem de fer
	        //la conversió
	        int formWidth = this.getWidth() - 50;
	        int width = (int) (formWidth * columnWidth[j] / 100);
            column.setPreferredWidth(width);

            //Alineació
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(columnAlign[j]);
            this.dataTable.getColumnModel().getColumn(j).setCellRenderer(renderer);
	    }
	}
	
	/***
	 * Tradueix tots els tokens corresponents
	 * als noms de columnes de l'informe.
	 * 
	 * @return
	 */
	private String[] translateColumns(String[] columnName){
		String[] result = columnName;
		
		for (int i = 0; i < result.length; i++){
			result[i] = Managers.i18n.getTranslation(result[i]);
		}
		return result;
	}
}
