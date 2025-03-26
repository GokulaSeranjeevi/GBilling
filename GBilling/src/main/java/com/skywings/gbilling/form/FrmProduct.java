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
import com.skywings.gbilling.logic.ProductLogic;
import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.TaxMaster;

@Component
public class FrmProduct extends JInternalFrame implements FormAction, KeyListener, ActionListener {

	private ControlResize controlResize;
	private JPanel panelEntry;
	private JPanel panelMain;
	private JPanel panelContent;
	private JPanel panelView;
	private JPanel panelMenu;

	private JLabel lblProcode, lblProName, lblShortName, lblGst, lblSGST, lblCGST, lblIncGst, lblPurUnit, lblSaleunit,
			lblHsnCode, lblActive;
	private JTextField txtProCode, txtProName, txtShortName, txtCgst, txtsgst;
	private JCheckBox chkActive, chkIncGst;
	private SkywingsComboBox<String> cmbTaxPer, cmbHsnCode, cmbPurUnit, cmbSaleUnit;
	private JTable tblProductView;
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

	private List<Product> product;
	private List<TaxMaster> lsttaxMaster;
	private List<MasterCommon> lstUnit;
	private List<MasterCommon> lstHSN;

	@Autowired
	private ProductLogic productLogic;

	public FrmProduct() {

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

		txtProCode.setText(productLogic.getProCode());

		cmbTaxPer.removeAllItems();
		cmbHsnCode.removeAllItems();
		cmbPurUnit.removeAllItems();
		cmbSaleUnit.removeAllItems();

		lsttaxMaster = productLogic.getGst();

		for (TaxMaster tax : lsttaxMaster) {

//			String taxName = tax.getTaxName();
//			double taxcode = tax.getTaxPer();

			cmbTaxPer.addListItem(new ListItem(tax.getTaxName(), tax.getTaxPer()));

		}

		lstUnit = productLogic.getUnit();

		for (MasterCommon common : lstUnit) {

			cmbSaleUnit.addListItem(new ListItem(common.getDescription()));
			cmbPurUnit.addListItem(new ListItem(common.getDescription()));

		}

		lstHSN = productLogic.getHsn();

		for (MasterCommon hsn : lstHSN) {

			cmbHsnCode.addListItem(new ListItem(hsn.getHSNCode()));

		}

		txtProCode.setEnabled(false);
		txtProName.setText("");
		txtShortName.setText("");
		chkActive.setSelected(true);
		FrmMDI.btnAdd.requestFocus();

	}

	private void createListeners() {

		cmbTaxPer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					chkIncGst.requestFocus();

					String cgst = String.valueOf(cmbTaxPer.getSelectedItem());

					txtCgst.setText(String.valueOf(Double.parseDouble(cgst) / 2));
					txtsgst.setText(String.valueOf(Double.parseDouble(cgst) / 2));

				}

