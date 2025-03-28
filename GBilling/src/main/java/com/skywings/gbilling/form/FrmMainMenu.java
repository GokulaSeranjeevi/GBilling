package com.skywings.gbilling.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.common.CommonMethods;
import com.skywings.gbilling.common.ControlResize;
import com.skywings.gbilling.common.ImageResources;
import com.skywings.gbilling.common.LoginCredential;
import com.skywings.gbilling.common.SkywingsFont;
import com.skywings.gbilling.common.SkywingsFont.FontStyle;
import com.skywings.gbilling.common.TimerJob;
import com.skywings.gbilling.start.Applicationmain;

@Component
@Scope("prototype")
public class FrmMainMenu extends JFrame implements ActionListener {

	private JPanel panelMain;
	private JPanel panelTitle;
	private JPanel panelLine;
	private JPanel panelDetail;
	private JPanel panelLine2;
	private JPanel panelContent;
	private JPanel panelLogin;
	private JPanel panelLine3;
	private JPanel panelMenu;
	private JPanel panelShortcut;
	private JPanel panelPending;

	private JLabel lblHeading;
	private JLabel lblCompanyName;

	private JLabel lblLoginImage;
	private JLabel lblUserName;
	private JLabel lblpassword;
	private JLabel userLogin;
	private JLabel lblDevelopedby;
	private JLabel lblOperatorLabel;
	private JLabel lblVersion;
	private JLabel lblServerIpValue;
	private JLabel lblDateTime;
	private JLabel lblPendingHead;
	private JLabel lblPendingCalls;
	private JLabel lblPendingDetails;
	private JLabel lblDayHead;
	private JLabel lblDayDetail;
	private JLabel lblDayCalls;
	private JLabel lblModuleHead;
	private JLabel lblModuleDetail;
	private JLabel lblModuleCalls;
	private JLabel lblMinimize;
	private JLabel gifLabel;

	private JScrollPane scrlPendingCalls;
	private JScrollPane scrlDayCalls;
	private JScrollPane scrlModuleCalls;

	private JButton btnMaster;
	private JButton btnSalesEntry;
	private JButton btnEInvoice;
	private JButton btnReports;
	private JButton btnReadyCalls;
	private JButton btnCompletedCalls;
	private JButton btnDataMaintenance;
	private JButton btnDailyActivity;
	private JButton btnVersionUpgrade;
	private JButton btnDataValidation;

	private JButton btnLogOut;
	private JButton btnExit;

	private Color color1 = Color.decode("#2E86C1");
	private Color color2 = Color.decode("#FFFFFF");
	private Color color3 = Color.decode("#5DADE2");
	private Color color4 = Color.decode("#AED6F1");
	private Color color5 = Color.decode("#e4dedf");
	private Color color6 = Color.decode("#004466");

	private Color fontColor1 = Color.decode("#17202A");
	private Color fontColor2 = Color.decode("#3B3B3B");

	private SkywingsFont skywingsFont = new SkywingsFont();
	private ControlResize controlResize;

//	@Autowired
//	private LogicMainMenu logicMainMenu;
	@Autowired
	private CommonMethods commonMethods;

	public FrmMainMenu() throws Exception {

		setTitle("Calls");
		getContentPane().setPreferredSize(new Dimension(958, 728));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		setUndecorated(true);
		pack();

		createControls();
		createListeners();

		controlResize = new ControlResize(this);
		setSize(controlResize.getRectangle().getSize());
		controlResize.reAlignControls();

	}

