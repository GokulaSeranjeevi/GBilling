package com.skywings.gbilling.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jilaba.control.JTextFieldEnum.TextInputType;
import com.skywings.gbilling.common.CommonMethods;
import com.skywings.gbilling.common.ControlResize;
import com.skywings.gbilling.common.CustomFonts;
import com.skywings.gbilling.common.ImageResources;
import com.skywings.gbilling.common.JTextFieldComponent;
import com.skywings.gbilling.common.ListItem;
import com.skywings.gbilling.common.LoginCredential;
import com.skywings.gbilling.common.SkywingsComboBox;
import com.skywings.gbilling.common.SkywingsFont;
import com.skywings.gbilling.common.SkywingsFont.FontStyle;
import com.skywings.gbilling.common.TimerJob;
import com.skywings.gbilling.common.skywingsSpinner;
import com.skywings.gbilling.logic.SalesLogic;
import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.Sales;
import com.skywings.gbilling.model.SubProduct;
import com.skywings.gbilling.model.VendorMaster;
import com.skywings.gbilling.start.Applicationmain;

@Component
@Scope("prototype")
public class FrmSalesEntry extends JFrame implements ActionListener, KeyListener {

	private JPanel panelMain;
	private JPanel panelEntry;
	private JPanel panelView;
	private JPanel panelTitle;
	private JPanel panelLine;
	private JPanel panelDetail;
	private JPanel panelLine2;
	private JPanel panelContent;
	private JPanel panelLine3;
	private JPanel panelButton;
	private JPanel panelViewDetail;
	private JPanel panelCallDate;
	private JPanel panelOrderby;
	private JPanel panelEdit;

	private JLabel lblDevelopedby;
	private JLabel lblVersion;
	private JLabel lblServerIpValue;
	private JLabel lblDateTime;
	private JLabel lblOperatorLabel;
	private JLabel lblMinimize, lblCallMnuHead;

	private JButton btnAdd;
	private JButton btnEntryView;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnExit;
	private JButton btnCancel;
	private JButton btnImage;
	private JButton btnBack;
	private JButton btnUpdate;

	private boolean tbladd = false;

	private JLabel lblTranDate, lblTranNo, lblAcctId, lblAcctName, lblRoute, lblSalesType, lblSalman, lblProduct,
			lblSubProduct, lblQty, lblUnit, lblMRP, lblRate, lblNetAmount, lblTax, lblGrossAmt, lbldiscount,
			lblNarration, lblVechileNo, lblInstructions;
	private JTextField txtTranNo, txtMRP, txtAcctId, txtQty, txtRate, txtNetAmount, txtTax, txtGrossAmt, txtdiscount,
			txtNarration, txtVechileNo;
	private skywingsSpinner spnTranDate;
	private JTable tblSales, tblView;
	private JScrollPane scrPane, scrView;
	private SkywingsComboBox<String> cmbAcctName;
	private SkywingsComboBox<String> cmbRoute;
	private SkywingsComboBox<String> cmbSalesType;
	private SkywingsComboBox<String> cmbSaleman;
	private SkywingsComboBox<String> cmbProduct;
	private SkywingsComboBox<String> cmbSubProduct;
	private SkywingsComboBox<String> cmbUnit;
	private JButton btnEntryAdd, btnEntryClear;

	private Color color1 = Color.decode("#66023c");
	private Color color2 = Color.decode("#FFFFFF");
	private Color color3 = Color.decode("#843462");
	private Color color4 = Color.decode("#e0ccd8");
	private Color color5 = Color.decode("#e4dedf");
	private Color color6 = Color.decode("#000000");
	private Color color7 = Color.decode("#FF0000");

	private SkywingsFont skywingsFont = new SkywingsFont();

	private List<VendorMaster> lstVendor;
	private List<CityMaster> lstRoute;
	private List<String> lstSalesType;
	private List<Operator> lstOpertor;
	private List<Product> lstProduct;
	private List<SubProduct> lstSubProduct;
	private List<MasterCommon> lstUnit;

	@Autowired
	private SalesLogic salesLogic;
	private Color fontColor1 = Color.decode("#17202A");
	private ControlResize controlResize;

	private DecimalFormat nf = new DecimalFormat("0");
	private DecimalFormat df = new DecimalFormat("0.00");

	private JTextFieldComponent txtfComponent = new JTextFieldComponent();
	private Sales sales;
	private List<Sales> lstSales = new ArrayList<Sales>();

	public FrmSalesEntry() throws Exception {

		setTitle("Sales");
		getContentPane().setPreferredSize(new Dimension(958, 728));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		setUndecorated(true);
		pack();
		createControls();
		createListeners();
		addFocusListeners();

		controlResize = new ControlResize(this);
		setSize(controlResize.getRectangle().getSize());
		controlResize.reAlignControls();

	}

	@PostConstruct
	private void loadInitialize() {

		txtTranNo.setText(salesLogic.getTranNo());
		txtTranNo.setEnabled(false);

		spnTranDate.requestFocus();

		lstVendor = salesLogic.getVendor();

		for (VendorMaster vendor : lstVendor) {

			cmbAcctName.addListItem(new ListItem(vendor.getAcctName(), vendor.getAcctCode()));

		}
		lstRoute = salesLogic.getCity();

		for (CityMaster city : lstRoute) {

			String name = city.getDesc();

			cmbRoute.addListItem(new ListItem(city.getDesc(), city.getCitycode()));

		}

		cmbSalesType.addListItem(new ListItem("Local Sales", 1));
		cmbSalesType.addListItem(new ListItem("Other State Sales", 2));

		lstOpertor = salesLogic.getSalesMan();

		for (Operator sales : lstOpertor) {

			cmbSaleman.addListItem(new ListItem(sales.getOperNameS(), sales.getOperatorId()));

		}

		lstProduct = salesLogic.getProduct();

		for (Product pro : lstProduct) {

			cmbProduct.addListItem(new ListItem(pro.getProName(), pro.getProCode()));

		}
		lstSubProduct = salesLogic.getSubProduct();

		for (SubProduct pro : lstSubProduct) {

			cmbSubProduct.addListItem(new ListItem(pro.getSubProName(), pro.getSubcode()));

		}
		lstUnit = salesLogic.getUnit();

		for (MasterCommon unit : lstUnit) {

			cmbUnit.addListItem(new ListItem(unit.getDescription(), unit.getUnit()));

		}

		disableAllComponents(panelContent);

		btnBack.setEnabled(false);
		btnSave.setEnabled(false);
		btnEdit.setEnabled(false);

	}

