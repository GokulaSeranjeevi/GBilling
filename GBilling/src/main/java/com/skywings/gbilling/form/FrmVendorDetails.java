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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import com.skywings.gbilling.common.ListItem;
import com.skywings.gbilling.common.SkywingsComboBox;
import com.skywings.gbilling.logic.VendorLogic;
import com.skywings.gbilling.model.BalGroup;
import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.StateMaster;
import com.skywings.gbilling.model.VendorMaster;

@Component
public class FrmVendorDetails extends JInternalFrame implements FormAction, KeyListener, ActionListener {

	private ControlResize controlResize;
	private JPanel panelEntry;
	private JPanel panelMain;
	private JPanel panelContent;
	private JPanel panelView;
	private JPanel panelMenu;

	private JLabel lblAcctcode, lblAcctName, lblShortName, lblGroup, lblAddress1, lblAddress2, lblAddress3, lblPincode,
			lblPhoneno, lblMobileNo, lblGSTNo, lblEmailId, lblPanNo, lblCity, lblState, lblActive;
	private JTextField txtAcctCode, txtAcctName, txtShortName, txtAddress1, txtAddress2, txtAddress3, txtPinCode,
			txtPhoneNo, txtMobileNo, txtGSTNo, txtEmailId, txtPanNo;
	private JCheckBox chkActive;
	private SkywingsComboBox<String> cmbGroup, cmbCity, cmbState;
	private JTable tblVendorView;
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

	private List<StateMaster> lstStateMaster;
	private List<BalGroup> lstGroup;
	private List<CityMaster> lstcityMaster;

	@Autowired
	private VendorLogic vendorLogic;

	public FrmVendorDetails() {

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

		txtAcctCode.setText(vendorLogic.getAcctcode());

		cmbGroup.removeAllItems();
		cmbCity.removeAllItems();
		cmbState.removeAllItems();

		lstStateMaster = vendorLogic.getState();

		for (StateMaster state : lstStateMaster) {

			String statemaster = state.getDesc();

			cmbState.addListItem(new ListItem(state.getDesc()));

		}

		lstGroup = vendorLogic.getGroup();

		for (BalGroup grp : lstGroup) {

			String desc = grp.getGroupName();

			cmbGroup.addListItem(new ListItem(grp.getGroupName()));

		}

		lstcityMaster = vendorLogic.getCity();

		for (CityMaster city : lstcityMaster) {

			String desc = city.getDesc();

			cmbCity.addListItem(new ListItem(desc));

		}

		txtAcctCode.setEnabled(false);
		txtAcctName.setText("");
		txtShortName.setText("");
		txtAddress1.setText("");
		txtAddress2.setText("");
		txtAddress3.setText("");
		txtPinCode.setText("");
		txtPhoneNo.setText("");
		txtMobileNo.setText("");
		txtEmailId.setText("");
		txtGSTNo.setText("");
		txtPanNo.setText("");
		txtPinCode.setText("");
		chkActive.setSelected(true);
		FrmMDI.btnAdd.requestFocus();

	}

	private void createListeners() {

		cmbGroup.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					txtShortName.requestFocus();

				}

				super.keyPressed(e);
			}
		});

		cmbState.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					chkActive.requestFocus();
				}

				super.keyPressed(e);
			}
		});

		cmbCity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					cmbState.requestFocus();
				}

				super.keyPressed(e);
			}
		});

		txtAcctName.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtAcctName.getText())) {
					JOptionPane.showMessageDialog(null, "AcctName Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});
		txtShortName.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtShortName.getText())) {
					JOptionPane.showMessageDialog(null, "ShortName Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});
		txtPinCode.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtPinCode.getText())) {
					JOptionPane.showMessageDialog(null, "Pincode Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});
		txtMobileNo.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtMobileNo.getText())) {
					JOptionPane.showMessageDialog(null, "MobileNo Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});

		txtGSTNo.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtGSTNo.getText())) {
					JOptionPane.showMessageDialog(null, "GSTNo Shouldn't Empty!...");
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

	}

