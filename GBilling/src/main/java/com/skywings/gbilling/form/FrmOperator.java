package com.skywings.gbilling.form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.common.ControlResize;
import com.skywings.gbilling.common.CustomFonts;
import com.skywings.gbilling.common.FormAction;
import com.skywings.gbilling.common.JTextFieldComponent;
import com.skywings.gbilling.common.PasswordValidation;
import com.skywings.gbilling.logic.OperatorLogic;
import com.skywings.gbilling.model.Operator;

@Component
public class FrmOperator extends JInternalFrame implements FormAction, KeyListener, ActionListener {

	private ControlResize controlResize;
	private JPanel panelEntry;
	private JPanel panelMain;
	private JPanel panelContent;
	private JPanel panelView;
	private JPanel panelMenu;

	private JLabel lblOperId, lblOperName, lblOperPass, lblActive;
	private JTextField txtOperId, txtOperName;
	private JPasswordField txtOperPass;
	private JCheckBox chkActive;
	private JTable tblOperView;
	private JScrollPane scrPane;

	private JTextFieldComponent txtfComponent = new JTextFieldComponent();

	private Color color1 = Color.decode("#3eb489");
	private Color color2 = Color.decode("#FFFFFF");
	private Color color3 = Color.decode("#73bda2");
	private Color color4 = Color.decode("#cdcdcd");
	private Color color5 = Color.decode("#e4dedf");
	private Color color6 = Color.decode("#b43e69");
	private Color color7 = Color.decode("#ADD8E6");

	private FrmMDI frmMDI;

	@Autowired
	private OperatorLogic operatorLogic;

	public FrmOperator() {

		setBounds(-10, -110, 1930, 900);
//		pack();
		setTitle("ProductCreation");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createControls();
		createListeners();

	}

	@PostConstruct
	private void loadDetails() throws Exception {

		txtOperId.setText(operatorLogic.getOperId());
		txtOperId.setEnabled(false);
		txtOperName.setText("");
		txtOperPass.setText("");
		chkActive.setSelected(true);
		FrmMDI.btnAdd.requestFocus();

	}