	private static void disableAllComponents(JPanel panel) {
		for (java.awt.Component component : panel.getComponents()) {
			component.setEnabled(false);
			if (component instanceof JTextField) {
				((JTextField) component).setEditable(false);
			}
			if (component instanceof JTextArea) {
				((JTextArea) component).setEditable(false);
			}

		}
	}

	private static void enableAllComponents(JPanel panel) {
		for (java.awt.Component component : panel.getComponents()) {
			component.setEnabled(true);
			if (component instanceof JTextField) {
				((JTextField) component).setEditable(true);
			}
			if (component instanceof JTextArea) {
				((JTextArea) component).setEditable(true);
			}
		}
	}

	private void createListeners() {
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});

		spnTranDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					cmbAcctName.requestFocus();
				}

			}
		});
		txtMRP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		tblSales.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {

					int response = JOptionPane.showConfirmDialog(panelContent, "Are you sure to delete this row?",
							"Confirm Deletion", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {

						int selectedRow = tblSales.getSelectedRow();

						if (selectedRow != -1) {
							DefaultTableModel model = (DefaultTableModel) tblSales.getModel();

							model.removeRow(selectedRow);

							lstSales.remove(selectedRow);
						}
					}
				} else {
					JOptionPane.showMessageDialog(panelContent, "Deletion Cancelled ..!");
				}

				if (e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
					int selectedRow = tblSales.getSelectedRow();

//					if (selectedRow != -1) {
//						DefaultTableModel model = (DefaultTableModel) tblSales.getModel();
//
//						String subProName = (String) model.getValueAt(selectedRow, 0);
//						double qty = (Double) model.getValueAt(selectedRow, 1);
//						String unitName = (String) model.getValueAt(selectedRow, 2);
//						double rate = (Double) model.getValueAt(selectedRow, 3);
//						double netAmount = (Double) model.getValueAt(selectedRow, 5);
//						double taxAmount = (Double) model.getValueAt(selectedRow, 6);
//						double grossAmount = (Double) model.getValueAt(selectedRow, 7);
//
//						cmbSubProduct.setSelectedItem(subProName);
//						txtQty.setText(String.valueOf(qty));
//						cmbUnit.setSelectedItem(unitName);
//						txtRate.setText(String.valueOf(rate));
//						txtNetAmount.setText(String.valueOf(netAmount));
//						txtTax.setText(String.valueOf(taxAmount));
//						txtGrossAmt.setText(String.valueOf(grossAmount));
//
//					}

					JOptionPane.showMessageDialog(panelContent, "Apology to You, Now This Option Not Enabled ..!");
				}

			}

		});

	}

	private void createControls() throws Exception {

		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 958, 728);
		panelMain.setLayout(null);
		panelMain.setBackground(color2);

		panelEntry = new JPanel();
		panelEntry.setBounds(0, 0, 958, 650);
		panelEntry.setLayout(null);
		panelEntry.setBackground(color2);

		panelView = new JPanel();
		panelView.setBounds(0, 0, 958, 650);
		panelView.setLayout(null);
		panelView.setBackground(color2);

		panelMain.add(panelTitleInialize());
		panelMain.add(panelLineInialize());
		panelMain.add(panelDetailInitialize());
		panelMain.add(panelLine2Initialize());
		panelEntry.add(panelContentInitoalize());
		panelMain.add(panelLine3Inialize());
		panelMain.add(panelButtonInialize());
		panelView.add(panelViewDetail());