//	protected void view() throws Exception {
//
//		String[] columnNames = { "OperatorId", "OperatorName", "Active", "Password" };
//
//		List<Operator> operatorList = operatorLogic.getOperator();
//
//		Object[][] data = new Object[operatorList.size()][columnNames.length];
//
//		for (int i = 0; i < operatorList.size(); i++) {
//			Operator operator = operatorList.get(i);
//			data[i][0] = operator.getOperatorId();
//			data[i][1] = operator.getOperNameS();
//			data[i][2] = operator.getActive() == "Y" ? "No" : "Yes";
//			data[i][3] = operator.getOperPass();
//		}
//		;
//
//		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		};
//		tblOperView = new JTable(model);
//
//		tblOperView.getColumnModel().getColumn(3).setMinWidth(0);
//		tblOperView.getColumnModel().getColumn(3).setMaxWidth(0);
//		tblOperView.getColumnModel().getColumn(3).setWidth(0);
//		tblOperView.getColumnModel().getColumn(3).setResizable(false);
//
//		tblOperView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		tblOperView.getTableHeader().setReorderingAllowed(false);
//		tblOperView.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
//		tblOperView.setFont(CustomFonts.fontCalibriPlain15);
//		tblOperView.setForeground(Color.BLACK);
//		tblOperView.getTableHeader().setForeground(color6);
//		tblOperView.getTableHeader().setBackground(Color.WHITE);
//		tblOperView.setRowHeight(22);
//
////		tblOperView.getTableHeader().setToolTipText("Table Header Tooltip");
//
//		tblOperView.setVisible(true);
//		tblOperView.addKeyListener(this);
//
//		scrPane = new JScrollPane(tblOperView);
//		scrPane.setBounds(50, 50, 700, 360);
//		scrPane.getViewport().setBackground(color2);
//		scrPane.setVisible(true);
//
//		panelView.add(scrPane);
//
//	}

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
		panelView.setBounds(200, 100, 1500, 700);
		panelView.setBorder(BorderFactory.createEtchedBorder(color1, color1));
		panelView.setBackground(color2);
		panelView.setVisible(true);

		panelMain.add(panelView);

		return panelView;
	}

	private java.awt.Component panelContentInitialize() {

		int lblWidth = 150;
		int lblHeight = 50;
		int txtwidth = 180;
		int txtheight = 30;

		panelContent = new JPanel();
		panelContent.setLayout(null);
		panelContent.setBounds(200, 110, 1500, 700);
		panelContent.setBorder(BorderFactory.createEtchedBorder(color1, color1));
		panelContent.setBackground(color2);
		panelContent.setVisible(true);

		lblAcctcode = new JLabel("VendorCode");
		lblAcctcode.setHorizontalAlignment(SwingConstants.LEFT);
		lblAcctcode.setBounds(130, 10, lblWidth, lblHeight);
		lblAcctcode.setFont(CustomFonts.font);
		lblAcctcode.setForeground(Color.BLACK);
		lblAcctcode.setBackground(color2);
		lblAcctcode.setVisible(true);

		txtAcctCode = new JTextField();
		txtAcctCode.setBounds(lblAcctcode.getX() + lblAcctcode.getWidth() + 30, lblAcctcode.getY() + 10, txtwidth,
				txtheight);
		txtAcctCode.setFont(CustomFonts.fontCalibriBold);
		txtAcctCode.setBackground(color2);
		txtAcctCode.setForeground(Color.BLACK);
		txtAcctCode.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtAcctCode.addKeyListener(this);

		lblAcctName = new JLabel("Vendor Name");
		lblAcctName.setHorizontalAlignment(SwingConstants.LEFT);
		lblAcctName.setBounds(lblAcctcode.getX(), lblAcctcode.getY() + lblAcctcode.getHeight() + 40, lblWidth,
				lblHeight);
		lblAcctName.setFont(CustomFonts.font);
		lblAcctName.setForeground(Color.BLACK);
		lblAcctName.setBackground(color2);
		lblAcctName.setVisible(true);

		txtAcctName = new JTextField();
		txtAcctName.setBounds(lblAcctName.getX() + lblAcctName.getWidth() + 30, lblAcctName.getY() + 10, txtwidth + 80,
				txtheight);
		txtAcctName.setFont(CustomFonts.fontCalibriBold);
		txtAcctName.setBackground(color2);
		txtAcctName.setForeground(Color.BLACK);
		txtAcctName.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtAcctName.addKeyListener(this);

		txtfComponent.textLength(txtAcctName, 20);

		lblGroup = new JLabel("AccGroup");
		lblGroup.setHorizontalAlignment(SwingConstants.LEFT);
		lblGroup.setBounds(lblAcctName.getX(), lblAcctName.getY() + lblAcctName.getHeight() + 40, lblWidth, lblHeight);
		lblGroup.setFont(CustomFonts.font);
		lblGroup.setForeground(Color.BLACK);
		lblGroup.setBackground(color2);
		lblGroup.setVisible(true);

		cmbGroup = new SkywingsComboBox<String>();
		cmbGroup.setBounds(lblGroup.getX() + lblGroup.getWidth() + 30, lblGroup.getY() + 10, txtwidth + 80, txtheight);
		cmbGroup.setFont(CustomFonts.font);
		cmbGroup.setForeground(Color.BLACK);
		cmbGroup.setBackground(color2);
		cmbGroup.setVisible(true);

		lblShortName = new JLabel("ShortName");
		lblShortName.setHorizontalAlignment(SwingConstants.LEFT);
		lblShortName.setBounds(lblGroup.getX(), lblGroup.getY() + lblGroup.getHeight() + 40, lblWidth, lblHeight);
		lblShortName.setFont(CustomFonts.font);
		lblShortName.setForeground(Color.BLACK);
		lblShortName.setBackground(color2);
		lblShortName.setVisible(true);

		txtShortName = new JTextField();
		txtShortName.setBounds(lblShortName.getX() + lblShortName.getWidth() + 30, lblShortName.getY() + 10,
				txtwidth + 80, txtheight);
		txtShortName.setFont(CustomFonts.fontCalibriBold);
		txtShortName.setBackground(color2);
		txtShortName.setForeground(Color.BLACK);
		txtShortName.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtShortName.addKeyListener(this);

		txtfComponent.textLength(txtShortName, 10);

		lblAddress1 = new JLabel("Address 1");
		lblAddress1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress1.setBounds(lblShortName.getX(), lblShortName.getY() + lblShortName.getHeight() + 40, lblWidth,
				lblHeight);
		lblAddress1.setFont(CustomFonts.font);
		lblAddress1.setForeground(Color.BLACK);
		lblAddress1.setBackground(color2);
		lblAddress1.setVisible(true);

		txtAddress1 = new JTextField();
		txtAddress1.setBounds(lblAddress1.getX() + lblAddress1.getWidth() + 30, lblAddress1.getY() + 10, txtwidth + 80,
				txtheight);
		txtAddress1.setFont(CustomFonts.fontCalibriBold);
		txtAddress1.setBackground(color2);
		txtAddress1.setForeground(Color.BLACK);
		txtAddress1.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtAddress1.addKeyListener(this);

		txtfComponent.textLength(txtAddress1, 50);

		lblAddress2 = new JLabel("Address2");
		lblAddress2.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress2.setBounds(lblAddress1.getX(), lblAddress1.getY() + lblAddress1.getHeight() + 40, lblWidth,
				lblHeight);
		lblAddress2.setFont(CustomFonts.font);
		lblAddress2.setForeground(Color.BLACK);
		lblAddress2.setBackground(color2);
		lblAddress2.setVisible(true);

		txtAddress2 = new JTextField();
		txtAddress2.setBounds(lblAddress2.getX() + lblAddress2.getWidth() + 30, lblAddress2.getY() + 10, txtwidth + 80,
				txtheight);
		txtAddress2.setFont(CustomFonts.fontCalibriBold);
		txtAddress2.setBackground(color2);
		txtAddress2.setForeground(Color.BLACK);
		txtAddress2.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtAddress2.addKeyListener(this);

		txtfComponent.textLength(txtAddress2, 50);

		lblAddress3 = new JLabel("Address 3");
		lblAddress3.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress3.setBounds(lblAddress2.getX(), lblAddress2.getY() + lblAddress2.getHeight() + 40, lblWidth,
				lblHeight);
		lblAddress3.setFont(CustomFonts.font);
		lblAddress3.setForeground(Color.BLACK);
		lblAddress3.setBackground(color2);
		lblAddress3.setVisible(true);

//		lblAddress3 = new JLabel("Address 3");
//		lblAddress3.setHorizontalAlignment(SwingConstants.LEFT);
//		lblAddress3.setBounds(txtAcctCode.getX() + txtAcctCode.getWidth() + 350, lblAcctcode.getY(), lblWidth,
//				lblHeight);
//		lblAddress3.setFont(CustomFonts.font);
//		lblAddress3.setForeground(Color.BLACK);
//		lblAddress3.setBackground(color2);
//		lblAddress3.setVisible(true);

		txtAddress3 = new JTextField();
		txtAddress3.setBounds(lblAddress3.getX() + lblAddress3.getWidth() + 30, lblAddress3.getY() + 10, txtwidth + 80,
				txtheight);
		txtAddress3.setFont(CustomFonts.fontCalibriBold);
		txtAddress3.setBackground(color2);
		txtAddress3.setForeground(Color.BLACK);
		txtAddress3.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtAddress3.addKeyListener(this);

		txtfComponent.textLength(txtAddress3, 50);

		lblPincode = new JLabel("Pincode");
		lblPincode.setHorizontalAlignment(SwingConstants.LEFT);
		lblPincode.setBounds(lblAddress3.getX(), lblAddress3.getY() + lblAddress3.getHeight() + 40, lblWidth,
				lblHeight);
		lblPincode.setFont(CustomFonts.font);
		lblPincode.setForeground(Color.BLACK);
		lblPincode.setBackground(color2);
		lblPincode.setVisible(true);

		txtPinCode = new JTextField();
		txtPinCode.setBounds(lblPincode.getX() + lblPincode.getWidth() + 30, lblPincode.getY() + 10, txtwidth + 80,
				txtheight);
		txtPinCode.setFont(CustomFonts.fontCalibriBold);
		txtPinCode.setBackground(color2);
		txtPinCode.setForeground(Color.BLACK);
		txtAddress3.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtPinCode.addKeyListener(this);

		txtfComponent.textLength(txtPinCode, 6);

		lblPhoneno = new JLabel("PhoneNo");
		lblPhoneno.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneno.setBounds(txtAcctCode.getX() + txtAcctCode.getWidth() + 350, lblAcctcode.getY(), lblWidth,
				lblHeight);
		lblPhoneno.setFont(CustomFonts.font);
		lblPhoneno.setForeground(Color.BLACK);
		lblPhoneno.setBackground(color2);
		lblPhoneno.setVisible(true);

		txtPhoneNo = new JTextField();
		txtPhoneNo.setBounds(lblPhoneno.getX() + lblPhoneno.getWidth() + 30, lblPhoneno.getY() + 10, txtwidth + 80,
				txtheight);
		txtPhoneNo.setFont(CustomFonts.fontCalibriBold);
		txtPhoneNo.setBackground(color2);
		txtPhoneNo.setForeground(Color.BLACK);
		txtPhoneNo.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtPhoneNo.addKeyListener(this);

		txtfComponent.textLength(txtPhoneNo, 50);

		lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobileNo.setBounds(lblPhoneno.getX(), lblPhoneno.getY() + lblPhoneno.getHeight() + 40, lblWidth, lblHeight);
		lblMobileNo.setFont(CustomFonts.font);
		lblMobileNo.setForeground(Color.BLACK);
		lblMobileNo.setBackground(color2);
		lblMobileNo.setVisible(true);

		txtMobileNo = new JTextField();
		txtMobileNo.setBounds(lblMobileNo.getX() + lblMobileNo.getWidth() + 30, lblMobileNo.getY() + 10, txtwidth + 80,
				txtheight);
		txtMobileNo.setFont(CustomFonts.fontCalibriBold);
		txtMobileNo.setBackground(color2);
		txtMobileNo.setForeground(Color.BLACK);
		txtMobileNo.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtMobileNo.addKeyListener(this);

		txtfComponent.textLength(txtMobileNo, 10);

		lblEmailId = new JLabel("Email ID");
		lblEmailId.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmailId.setBounds(lblMobileNo.getX(), lblMobileNo.getY() + lblMobileNo.getHeight() + 40, lblWidth,
				lblHeight);
		lblEmailId.setFont(CustomFonts.font);
		lblEmailId.setForeground(Color.BLACK);
		lblEmailId.setBackground(color2);
		lblEmailId.setVisible(true);

		txtEmailId = new JTextField();
		txtEmailId.setBounds(lblEmailId.getX() + lblEmailId.getWidth() + 30, lblEmailId.getY() + 10, txtwidth + 80,
				txtheight);
		txtEmailId.setFont(CustomFonts.fontCalibriBold);
		txtEmailId.setBackground(color2);
		txtEmailId.setForeground(Color.BLACK);
		txtEmailId.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtEmailId.addKeyListener(this);

		txtfComponent.textLength(txtEmailId, 50);

//		cmbSaleUnit = new JComboBox<>();
//		cmbSaleUnit.setBounds(lblMobileNo.getX() + lblMobileNo.getWidth() + 30, lblMobileNo.getY() + 10, txtwidth + 20,
//				txtheight);
//		cmbSaleUnit.setFont(CustomFonts.font);
//		cmbSaleUnit.setForeground(Color.BLACK);
//		cmbSaleUnit.setBackground(color2);
//		cmbSaleUnit.setVisible(true);

		lblGSTNo = new JLabel("GSTNo");
		lblGSTNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblGSTNo.setBounds(lblEmailId.getX(), lblEmailId.getY() + lblEmailId.getHeight() + 40, lblWidth, lblHeight);
		lblGSTNo.setFont(CustomFonts.font);
		lblGSTNo.setForeground(Color.BLACK);
		lblGSTNo.setBackground(color2);
		lblGSTNo.setVisible(true);

		txtGSTNo = new JTextField();
		txtGSTNo.setBounds(lblGSTNo.getX() + lblGSTNo.getWidth() + 30, lblGSTNo.getY() + 10, txtwidth + 80, txtheight);
		txtGSTNo.setFont(CustomFonts.fontCalibriBold);
		txtGSTNo.setBackground(color2);
		txtGSTNo.setForeground(Color.BLACK);
		txtGSTNo.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtGSTNo.addKeyListener(this);

		txtfComponent.textLength(txtGSTNo, 50);

		lblPanNo = new JLabel("PANNo");
		lblPanNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPanNo.setBounds(lblGSTNo.getX(), lblGSTNo.getY() + lblGSTNo.getHeight() + 40, lblWidth, lblHeight);
		lblPanNo.setFont(CustomFonts.font);
		lblPanNo.setForeground(Color.BLACK);
		lblPanNo.setBackground(color2);
		lblPanNo.setVisible(true);

		txtPanNo = new JTextField();
		txtPanNo.setBounds(lblPanNo.getX() + lblPanNo.getWidth() + 30, lblPanNo.getY() + 10, txtwidth + 80, txtheight);
		txtPanNo.setFont(CustomFonts.fontCalibriBold);
		txtPanNo.setBackground(color2);
		txtPanNo.setForeground(Color.BLACK);
		txtPanNo.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtPanNo.addKeyListener(this);

		txtfComponent.textLength(txtPanNo, 50);

		lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setBounds(lblPanNo.getX(), lblPanNo.getY() + lblPanNo.getHeight() + 40, lblWidth, lblHeight);
		lblCity.setFont(CustomFonts.font);
		lblCity.setForeground(Color.BLACK);
		lblCity.setBackground(color2);
		lblCity.setVisible(true);

		cmbCity = new SkywingsComboBox<String>();
		cmbCity.setBounds(lblCity.getX() + lblCity.getWidth() + 30, lblCity.getY() + 10, txtwidth + 20, txtheight);
		cmbCity.setFont(CustomFonts.font);
		cmbCity.setForeground(Color.BLACK);
		cmbCity.setBackground(color2);
		cmbCity.setVisible(true);

		lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.LEFT);
		lblState.setBounds(lblCity.getX(), lblCity.getY() + lblCity.getHeight() + 40, lblWidth, lblHeight);
		lblState.setFont(CustomFonts.font);
		lblState.setForeground(Color.BLACK);
		lblState.setBackground(color2);
		lblState.setVisible(true);

		cmbState = new SkywingsComboBox<String>();
		cmbState.setBounds(lblState.getX() + lblState.getWidth() + 30, lblState.getY() + 10, txtwidth + 20, txtheight);
		cmbState.setFont(CustomFonts.font);
		cmbState.setForeground(Color.BLACK);
		cmbState.setBackground(color2);
		cmbState.setVisible(true);

		lblActive = new JLabel("Active");
		lblActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblActive.setBounds(lblState.getX(), lblState.getY() + lblState.getHeight() + 40, lblWidth, lblHeight);
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
		panelContent.add(lblAcctcode);
		panelContent.add(lblAcctName);
		panelContent.add(lblGroup);
		panelContent.add(lblShortName);
		panelContent.add(lblActive);
		panelContent.add(txtAcctCode);
		panelContent.add(txtAcctName);
		panelContent.add(txtShortName);
		panelContent.add(chkActive);
		panelContent.add(lblAddress1);
		panelContent.add(cmbGroup);
		panelContent.add(lblAddress3);
		panelContent.add(txtAddress1);
		panelContent.add(lblAddress2);
		panelContent.add(lblEmailId);
		panelContent.add(txtAddress2);
		panelContent.add(txtAddress3);
		panelContent.add(txtPhoneNo);
		panelContent.add(txtPinCode);
		panelContent.add(txtMobileNo);
		panelContent.add(txtEmailId);
		panelContent.add(txtGSTNo);
		panelContent.add(lblPincode);
		panelContent.add(lblPanNo);
		panelContent.add(txtPanNo);
