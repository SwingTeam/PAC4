package uoc.tdp.pac4.st.client.cx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.Enums.MessageType;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

import javax.swing.JComboBox;

@SuppressWarnings("unused")
public class ExampleWindow extends JFrame {

	private static final long serialVersionUID = -4301396368624900151L;
	private JPanel contentPane;
	private ClientManager<ETallerStocksInterface> _clientManager = null;
	/**
	 * Create the frame.
	 */
	public ExampleWindow() {
		this.setName(null);
		setTitle("TITLE_CLIENT_WINDOW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LABEL_EXAMPLE_CONNEXIO_RMI");
		lblNewLabel.setBounds(33, 26, 614, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnExample1 = new JButton("BUTTON_EXECUTE");
		btnExample1.setBounds(676, 21, 142, 25);
		contentPane.add(btnExample1);
		
		JLabel lblLabelexamplerecoverdata = new JLabel("LABEL_EXAMPLE_RECOVER_DATA");
		lblLabelexamplerecoverdata.setBounds(33, 62, 614, 15);
		contentPane.add(lblLabelexamplerecoverdata);
		
		JButton btnExample2 = new JButton("BUTTON_EXECUTE");
		btnExample2.setBounds(676, 57, 142, 25);
		contentPane.add(btnExample2);
		
		final JComboBox<ComboBoxItem> comboBoxExample2 = new JComboBox<ComboBoxItem>();
		comboBoxExample2.setBounds(190, 94, 381, 24);
		contentPane.add(comboBoxExample2);
		
		JButton btnExamble3 = new JButton("BUTTON_EXECUTE");
		btnExamble3.setBounds(676, 158, 142, 25);
		contentPane.add(btnExamble3);
		
		JLabel lblLabelexamplerecoverresultset = new JLabel("LABEL_EXAMPLE_RECOVER_RESULTSET");
		lblLabelexamplerecoverresultset.setBounds(33, 168, 614, 15);
		contentPane.add(lblLabelexamplerecoverresultset);
		
		JLabel lblLabelexampleupdatedata = new JLabel("LABEL_EXAMPLE_UPDATE_DATA");
		lblLabelexampleupdatedata.setBounds(33, 205, 614, 15);
		contentPane.add(lblLabelexampleupdatedata);
		
		JButton btnExample4 = new JButton("BUTTON_EXECUTE");
		btnExample4.setBounds(676, 195, 142, 25);
		contentPane.add(btnExample4);
		
		JLabel lblLabelexampleupdatedataintransaction = new JLabel("LABEL_EXAMPLE_UPDATE_DATA_IN_TRANSACTION");
		lblLabelexampleupdatedataintransaction.setBounds(33, 242, 614, 15);
		contentPane.add(lblLabelexampleupdatedataintransaction);
		
		JButton btnExample5 = new JButton("BUTTON_EXECUTE");
		btnExample5.setBounds(676, 232, 142, 25);
		contentPane.add(btnExample5);
		
		JLabel lblLabelexampleinsertdataintransaction = new JLabel("LABEL_EXAMPLE_INSERT_DATA_IN_TRANSACTION");
		lblLabelexampleinsertdataintransaction.setBounds(33, 279, 614, 15);
		contentPane.add(lblLabelexampleinsertdataintransaction);
		
		JButton btnExample6 = new JButton("BUTTON_EXECUTE");
		btnExample6.setBounds(676, 269, 142, 25);
		contentPane.add(btnExample6);
		
		JLabel label = new JLabel("LABEL_EXAMPLE_INSERT_DATA_IN_TRANSACTION");
		label.setBounds(33, 314, 614, 15);
		contentPane.add(label);
		
		JButton btnExample7 = new JButton("BUTTON_EXECUTE");
		btnExample7.setBounds(676, 304, 142, 25);
		contentPane.add(btnExample7);
		
		JLabel lblLabelexampledeletedataintransaction = new JLabel("LABEL_EXAMPLE_DELETE_DATA_IN_TRANSACTION");
		lblLabelexampledeletedataintransaction.setBounds(33, 351, 614, 15);
		contentPane.add(lblLabelexampledeletedataintransaction);
		
		JButton btnExample8 = new JButton("BUTTON_EXECUTE");
		btnExample8.setBounds(676, 341, 142, 25);
		contentPane.add(btnExample8);
		
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
		
		/* EVENTS - BEGIN *************************************************************************************/
		//BOTÓ INICIAR SERVIDOR
		btnExample1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startConnection();
				testConnection();
				stopConnection();
			}
		});

		//BOTÓ INICIAR SERVIDOR
		btnExample2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillinComboBox(comboBoxExample2);
			}
		});

		//BOTÓ RECUPERAR DADES
		btnExamble3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testSelectData();
			}
		});
		
		//BOTÓ ACTUALITZAR DADES
		btnExample4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testUpdateData();
			}
		});

		//BOTÓ ACTUALITZAR DADES EN TRANSACCIÓ
		btnExample5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testUpdateDataInTransaction();
			}
		});
		
		//BOTÓ D'INSERCIÓ DE DADES
		btnExample6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testInsertDataWithHashMap();
			}
		});

		//BOTÓ D'INSERCIÓ DE DADES AMB SENTÈNCIA SQL
		btnExample7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testInsertData();
			}
		});

		//BOTÓ D'ELIMINACIÓ DE DADES
		btnExample8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testDeleteData();
			}
		});
		
		
		/* EVENTS - END *************************************************************************************/
	}
	/***
	 * Omple un ComboBox amb la llista de locals.
	 * 
	 * @param comboBox Objecte que serà omplert.
	 */
	private void fillinComboBox(JComboBox<ComboBoxItem> comboBox){
		try {
			
			startConnection();
			List<Local> locals = this._clientManager.getRMIInterface().getEstablishmentList(null);
			
			if (locals != null){
				for (Local item : locals){
					comboBox.addItem(new ComboBoxItem(item.getTaxId(), item.getName()));
				}
			}
		} catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		
		}finally{
			stopConnection();
		}
	}
	
	/***
	 * Mostra el contingut de la tabla indicada
	 * 
	 * @param db Objecte DatabaseManager obert
	 * @throws STException
	 * @throws Exception
	 */
	private void showTableLocal(DatabaseManager db) throws STException, Exception{
		ResultSet resultSet = null;
		resultSet = db.selectData("Select * from " + Constants.TABLE_LOCAL);
	
		resultSet.beforeFirst();
		int i = 0;
		while (resultSet.next()){
			i++;
			Methods.showMessage(resultSet.getString(Constants.FIELD_NAME),Enums.MessageType.Info);
		}
		resultSet.close();
		resultSet = null;
		Methods.showMessage(String.format(Managers.i18n.getTranslation(TokenKeys.AVAILABLE_ESTABLIMENTS), i), Enums.MessageType.Info);
	}
	
	/***
	 * Métode que encarregat de fer la connexió
	 * RMI amb el servidor remot
	 */
	private void startConnection(){
		try{
		//Només carregarem les dades configurades la
		//primera vegada que es posi faci la connexió
		if (this._clientManager == null){
			try{
				String rmiUrl = Managers.settings.getSetting(Constants.SETTING_RMI_URL);
				int rmiPort = Integer.parseInt(Managers.settings.getSetting(Constants.SETTING_RMI_PORT));
				String rmiName = Managers.settings.getSetting(Constants.SETTING_RMI_NAME);
				this._clientManager = new ClientManager<ETallerStocksInterface>(rmiUrl, rmiPort, rmiName);

			} catch (IOException | NullPointerException e){
				Managers.exception.showException(new STException(e, TokenKeys.ERROR_CONFIGURATION_FILE));
				
			}
		}
		if (this._clientManager != null)
			this._clientManager.startConnection();
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_NOT_BOUND_EXCEPTION));
			
		}
	}
	
	/***
	 * Métode que encarregat de tancar la connexió
	 * RMI amb el servidor remot
	 */
	private void stopConnection(){
		try{
			if (this._clientManager != null){
				this._clientManager.stopConnection();
				this._clientManager = null;
			}
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		
		}
	}
	
	/***
	 * Métode que prova la connexió establerta
	 * amb el servidor remot RMI
	 */
	private void testConnection(){
		try {
			if (this._clientManager != null){
				if (this._clientManager.connectionStatus() == Enums.ConnectionStatus.Open){
					String result = this._clientManager.getRMIInterface().testRMIConnection();
					Methods.showMessage(result, Enums.MessageType.Info);
				} else {
					Methods.showMessage(TokenKeys.ERROR_CLOSED_CONNECTION, Enums.MessageType.Warning);
				}
			} else {
				Methods.showMessage(TokenKeys.ERROR_CLOSED_CONNECTION, Enums.MessageType.Error);
			}
		} catch (STException e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_REMOTE_EXCEPTION));

		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}
	
	/***
	 * Executa una sentència SQL d'eliminació
	 * de dades a la base de dades. 
	 * Fixeu-vos que no cal obrir la
	 * connexió a la base de dades ni tancar-la.
	 * També he afegit com s'utilitza el mètode
	 * getScalar, que recupera el valor de la
	 * primera columna de la primera fila
	 * del resultat d'una sentència de selecció.
	 *  
	 * ATENCIÓ: 
	 * ========
	 * 
	 * El que mostra aquest exemple no es
	 * pot reproduir a mètodes del costat del client.
	 * Només s'ha de reproduir a mètodes
	 * que es trobin a LA BANDA DEL SERVIDOR
	 */
	private void testDeleteData(){
		DatabaseManager db = null;
		try {
			db = new DatabaseManager();
			db.startTransaction();
			
			//Recuperem el cif d'un taller
			Object cif = db.getScalar("SELECT " + 
										Constants.FIELD_TAXID + 
										" FROM " + Constants.TABLE_LOCAL + 
										" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
										" LIMIT 1");

			//Eliminem el registre
			int result = db.deleteData("DELETE FROM " + Constants.TABLE_LOCAL + " WHERE cif = '" + cif + "'");
			
			//Mostrem els registres actuals
			this.showTableLocal(db);

			//Desfem els canvis
			db.finishTransaction(false);
			
		} catch (STException e){
			Managers.exception.showException(e);
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}
	
	/***
	 * Executa una d'inserció d'un registre
	 * a la base de dades. 
	 * Fixeu-vos que no cal obrir la
	 * connexió a la base de dades ni tancar-la.
	 * També he afegit com s'utilitza el mètode
	 * getScalar, que recupera el valor de la
	 * primera columna de la primera fila
	 * del resultat d'una sentència de selecció.
	 *  
	 * ATENCIÓ: 
	 * ========
	 * 
	 * El que mostra aquest exemple no es
	 * pot reproduir a mètodes del costat del client.
	 * Només s'ha de reproduir a mètodes
	 * que es trobin a LA BANDA DEL SERVIDOR
	 */
	private void testInsertData(){
		DatabaseManager db = null;
		try {
			db = new DatabaseManager();
			
			//Ho farem amb una transacció per a no
			//afectar a la informació actual de la
			//base de dades
			db.startTransaction();
			
			String currentCif = "New Cif";
			String currentName = "New Name";

			if (db.countTableRows(Constants.TABLE_LOCAL) > 0){
				currentCif = db.getScalar("SELECT " + 
												Constants.FIELD_TAXID + 
												" FROM " + Constants.TABLE_LOCAL + 
												" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
												" LIMIT 1").toString();
			
				currentName = db.getScalar("SELECT " + 
											Constants.FIELD_NAME + 
											" FROM " + Constants.TABLE_LOCAL +
											" WHERE " + Constants.FIELD_TAXID + " = '" + currentCif + "' ").toString();
			}
			
			String newCif = currentCif + "I";
			String newName = currentName + "I";

			//Canviem el seu nom
			int result = db.insertData("INSERT INTO " + 
										Constants.TABLE_LOCAL + 
										" (" + 
											Constants.FIELD_TAXID + ", " +
											Constants.FIELD_NAME +
										")" +
										" VALUES " +
										"("+
											"'" + newCif + "', " +
											"'" + newName + "'" +
										");");
			
			//Mostrem el resultat per pantalla...
			this.showTableLocal(db);
			
			//...i desfem els canvis
			db.finishTransaction(false);
			
		} catch (STException e){
			Managers.exception.showException(e);
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}
	
	
	/***
	 * Executa una d'inserció d'un registre
	 * a la base de dades. 
	 * Fixeu-vos que no cal obrir la
	 * connexió a la base de dades ni tancar-la.
	 * També he afegit com s'utilitza el mètode
	 * getScalar, que recupera el valor de la
	 * primera columna de la primera fila
	 * del resultat d'una sentència de selecció.
	 *  
	 * ATENCIÓ: 
	 * ========
	 * 
	 * El que mostra aquest exemple no es
	 * pot reproduir a mètodes del costat del client.
	 * Només s'ha de reproduir a mètodes
	 * que es trobin a LA BANDA DEL SERVIDOR
	 */
	private void testInsertDataWithHashMap(){
		DatabaseManager db = null;
		try {
			db = new DatabaseManager();
			
			//Ho farem amb una transacció per a no
			//afectar a la informació actual de la
			//base de dades
			db.startTransaction();
			
			String currentCif = "New Cif";
			String currentName = "New Name";

			if (db.countTableRows(Constants.TABLE_LOCAL) > 0){
				currentCif = db.getScalar("SELECT " + 
												Constants.FIELD_TAXID + 
												" FROM " + Constants.TABLE_LOCAL + 
												" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
												" LIMIT 1").toString();
			
				currentName = db.getScalar("SELECT " + 
											Constants.FIELD_NAME + 
											" FROM " + Constants.TABLE_LOCAL +
											" WHERE " + Constants.FIELD_TAXID + " = '" + currentCif + "' ").toString();
			}
			
			String newCif = currentCif + "I";
			String newName = currentName + "I";
			
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put(Constants.FIELD_TAXID, newCif);
			hashMap.put(Constants.FIELD_NAME, newName);
			
			int result = db.insertData(Constants.TABLE_LOCAL, hashMap); 
			
			//Mostrem el resultat per pantalla...
			this.showTableLocal(db);
			
			//...i desfem els canvis
			db.finishTransaction(false);
			
		} catch (STException e){
			Managers.exception.showException(e);
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}
	
	/***
	 * Executa una sentència SQL de selecció
	 * de dades a la base de dades. 
	 * Fixeu-vos que no cal obrir la
	 * connexió a la base de dades ni tancar-la.
	 * Només s'ha de crear un objecte DabatabeManager
	 * i cridar al seu mètode selectData, passant-li
	 * com a paràmetre la sentència a executar.
	 * 
	 * ATENCIÓ: 
	 * ========
	 * 
	 * El que mostra aquest exemple no es
	 * pot reproduir a mètodes del costat del client.
	 * Només s'ha de reproduir a mètodes
	 * que es trobin a LA BANDA DEL SERVIDOR
	 */
	private void testSelectData(){
		DatabaseManager db = null;
		try {
			db = new DatabaseManager();
			this.showTableLocal(db);

		} catch (STException e){
			Managers.exception.showException(e);
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}

	/***
	 * Executa una sentència SQL d'actualització
	 * de dades a la base de dades. 
	 * Fixeu-vos que no cal obrir la
	 * connexió a la base de dades ni tancar-la.
	 * També he afegit com s'utilitza el mètode
	 * getScalar, que recupera el valor de la
	 * primera columna de la primera fila
	 * del resultat d'una sentència de selecció.
	 *  
	 * ATENCIÓ: 
	 * ========
	 * 
	 * El que mostra aquest exemple no es
	 * pot reproduir a mètodes del costat del client.
	 * Només s'ha de reproduir a mètodes
	 * que es trobin a LA BANDA DEL SERVIDOR
	 */
	private void testUpdateData(){
		DatabaseManager db = null;
		try {
			//ATENCIÓ: EN AQUEST EXEMPLE NO USEM TRANSACCIÓ
			
			db = new DatabaseManager();
			//Recuperem el nom actual d'un taller
			Object oldValue = db.getScalar("SELECT " + 
											Constants.FIELD_NAME + 
											" FROM " + Constants.TABLE_LOCAL + 
											" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
											" LIMIT 1");
			//Recuperem el nom actual d'un taller
			Object oldTaxId = db.getScalar("SELECT " + 
											Constants.FIELD_TAXID + 
											" FROM " + Constants.TABLE_LOCAL + 
											" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
											" LIMIT 1");
			//Canviem el seu nom
			int result = db.updateData("UPDATE " + 
										Constants.TABLE_LOCAL + 
										" SET " + Constants.FIELD_NAME + " = 'New Value' " +
											"WHERE " + Constants.FIELD_TAXID + " = '" + oldTaxId.toString() + "'");
			
			//Recuperem el nom un cop hem fet la modificació
			Object currentValue = db.getScalar("SELECT " + 
												Constants.FIELD_NAME + 
												" FROM " + Constants.TABLE_LOCAL + 
												" ORDER BY " + Constants.FIELD_TAXID  + " ASC" +
												" LIMIT 1");
			
			//Mostrem un missatge amb el valor que hi havia abans i el d'ara
			Methods.showMessage("Old value = '" + oldValue + "'\nCurrent value = '" + currentValue + "'", Enums.MessageType.Info);

			//Retornem el registre al seu valor inicial
			//com que no utilitzem una transacció, ho hem
			//de fer manualment...
			result = db.updateData("UPDATE " + 
					Constants.TABLE_LOCAL + 
					" SET " + Constants.FIELD_NAME + " = '" + oldValue + "' " +
						"WHERE " + Constants.FIELD_NAME + " = '" + currentValue.toString() + "'");
			
		} catch (STException e){
			Managers.exception.showException(e);
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}

	/***
	 * Executa una sentència SQL d'actualització
	 * de dades a la base de dades usant una
	 * transacció. 
	 * Fixeu-vos que no cal obrir la
	 * connexió a la base de dades ni tancar-la,
	 * però si que s'ha d'iniciar i tancar la
	 * transacció.
	 *  
	 * ATENCIÓ: 
	 * ========
	 * 
	 * El que mostra aquest exemple no es
	 * pot reproduir a mètodes del costat del client.
	 * Només s'ha de reproduir a mètodes
	 * que es trobin a LA BANDA DEL SERVIDOR
	 */
	private void testUpdateDataInTransaction(){
		DatabaseManager db = null;
		try {
			db = new DatabaseManager();
			db.startTransaction();
			
			//Recuperem el nom actual d'un taller
			Object oldValue = db.getScalar("SELECT " + 
											Constants.FIELD_NAME + 
											" FROM " + Constants.TABLE_LOCAL + 
											" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
											" LIMIT 1");
			//Canviem el seu nom
			int result = db.updateData("UPDATE " + 
										Constants.TABLE_LOCAL + 
										" SET " + Constants.FIELD_NAME + " = 'New Value 1' " +
											"WHERE " + Constants.FIELD_NAME + " = '" + oldValue.toString() + "'");
			
			//Recuperem el nom un cop hem fet la modificació
			Object currentValue = db.getScalar("SELECT " + 
												Constants.FIELD_NAME + 
												" FROM " + Constants.TABLE_LOCAL + 
												" ORDER BY " + Constants.FIELD_TAXID  + " ASC" +
												" LIMIT 1");
			
			//Mostrem un missatge amb el valor que hi havia abans i el d'ara
			Methods.showMessage("Old value = '" + oldValue + "'\nCurrent value = '" + currentValue + "'", Enums.MessageType.Info);

			
			//Recuperem el nom actual d'un taller
			Object oldValue2 = db.getScalar("SELECT " + 
											Constants.FIELD_NAME + 
											" FROM " + Constants.TABLE_LOCAL + 
											" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
											" LIMIT 1");
			//Canviem el seu nom
			int result2 = db.updateData("UPDATE " + 
										Constants.TABLE_LOCAL + 
										" SET " + Constants.FIELD_NAME + " = 'New Value 2' " +
											"WHERE " + Constants.FIELD_NAME + " = '" + oldValue2.toString() + "'");
			
			//Recuperem el nom un cop hem fet la modificació
			Object currentValue2 = db.getScalar("SELECT " + 
												Constants.FIELD_NAME + 
												" FROM " + Constants.TABLE_LOCAL + 
												" ORDER BY " + Constants.FIELD_TAXID  + " ASC" +
												" LIMIT 1");
			
			//Mostrem un missatge amb el valor que hi havia abans i el d'ara
			Methods.showMessage("Old value = '" + oldValue2 + "'\nCurrent value = '" + currentValue2 + "'", Enums.MessageType.Info);
			

			//Recuperem el nom actual d'un taller
			Object oldValue3 = db.getScalar("SELECT " + 
											Constants.FIELD_NAME + 
											" FROM " + Constants.TABLE_LOCAL + 
											" ORDER BY " + Constants.FIELD_TAXID + " ASC" +
											" LIMIT 1");
			//Canviem el seu nom
			int result3 = db.updateData("UPDATE " + 
										Constants.TABLE_LOCAL + 
										" SET " + Constants.FIELD_NAME + " = 'New Value 3' " +
											"WHERE " + Constants.FIELD_NAME + " = '" + oldValue3.toString() + "'");
			
			//Recuperem el nom un cop hem fet la modificació
			Object currentValue3 = db.getScalar("SELECT " + 
												Constants.FIELD_NAME + 
												" FROM " + Constants.TABLE_LOCAL + 
												" ORDER BY " + Constants.FIELD_TAXID  + " ASC" +
												" LIMIT 1");
			
			//Mostrem un missatge amb el valor que hi havia abans i el d'ara
			Methods.showMessage("Old value = '" + oldValue3 + "'\nCurrent value = '" + currentValue3 + "'", Enums.MessageType.Info);
			
			
			//Retornem el registre al seu valor inicial,
			//indicant "FALSE" al paràmetre del mètode
			//finishTransaction. Si s'hagués indicat
			//true, s'haurien confirmat els canvis que
			//hem fet.
			db.finishTransaction(false);
			
		} catch (STException e){
			//Fixeu-vos que no cal fer un rollback si
			//hi ha una excepció, ja que el DatabaseManager
			//ja ho farà per nosaltres...
			Managers.exception.showException(e);
		
		} catch (Exception e){
			//Fixeu-vos que no cal fer un rollback si
			//hi ha una excepció, ja que el DatabaseManager
			//ja ho farà per nosaltres...
			Managers.exception.showException(new STException(e));
		} 
	}
}