	@PostConstruct
	private void loadDetails() {

		lblServerIpValue.setText(commonMethods.getIpAddress());

		/*
		 * lstPendingCalls = logicMainMenu.loadPendingCalls();
		 * 
		 * List<Object> lstobject = null;
		 * 
		 * for (Calls calls : lstPendingCalls) {
		 * 
		 * lstobject = new ArrayList<Object>();
		 * 
		 * lstobject.add(calls.getMnudescription());
		 * lstobject.add(calls.getMnuNoOfCalls());
		 * 
		 * tblPendingCalls.addRow(lstobject);
		 * 
		 * }
		 */
		/*
		 * lstTodayCalls = logicMainMenu.loadTodayCalls();
		 * 
		 * List<Object> lstTodayobject = null;
		 * 
		 * for (Calls calls : lstTodayCalls) {
		 * 
		 * lstTodayobject = new ArrayList<Object>();
		 * 
		 * lstTodayobject.add(calls.getMnudescription());
		 * lstTodayobject.add(calls.getMnuNoOfCalls());
		 * 
		 * tblDayCalls.addRow(lstTodayobject);
		 * 
		 * }
		 * 
		 * lstModuleCalls = logicMainMenu.loadModuleCalls();
		 * 
		 * List<Object> lstModuleobject = null;
		 * 
		 * for (Calls calls : lstModuleCalls) {
		 * 
		 * lstModuleobject = new ArrayList<Object>();
		 * 
		 * lstModuleobject.add(calls.getMnudescription());
		 * lstModuleobject.add(calls.getMnuNoOfCalls());
		 * 
		 * tblModuleCalls.addRow(lstModuleobject);
		 * 
		 * }
		 */
	}