				super.keyPressed(e);
			}
		});
		chkIncGst.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					cmbPurUnit.requestFocus();
				}

				super.keyPressed(e);
			}
		});
		cmbPurUnit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					cmbSaleUnit.requestFocus();
				}

				super.keyPressed(e);
			}
		});
		cmbSaleUnit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					cmbHsnCode.requestFocus();
				}

				super.keyPressed(e);
			}
		});
		cmbHsnCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					chkActive.requestFocus();
				}

				super.keyPressed(e);
			}
		});

		cmbTaxPer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					chkIncGst.requestFocus();
				}

				super.keyPressed(e);
			}
		});

		txtProName.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtProName.getText())) {
					JOptionPane.showMessageDialog(null, "OperatorName Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});
		txtShortName.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtProName.getText())) {
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
		panelView.setBounds(550, 200, 800, 500);
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
		panelContent.setBounds(300, 160, 1300, 600);
		panelContent.setBorder(BorderFactory.createEtchedBorder(color1, color1));
		panelContent.setBackground(color2);
		panelContent.setVisible(true);

		lblProcode = new JLabel("Product Code");
		lblProcode.setHorizontalAlignment(SwingConstants.LEFT);
		lblProcode.setBounds(100, 80, lblWidth, lblHeight);
		lblProcode.setFont(CustomFonts.font);
		lblProcode.setForeground(Color.BLACK);
		lblProcode.setBackground(color2);
		lblProcode.setVisible(true);

		txtProCode = new JTextField();
		txtProCode.setBounds(lblProcode.getX() + lblProcode.getWidth() + 30, lblProcode.getY() + 10, txtwidth,
				txtheight);
		txtProCode.setFont(CustomFonts.fontCalibriBold);
		txtProCode.setBackground(color2);
		txtProCode.setForeground(Color.BLACK);
		txtProCode.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtProCode.addKeyListener(this);

		lblProName = new JLabel("Product Name");
		lblProName.setHorizontalAlignment(SwingConstants.LEFT);
		lblProName.setBounds(lblProcode.getX(), lblProcode.getY() + lblProcode.getHeight() + 40, lblWidth, lblHeight);
		lblProName.setFont(CustomFonts.font);
		lblProName.setForeground(Color.BLACK);
		lblProName.setBackground(color2);
		lblProName.setVisible(true);

		txtProName = new JTextField();
		txtProName.setBounds(lblProName.getX() + lblProName.getWidth() + 30, lblProName.getY() + 10, txtwidth + 80,
				txtheight);
		txtProName.setFont(CustomFonts.fontCalibriBold);
		txtProName.setBackground(color2);
		txtProName.setForeground(Color.BLACK);
		txtProName.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtProName.addKeyListener(this);

		txtfComponent.textLength(txtProName, 20);

		lblShortName = new JLabel("ShortName ");
		lblShortName.setHorizontalAlignment(SwingConstants.LEFT);
		lblShortName.setBounds(lblProName.getX(), lblProName.getY() + lblProName.getHeight() + 40, lblWidth, lblHeight);
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

		lblGst = new JLabel("GST Per(%)");
		lblGst.setHorizontalAlignment(SwingConstants.LEFT);
		lblGst.setBounds(lblShortName.getX(), lblShortName.getY() + lblShortName.getHeight() + 40, lblWidth, lblHeight);
		lblGst.setFont(CustomFonts.font);
		lblGst.setForeground(Color.BLACK);
		lblGst.setBackground(color2);
		lblGst.setVisible(true);

		cmbTaxPer = new SkywingsComboBox<String>();
		cmbTaxPer.setBounds(lblGst.getX() + lblGst.getWidth() + 30, lblGst.getY() + 10, txtwidth + 80, txtheight);
		cmbTaxPer.setFont(CustomFonts.font);
		cmbTaxPer.setForeground(Color.BLACK);
		cmbTaxPer.setBackground(color2);
		cmbTaxPer.setVisible(true);

		lblCGST = new JLabel("CGST (%)");
		lblCGST.setHorizontalAlignment(SwingConstants.LEFT);
		lblCGST.setBounds(lblGst.getX(), lblGst.getY() + lblGst.getHeight() + 40, lblWidth, lblHeight);
		lblCGST.setFont(CustomFonts.font);
		lblCGST.setForeground(Color.BLACK);
		lblCGST.setBackground(color2);
		lblCGST.setVisible(true);

		txtCgst = new JTextField();
		txtCgst.setBounds(lblCGST.getX() + lblCGST.getWidth() + 30, lblCGST.getY() + 10, txtwidth / 2, txtheight);
		txtCgst.setFont(CustomFonts.fontCalibriBold);
		txtCgst.setBackground(color2);
		txtCgst.setForeground(Color.BLACK);
		txtCgst.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtCgst.addKeyListener(this);

		txtfComponent.textLength(txtCgst, 10);

		lblSGST = new JLabel("SGST (%)");
		lblSGST.setHorizontalAlignment(SwingConstants.LEFT);
		lblSGST.setBounds(txtCgst.getX() + txtCgst.getWidth() + 30, lblCGST.getY(), lblWidth, lblHeight);
		lblSGST.setFont(CustomFonts.font);
		lblSGST.setForeground(Color.BLACK);
		lblSGST.setBackground(color2);
		lblSGST.setVisible(true);

		txtsgst = new JTextField();
		txtsgst.setBounds(lblSGST.getX() + lblSGST.getWidth(), lblSGST.getY() + 10, txtwidth / 2, txtheight);
		txtsgst.setFont(CustomFonts.fontCalibriBold);
		txtsgst.setBackground(color2);
		txtsgst.setForeground(Color.BLACK);
		txtsgst.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtsgst.addKeyListener(this);

		txtfComponent.textLength(txtsgst, 10);

		lblIncGst = new JLabel("Inclusive GST");
		lblIncGst.setHorizontalAlignment(SwingConstants.LEFT);
		lblIncGst.setBounds(txtProCode.getX() + txtProCode.getWidth() + 350, lblProcode.getY(), lblWidth, lblHeight);
		lblIncGst.setFont(CustomFonts.font);
		lblIncGst.setForeground(Color.BLACK);
		lblIncGst.setBackground(color2);
		lblIncGst.setVisible(true);

		chkIncGst = new JCheckBox("Yes");
		chkIncGst.setBounds(lblIncGst.getX() + lblIncGst.getWidth() + 30, lblIncGst.getY(), lblWidth, lblHeight);
		chkIncGst.setFont(CustomFonts.font);
		chkIncGst.setForeground(Color.BLACK);
		chkIncGst.setBackground(color2);
		chkIncGst.setSelected(true);
		chkIncGst.setVisible(true);

		lblPurUnit = new JLabel("Purchase Unit");
		lblPurUnit.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurUnit.setBounds(lblIncGst.getX(), lblIncGst.getY() + lblIncGst.getHeight() + 40, lblWidth, lblHeight);
		lblPurUnit.setFont(CustomFonts.font);
		lblPurUnit.setForeground(Color.BLACK);
		lblPurUnit.setBackground(color2);
		lblPurUnit.setVisible(true);

		cmbPurUnit = new SkywingsComboBox<String>();
		cmbPurUnit.setBounds(lblPurUnit.getX() + lblPurUnit.getWidth() + 30, lblPurUnit.getY() + 10, txtwidth + 20,
				txtheight);
		cmbPurUnit.setFont(CustomFonts.font);
		cmbPurUnit.setForeground(Color.BLACK);
		cmbPurUnit.setBackground(color2);
		cmbPurUnit.setVisible(true);

		lblSaleunit = new JLabel("Sale Unit");
		lblSaleunit.setHorizontalAlignment(SwingConstants.LEFT);
		lblSaleunit.setBounds(lblPurUnit.getX(), lblPurUnit.getY() + lblPurUnit.getHeight() + 40, lblWidth, lblHeight);
		lblSaleunit.setFont(CustomFonts.font);
		lblSaleunit.setForeground(Color.BLACK);
		lblSaleunit.setBackground(color2);
		lblSaleunit.setVisible(true);

		cmbSaleUnit = new SkywingsComboBox<String>();
		cmbSaleUnit.setBounds(lblSaleunit.getX() + lblSaleunit.getWidth() + 30, lblSaleunit.getY() + 10, txtwidth + 20,
				txtheight);
		cmbSaleUnit.setFont(CustomFonts.font);
		cmbSaleUnit.setForeground(Color.BLACK);
		cmbSaleUnit.setBackground(color2);
		cmbSaleUnit.setVisible(true);

		lblHsnCode = new JLabel("HsnCode");
		lblHsnCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblHsnCode.setBounds(lblSaleunit.getX(), lblSaleunit.getY() + lblSaleunit.getHeight() + 40, lblWidth,
				lblHeight);
		lblHsnCode.setFont(CustomFonts.font);
		lblHsnCode.setForeground(Color.BLACK);
		lblHsnCode.setBackground(color2);
		lblHsnCode.setVisible(true);

		cmbHsnCode = new SkywingsComboBox<String>();
		cmbHsnCode.setBounds(lblHsnCode.getX() + lblHsnCode.getWidth() + 30, lblHsnCode.getY() + 10, txtwidth + 20,
				txtheight);
		cmbHsnCode.setFont(CustomFonts.font);
		cmbHsnCode.setForeground(Color.BLACK);
		cmbHsnCode.setBackground(color2);
		cmbHsnCode.setVisible(true);

		lblActive = new JLabel("Active");
		lblActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblActive.setBounds(lblHsnCode.getX(), lblHsnCode.getY() + lblHsnCode.getHeight() + 40, lblWidth, lblHeight);
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
		panelContent.add(lblProcode);
		panelContent.add(lblProName);
		panelContent.add(lblShortName);
		panelContent.add(lblActive);
		panelContent.add(txtProCode);
		panelContent.add(txtProName);
		panelContent.add(txtShortName);
		panelContent.add(chkActive);
		panelContent.add(lblGst);
		panelContent.add(cmbTaxPer);
		panelContent.add(lblCGST);
		panelContent.add(txtCgst);
		panelContent.add(lblSGST);
		panelContent.add(txtsgst);
		panelContent.add(lblIncGst);
		panelContent.add(chkIncGst);
		panelContent.add(lblPurUnit);
		panelContent.add(cmbPurUnit);
		panelContent.add(lblSaleunit);
		panelContent.add(cmbSaleUnit);
		panelContent.add(lblHsnCode);
		panelContent.add(cmbHsnCode);

		return panelContent;
	}

	@Override
	public boolean formAdd() {

		txtProName.requestFocus();
		return true;
	}

	@Override
	public boolean formSave() {

		save();

		return true;
	}

	private void save() {

		if ("".equalsIgnoreCase(txtProName.getText())) {
			JOptionPane.showMessageDialog(null, "ProName Should Not Empty!...");
			txtProName.requestFocus();
			return;
		}
		if ("".equalsIgnoreCase(txtShortName.getText())) {
			JOptionPane.showMessageDialog(null, "ShortName Should Not Empty!...");
			txtShortName.requestFocus();
			return;
		}

		Product product = new Product();

		product.setProCode(txtProCode.getText());
		product.setProName(txtProName.getText());
		product.setShortName(txtShortName.getText());
		product.setCatcode(1);
		product.setTaxcode(1);
		product.setIncGst(chkIncGst.isSelected() ? "Y" : "N");
		product.setPurUnit(String.valueOf(cmbPurUnit.getSelectedItem()).substring(0, 1));
		product.setUnit(String.valueOf(cmbSaleUnit.getSelectedItem()).substring(0, 1));
		product.setHsnCode(String.valueOf(cmbHsnCode.getSelectedItem()));
		product.setActive(chkActive.isSelected() ? "Y" : "N");

		try {
			productLogic.saveProduct(product);
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

			tblProductView.requestFocus();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return true;
	}

	private void view() throws Exception {

		String[] columnNames = { "ProductCode", "ProductName", "ShortName", "Tax", "IncGST", "PurchaseUnit", "HSNCode",
				"Unit", "Active" };

		List<Product> productList = productLogic.getProduct();

		Object[][] data = new Object[productList.size()][columnNames.length];

		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);
			data[i][0] = product.getProCode();
			data[i][1] = product.getProName();
			data[i][2] = product.getShortName();
			data[i][3] = product.getTaxcode();
			data[i][4] = product.getIncGst();
			data[i][5] = product.getPurUnit();
			data[i][6] = product.getHsnCode();
			data[i][7] = product.getUnit();
			data[i][8] = product.getActive() == "Y" ? "No" : "Yes";
		}
		;

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblProductView = new JTable(model);

		tblProductView.getColumnModel().getColumn(3).setMinWidth(0);
		tblProductView.getColumnModel().getColumn(3).setMaxWidth(0);
		tblProductView.getColumnModel().getColumn(3).setWidth(0);
		tblProductView.getColumnModel().getColumn(3).setResizable(false);

		tblProductView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblProductView.getTableHeader().setReorderingAllowed(false);
		tblProductView.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
		tblProductView.setFont(CustomFonts.fontCalibriPlain15);
		tblProductView.setForeground(Color.BLACK);
		tblProductView.getTableHeader().setForeground(color6);
		tblProductView.getTableHeader().setBackground(Color.WHITE);
		tblProductView.setRowHeight(22);