//		panelContent.add(chkIncGst);
		panelContent.add(lblPhoneno);
		panelContent.add(lblState);
		panelContent.add(cmbState);
		panelContent.add(lblMobileNo);
//		panelContent.add(cmbSaleUnit);
		panelContent.add(lblGSTNo);
		panelContent.add(lblCity);
		panelContent.add(cmbCity);

		return panelContent;
	}

	@Override
	public boolean formAdd() {

		txtAcctName.requestFocus();
		return true;
	}

	@Override
	public boolean formSave() {

		save();

		return true;
	}

	private void save() {

		if ("".equalsIgnoreCase(txtAcctName.getText())) {
			JOptionPane.showMessageDialog(null, "AcctName Should Not Empty!...");
			txtAcctName.requestFocus();
			return;
		}
		if ("".equalsIgnoreCase(txtShortName.getText())) {
			JOptionPane.showMessageDialog(null, "ShortName Should Not Empty!...");
			txtShortName.requestFocus();
			return;
		}
		if ("".equalsIgnoreCase(txtMobileNo.getText())) {
			JOptionPane.showMessageDialog(null, "MobileNo Should Not Empty!...");
			txtMobileNo.requestFocus();
			return;
		}
		if ("".equalsIgnoreCase(txtGSTNo.getText())) {
			JOptionPane.showMessageDialog(null, "GSTNo Should Not Empty!...");
			txtGSTNo.requestFocus();
			return;
		}

		VendorMaster vendor = new VendorMaster();

		vendor.setAcctCode(txtAcctCode.getText());
		vendor.setAcctName(txtAcctName.getText());
		vendor.setGroupCode("105");
		vendor.setShortName(txtShortName.getText());
		vendor.setAddress1(txtAddress1.getText());
		vendor.setAddress2(txtAddress2.getText());
		vendor.setAddress3(txtAddress3.getText());
		vendor.setPincode(txtPinCode.getText());
		vendor.setPhone(txtPhoneNo.getText());
		vendor.setMobileNo(txtMobileNo.getText());
		vendor.setEmailId(txtEmailId.getText());
		vendor.setGSTNo(txtGSTNo.getText());
		vendor.setPANNo(txtPanNo.getText());
		vendor.setCityCode("1");
		vendor.setStateCode("33");
		vendor.setActive(chkActive.isSelected() ? "Y" : "N");

		try {
			vendorLogic.saveVendor(vendor);
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

			tblVendorView.requestFocus();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}

	private void view() throws Exception {

		String[] columnNames = { "AcctCode", "AcctName", "ShortName", "GroupName", "Address1", "Address2", "Address3",
				"PinCode", "PnoneNo", "MobileNo", "EmailId", "GstNo", "PanNo", "City", "State" };

		List<VendorMaster> vendorList = vendorLogic.getVendor();

		Object[][] data = new Object[vendorList.size()][columnNames.length];

		for (int i = 0; i < vendorList.size(); i++) {
			VendorMaster vendorMaster = vendorList.get(i);
			data[i][0] = vendorMaster.getAcctCode();
			data[i][1] = vendorMaster.getAcctName();
			data[i][2] = vendorMaster.getShortName();
			data[i][3] = vendorMaster.getAddress1();
			data[i][4] = vendorMaster.getAddress2();
			data[i][5] = vendorMaster.getAddress3();
			data[i][6] = vendorMaster.getPincode();
			data[i][7] = vendorMaster.getPhone();
			data[i][8] = vendorMaster.getMobileNo();
			data[i][9] = vendorMaster.getEmailId();
			data[i][10] = vendorMaster.getGSTNo();
			data[i][11] = vendorMaster.getPANNo();
			data[i][12] = vendorMaster.getCity();
			data[i][13] = vendorMaster.getState();
		}
		;

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblVendorView = new JTable(model);

		tblVendorView.getColumnModel().getColumn(3).setMinWidth(0);
		tblVendorView.getColumnModel().getColumn(3).setMaxWidth(0);
		tblVendorView.getColumnModel().getColumn(3).setWidth(0);
		tblVendorView.getColumnModel().getColumn(3).setResizable(false);

		tblVendorView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblVendorView.getTableHeader().setReorderingAllowed(false);
		tblVendorView.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
		tblVendorView.setFont(CustomFonts.fontCalibriPlain15);
		tblVendorView.setForeground(Color.BLACK);
		tblVendorView.getTableHeader().setForeground(color6);
		tblVendorView.getTableHeader().setBackground(Color.WHITE);
		tblVendorView.setRowHeight(22);

//		tblOperView.getTableHeader().setToolTipText("Table Header Tooltip");

		tblVendorView.setVisible(true);
		tblVendorView.addKeyListener(this);

		scrPane = new JScrollPane(tblVendorView);
		scrPane.setBounds(50, 50, 1400, 600);
		scrPane.getViewport().setBackground(color2);
		scrPane.setVisible(true);

		panelView.add(scrPane);

	}

	@Override
	public boolean formEdit() {

		panelContent.setVisible(true);
		panelView.setVisible(false);

		int selectedRow = 0;
		selectedRow = tblVendorView.getSelectedRow();
		if (selectedRow != -1) {
			txtAcctCode.setText(tblVendorView.getValueAt(selectedRow, 0).toString());
			txtAcctName.setText(tblVendorView.getValueAt(selectedRow, 1).toString());
			chkActive.setSelected(tblVendorView.getValueAt(selectedRow, 3) == "Yes" ? false : true);
			txtShortName.setText(tblVendorView.getValueAt(selectedRow, 3).toString());
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

			txtAcctCode.setText(vendorLogic.getAcctcode());
			txtAcctName.setText("");
			txtShortName.setText("");
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

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (e.getSource() == txtAcctCode) {
				txtAcctName.requestFocus();
			} else if (e.getSource() == txtAcctName) {
				cmbGroup.requestFocus();
			} else if (e.getSource() == txtShortName) {
				txtAddress1.requestFocus();
			} else if (e.getSource() == txtAddress1) {
				txtAddress2.requestFocus();
			} else if (e.getSource() == txtAddress2) {
				txtAddress3.requestFocus();
			} else if (e.getSource() == txtAddress3) {
				txtPinCode.requestFocus();
			} else if (e.getSource() == txtPinCode) {
				txtPhoneNo.requestFocus();
			} else if (e.getSource() == txtPhoneNo) {
				txtMobileNo.requestFocus();
			} else if (e.getSource() == txtMobileNo) {
				txtEmailId.requestFocus();
			} else if (e.getSource() == txtEmailId) {
				txtGSTNo.requestFocus();
			} else if (e.getSource() == txtGSTNo) {
				txtPanNo.requestFocus();
			} else if (e.getSource() == txtPanNo) {
				cmbCity.requestFocus();
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