//		panelView.add(panelReadyDetail());

		getContentPane().add(panelMain);
		panelMain.add(panelEntry);
		panelMain.add(panelView);

	}

	private java.awt.Component panelViewDetail() {

		panelViewDetail = new JPanel();
		panelViewDetail.setBounds(0, 0, 958, 650);
		panelViewDetail.setLayout(null);
		panelViewDetail.setBackground(color2);
		panelViewDetail.setVisible(true);

		panelViewDetail.setBorder(BorderFactory.createEtchedBorder(color3, color3));

//		tblView();

		panelView.add(panelViewDetail);

		return panelViewDetail;
	}

	private void tblView() {

		String[] columnNames = { "SubProduct", "Qty", "Unit", "MRP/BAG", "Rate", "NetAmount", "TaxAmount",
				"TotalAmount" };

		Object[][] data1;
		data1 = new Object[][] {};

		if (tblView == null) {
			DefaultTableModel model = new DefaultTableModel(data1, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			tblView = new JTable(model);

			tblView.getColumnModel().getColumn(3).setMinWidth(0);
			tblView.getColumnModel().getColumn(3).setMaxWidth(0);
			tblView.getColumnModel().getColumn(3).setWidth(0);
			tblView.getColumnModel().getColumn(3).setResizable(false);

			tblView.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tblView.getTableHeader().setReorderingAllowed(false);
			tblView.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
			tblView.setFont(CustomFonts.fontCalibriPlain15);
			tblView.setForeground(Color.BLACK);
			tblView.getTableHeader().setForeground(color6);
			tblView.getTableHeader().setBackground(Color.WHITE);
			tblView.setRowHeight(22);

			tblView.setVisible(true);
			tblView.addKeyListener(this);

			scrPane = new JScrollPane(tblSales);
			scrPane.setBounds(lblSalman.getX(), lblSalman.getY() + lblSalman.getHeight() + 20, 830, 180);
			scrPane.getViewport().setBackground(color2);
			scrPane.setVisible(true);

			panelViewDetail.add(scrPane);
		}

	}

	private java.awt.Component panelContentInitoalize() {
		int lblWidth = 130;
		int lblHeight = 30;
		int txtWidth = 120;
		int txtHeight = 20;

		panelContent = new JPanel();
		panelContent.setBounds(panelLine2.getX() + 50, panelLine2.getY() + 20, 850, 500);
		panelContent.setLayout(null);
		panelContent.setBackground(color2);
		panelContent.setVisible(false);

		panelContent.setBorder(BorderFactory.createEtchedBorder(color3, color3));

		lblTranNo = new JLabel("TranNo");
		lblTranNo.setBounds(10, 0, lblWidth, lblHeight);
		lblTranNo.setBackground(color2);
		lblTranNo.setVisible(true);
		lblTranNo.setFont(CustomFonts.font);

		txtTranNo = new JTextField();
		txtTranNo.setBounds(lblTranNo.getX() + 80, lblTranNo.getY() + 5, 100, txtHeight);
		txtTranNo.setVisible(true);
		txtTranNo.setFont(CustomFonts.fontCalibriPlain15);
		txtTranNo.addKeyListener(this);

//		txtfComponent.textLength(txtTranNo, 20);

		lblTranDate = new JLabel("Tran Date");
		lblTranDate.setBounds(lblTranNo.getX(), lblTranNo.getY() + lblTranNo.getHeight() + 10, lblWidth, lblHeight);
		lblTranDate.setBackground(color2);
		lblTranDate.setVisible(true);
		lblTranDate.setFont(CustomFonts.font);

		spnTranDate = new skywingsSpinner();
		spnTranDate.setBounds(lblTranDate.getX() + 80, lblTranDate.getY() + 10, 80, txtHeight);
		spnTranDate.setFont(CustomFonts.fontCalibriBold);
		spnTranDate.setForeground(Color.BLACK);
		spnTranDate.setVisible(true);
		spnTranDate.addKeyListener(this);

		lblAcctName = new JLabel("Party Name");
		lblAcctName.setBounds(lblTranDate.getX(), lblTranDate.getY() + lblTranDate.getHeight() + 20, lblWidth,
				lblHeight);
		lblAcctName.setBackground(color2);
		lblAcctName.setVisible(true);
		lblAcctName.setFont(CustomFonts.font);

		cmbAcctName = new SkywingsComboBox<String>();
		cmbAcctName.setBounds(lblAcctName.getX() + 80, lblAcctName.getY(), txtWidth, txtHeight);
		cmbAcctName.setBackground(color2);
		cmbAcctName.setFont(CustomFonts.fontCalibriBold);
		cmbAcctName.setVisible(true);
		cmbAcctName.addKeyListener(this);

		lblRoute = new JLabel("Route");
		lblRoute.setBounds(lblAcctName.getX(), lblAcctName.getY() + lblAcctName.getHeight() + 20, lblWidth, lblHeight);
		lblRoute.setBackground(color2);
		lblRoute.setVisible(true);
		lblRoute.setFont(CustomFonts.font);

		cmbRoute = new SkywingsComboBox<String>();
		cmbRoute.setBounds(lblRoute.getX() + 80, lblRoute.getY(), txtWidth, txtHeight);
		cmbRoute.setBackground(color2);
		cmbRoute.setFont(CustomFonts.fontCalibriBold);
		cmbRoute.setVisible(true);
		cmbRoute.addKeyListener(this);

		lblSalesType = new JLabel("SalesType");
		lblSalesType.setBounds(lblRoute.getX(), lblRoute.getY() + lblRoute.getHeight() + 20, lblWidth, lblHeight);
		lblSalesType.setBackground(color2);
		lblSalesType.setVisible(true);
		lblSalesType.setFont(CustomFonts.font);

		cmbSalesType = new SkywingsComboBox<String>();
		cmbSalesType.setBounds(lblSalesType.getX() + 80, lblSalesType.getY(), txtWidth, txtHeight);
		cmbSalesType.setBackground(color2);
		cmbSalesType.setFont(CustomFonts.fontCalibriBold);
		cmbSalesType.setVisible(true);
		cmbSalesType.addKeyListener(this);

		lblSalman = new JLabel("SalesMan");
		lblSalman.setBounds(lblSalesType.getX(), lblSalesType.getY() + lblSalesType.getHeight() + 20, lblWidth,
				lblHeight);
		lblSalman.setBackground(color2);
		lblSalman.setVisible(true);
		lblSalman.setFont(CustomFonts.font);

		cmbSaleman = new SkywingsComboBox<String>();
		cmbSaleman.setBounds(lblSalman.getX() + 80, lblSalman.getY(), txtWidth, txtHeight);
		cmbSaleman.setBackground(color2);
		cmbSaleman.setFont(CustomFonts.fontCalibriBold);
		cmbSaleman.setVisible(true);
		cmbSaleman.addKeyListener(this);

		lblProduct = new JLabel("Product");
		lblProduct.setBounds(txtTranNo.getX() + txtTranNo.getWidth() + 80, txtTranNo.getY(), lblWidth, lblHeight);
		lblProduct.setBackground(color2);
		lblProduct.setVisible(true);
		lblProduct.setFont(CustomFonts.font);

		cmbProduct = new SkywingsComboBox<String>();
		cmbProduct.setBounds(lblProduct.getX() + 80, lblProduct.getY(), txtWidth, txtHeight);
		cmbProduct.setBackground(color2);
		cmbProduct.setFont(CustomFonts.fontCalibriBold);
		cmbProduct.setVisible(true);
		cmbProduct.addKeyListener(this);

		lblSubProduct = new JLabel("SubProduct");
		lblSubProduct.setBounds(lblProduct.getX(), lblProduct.getY() + lblProduct.getHeight() + 20, lblWidth,
				lblHeight);
		lblSubProduct.setBackground(color2);
		lblSubProduct.setVisible(true);
		lblSubProduct.setFont(CustomFonts.font);

		cmbSubProduct = new SkywingsComboBox<String>();
		cmbSubProduct.setBounds(lblSubProduct.getX() + 80, lblSubProduct.getY(), txtWidth, txtHeight);
		cmbSubProduct.setBackground(color2);
		cmbSubProduct.setFont(CustomFonts.fontCalibriBold);
		cmbSubProduct.setVisible(true);
		cmbSubProduct.addKeyListener(this);

		lblQty = new JLabel("Qty");
		lblQty.setBounds(lblSubProduct.getX(), lblSubProduct.getY() + lblSubProduct.getHeight() + 20, lblWidth,
				lblHeight);
		lblQty.setBackground(color2);
		lblQty.setVisible(true);
		lblQty.setFont(CustomFonts.font);

		txtQty = new JTextField();
		txtQty.setBounds(lblQty.getX() + 80, lblQty.getY(), txtWidth, txtHeight);
		txtQty.setVisible(true);
		txtQty.setFont(CustomFonts.fontCalibriPlain15);
		txtQty.addKeyListener(this);

//		txtfComponent.textLength(txtQty, 20);

		lblUnit = new JLabel("Unit");
		lblUnit.setBounds(lblQty.getX(), lblQty.getY() + lblQty.getHeight() + 20, lblWidth, lblHeight);
		lblUnit.setBackground(color2);
		lblUnit.setVisible(true);
		lblUnit.setFont(CustomFonts.font);

		cmbUnit = new SkywingsComboBox<String>();
		cmbUnit.setBounds(lblUnit.getX() + 80, lblUnit.getY(), txtWidth, txtHeight);
		cmbUnit.setBackground(color2);
		cmbUnit.setFont(CustomFonts.fontCalibriBold);
		cmbUnit.setVisible(true);
		cmbUnit.addKeyListener(this);

		lblMRP = new JLabel("MRP\\BAG");
		lblMRP.setBounds(lblUnit.getX(), lblUnit.getY() + lblUnit.getHeight() + 20, lblWidth, lblHeight);
		lblMRP.setBackground(color2);
		lblMRP.setVisible(true);
		lblMRP.setFont(CustomFonts.font);

		txtMRP = new JTextField();
		txtMRP.setBounds(lblMRP.getX() + 80, lblMRP.getY(), txtWidth, txtHeight);
		txtMRP.setVisible(true);
		txtMRP.setFont(CustomFonts.fontCalibriPlain15);
		txtMRP.addKeyListener(this);

//		txtfComponent.textLength(txtMRP, 20);

		lblRate = new JLabel("Rate");
		lblRate.setBounds(lblMRP.getX(), lblMRP.getY() + lblMRP.getHeight() + 20, lblWidth, lblHeight);
		lblRate.setBackground(color2);
		lblRate.setVisible(true);
		lblRate.setFont(CustomFonts.font);

		txtRate = new JTextField();
		txtRate.setBounds(lblRate.getX() + 80, lblRate.getY(), txtWidth, txtHeight);
		txtRate.setVisible(true);
		txtRate.setFont(CustomFonts.fontCalibriPlain15);
		txtRate.addKeyListener(this);

//		txtfComponent.textLength(txtRate, 20);

		lblNetAmount = new JLabel("NetAmount");
		lblNetAmount.setBounds(cmbProduct.getX() + cmbProduct.getWidth() + 80, cmbProduct.getY(), lblWidth, lblHeight);
		lblNetAmount.setBackground(color2);
		lblNetAmount.setVisible(true);
		lblNetAmount.setFont(CustomFonts.font);

		txtNetAmount = new JTextField();
		txtNetAmount.setBounds(lblNetAmount.getX() + 80, lblNetAmount.getY(), txtWidth, txtHeight);
		txtNetAmount.setVisible(true);
		txtNetAmount.setFont(CustomFonts.fontCalibriPlain15);
		txtNetAmount.addKeyListener(this);

//		txtfComponent.textLength(txtNetAmount, 20);

		lblTax = new JLabel("Tax Amount");
		lblTax.setBounds(lblNetAmount.getX(), lblNetAmount.getY() + lblNetAmount.getHeight() + 20, lblWidth, lblHeight);
		lblTax.setBackground(color2);
		lblTax.setVisible(true);
		lblTax.setFont(CustomFonts.font);

		txtTax = new JTextField();
		txtTax.setBounds(lblTax.getX() + 80, lblTax.getY(), txtWidth, txtHeight);
		txtTax.setVisible(true);
		txtTax.setFont(CustomFonts.fontCalibriPlain15);
		txtTax.addKeyListener(this);

//		txtfComponent.textLength(txtTax, 20);

		lblGrossAmt = new JLabel("Gross Amount");
		lblGrossAmt.setBounds(lblTax.getX(), lblTax.getY() + lblTax.getHeight() + 20, lblWidth, lblHeight);
		lblGrossAmt.setBackground(color2);
		lblGrossAmt.setVisible(true);
		lblGrossAmt.setFont(CustomFonts.font);

		txtGrossAmt = new JTextField();
		txtGrossAmt.setBounds(lblGrossAmt.getX() + 80, lblGrossAmt.getY(), txtWidth, txtHeight);
		txtGrossAmt.setVisible(true);
		txtGrossAmt.setFont(CustomFonts.fontCalibriPlain15);
		txtGrossAmt.addKeyListener(this);

//		txtfComponent.textLength(txtGrossAmt, 20);

		lblNarration = new JLabel("Narration");
		lblNarration.setBounds(lblGrossAmt.getX(), lblGrossAmt.getY() + lblGrossAmt.getHeight() + 20, lblWidth,
				lblHeight);
		lblNarration.setBackground(color2);
		lblNarration.setVisible(true);
		lblNarration.setFont(CustomFonts.font);

		txtNarration = new JTextField();
		txtNarration.setBounds(lblNarration.getX() + 80, lblNarration.getY(), txtWidth, txtHeight);
		txtNarration.setVisible(true);
		txtNarration.setFont(CustomFonts.fontCalibriPlain15);
		txtNarration.addKeyListener(this);

//		txtfComponent.textLength(txtVechileNo, 40);

		lblVechileNo = new JLabel("VechileNo");
		lblVechileNo.setBounds(lblNarration.getX(), lblNarration.getY() + lblNarration.getHeight() + 20, lblWidth,
				lblHeight);
		lblVechileNo.setBackground(color2);
		lblVechileNo.setVisible(true);
		lblVechileNo.setFont(CustomFonts.font);

		txtVechileNo = new JTextField();
		txtVechileNo.setBounds(lblVechileNo.getX() + 80, lblVechileNo.getY(), txtWidth, txtHeight);
		txtVechileNo.setVisible(true);
		txtVechileNo.setFont(CustomFonts.fontCalibriPlain15);
		txtVechileNo.addKeyListener(this);

//		txtfComponent.textLength(txtVechileNo, 20);

		btnEntryAdd = new JButton("Add");
		btnEntryAdd.setBounds(lblVechileNo.getX() + 50, lblVechileNo.getY() + lblVechileNo.getHeight() + 30, 50, 20);
		btnEntryAdd.setBackground(color3);
		btnEntryAdd.setForeground(color2);
		btnEntryAdd.setVisible(true);
		btnEntryAdd.setMnemonic(KeyEvent.VK_C);
		btnEntryAdd.addKeyListener(this);
		btnEntryAdd.addActionListener(this);
		btnEntryAdd.addKeyListener(this);

		btnEntryClear = new JButton("Clear");
		btnEntryClear.setBounds(btnEntryAdd.getX() + btnEntryAdd.getWidth() + 20, btnEntryAdd.getY(), 50, 20);
		btnEntryClear.setBackground(color3);
		btnEntryClear.setForeground(color2);
		btnEntryClear.setVisible(true);
		btnEntryClear.setMnemonic(KeyEvent.VK_C);
		btnEntryClear.addKeyListener(this);
		btnEntryClear.addActionListener(this);

		tblCreation(tbladd);

		lblInstructions = new JLabel("'E' For Edit || 'D' For Delete ");
		lblInstructions.setBounds(tblSales.getX() + (7 * 100), tblSales.getY() + 470, lblWidth, lblHeight);
		lblInstructions.setBackground(color2);
		lblInstructions.setForeground(color7);
		lblInstructions.setVisible(true);
		lblInstructions.setFont(CustomFonts.font);

		panelContent.add(scrPane);
		panelMain.add(panelContent);
//		panelMain.add(tblSales);

		panelContent.add(lblTranDate);
		panelContent.add(spnTranDate);
		panelContent.add(lblTranNo);
		panelContent.add(txtTranNo);
		panelContent.add(lblAcctName);
		panelContent.add(cmbAcctName);
		panelContent.add(lblRoute);
		panelContent.add(cmbRoute);
		panelContent.add(lblSalesType);
		panelContent.add(cmbSalesType);
		panelContent.add(lblSalman);
		panelContent.add(cmbSaleman);
		panelContent.add(lblProduct);
		panelContent.add(cmbProduct);
		panelContent.add(lblSubProduct);
		panelContent.add(cmbSubProduct);
		panelContent.add(lblQty);
		panelContent.add(txtQty);
		panelContent.add(lblRate);
		panelContent.add(txtRate);
		panelContent.add(lblUnit);
		panelContent.add(cmbUnit);
		panelContent.add(lblMRP);
		panelContent.add(txtMRP);
		panelContent.add(lblNetAmount);
		panelContent.add(txtNetAmount);
		panelContent.add(lblTax);
		panelContent.add(txtTax);
		panelContent.add(lblGrossAmt);
		panelContent.add(txtGrossAmt);
		panelContent.add(lblNarration);
		panelContent.add(txtNarration);
		panelContent.add(lblVechileNo);
		panelContent.add(txtVechileNo);
		panelContent.add(btnEntryAdd);
		panelContent.add(btnEntryClear);
		panelContent.add(lblInstructions);

		return panelContent;
	}

	private void tblCreation(boolean tbladd) {

//		String[] columnNames = { "Product", "Qty", "Unit", "MRP/BAG", "Rate", "NetAmount", "TaxAmount", "TotalAmount" };
//
//		Sales sales = new Sales();
//
//		Object[][] data;
//
//		if (tbladd == false) {
//			data = new Object[][] {};
//		} else {
//
//			sales.setProName(String.valueOf(cmbProduct.getSelectedItem()));
//			sales.setPieces(Integer.valueOf(txtQty.getText()));
//			sales.setUnitName(String.valueOf(cmbUnit.getSelectedItem()));
//			sales.setRate(Double.valueOf(txtRate.getText()));
//			sales.setNetAmount(Double.valueOf(txtNetAmount.getText()));
//			sales.setTaxAmount(Double.valueOf(txtTax.getText()));
//			sales.setGrossAmount(Double.valueOf(txtGrossAmt.getText()));
//			sales.setTranNo(Integer.valueOf(txtTranNo.getText()));
//			sales.setTranDate(spnTranDate.getDateValue());
//			sales.setAcctId(Integer.valueOf(String.valueOf(cmbAcctName.getSelectedItemValue())));
//			sales.setAcctName(String.valueOf(cmbAcctName.getSelectedItem()));
//			data = new Object[][] { { sales.getProName(), sales.getPieces(), sales.getUnitName(), sales.getRate(),
//					sales.getRate(), sales.getNetAmount(), sales.getTaxAmount(), sales.getGrossAmount() } };
//		}
//
//		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		};
//		// Create JTable with the model
//		tblSales = new JTable(model);
//
//		// Column settings (Optional)
//		tblSales.getColumnModel().getColumn(3).setMinWidth(0);
//		tblSales.getColumnModel().getColumn(3).setMaxWidth(0);
//		tblSales.getColumnModel().getColumn(3).setWidth(0);
//		tblSales.getColumnModel().getColumn(3).setResizable(false);
//
//		tblSales.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		tblSales.getTableHeader().setReorderingAllowed(false);
//		tblSales.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
//		tblSales.setFont(CustomFonts.fontCalibriPlain15);
//		tblSales.setForeground(Color.BLACK);
//		tblSales.getTableHeader().setForeground(color6);
//		tblSales.getTableHeader().setBackground(Color.WHITE);
//		tblSales.setRowHeight(22);
//
//		tblSales.setVisible(true);
//		tblSales.addKeyListener(this);
//
//		scrPane = new JScrollPane(tblSales);
//		scrPane.setBounds(lblSalman.getX(), lblSalman.getY() + lblSalman.getHeight() + 20, 830, 180);
//		scrPane.getViewport().setBackground(color2);
//		scrPane.setVisible(true);
//
//		panelContent.revalidate(); // Make sure the UI is updated
//		panelContent.repaint();
//
//		scrPane.repaint();
//		scrPane.revalidate();	

		String[] columnNames = { "SubProduct", "Qty", "Unit", "MRP/BAG", "Rate", "NetAmount", "TaxAmount",
				"TotalAmount" };

		Object[][] data;

		if (tbladd == false) {
			data = new Object[][] {};
		} else {
			sales = new Sales();

			sales.setTranNo(Integer.valueOf(txtTranNo.getText()));
			sales.setTranDate(spnTranDate.getDateValue());
			sales.setAcctId(Integer.valueOf(String.valueOf(cmbAcctName.getSelectedItemValue())));
			sales.setAcctName(String.valueOf(cmbAcctName.getSelectedItem()));
			sales.setRouteId(Integer.valueOf(String.valueOf(cmbRoute.getSelectedItemValue())));
			sales.setRouteName(String.valueOf(cmbRoute.getSelectedItem()));
			sales.setSalesType(String.valueOf(cmbSalesType.getSelectedItemValue()));
			sales.setSalMan(String.valueOf(cmbSaleman.getSelectedItemValue()));
			sales.setProcode(Integer.valueOf(String.valueOf(cmbProduct.getSelectedItemValue())));
			sales.setProName(String.valueOf(cmbProduct.getSelectedItem()));
			sales.setPieces(Double.valueOf(String.valueOf(txtQty.getText())));
			sales.setUnit(String.valueOf(cmbUnit.getSelectedItemValue()));
			sales.setUnitName(String.valueOf(cmbUnit.getSelectedItem()));
			sales.setRate(Double.valueOf(txtRate.getText()));
			sales.setNetAmount(Double.valueOf(txtNetAmount.getText()));
			sales.setTaxAmount(Double.valueOf(txtTax.getText()));
			sales.setGrossAmount(Double.valueOf(txtGrossAmt.getText()));
			sales.setNarration1(txtNarration.getText());
			sales.setVechileNo(txtVechileNo.getText());
			sales.setSubProName(String.valueOf(cmbSubProduct.getSelectedItem()));

			lstSales.add(sales);

			data = new Object[][] { { sales.getSubProName(), sales.getPieces(), sales.getUnitName(), sales.getRate(),
					sales.getRate(), sales.getNetAmount(), sales.getTaxAmount(), sales.getGrossAmount() } };
		}

		if (tblSales == null) {
			DefaultTableModel model = new DefaultTableModel(data, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			tblSales = new JTable(model);

			tblSales.getColumnModel().getColumn(3).setMinWidth(0);
			tblSales.getColumnModel().getColumn(3).setMaxWidth(0);
			tblSales.getColumnModel().getColumn(3).setWidth(0);
			tblSales.getColumnModel().getColumn(3).setResizable(false);

			tblSales.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tblSales.getTableHeader().setReorderingAllowed(false);
			tblSales.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
			tblSales.setFont(CustomFonts.fontCalibriPlain15);
			tblSales.setForeground(Color.BLACK);
			tblSales.getTableHeader().setForeground(color6);
			tblSales.getTableHeader().setBackground(Color.WHITE);
			tblSales.setRowHeight(22);

			tblSales.setVisible(true);
			tblSales.addKeyListener(this);

			scrPane = new JScrollPane(tblSales);
			scrPane.setBounds(lblSalman.getX(), lblSalman.getY() + lblSalman.getHeight() + 20, 830, 180);
			scrPane.getViewport().setBackground(color2);
			scrPane.setVisible(true);

			panelContent.add(scrPane);
		} else {
			DefaultTableModel model = (DefaultTableModel) tblSales.getModel();

			model.addRow(new Object[] { sales.getSubProName(), sales.getPieces(), sales.getUnitName(), sales.getRate(),
					sales.getRate(), sales.getNetAmount(), sales.getTaxAmount(), sales.getGrossAmount() });

			model.fireTableDataChanged();
		}

	}

	public void clearTableData() {
		DefaultTableModel model = (DefaultTableModel) tblSales.getModel();
		model.setRowCount(0); // Removes only the rows, columns remain intact
	}

	private java.awt.Component panelButtonInialize() {
		int btnWidth = 50;
		int btnHeight = 20;
		panelButton = new JPanel();
		panelButton.setBounds(0, panelContent.getY() + panelContent.getHeight() + 18, 1000, 40);
		panelButton.setLayout(null);
		panelButton.setBackground(color3);
//		panelButton.setBorder(BorderFactory.createEtchedBorder(color3, color3));

		btnAdd = new JButton("Add");
		btnAdd.setHorizontalAlignment(SwingConstants.CENTER);
		btnAdd.setBounds(50, 10, btnWidth, btnHeight);
		btnAdd.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnAdd.setMnemonic(KeyEvent.VK_A);
		btnAdd.setBackground(color1);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setVisible(true);
		btnAdd.addActionListener(this);
		btnAdd.setVerifyInputWhenFocusTarget(false);
		btnAdd.addKeyListener(this);

		btnSave = new JButton("Save");
		btnSave.setHorizontalAlignment(SwingConstants.CENTER);
		btnSave.setBounds(btnAdd.getX() + btnWidth + 80, btnAdd.getY(), btnWidth, btnHeight);
		btnSave.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setBackground(color1);
		btnSave.setForeground(Color.WHITE);
		btnSave.setVisible(true);
		btnSave.addActionListener(this);
		btnSave.addKeyListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		btnUpdate.setBounds(btnAdd.getX() + btnWidth + 80, btnAdd.getY(), btnWidth + 10, btnHeight);
		btnUpdate.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.setBackground(color1);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setVisible(false);
		btnUpdate.addActionListener(this);
		btnUpdate.addKeyListener(this);

		btnEntryView = new JButton("View");
		btnEntryView.setHorizontalAlignment(SwingConstants.CENTER);
		btnEntryView.setBounds(btnSave.getX() + btnWidth + 80, btnSave.getY(), btnWidth, btnHeight);
		btnEntryView.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnEntryView.setMnemonic(KeyEvent.VK_V);
		btnEntryView.setBackground(color1);
		btnEntryView.setForeground(Color.WHITE);
		btnEntryView.setVisible(true);
		btnEntryView.addActionListener(this);
		btnEntryView.setVerifyInputWhenFocusTarget(false);
		btnEntryView.addKeyListener(this);

		btnEdit = new JButton("Edit");
		btnEdit.setHorizontalAlignment(SwingConstants.CENTER);
		btnEdit.setBounds(btnEntryView.getX() + btnWidth + 80, btnEntryView.getY(), btnWidth, btnHeight);
		btnEdit.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnEdit.setMnemonic(KeyEvent.VK_E);
		btnEdit.setBackground(color1);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setVisible(true);
		btnEdit.addActionListener(this);
		btnEdit.setVerifyInputWhenFocusTarget(false);
		btnEdit.addKeyListener(this);

		btnCancel = new JButton("Cancel");
		btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancel.setBounds(btnEdit.getX() + btnWidth + 80, btnEdit.getY(), btnWidth, btnHeight);
		btnCancel.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.setBackground(color1);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setVisible(true);
		btnCancel.addActionListener(this);
		btnCancel.setVerifyInputWhenFocusTarget(false);
		btnCancel.addKeyListener(this);

		btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setBounds(btnCancel.getX() + btnWidth + 80, btnCancel.getY(), btnWidth, btnHeight);
		btnBack.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnBack.setMnemonic(KeyEvent.VK_B);
		btnBack.setBackground(color1);
		btnBack.setForeground(Color.WHITE);
		btnBack.setVisible(true);
		btnBack.addActionListener(this);
		btnBack.setVerifyInputWhenFocusTarget(false);
		btnBack.addKeyListener(this);

		btnExit = new JButton("Close");
		btnExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.setBounds(btnBack.getX() + btnWidth + 80, btnBack.getY(), btnWidth, btnHeight);
		btnExit.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		btnExit.setBackground(color1);
		btnExit.setForeground(Color.WHITE);
		btnExit.setMnemonic(KeyEvent.VK_L);
		btnExit.setMargin(new Insets(0, 0, 0, 0));
		btnExit.setVisible(true);
		btnExit.addActionListener(this);
		btnExit.setVerifyInputWhenFocusTarget(false);
		btnExit.addKeyListener(this);

		panelMain.add(panelButton);
		panelButton.add(btnAdd);
		panelButton.add(btnSave);
		panelButton.add(btnUpdate);
		panelButton.add(btnEntryView);
		panelButton.add(btnEdit);
		panelButton.add(btnCancel);
		panelButton.add(btnBack);
		panelButton.add(btnExit);
		return panelButton;

	}

	private java.awt.Component panelLine3Inialize() throws Exception {

		panelLine3 = new JPanel();
		panelLine3.setBounds(0, 680, 958, 6);
		panelLine3.setLayout(null);
		panelLine3.setBackground(color1);
		panelLine3.setVisible(true);

		lblDevelopedby = new JLabel();
		lblDevelopedby.setBounds(825, panelLine3.getY(), 150, 50);
		lblDevelopedby.setBackground(color1);
		CommonMethods.setIcon(ImageResources.developedBy, lblDevelopedby);
		lblDevelopedby.setVisible(true);

		lblMinimize = new JLabel("");
		lblMinimize.setBounds(lblDevelopedby.getX() + 120, lblDevelopedby.getY() + 8, 10, 40);
		lblMinimize.setBackground(color1);
		lblMinimize.setOpaque(true);
		lblMinimize.setVisible(true);

		panelMain.add(panelLine3);
		panelMain.add(lblDevelopedby);
		panelMain.add(lblMinimize);

		return panelLine3;
	}

	private java.awt.Component panelLine2Initialize() {
		panelLine2 = new JPanel();
		panelLine2.setBounds(panelDetail.getX(), panelDetail.getY() + 30, 958, 3);
		panelLine2.setLayout(null);
		panelLine2.setBackground(color5);
		panelLine2.setVisible(true);

		panelEntry.add(panelLine2);

		return panelLine2;
	}

	private java.awt.Component panelDetailInitialize() {
		panelDetail = new JPanel();
		panelDetail.setBounds(panelLine.getX(), panelLine.getY() + 30, 958, 30);
		panelDetail.setLayout(null);
		panelDetail.setBackground(color2);
		panelDetail.setVisible(true);

		// lblHeading = new JLabel("CALLS REGISTER");
		// lblHeading.setBounds(865, -10, 170, 50);
		// // lblHeading.setBounds(panelDetail.getWidth() / 2, panelDetail.getY() / 2,
		// 20, 20);
		// lblHeading.setFont(jilabaFonts.getFont(FontStyle.BOLD, 23));
		// lblHeading.setForeground(fontColor1);
		// lblHeading.setVisible(true);

		lblCallMnuHead = new JLabel("SALES ENTRY");
		lblCallMnuHead.setBounds(10, -10, 170, 50);
		// lblHeading.setBounds(panelDetail.getWidth() / 2, panelDetail.getY() / 2, 20,
		// 20);
		lblCallMnuHead.setFont(skywingsFont.getFont(FontStyle.BOLD, 23));
		lblCallMnuHead.setForeground(fontColor1);
		lblCallMnuHead.setVisible(true);

		// panelDetail.add(lblHeading);
		panelDetail.add(lblCallMnuHead);
		panelMain.add(panelDetail);
		return panelLine;
	}

	private java.awt.Component panelLineInialize() {
		panelLine = new JPanel();
		panelLine.setBounds(0, 30, 958, 6);
		panelLine.setLayout(null);
		panelLine.setBackground(color3);
		panelLine.setVisible(true);

		panelEntry.add(panelLine);
		return panelLine;
	}

	private java.awt.Component panelTitleInialize() throws Exception {

		panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 958, 30);
		panelTitle.setLayout(null);
		panelTitle.setBackground(color1);
		panelTitle.setVisible(true);

		lblOperatorLabel = new JLabel(FrmLogin.Operator);
		lblOperatorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblOperatorLabel.setBounds(10, 5, 120, 22);
		lblOperatorLabel.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		lblOperatorLabel.setForeground(color2);
		CommonMethods.setIcon(ImageResources.SERVERIPICON, lblOperatorLabel);
		lblOperatorLabel.setVisible(true);

		lblVersion = new JLabel(Applicationmain.VERSION);
		lblVersion.setBounds(170, 5, 100, 22);
		lblVersion.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		lblVersion.setForeground(color2);
		CommonMethods.setIcon(ImageResources.VERSIONICON, lblVersion);
		lblVersion.setVisible(true);

		lblServerIpValue = new JLabel(LoginCredential.getLocalIpAdd());
		lblServerIpValue.setBounds(620, 5, 120, 22);
		lblServerIpValue.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		lblServerIpValue.setForeground(color2);
		CommonMethods.setIcon(ImageResources.LOCALIPICON, lblServerIpValue);
		lblServerIpValue.setVisible(true);

		String localdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a"));
		lblDateTime = new JLabel(localdate);
		lblDateTime.setBounds(780, 5, 170, 22);
		lblDateTime.setFont(skywingsFont.getFont(FontStyle.BOLD, 16));
		lblDateTime.setForeground(color2);
		lblDateTime.setHorizontalAlignment(JLabel.RIGHT);
		CommonMethods.setIcon(ImageResources.DATEICON, lblDateTime);
		lblDateTime.setVisible(true);
		new Timer().scheduleAtFixedRate(new TimerJob(lblDateTime), Calendar.getInstance().getTime(), 1);

		panelTitle.add(lblOperatorLabel);
		panelTitle.add(lblServerIpValue);
		panelTitle.add(lblDateTime);

		panelTitle.add(lblVersion);

		getContentPane().add(panelTitle);

		return panelTitle;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnEntryAdd) {

			tbladd = true;

			tblCreation(tbladd);

			spnTranDate.setEnabled(false);
			cmbAcctName.setEnabled(false);
			cmbRoute.setEnabled(false);
			cmbSalesType.setEnabled(false);
			cmbSaleman.setEnabled(false);

			cmbProduct.requestFocus();
			txtQty.setText("");
			txtMRP.setText("");
			txtRate.setText("");
			txtNetAmount.setText("");
			txtTax.setText("");
			txtGrossAmt.setText("");
			txtNarration.setText("");

		} else if (e.getSource() == btnEntryClear) {

			spnTranDate.setEnabled(true);
			cmbAcctName.setEnabled(true);
			cmbRoute.setEnabled(true);
			cmbSalesType.setEnabled(true);
			cmbSaleman.setEnabled(true);
			txtQty.setText("");
			txtMRP.setText("");
			txtRate.setText("");
			txtNetAmount.setText("");
			txtTax.setText("");
			txtGrossAmt.setText("");
			txtNarration.setText("");
			cmbAcctName.setSelectedIndex(0);
			cmbRoute.setSelectedIndex(0);
			cmbSalesType.setSelectedIndex(0);
			cmbSaleman.setSelectedIndex(0);
			cmbProduct.setSelectedIndex(0);
			cmbSubProduct.setSelectedIndex(0);
			cmbUnit.setSelectedIndex(0);

			spnTranDate.requestFocus();

		}

		else if (e.getSource() == btnSave) {

			salesLogic.transactionSave(lstSales);

			loadInitialize();

			clearTableData();

			txtVechileNo.setText("");

		} else if (e.getSource() == btnAdd) {

			enableAllComponents(panelContent);

			panelContent.setEnabled(true);
			btnCancel.setEnabled(true);
			spnTranDate.requestFocus();
			txtTranNo.setEnabled(false);

		} else if (e.getSource() == btnExit) {

			clearTableData();
			setVisible(false);

			FrmMainMenu frmMainMenu = Applicationmain.getAbstractApplicationContext().getBean(FrmMainMenu.class);
			frmMainMenu.setVisible(true);

		} else if (e.getSource() == btnCancel) {

			loadInitialize();
			clearTableData();
			txtVechileNo.setText("");
		} else if (e.getSource() == btnEntryView) {

			panelContent.setEnabled(false);

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (e.getSource() == txtTranNo) {
				spnTranDate.requestFocus();
			} else if (e.getSource() == cmbAcctName) {
				cmbRoute.requestFocus();
			} else if (e.getSource() == cmbRoute) {
				cmbSalesType.requestFocus();
			} else if (e.getSource() == cmbSalesType) {
				cmbSaleman.requestFocus();
			} else if (e.getSource() == cmbSaleman) {
				cmbProduct.requestFocus();
			} else if (e.getSource() == cmbProduct) {
				cmbSubProduct.requestFocus();
			} else if (e.getSource() == cmbSubProduct) {
				txtQty.requestFocus();
			} else if (e.getSource() == txtQty) {
				cmbUnit.requestFocus();
			} else if (e.getSource() == cmbUnit) {
				txtMRP.requestFocus();
			} else if (e.getSource() == txtMRP) {
				txtRate.requestFocus();
			} else if (e.getSource() == txtRate) {

				double netAmt = Double.valueOf(txtQty.getText()) * Double.valueOf(txtRate.getText());
				txtNetAmount.setText(String.valueOf(df.format(netAmt)));

				double taxAmt = netAmt * 0.05;
				txtTax.setText(String.valueOf(df.format(taxAmt)));

				double totAmt = netAmt + taxAmt;
				txtGrossAmt.setText(String.valueOf(df.format(totAmt)));

				txtNetAmount.setEnabled(false);
				txtTax.setEnabled(false);
				txtGrossAmt.setEnabled(false);
				txtNarration.requestFocus();

			} else if (e.getSource() == txtNetAmount) {
				txtTax.requestFocus();
			} else if (e.getSource() == txtTax) {
				txtGrossAmt.requestFocus();
			} else if (e.getSource() == txtGrossAmt) {
				txtNarration.requestFocus();
			} else if (e.getSource() == txtNarration) {
				txtVechileNo.requestFocus();
			} else if (e.getSource() == txtVechileNo) {
				btnEntryAdd.requestFocus();
			} else if (e.getSource() == btnEntryAdd) {
				btnSave.setEnabled(true);
				btnSave.requestFocus();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void addFocusListeners() {
		txtQty.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				formatTextField(e);

			}
		});

		txtMRP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				String text = txtMRP.getText();
				if (!text.isEmpty()) {
					double value = Double.parseDouble(text);
					txtMRP.setText(nf.format(value));
				}
			}
		});

		txtRate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				formatTextField(e);
			}
		});

		txtNetAmount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				formatTextField(e);
			}
		});

		txtTax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				formatTextField(e);
			}
		});

		txtGrossAmt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				formatTextField(e);
			}
		});
	}

	private void formatTextField(FocusEvent e) {
		try {
			String text = ((JTextField) e.getSource()).getText();

			if (!text.isEmpty()) {
				double value = Double.parseDouble(text);
				((JTextField) e.getSource()).setText(df.format(value));
			}
		} catch (NumberFormatException ex) {
			((JTextField) e.getSource()).setText("");
		}
	}

}