	private void createListeners() {

		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		gifLabel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					gifLabel.setVisible(false);
					panelContent.requestFocus();
				}
			}
		});

	}

	private void createControls() throws Exception {
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, 958, 728);
		panelMain.setLayout(null);
		panelMain.setBackground(color2);
		panelMain.add(panelTitleInialize());
		panelMain.add(panelLineInialize());
		panelMain.add(panelDetailInitialize());
		panelMain.add(panelLine2Initialize());
		panelMain.add(panelContentInitialize());
		panelMain.add(panelShortcutInitialize());
		panelMain.add(panelLine3Inialize());
		// panelMain.add(panelPendingCalls());

		getContentPane().add(panelMain);

	}

	private java.awt.Component panelShortcutInitialize() {

		panelShortcut = new JPanel();
		panelShortcut.setBounds(panelContent.getX() + panelContent.getWidth() + 35, panelLine2.getY() + 20, 250, 560);
		panelShortcut.setLayout(null);
		panelShortcut.setVisible(true);
		panelShortcut.setBackground(color2);
		panelShortcut.setBorder(BorderFactory.createEtchedBorder(color1, color1));

		/*
		 * lblHeading = new JLabel("PENDING CALLS SUMMARY"); lblHeading.setBounds(80,
		 * -10, 150, 50); lblHeading.setForeground(color6);
		 * lblHeading.setFont(skywingsFont.getFont(FontStyle.BOLD, 15));
		 * lblHeading.setVisible(true);
		 * 
		 * // panelPending = new JPanel(); // panelPending.setBounds(10,
		 * lblHeading.getY()+30, 230, 150); // panelPending.setLayout(null); //
		 * panelPending.setVisible(true); // panelPending.setBackground(color2); //
		 * panelPending.setBorder(BorderFactory.createEtchedBorder(color4, color4));
		 * 
		 * // panelShortcut.add(lblHeading); // panelShortcut.add(panelPending);
		 * 
		 * tblPendingCalls = new JilabaTable(getPendingCalls());
		 * tblPendingCalls.setAutoResizeMode(JilabaTable.AUTO_RESIZE_OFF);
		 * tblPendingCalls.getTableHeader().setReorderingAllowed(false);
		 * tblPendingCalls.getTableHeader().setFont(skywingsFont.getFont(FontStyle.BOLD,
		 * 16)); tblPendingCalls.setFont(skywingsFont.getFont(FontStyle.BOLD, 15));
		 * tblPendingCalls.getTableHeader().setForeground(color3);
		 * tblPendingCalls.getTableHeader().setBackground(Color.WHITE);
		 * tblPendingCalls.setRowHeight(20); tblPendingCalls.setVisible(true);
		 * 
		 * scrlPendingCalls = new JScrollPane(tblPendingCalls);
		 * scrlPendingCalls.setBounds(10, 30, 230, 150);
		 * scrlPendingCalls.getViewport().setBackground(tblPendingCalls.getTableHeader()
		 * .getBackground()); scrlPendingCalls.setVisible(true);
		 * 
		 * lblDayHead = new JLabel("TODAY CALLS SUMMARY"); lblDayHead.setBounds(85,
		 * tblPendingCalls.getY() + scrlPendingCalls.getHeight() + 20, 150, 50);
		 * lblDayHead.setForeground(color6);
		 * lblDayHead.setFont(skywingsFont.getFont(FontStyle.BOLD, 15));
		 * lblDayHead.setVisible(true);
		 * 
		 * tblDayCalls = new JilabaTable(getPendingCalls());
		 * tblDayCalls.setAutoResizeMode(JilabaTable.AUTO_RESIZE_OFF);
		 * tblDayCalls.getTableHeader().setReorderingAllowed(false);
		 * tblDayCalls.getTableHeader().setFont(skywingsFont.getFont(FontStyle.BOLD,
		 * 16)); tblDayCalls.setFont(skywingsFont.getFont(FontStyle.BOLD, 15));
		 * tblDayCalls.getTableHeader().setForeground(color3);
		 * tblDayCalls.getTableHeader().setBackground(Color.WHITE);
		 * tblDayCalls.setRowHeight(20); tblDayCalls.setVisible(true);
		 * 
		 * scrlDayCalls = new JScrollPane(tblDayCalls); scrlDayCalls.setBounds(10,
		 * lblDayHead.getY() + lblDayHead.getHeight() - 10, 230, 150);
		 * scrlDayCalls.getViewport().setBackground(tblDayCalls.getTableHeader().
		 * getBackground()); scrlDayCalls.setVisible(true);
		 * 
		 * lblModuleHead = new JLabel("MODULEWISE CALLS SUMMARY");
		 * lblModuleHead.setBounds(75, scrlDayCalls.getY() + scrlDayCalls.getHeight() -
		 * 10, 150, 50); lblModuleHead.setForeground(color6);
		 * lblModuleHead.setFont(skywingsFont.getFont(FontStyle.BOLD, 15));
		 * lblModuleHead.setVisible(true);
		 * 
		 * tblModuleCalls = new JilabaTable(getModuleCalls());
		 * tblModuleCalls.setAutoResizeMode(JilabaTable.AUTO_RESIZE_OFF);
		 * tblModuleCalls.getTableHeader().setReorderingAllowed(false);
		 * tblModuleCalls.getTableHeader().setFont(skywingsFont.getFont(FontStyle.BOLD,
		 * 16)); tblModuleCalls.setFont(skywingsFont.getFont(FontStyle.BOLD, 15));
		 * tblModuleCalls.getTableHeader().setForeground(color3);
		 * tblModuleCalls.getTableHeader().setBackground(Color.WHITE);
		 * tblModuleCalls.setRowHeight(20); tblModuleCalls.setVisible(true);
		 * 
		 * scrlModuleCalls = new JScrollPane(tblModuleCalls);
		 * scrlModuleCalls.setBounds(10, lblModuleHead.getY() +
		 * lblModuleHead.getHeight() - 10, 230, 150);
		 * scrlModuleCalls.getViewport().setBackground(tblModuleCalls.getTableHeader().
		 * getBackground()); scrlModuleCalls.setVisible(true);
		 */
		panelMain.add(panelShortcut);
//		panelShortcut.add(scrlPendingCalls);
//		panelShortcut.add(scrlDayCalls);
//		panelShortcut.add(scrlModuleCalls);
//		panelShortcut.add(lblHeading);
//		panelShortcut.add(lblDayHead);
//		panelShortcut.add(lblModuleHead);

		// Create sample data
		/*
		 * Object[][] data = { { "John", 25, "Male" }, { "Emily", 30, "Female" }, {
		 * "Tom", 35, "Male" } };
		 * 
		 * String[] columnHeaders = { "Name", "Age", "Gender" };
		 * 
		 * DefaultTableModel model = new DefaultTableModel(data.length,
		 * columnHeaders.length); model.setColumnIdentifiers(columnHeaders);
		 * 
		 * // Populate the table model with data by column for (int col = 0; col <
		 * columnHeaders.length; col++) { for (int row = 0; row < data.length; row++) {
		 * model.setValueAt(data[row][col], row, col); } }
		 * 
		 * // Create a JTable with the table model JTable table = new JTable(model);
		 * 
		 * // Create a row header using JList JList<String> rowHeader = new JList<>(new
		 * AbstractListModel<String>() { String[] headers = { "Row 1", "Row 2", "Row 3"
		 * };
		 * 
		 * public int getSize() { return headers.length; }
		 * 
		 * public String getElementAt(int index) { return headers[index]; } });
		 * 
		 * // Create a scroll pane to hold the table and row header JScrollPane
		 * scrollPane = new JScrollPane(table); scrollPane.setRowHeaderView(rowHeader);
		 * scrollPane.setBounds(10, 10, 200, 150); scrollPane.setVisible(true);
		 */

		return panelShortcut;
	}

	private JPanel panelContentInitialize() throws Exception {

		int btnX = 0;
		int btnY = 0;
		int btnWidth = 80;
		int btnHeight = 82;
		int btnHSpace = 100;
		int btnVSpace = 130;

		panelContent = new JPanel();
		panelContent.setLayout(null);
		panelContent.setBounds(0, 84, 658, 591);
		panelContent.setBackground(color2);
		panelContent.setVisible(true);

		panelMenu = new JPanel();
		panelMenu.setLayout(null);
		panelMenu.setBackground(color2);
		panelMenu.setLocation(0, 25);
		panelMenu.setSize(650, 580);
		panelMenu.setVisible(true);
		// panelMenu.setBorder(BorderFactory.createEtchedBorder(color1, color1));

		btnX = 30;
		btnY = 30;

		btnMaster = new JButton("Master");
		btnMaster.setOpaque(false);
		btnMaster.setContentAreaFilled(false);
		btnMaster.setHorizontalTextPosition(JButton.CENTER);
		btnMaster.setVerticalTextPosition(JButton.BOTTOM);
		btnMaster.setFocusable(false);
		btnMaster.setBorderPainted(true);
		btnMaster.setForeground(fontColor2);
		btnMaster.setBorder(BorderFactory.createLineBorder(Color.decode("#BF360C")));
		// btnMaster.setBorder(BorderFactory.createEtchedBorder(color1, color1));
		// btnMaster.setBorder(BorderFactory.createEmptyBorder());
		btnMaster.setMnemonic(KeyEvent.VK_M);
		btnMaster.setBounds(btnX, btnY, btnWidth, btnHeight);
		btnMaster.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CommonMethods.setIcon(ImageResources.MASTERICON, btnMaster);
		btnMaster.setVisible(true);
		btnMaster.addActionListener(this);

		btnX = btnX + btnHSpace;

		btnSalesEntry = new JButton("Sales Entry");
		btnSalesEntry.setOpaque(false);
		btnSalesEntry.setContentAreaFilled(false);
		btnSalesEntry.setHorizontalTextPosition(JButton.CENTER);
		btnSalesEntry.setVerticalTextPosition(JButton.BOTTOM);
		btnSalesEntry.setFocusable(false);
		btnSalesEntry.setBorderPainted(true);
		btnSalesEntry.setForeground(fontColor2);
		btnSalesEntry.setBorder(BorderFactory.createLineBorder(Color.decode("#FFA000")));
		btnSalesEntry.setMnemonic(KeyEvent.VK_S);
		btnSalesEntry.setBounds(btnX, btnY, btnWidth, btnHeight);
		btnSalesEntry.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CommonMethods.setIcon(ImageResources.SALESENTRYICON, btnSalesEntry);
		btnSalesEntry.setVisible(true);
		btnSalesEntry.addActionListener(this);

		btnX = btnX + btnHSpace;

		btnEInvoice = new JButton("E-Invoice Generation");
		btnEInvoice.setOpaque(false);
		btnEInvoice.setContentAreaFilled(false);
		btnEInvoice.setHorizontalTextPosition(JButton.CENTER);
		btnEInvoice.setVerticalTextPosition(JButton.BOTTOM);
		btnEInvoice.setFocusable(false);
		btnEInvoice.setBorderPainted(true);
		btnEInvoice.setForeground(fontColor2);
		btnEInvoice.setBorder(BorderFactory.createLineBorder(Color.decode("#3733FF")));
		btnEInvoice.setMnemonic(KeyEvent.VK_E);
		btnEInvoice.setBounds(btnX, btnY, btnWidth, btnHeight);
		btnEInvoice.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CommonMethods.setIcon(ImageResources.EINVOICE, btnEInvoice);
		btnEInvoice.setVisible(true);
		btnEInvoice.addActionListener(this);

		btnX = btnX + btnHSpace;

		btnReports = new JButton("Reports");
		btnReports.setOpaque(false);
		btnReports.setContentAreaFilled(false);
		btnReports.setHorizontalTextPosition(JButton.CENTER);
		btnReports.setVerticalTextPosition(JButton.BOTTOM);
		btnReports.setFocusable(false);
		btnReports.setBorderPainted(true);
		btnReports.setForeground(fontColor2);
		btnReports.setBorder(BorderFactory.createLineBorder(Color.decode("#009900")));
		btnReports.setMnemonic(KeyEvent.VK_R);
		btnReports.setBounds(btnX, btnY, btnWidth, btnHeight);
		btnReports.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CommonMethods.setIcon(ImageResources.REPORTS, btnReports);
		btnReports.setVisible(true);
		btnReports.addActionListener(this);

		btnX = btnX + btnHSpace;

		/*
		 * btnReadyCalls = new JButton("Ready Calls"); btnReadyCalls.setOpaque(false);
		 * btnReadyCalls.setContentAreaFilled(false);
		 * btnReadyCalls.setHorizontalTextPosition(JButton.CENTER);
		 * btnReadyCalls.setVerticalTextPosition(JButton.BOTTOM);
		 * btnReadyCalls.setFocusable(false); btnReadyCalls.setBorderPainted(true);
		 * btnReadyCalls.setForeground(fontColor2);
		 * btnReadyCalls.setBorder(BorderFactory.createLineBorder(Color.decode("#ff4000"
		 * ))); btnReadyCalls.setMnemonic(KeyEvent.VK_R); btnReadyCalls.setBounds(btnX,
		 * btnY, btnWidth, btnHeight); btnReadyCalls.setCursor(new
		 * Cursor(Cursor.HAND_CURSOR)); CommonMethods.setIcon(ImageResources.READYCALLS,
		 * btnReadyCalls); btnReadyCalls.setVisible(true);
		 * btnReadyCalls.addActionListener(this);
		 * 
		 * btnY = btnY + btnVSpace; btnX = 30;
		 * 
		 * btnCompletedCalls = new JButton("Completed Calls");
		 * btnCompletedCalls.setOpaque(false);
		 * btnCompletedCalls.setContentAreaFilled(false);
		 * btnCompletedCalls.setHorizontalTextPosition(JButton.CENTER);
		 * btnCompletedCalls.setVerticalTextPosition(JButton.BOTTOM);
		 * btnCompletedCalls.setFocusable(false);
		 * btnCompletedCalls.setBorderPainted(true);
		 * btnCompletedCalls.setForeground(fontColor2);
		 * btnCompletedCalls.setBorder(BorderFactory.createLineBorder(Color.decode(
		 * "#ff0066"))); btnCompletedCalls.setMnemonic(KeyEvent.VK_C);
		 * btnCompletedCalls.setBounds(btnX, btnY, btnWidth, btnHeight);
		 * btnCompletedCalls.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 * CommonMethods.setIcon(ImageResources.COMPLETEDCALLS, btnCompletedCalls);
		 * btnCompletedCalls.addActionListener(this);
		 * 
		 * btnX = btnX + btnHSpace;
		 * 
		 * btnDataMaintenance = new JButton("Database Maintain");
		 * btnDataMaintenance.setOpaque(false);
		 * btnDataMaintenance.setContentAreaFilled(false);
		 * btnDataMaintenance.setHorizontalTextPosition(JButton.CENTER);
		 * btnDataMaintenance.setVerticalTextPosition(JButton.BOTTOM);
		 * btnDataMaintenance.setFocusable(false);
		 * btnDataMaintenance.setBorderPainted(true);
		 * btnDataMaintenance.setForeground(fontColor2);
		 * btnDataMaintenance.setBorder(BorderFactory.createLineBorder(Color.decode(
		 * "#16b797"))); btnDataMaintenance.setMnemonic(KeyEvent.VK_M);
		 * btnDataMaintenance.setBounds(btnX, btnY, btnWidth, btnHeight);
		 * btnDataMaintenance.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 * CommonMethods.setIcon(ImageResources.DATAMAINTENANCE, btnDataMaintenance);
		 * btnDataMaintenance.addActionListener(this);
		 * 
		 * btnX = btnX + btnHSpace;
		 * 
		 * btnDailyActivity = new JButton("Daily Activity");
		 * btnDailyActivity.setOpaque(false);
		 * btnDailyActivity.setContentAreaFilled(false);
		 * btnDailyActivity.setHorizontalTextPosition(JButton.CENTER);
		 * btnDailyActivity.setVerticalTextPosition(JButton.BOTTOM);
		 * btnDailyActivity.setFocusable(false);
		 * btnDailyActivity.setBorderPainted(true);
		 * btnDailyActivity.setForeground(fontColor2);
		 * btnDailyActivity.setBorder(BorderFactory.createLineBorder(Color.decode(
		 * "#b54d19"))); btnDailyActivity.setMnemonic(KeyEvent.VK_Y);
		 * btnDailyActivity.setBounds(btnX, btnY, btnWidth, btnHeight);
		 * btnDailyActivity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 * CommonMethods.setIcon(ImageResources.DAILYACTIVITY, btnDailyActivity);
		 * btnDailyActivity.addActionListener(this);
		 * 
		 * btnX = btnX + btnHSpace;
		 * 
		 * btnDataValidation = new JButton("Data Validation");
		 * btnDataValidation.setOpaque(false);
		 * btnDataValidation.setContentAreaFilled(false);
		 * btnDataValidation.setHorizontalTextPosition(JButton.CENTER);
		 * btnDataValidation.setVerticalTextPosition(JButton.BOTTOM);
		 * btnDataValidation.setFocusable(false);
		 * btnDataValidation.setBorderPainted(true);
		 * btnDataValidation.setForeground(fontColor2);
		 * btnDataValidation.setBorder(BorderFactory.createLineBorder(Color.decode(
		 * "#d34aae"))); btnDataValidation.setMnemonic(KeyEvent.VK_V);
		 * btnDataValidation.setBounds(btnX, btnY, btnWidth, btnHeight);
		 * btnDataValidation.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 * CommonMethods.setIcon(ImageResources.DATAVALIDATION, btnDataValidation);
		 * btnDataValidation.addActionListener(this);
		 * 
		 * btnX = btnX + btnHSpace;
		 * 
		 * btnVersionUpgrade = new JButton("Version Upgrade");
		 * btnVersionUpgrade.setOpaque(false);
		 * btnVersionUpgrade.setContentAreaFilled(false);
		 * btnVersionUpgrade.setHorizontalTextPosition(JButton.CENTER);
		 * btnVersionUpgrade.setVerticalTextPosition(JButton.BOTTOM);
		 * btnVersionUpgrade.setFocusable(false);
		 * btnVersionUpgrade.setBorderPainted(true);
		 * btnVersionUpgrade.setForeground(fontColor2);
		 * btnVersionUpgrade.setBorder(BorderFactory.createLineBorder(Color.decode(
		 * "#cc3300"))); btnVersionUpgrade.setMnemonic(KeyEvent.VK_L);
		 * btnVersionUpgrade.setBounds(btnX, btnY, btnWidth, btnHeight);
		 * btnVersionUpgrade.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 * CommonMethods.setIcon(ImageResources.VERSION, btnVersionUpgrade);
		 * btnVersionUpgrade.setVisible(true);
		 * btnVersionUpgrade.addActionListener(this);
		 */
		btnY = btnY + btnVSpace;
		btnX = 30;

		btnLogOut = new JButton("LogOut");
		btnLogOut.setOpaque(false);
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setHorizontalTextPosition(JButton.CENTER);
		btnLogOut.setVerticalTextPosition(JButton.BOTTOM);
		btnLogOut.setFocusable(false);
		btnLogOut.setBorderPainted(true);
		btnLogOut.setForeground(fontColor2);
		btnLogOut.setBorder(BorderFactory.createLineBorder(Color.decode("#cc3300")));
		btnLogOut.setMnemonic(KeyEvent.VK_L);
		btnLogOut.setBounds(btnX, btnY, btnWidth, btnHeight);
		btnLogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CommonMethods.setIcon(ImageResources.LOGOUT, btnLogOut);
		btnLogOut.setVisible(true);
		btnLogOut.addActionListener(this);

		btnX = btnX + btnHSpace;

		btnExit = new JButton("Exit");
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setHorizontalTextPosition(JButton.CENTER);
		btnExit.setVerticalTextPosition(JButton.BOTTOM);
		btnExit.setFocusable(false);
		btnExit.setBorderPainted(true);
		btnExit.setForeground(fontColor2);
		btnExit.setBorder(BorderFactory.createLineBorder(Color.decode("#333300")));
		btnExit.setMnemonic(KeyEvent.VK_X);
		btnExit.setBounds(btnX, btnY, btnWidth, btnHeight);
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		CommonMethods.setIcon(ImageResources.EXIT, btnExit);
		btnExit.setVisible(true);
		btnExit.addActionListener(this);

		// JFrame frame = new JFrame("");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setSize(600, 500);

		gifLabel = new JLabel("");
		gifLabel.setBounds(200, 80, 600, 500);
		gifLabel.setBackground(color1);
		gifLabel.setFocusable(true);
		gifLabel.setVisible(true); // Initially hide the GIF

		// frame.add(gifLabel);

		panelContent.add(gifLabel);
		panelContent.add(btnMaster);

		panelContent.add(btnSalesEntry);
		panelContent.add(btnEInvoice);
		panelContent.add(btnReports);
//		panelContent.add(btnReadyCalls);
//		panelContent.add(btnCompletedCalls);
//		panelContent.add(btnDataMaintenance);
//		panelContent.add(btnDailyActivity);
//		panelContent.add(btnVersionUpgrade);
//		panelContent.add(btnDataValidation);

		panelContent.add(btnLogOut);
		panelContent.add(btnExit);

		panelContent.add(panelMenu);
		panelMain.add(panelContent);

		return panelContent;
	}

	private JPanel panelLine3Inialize() throws Exception {

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

	private JPanel panelLine2Initialize() {

		panelLine2 = new JPanel();
		panelLine2.setBounds(panelDetail.getX(), panelDetail.getY() + 30, 958, 3);
		panelLine2.setLayout(null);
		panelLine2.setBackground(color5);
		panelLine2.setVisible(true);

		panelMain.add(panelLine2);

		return panelLine2;
	}

	private JPanel panelDetailInitialize() {
		panelDetail = new JPanel();
		panelDetail.setBounds(panelLine.getX(), panelLine.getY() + 30, 958, 30);
		panelDetail.setLayout(null);
		panelDetail.setBackground(color2);
		panelDetail.setVisible(true);

		lblHeading = new JLabel("'G' BILLING & MAINTANENCE");
		lblHeading.setBounds(20, -10, 170, 50);
		lblHeading.setFont(skywingsFont.getFont(FontStyle.BOLD, 23));
		lblHeading.setForeground(fontColor1);
		lblHeading.setVisible(true);

		lblCompanyName = new JLabel("SREE SENTHOOR MURUGAN TRADERS");
		lblCompanyName.setBounds(lblHeading.getX() + 750, lblHeading.getY(), 260, 50);
		// lblHeading.setBounds(panelDetail.getWidth() / 2, panelDetail.getY() / 2, 20,
		// 20);
		lblCompanyName.setFont(skywingsFont.getFont(FontStyle.BOLD, 23));
		lblCompanyName.setForeground(Color.decode("#FF0000"));
//		lblCompanyName.setVisible(true);

		// Create a Timer that fires every 1000 milliseconds (1 second)
		javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Toggle the visibility of the label on every timer tick
				lblCompanyName.setVisible(!lblCompanyName.isVisible());
			}
		});

		// Start the timer
		timer.start();

		panelDetail.add(lblHeading);
		panelDetail.add(lblCompanyName);

		panelMain.add(panelDetail);
		return panelLine;
	}

	private JPanel panelLineInialize() {

		panelLine = new JPanel();
		panelLine.setBounds(0, 30, 958, 6);
		panelLine.setLayout(null);
		panelLine.setBackground(color3);
		panelLine.setVisible(true);

		panelMain.add(panelLine);
		return panelLine;
	}

	private JPanel panelTitleInialize() throws Exception {

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

		try {

			if (e.getSource() == btnExit) {

				int response = JOptionPane.showConfirmDialog(panelMain, "Are You Sure To Exit?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {

					System.exit(0);
				} else {
					return;
				}
			} else if (e.getSource() == btnLogOut) {

				setVisible(false);

				FrmLogin frmLogin = Applicationmain.getAbstractApplicationContext().getBean(FrmLogin.class);
				frmLogin.setVisible(true);

			} else if (e.getSource() == btnSalesEntry) {

				setVisible(false);

				FrmSalesEntry frmSalesEntry = Applicationmain.getAbstractApplicationContext()
						.getBean(FrmSalesEntry.class);
				frmSalesEntry.setVisible(true);

			} else if (e.getSource() == btnEInvoice) {

				setVisible(false);
				FrmEInvoice frmEinvoice = Applicationmain.getAbstractApplicationContext()
						.getBean(FrmEInvoice.class);
				frmEinvoice.setVisible(true);

			} else if (e.getSource() == btnReports) {

				setVisible(false);

//				FrmDevCalls frmDevCalls = Applicationmain.getAbstractApplicationContext().getBean(FrmDevCalls.class);
//				frmDevCalls.setVisible(true);

			} else if (e.getSource() == btnReadyCalls) {

				setVisible(false);

//				FrmReadyCalls frmReadyCalls = Applicationmain.getAbstractApplicationContext()
//						.getBean(FrmReadyCalls.class);
//				frmReadyCalls.setVisible(true);

			} else if (e.getSource() == btnCompletedCalls) {

				setVisible(false);

//				FrmCallsCompleted frmCallsCompleted = Applicationmain.getAbstractApplicationContext()
//						.getBean(FrmCallsCompleted.class);
//
//				frmCallsCompleted.setVisible(true);

			} else if (e.getSource() == btnDataValidation) {

				if (FrmLogin.designation == 3) {

					setVisible(false);

//					FrmDataValidation frmDataValidation = Applicationmain.getAbstractApplicationContext()
//							.getBean(FrmDataValidation.class);
//
//					frmDataValidation.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(panelMain, "You dont have Rights ...!");
				}

			} else if (e.getSource() == btnMaster) {

				setVisible(false);

				FrmMDI frmMDI = Applicationmain.getAbstractApplicationContext().getBean(FrmMDI.class);
				frmMDI.setVisible(true);

			} else if (e.getSource() == btnDataMaintenance) {

				workinProgress();

			} else if (e.getSource() == btnVersionUpgrade) {

				int response = JOptionPane.showConfirmDialog(null, " Do you want to Version Upgrade?", "Close JAR File",
						JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {

//					FrmVersionUpgrade frmVersionUpgrade = new FrmVersionUpgrade();

					System.exit(0);

				} else {
					return;
				}

			} else if (e.getSource() == btnDailyActivity) {

				setVisible(false);

//				FrmDailyActivity frmDailyActivity = Applicationmain.getAbstractApplicationContext()
//						.getBean(FrmDailyActivity.class);
//				frmDailyActivity.setVisible(true);
			}

		} catch (Exception e2) {

			e2.printStackTrace();
		}
	}

	private void frmVersionUpgrade() {

		String sourcePath = "\\\\192.168.10.20\\jilabaexes\\Docs\\GokulaSeranjeevi V\\HHH\\Calls.jar"; // Source path
		String currentDir = System.getProperty("user.dir");
		String destinationPath = currentDir + File.separator + "Calls.jar";

		// String destinationPath = "\\\\172.16.10.97\\e\\Calls.jar"; // Destination
		// path

		try {
			copyJarFile(sourcePath, destinationPath);
			// JOptionPane.showMessageDialog(null, "", "JAR file copied successfully.", 0);

			openJarFile(destinationPath);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(panelMain, "Error occurred while copying the JAR file: " + e.getMessage());
		}
	}

	private static void copyJarFile(String source, String destination) throws IOException {
		Path sourcePath = Paths.get(source);
		Path destinationPath = Paths.get(destination);

		// Copy the file (overwriting if it exists)
		Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

	}

	private void openJarFile(String path) throws HeadlessException {
		File jarFile = new File(path);
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(jarFile);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(panelMain, "Error opening the JAR file: " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(panelMain, "Desktop is not supported on this platform.");
		}
	}

	private void workinProgress() {

		gifLabel.setVisible(true);
		gifLabel.requestFocusInWindow();
		ImageIcon gifIcon = new ImageIcon(
				"C:\\Users\\avrsw\\eclipse-workspace\\CallsEntry\\src\\main\\resources\\resource\\WIP.gif"); // Update
																												// with
																												// your
																												// GIF
																												// path
		gifLabel.setIcon(gifIcon);
		gifLabel.setVisible(true); // Show the GIF

		// Delay for 2 seconds (2000 milliseconds)
		// Thread.sleep(2000);

	}

}