	private void createListeners() {

		txtOperName.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtOperName.getText())) {
					JOptionPane.showMessageDialog(null, "OperatorName Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});
		txtOperPass.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtOperName.getText())) {
					JOptionPane.showMessageDialog(null, "Password Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});

		chkActive.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					FrmMDI.btnSave.requestFocus();
				}
			}

		});

		/*
		 * FrmMDI.btnAdd.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * txtOperName.requestFocus();
		 * 
		 * } });
		 */

		/*
		 * FrmMDI.btnClose.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * // Get the existing instance of FrmMDI from the Spring context FrmMDI
		 * currentFrmMDI =
		 * Applicationmain.getAbstractApplicationContext().getBean(FrmMDI.class);
		 * 
		 * // Check if the current instance is already visible and close it if
		 * (currentFrmMDI != null) { currentFrmMDI.dispose(); // Dispose of the current
		 * FrmMDI instance to release resources currentFrmMDI.setVisible(false); //
		 * Optionally, you can also hide it instead of disposing
		 * currentFrmMDI.frmclose(); }
		 * 
		 * // Create a fresh new instance of FrmMDI (this will be the same instance due
		 * to // Singleton) FrmMDI newFrmMDI =
		 * Applicationmain.getAbstractApplicationContext().getBean(FrmMDI.class);
		 * 
		 * // Now show the new instance (same instance due to Singleton)
		 * newFrmMDI.setVisible(true); //
		 * 
		 * } });
		 */

		/*
		 * FrmMDI.btnSave.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * Operator operator = new Operator();
		 * 
		 * operator.setOperatorId(txtOperId.getText());
		 * operator.setOperNameS(txtOperName.getText());
		 * operator.setOperPass(Validation.encrypt(txtOperPass.getText()));
		 * operator.setActive(chkActive.isSelected() ? "Y" : "N");
		 * 
		 * try { operatorLogic.saveOperator(operator); loadDetails(); } catch (Exception
		 * e1) { e1.printStackTrace(); }
		 * 
		 * JOptionPane.showMessageDialog(desktopIcon, "Saved Successfully...!");
		 * 
		 * } });
		 */

		/*
		 * FrmMDI.btnCancel.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * try {
		 * 
		 * panelView.setVisible(false); panelContent.setVisible(true);
		 * FrmMDI.btnAdd.requestFocus();
		 * 
		 * txtOperId.setText(operatorLogic.getOperId()); txtOperName.setText("");
		 * txtOperPass.setText(""); chkActive.setSelected(true);
		 * 
		 * } catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * } });
		 */

		/*
		 * FrmMDI.btnView.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * try {
		 * 
		 * panelContent.setVisible(false); panelView.setVisible(true);
		 * 
		 * view();
		 * 
		 * FrmMDI.btnSave.setEnabled(false); FrmMDI.btnCancel.setEnabled(false);
		 * FrmMDI.btnEdit.setEnabled(true); FrmMDI.btnBack.setEnabled(true);
		 * 
		 * tblOperView.requestFocus();
		 * 
		 * } catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * } });
		 */
		/*
		 * FrmMDI.btnBack.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * try { panelView.setVisible(false); panelContent.setVisible(true);
		 * loadDetails();
		 * 
		 * FrmMDI.btnSave.setEnabled(true); FrmMDI.btnCancel.setEnabled(true);
		 * FrmMDI.btnBack.setEnabled(false); FrmMDI.btnEdit.setEnabled(false);
		 * 
		 * } catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * } });
		 */

		/*
		 * FrmMDI.btnEdit.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * panelContent.setVisible(true); panelView.setVisible(false);
		 * 
		 * // try { // loadDetails(); // } catch (Exception e1) { // // TODO
		 * Auto-generated catch block // e1.printStackTrace(); // }
		 * 
		 * int selectedRow = 0; selectedRow = tblOperView.getSelectedRow(); if
		 * (selectedRow != -1) { txtOperId.setText(tblOperView.getValueAt(selectedRow,
		 * 0).toString()); txtOperName.setText(tblOperView.getValueAt(selectedRow,
		 * 1).toString()); chkActive.setSelected(tblOperView.getValueAt(selectedRow, 3)
		 * == "Yes" ? false : true);
		 * txtOperPass.setText(tblOperView.getValueAt(selectedRow, 3).toString()); }
		 * 
		 * FrmMDI.btnSave.setEnabled(true); FrmMDI.btnCancel.setEnabled(true);
		 * FrmMDI.btnBack.setEnabled(false); FrmMDI.btnEdit.setEnabled(false);
		 * 
		 * } });
		 */
	}

	protected void view() throws Exception {

		String[] columnNames = { "OperatorId", "OperatorName", "Active", "Password" };

		List<Operator> operatorList = operatorLogic.getOperator();

		Object[][] data = new Object[operatorList.size()][columnNames.length];

		for (int i = 0; i < operatorList.size(); i++) {
			Operator operator = operatorList.get(i);
			data[i][0] = operator.getOperatorId();
			data[i][1] = operator.getOperNameS();
			data[i][2] = operator.getActive() == "Y" ? "No" : "Yes";
			data[i][3] = operator.getOperPass();
		}
		;

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblOperView = new JTable(model);

		tblOperView.getColumnModel().getColumn(3).setMinWidth(0);
		tblOperView.getColumnModel().getColumn(3).setMaxWidth(0);
		tblOperView.getColumnModel().getColumn(3).setWidth(0);
		tblOperView.getColumnModel().getColumn(3).setResizable(false);

		tblOperView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblOperView.getTableHeader().setReorderingAllowed(false);
		tblOperView.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
		tblOperView.setFont(CustomFonts.fontCalibriPlain15);
		tblOperView.setForeground(Color.BLACK);
		tblOperView.getTableHeader().setForeground(color6);
		tblOperView.getTableHeader().setBackground(Color.WHITE);
		tblOperView.setRowHeight(22);

//		tblOperView.getTableHeader().setToolTipText("Table Header Tooltip");

		tblOperView.setVisible(true);
		tblOperView.addKeyListener(this);

		scrPane = new JScrollPane(tblOperView);
		scrPane.setBounds(50, 50, 700, 360);
		scrPane.getViewport().setBackground(color2);
		scrPane.setVisible(true);

		panelView.add(scrPane);

	}

	private void createControls() {

		panelMain = new JPanel();
		panelMain.setBounds(100, 200, 1930, 900);
		panelMain.setLayout(null);
		panelMain.setBackground(color2);
		panelMain.add(panelContentInitialize());
		panelMain.add(panelViewDetail());

		getContentPane().add(panelMain);

	}

	private java.awt.Component panelViewDetail() {

		panelView = new JPanel();
		panelView.setLayout(null);
		panelView.setBounds(550, 200, 800, 500);
		panelView.setBorder(BorderFactory.createEtchedBorder(color1, color1));
		panelView.setBackground(color2);
		panelView.setVisible(true);

		/*
		 * tblOperView = new JTable(); //
		 * tblOperView.setAutoResizeMode(JilabaTable.AUTO_RESIZE_OFF);
		 * tblOperView.getTableHeader().setReorderingAllowed(false);
		 * tblOperView.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
		 * tblOperView.setFont(CustomFonts.fontCalibriPlain15);
		 * tblOperView.setForeground(Color.BLACK);
		 * tblOperView.getTableHeader().setForeground(color6);
		 * tblOperView.getTableHeader().setBackground(Color.WHITE);
		 * tblOperView.setRowHeight(22); tblOperView.setVisible(true);
		 * tblOperView.addKeyListener(this);
		 */

		panelMain.add(panelView);

		return panelView;
	}

	private java.awt.Component panelContentInitialize() {

		int btnX = 0;
		int btnY = 0;
		int btnWidth = 80;
		int btnHeight = 82;
		int btnHSpace = 100;
		int btnVSpace = 130;
		int lblWidth = 150;
		int lblHeight = 50;
		int txtwidth = 180;
		int txtheight = 30;

		panelContent = new JPanel();
		panelContent.setLayout(null);
		panelContent.setBounds(550, 200, 800, 500);
		panelContent.setBorder(BorderFactory.createEtchedBorder(color1, color1));
		panelContent.setBackground(color2);
		panelContent.setVisible(true);

		lblOperId = new JLabel("Operator ID");
		lblOperId.setHorizontalAlignment(SwingConstants.LEFT);
		lblOperId.setBounds(180, 80, lblWidth, lblHeight);
		lblOperId.setFont(CustomFonts.font);
		lblOperId.setForeground(Color.BLACK);
		lblOperId.setBackground(color2);
		lblOperId.setVisible(true);

		txtOperId = new JTextField();
		txtOperId.setBounds(lblOperId.getX() + lblOperId.getWidth() + 30, lblOperId.getY() + 10, txtwidth, txtheight);
		txtOperId.setFont(CustomFonts.fontCalibriBold);
		txtOperId.setBackground(color2);
		txtOperId.setForeground(Color.BLACK);
		txtOperId.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtOperId.addKeyListener(this);

		lblOperName = new JLabel("Operator Name");
		lblOperName.setHorizontalAlignment(SwingConstants.LEFT);
		lblOperName.setBounds(lblOperId.getX(), lblOperId.getY() + lblOperId.getHeight() + 40, lblWidth, lblHeight);
		lblOperName.setFont(CustomFonts.font);
		lblOperName.setForeground(Color.BLACK);
		lblOperName.setBackground(color2);
		lblOperName.setVisible(true);

		txtOperName = new JTextField();
		txtOperName.setBounds(lblOperName.getX() + lblOperName.getWidth() + 30, lblOperName.getY() + 10, txtwidth + 80,
				txtheight);
		txtOperName.setFont(CustomFonts.fontCalibriBold);
		txtOperName.setBackground(color2);
		txtOperName.setForeground(Color.BLACK);
		txtOperName.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtOperName.addKeyListener(this);

		txtfComponent.textLength(txtOperName, 20);

		/*
		 * int maxLength = 20; PlainDocument doc = (PlainDocument)
		 * txtOperName.getDocument(); doc.setDocumentFilter(new DocumentFilter() {
		 * 
		 * @Override public void insertString(FilterBypass fb, int offset, String
		 * string, AttributeSet attr) throws BadLocationException { if
		 * (fb.getDocument().getLength() + string.length() <= maxLength) {
		 * super.insertString(fb, offset, string, attr); } }
		 * 
		 * @Override public void replace(FilterBypass fb, int offset, int length, String
		 * text, AttributeSet attrs) throws BadLocationException { if
		 * (fb.getDocument().getLength() + text.length() - length <= maxLength) {
		 * super.replace(fb, offset, length, text, attrs); } } });
		 */

		lblOperPass = new JLabel("Password");
		lblOperPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblOperPass.setBounds(lblOperName.getX(), lblOperName.getY() + lblOperName.getHeight() + 40, lblWidth,
				lblHeight);
		lblOperPass.setFont(CustomFonts.font);
		lblOperPass.setForeground(Color.BLACK);
		lblOperPass.setBackground(color2);
		lblOperPass.setVisible(true);

		txtOperPass = new JPasswordField();
		txtOperPass.setBounds(lblOperPass.getX() + lblOperPass.getWidth() + 30, lblOperPass.getY() + 10, txtwidth + 80,
				txtheight);
		txtOperPass.setFont(CustomFonts.fontCalibriBold);
		txtOperPass.setBackground(color2);
		txtOperPass.setForeground(Color.BLACK);
		txtOperPass.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtOperPass.addKeyListener(this);

		txtfComponent.textLength(txtOperPass, 10);

		lblActive = new JLabel("Active");
		lblActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblActive.setBounds(lblOperPass.getX(), lblOperPass.getY() + lblOperName.getHeight() + 40, lblWidth, lblHeight);
		lblActive.setFont(CustomFonts.font);
		lblActive.setForeground(Color.BLACK);
		lblActive.setBackground(color2);
		lblActive.setVisible(true);

		chkActive = new JCheckBox("Yes");
		chkActive.setBounds(lblActive.getX() + lblActive.getWidth() + 30, lblActive.getY(), lblWidth, lblHeight);
		chkActive.setFont(CustomFonts.font);
		chkActive.setForeground(Color.BLACK);
		chkActive.setBackground(color2);
		chkActive.setSelected(true);
		chkActive.setVisible(true);

		panelMain.add(panelContent);
		panelContent.add(lblOperId);
		panelContent.add(lblOperName);
		panelContent.add(lblOperPass);
		panelContent.add(lblActive);
		panelContent.add(txtOperId);
		panelContent.add(txtOperName);
		panelContent.add(txtOperPass);
		panelContent.add(chkActive);

		return panelContent;
	}

	@Override
	public boolean formAdd() {

		txtOperName.requestFocus();
		return true;
	}

	@Override
	public boolean formSave() {

		try {
			save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private void save() throws Exception {

		if ("".equalsIgnoreCase(txtOperName.getText())) {
			JOptionPane.showMessageDialog(null, "Operator Should Not Empty!...");
			txtOperName.requestFocus();
			return;
		}
		if ("".equalsIgnoreCase(txtOperPass.getText())) {
			JOptionPane.showMessageDialog(null, "Password Should Not Empty!...");
			txtOperName.requestFocus();
			return;
		}

		Operator operator = new Operator();

		operator.setOperatorId(Integer.parseInt(txtOperId.getText()));
		operator.setOperNameS(txtOperName.getText());
		operator.setOperPass(PasswordValidation.encript(txtOperPass.getText()));
		operator.setActive(chkActive.isSelected() ? "Y" : "N");

		try {
			operatorLogic.saveOperator(operator);
			loadDetails();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JOptionPane.showMessageDialog(desktopIcon, "Saved Successfully...!");

	}

	@Override
	public boolean formView() {

		try {
			panelContent.setVisible(false);
			panelView.setVisible(true);

			view();

			tblOperView.requestFocus();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean formEdit() {

		panelContent.setVisible(true);
		panelView.setVisible(false);

		int selectedRow = 0;
		selectedRow = tblOperView.getSelectedRow();
		if (selectedRow != -1) {
			txtOperId.setText(tblOperView.getValueAt(selectedRow, 0).toString());
			txtOperName.setText(tblOperView.getValueAt(selectedRow, 1).toString());
			chkActive.setSelected(tblOperView.getValueAt(selectedRow, 3) == "Yes" ? false : true);
			txtOperPass.setText(tblOperView.getValueAt(selectedRow, 3).toString());
		}

		return true;
	}

	@Override
	public boolean formPosting() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean formBack() {

		try {
			panelView.setVisible(false);
			panelContent.setVisible(true);
			loadDetails();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean formCancel() {

		try {

			panelView.setVisible(false);
			panelContent.setVisible(true);

			txtOperId.setText(operatorLogic.getOperId());
			txtOperName.setText("");
			txtOperPass.setText("");
			chkActive.setSelected(true);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean formClose() {

		this.dispose();
		FrmMDI formMDI;
		try {
			formMDI = new FrmMDI();
			formMDI.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (e.getSource() == txtOperId) {

				txtOperName.requestFocus();

			} else if (e.getSource() == txtOperName) {
				txtOperPass.requestFocus();

			} else if (e.getSource() == txtOperPass) {

				chkActive.requestFocus();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