//		tblOperView.getTableHeader().setToolTipText("Table Header Tooltip");

		tblProductView.setVisible(true);
		tblProductView.addKeyListener(this);

		scrPane = new JScrollPane(tblProductView);
		scrPane.setBounds(50, 50, 700, 360);
		scrPane.getViewport().setBackground(color2);
		scrPane.setVisible(true);

		panelView.add(scrPane);

	}

	@Override
	public boolean formEdit() {

		panelContent.setVisible(true);
		panelView.setVisible(false);

		int selectedRow = 0;
		selectedRow = tblProductView.getSelectedRow();
		if (selectedRow != -1) {
			txtProCode.setText(tblProductView.getValueAt(selectedRow, 0).toString());
			txtProName.setText(tblProductView.getValueAt(selectedRow, 1).toString());
			chkActive.setSelected(tblProductView.getValueAt(selectedRow, 3) == "Yes" ? false : true);
			txtShortName.setText(tblProductView.getValueAt(selectedRow, 3).toString());
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

			txtProCode.setText(productLogic.getProCode());
			txtProName.setText("");
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
			if (e.getSource() == txtProCode) {
				txtProName.requestFocus();
			} else if (e.getSource() == txtProName) {
				txtShortName.requestFocus();
			} else if (e.getSource() == txtShortName) {
				cmbTaxPer.requestFocus();
			} else if (e.getSource() == cmbTaxPer) {
				chkIncGst.requestFocus();
			} else if (e.getSource() == chkIncGst) {
				cmbPurUnit.requestFocus();
			} else if (e.getSource() == cmbPurUnit) {
				cmbSaleUnit.requestFocus();
			} else if (e.getSource() == cmbSaleUnit) {
				cmbHsnCode.requestFocus();
			} else if (e.getSource() == cmbHsnCode) {
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
