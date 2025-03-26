package com.skywings.gbilling.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.common.Buttons;
import com.skywings.gbilling.common.CommonMethods;
import com.skywings.gbilling.common.ControlResize;
import com.skywings.gbilling.common.ImageLoad;
import com.skywings.gbilling.common.ImageResources;
import com.skywings.gbilling.common.LoginCredential;
import com.skywings.gbilling.common.SkywingsFont;
import com.skywings.gbilling.common.SkywingsFont.FontStyle;
import com.skywings.gbilling.common.TimerJob;
import com.skywings.gbilling.start.Applicationmain;

@Component
public class FrmMDI extends JFrame implements ActionListener, KeyListener {

	private JPanel panelMain;
	private JPanel panelTitle;
	private JPanel panelLine;
	private JPanel panelDetail;
	private JPanel panelLine2;
	private JPanel panelContent;
	private JPanel panelLogin;
	private JPanel panelForm;
	private JPanel panelLine3;
	private JPanel panelButtons;

	private JMenuBar jMenuBar;
	private JMenu mnuMaster;
	private JMenuItem mnuExit;
	private JMenuItem mnuItemOperator;
	private JMenuItem mnuItemProduct;
	private JMenuItem mnuItemSubProduct;
	private JMenuItem mnuItemVendor;

	private JLabel lblHeading;
	private JLabel lblCompanyName;
	private JLabel lblLoginImage;
	private JLabel lblUserName;
	private JLabel lblpassword;
	private JLabel userLogin;
	private JLabel lblMenuName;
	private String operMenu = "Master >> OperatorDetails ";
	private String proMenu = "Master >> ProductDetails ";
	private String subproMenu = "Master >> SubProductDetails ";
	private String vendorMenu = "Master >> VendorDetails ";

	private JLabel lblOperatorLabel;
	private JLabel lblVersion;
	private JLabel lblServerIpValue;
	private JLabel lblDateTime;
	private JLabel lblMinimize;
	private JLabel lblDevelopedby;

	public static JButton btnAdd;
	public static JButton btnSave;
	public static JButton btnView;
	public static JButton btnEdit;
	public static JButton btnBack;
	public static JButton btnCancel;
	public static JButton btnClose;
	public static JButton btnUpdate;

	private Color color1 = Color.decode("#3eb489");
	private Color color2 = Color.decode("#FFFFFF");
	private Color color3 = Color.decode("#73bda2");
	private Color color4 = Color.decode("#cdcdcd");
	private Color color5 = Color.decode("#e4dedf");
	private Color color6 = Color.decode("#b43e69");
	private Color color7 = Color.decode("#ADD8E6");

	private Color fontColor1 = Color.decode("#17202A");

	private ControlResize controlResize;

	@Autowired
	private FrmOperator frmOperator;
	@Autowired
	private FrmProduct frmProduct;
	@Autowired
	private FrmSubproduct frmSubproduct;
	@Autowired
	private FrmVendorDetails frmVendorDetails;
	@Autowired
	private FrmMainMenu frmMainMenu;

	private SkywingsFont skywingFont = new SkywingsFont();

	public FrmMDI() throws Exception {

		setTitle("Master");
		setLayout(null);
		setUndecorated(true);
		setDefaultCloseOperation(0);
		getContentPane().setPreferredSize(new Dimension(958, 728));

		setUndecorated(true);
		pack();

		createControls();

		controlResize = new ControlResize(this);
		setSize(controlResize.getRectangle().getSize());
		controlResize.reAlignControls();

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
		panelMain.add(PanelButtons());

		panelMain.add(panelContentInitalize());
		panelMain.add(panelLine3Inialize());

		panelButtons.setVisible(false);

		getContentPane().add(panelMain);

	}

	private java.awt.Component PanelButtons() {
		int btnWidth = 50;
		int btnHeight = 20;
		panelButtons = new JPanel();
		panelButtons.setBounds(0, 630, 1000, 40);
		panelButtons.setLayout(null);
		panelButtons.setBackground(color3);
		// panelButton.setBorder(BorderFactory.createEtchedBorder(color3, color3));

		btnAdd = new JButton("Add");
		btnAdd.setHorizontalAlignment(SwingConstants.CENTER);
		btnAdd.setBounds(50, 10, btnWidth, btnHeight);
		btnAdd.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnAdd.setMnemonic(KeyEvent.VK_A);
		btnAdd.setBackground(color1);
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setVisible(true);
		btnAdd.addActionListener(this);
		btnAdd.setVerifyInputWhenFocusTarget(false);
		btnAdd.addKeyListener(this);

		btnSave = new JButton("Save");
		btnSave.setHorizontalAlignment(SwingConstants.CENTER);
		btnSave.setBounds(btnAdd.getX() + btnWidth + 80, btnAdd.getY(), btnWidth, btnHeight);
		btnSave.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnSave.setBackground(color1);
		btnSave.setForeground(Color.BLACK);
		btnSave.setVisible(true);
		btnSave.addActionListener(this);
		btnSave.addKeyListener(this);

		btnUpdate = new JButton("Update");
		btnUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		btnUpdate.setBounds(btnAdd.getX() + btnWidth + 80, btnAdd.getY(), btnWidth + 10, btnHeight);
		btnUpdate.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnUpdate.setBackground(color1);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setVisible(false);
		btnUpdate.addActionListener(this);
		btnUpdate.addKeyListener(this);

		btnView = new JButton("View");
		btnView.setHorizontalAlignment(SwingConstants.CENTER);
		btnView.setBounds(btnSave.getX() + btnWidth + 80, btnSave.getY(), btnWidth, btnHeight);
		btnView.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnView.setMnemonic(KeyEvent.VK_V);
		btnView.setBackground(color1);
		btnView.setForeground(Color.BLACK);
		btnView.setVisible(true);
		btnView.addActionListener(this);
		btnView.setVerifyInputWhenFocusTarget(false);
		btnView.addKeyListener(this);

		btnEdit = new JButton("Edit");
		btnEdit.setHorizontalAlignment(SwingConstants.CENTER);
		btnEdit.setBounds(btnView.getX() + btnWidth + 80, btnView.getY(), btnWidth, btnHeight);
		btnEdit.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnEdit.setMnemonic(KeyEvent.VK_E);
		btnEdit.setBackground(color1);
		btnEdit.setForeground(Color.BLACK);
		btnEdit.setVisible(true);
		btnEdit.addActionListener(this);
		btnEdit.setVerifyInputWhenFocusTarget(false);
		btnEdit.addKeyListener(this);

		btnCancel = new JButton("Cancel");
		btnCancel.setHorizontalAlignment(SwingConstants.CENTER);
		btnCancel.setBounds(btnEdit.getX() + btnWidth + 80, btnEdit.getY(), btnWidth, btnHeight);
		btnCancel.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnCancel.setMnemonic(KeyEvent.VK_C);
		btnCancel.setBackground(color1);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setVisible(true);
		btnCancel.addActionListener(this);
		btnCancel.setVerifyInputWhenFocusTarget(false);
		btnCancel.addKeyListener(this);

		btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setBounds(btnCancel.getX() + btnWidth + 80, btnCancel.getY(), btnWidth, btnHeight);
		btnBack.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnBack.setMnemonic(KeyEvent.VK_B);
		btnBack.setBackground(color1);
		btnBack.setForeground(Color.BLACK);
		btnBack.setVisible(true);
		btnBack.addActionListener(this);
		btnBack.setVerifyInputWhenFocusTarget(false);
		btnBack.addKeyListener(this);

		btnClose = new JButton("Close");
		btnClose.setHorizontalAlignment(SwingConstants.CENTER);
		btnClose.setBounds(btnBack.getX() + btnWidth + 80, btnBack.getY(), btnWidth, btnHeight);
		btnClose.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		btnClose.setBackground(color1);
		btnClose.setForeground(Color.BLACK);
		btnClose.setMnemonic(KeyEvent.VK_L);
		btnClose.setMargin(new Insets(0, 0, 0, 0));
		btnClose.setVisible(true);
		btnClose.addActionListener(this);
		btnClose.setVerifyInputWhenFocusTarget(false);
		btnClose.addKeyListener(this);

		panelMain.add(panelButtons);
		panelButtons.add(btnAdd);
		panelButtons.add(btnSave);
		panelButtons.add(btnUpdate);
		panelButtons.add(btnView);
		panelButtons.add(btnEdit);
		panelButtons.add(btnCancel);
		panelButtons.add(btnBack);
		panelButtons.add(btnClose);
		return panelButtons;

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

	private java.awt.Component panelContentInitalize() {

		panelForm = new JPanel(null);
		panelForm.setBounds(0, panelLine2.getY() + panelLine2.getHeight(), 958, 520);
		panelForm.setVisible(true);
		panelForm.setBackground(color2);
		panelMain.add(panelForm);

		return panelForm;
	}

	private java.awt.Component panelLine2Initialize() {

		panelLine2 = new JPanel();
		panelLine2.setBounds(panelDetail.getX(), panelDetail.getY() + 30, 958, 3);
		panelLine2.setLayout(null);
		panelLine2.setBackground(color5);
		panelLine2.setVisible(true);

		panelMain.add(panelLine2);

		return panelLine2;
	}

	private java.awt.Component panelDetailInitialize() {

		panelDetail = new JPanel();
		panelDetail.setBounds(panelLine.getX(), panelLine.getY() + 50, 958, 30);
		panelDetail.setLayout(null);
		panelDetail.setBackground(color2);
		panelDetail.setVisible(true);

		lblMenuName = new JLabel("");
		lblMenuName.setBounds(20, -15, 170, 50);
		// lblHeading.setBounds(panelDetail.getWidth() / 2, panelDetail.getY() / 2, 20,
		// 20);
		lblMenuName.setFont(skywingFont.getFont(FontStyle.PLAIN, 20));
		lblMenuName.setForeground(fontColor1);
		lblMenuName.setVisible(true);

		lblHeading = new JLabel("");
		lblHeading.setBounds(420, -10, 170, 50);
		// lblHeading.setBounds(panelDetail.getWidth() / 2, panelDetail.getY() / 2, 20,
		// 20);
		lblHeading.setFont(skywingFont.getFont(FontStyle.BOLD, 23));
		lblHeading.setForeground(fontColor1);
		lblHeading.setVisible(true);

		lblCompanyName = new JLabel("");
		lblCompanyName = new JLabel("SREE SENTHOOR MURUGAN TRADERS");
		lblCompanyName.setBounds(lblHeading.getX() + 350, lblHeading.getY(), 260, 50);
		// lblHeading.setBounds(panelDetail.getWidth() / 2, panelDetail.getY() / 2, 20,
		// 20);
		lblCompanyName.setFont(skywingFont.getFont(FontStyle.BOLD, 23));
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
		panelDetail.add(lblMenuName);

		panelMain.add(panelDetail);

		return panelDetail;
	}

	private java.awt.Component panelLineInialize() {

		ImageLoad imageLoad = new ImageLoad();

		panelLine = new JPanel();
		panelLine.setBounds(0, 30, 958, 6);
		panelLine.setLayout(null);
		panelLine.setBackground(color3);
		panelLine.setVisible(true);

		jMenuBar = new JMenuBar();
		jMenuBar.setBounds(0, 30, 958, 30);
		jMenuBar.setBackground(color2);
		jMenuBar.setBorder(null);
		jMenuBar.setVisible(true);

		mnuMaster = new JMenu("Master");
		mnuMaster.setBounds(0, 30, 958, 20);
		mnuMaster.setBackground(color2);
		mnuMaster.setBorder(null);
		mnuMaster.setVisible(true);

		imageLoad.load(mnuMaster);

		mnuExit = new JMenuItem("Exit");
		mnuExit.setBounds(0, 30, 958, 20);
		mnuExit.setBackground(color2);
		mnuExit.setBorder(null);
		mnuExit.setVisible(true);
		mnuExit.addActionListener(this);

		imageLoad.loadMenuItem(mnuExit);

		mnuItemOperator = new JMenuItem("Operator Details");
		mnuItemOperator.setBounds(0, 30, 958, 20);
		mnuItemOperator.setBackground(color2);
		mnuItemOperator.setBorder(null);
		mnuItemOperator.setVisible(true);
		mnuItemOperator.addActionListener(this);

		imageLoad.loadMenuItem(mnuItemOperator);

		mnuItemProduct = new JMenuItem("Product Details");
		mnuItemProduct.setBounds(0, 30, 958, 20);
		mnuItemProduct.setBackground(color2);
		mnuItemProduct.setBorder(null);
		mnuItemProduct.setVisible(true);
		mnuItemProduct.addActionListener(this);

		imageLoad.loadMenuItem(mnuItemProduct);

		mnuItemSubProduct = new JMenuItem("SubProduct Details");
		mnuItemSubProduct.setBounds(0, 30, 958, 20);
		mnuItemSubProduct.setBackground(color2);
		mnuItemSubProduct.setBorder(null);
		mnuItemSubProduct.setVisible(true);
		mnuItemSubProduct.addActionListener(this);

		imageLoad.loadMenuItem(mnuItemSubProduct);

		mnuItemVendor = new JMenuItem("Vendor Details");
		mnuItemVendor.setBounds(0, 30, 958, 20);
		mnuItemVendor.setBackground(color2);
		mnuItemVendor.setBorder(null);
		mnuItemVendor.setVisible(true);
		mnuItemVendor.addActionListener(this);

		imageLoad.loadMenuItem(mnuItemVendor);

		panelMain.add(jMenuBar);
		jMenuBar.add(mnuMaster);
		jMenuBar.add(mnuExit);
		mnuMaster.add(mnuItemOperator);
		mnuMaster.add(mnuItemProduct);
		mnuMaster.add(mnuItemSubProduct);
		mnuMaster.add(mnuItemVendor);

//		panelMain.add(panelLine);
		return jMenuBar;
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
		lblOperatorLabel.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		lblOperatorLabel.setForeground(color2);
		CommonMethods.setIcon(ImageResources.SERVERIPICON, lblOperatorLabel);
		lblOperatorLabel.setVisible(true);

		lblVersion = new JLabel(Applicationmain.VERSION);
		lblVersion.setBounds(170, 5, 100, 22);

		lblVersion.setForeground(color2);
		CommonMethods.setIcon(ImageResources.VERSIONICON, lblVersion);
		lblVersion.setVisible(true);

		lblServerIpValue = new JLabel(LoginCredential.getLocalIpAdd());
		lblServerIpValue.setBounds(620, 5, 120, 22);
		lblServerIpValue.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		lblServerIpValue.setForeground(color2);
		CommonMethods.setIcon(ImageResources.LOCALIPICON, lblServerIpValue);
		lblServerIpValue.setVisible(true);

		String localdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a"));
		lblDateTime = new JLabel(localdate);
		lblDateTime.setBounds(780, 5, 170, 22);
		lblDateTime.setFont(skywingFont.getFont(FontStyle.BOLD, 16));
		lblDateTime.setForeground(color2);
		lblDateTime.setHorizontalAlignment(JLabel.RIGHT);
		CommonMethods.setIcon(ImageResources.DATEICON, lblDateTime);
		lblDateTime.setVisible(true);
		new Timer().scheduleAtFixedRate(new TimerJob(lblDateTime), Calendar.getInstance().getTime(), 1);

		panelTitle.add(lblOperatorLabel);
		panelTitle.add(lblServerIpValue);
		panelTitle.add(lblDateTime);

		panelTitle.add(lblVersion);

		panelMain.add(panelTitle);

		return panelTitle;

	}

	public void frmclose() {

		dispose();
		setVisible(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mnuExit) {

			setVisible(false);

			FrmMainMenu frmMenu = Applicationmain.getAbstractApplicationContext().getBean(FrmMainMenu.class);
			frmMenu.setVisible(true);

		}

		if (e.getSource() == mnuItemOperator) {

			if (null == frmOperator) {
				frmOperator = Applicationmain.getAbstractApplicationContext().getBean(FrmOperator.class);
			}
			panelForm.removeAll();
			panelForm.add(frmOperator);
			frmOperator.setVisible(true);
			panelButtons.setVisible(true);
			mnuMaster.setVisible(false);
			mnuExit.setVisible(false);
			btnAdd.requestFocus();
			lblMenuName.setText(operMenu);
			buttonVisibilityMasterLoad();
			btnBack.setEnabled(false);
			btnEdit.setEnabled(false);

			Applicationmain.currentForm = frmOperator;
		} else if (e.getSource() == mnuItemProduct) {

			if (null == frmProduct) {
				frmProduct = Applicationmain.getAbstractApplicationContext().getBean(FrmProduct.class);
			}
			panelForm.removeAll();
			panelForm.add(frmProduct);
			frmProduct.setVisible(true);
			panelButtons.setVisible(true);
			mnuMaster.setVisible(false);
			mnuExit.setVisible(false);
			btnAdd.requestFocus();
			lblMenuName.setText(proMenu);
			buttonVisibilityMasterLoad();
			btnBack.setEnabled(false);
			btnEdit.setEnabled(false);

			Applicationmain.currentForm = frmProduct;
		} else if (e.getSource() == mnuItemSubProduct) {

			if (null == frmSubproduct) {
				frmSubproduct = Applicationmain.getAbstractApplicationContext().getBean(FrmSubproduct.class);
			}
			panelForm.removeAll();
			panelForm.add(frmSubproduct);
			frmSubproduct.setVisible(true);
			panelButtons.setVisible(true);
			mnuMaster.setVisible(false);
			mnuExit.setVisible(false);
			btnAdd.requestFocus();
			lblMenuName.setText(subproMenu);
			buttonVisibilityMasterLoad();
			btnBack.setEnabled(false);
			btnEdit.setEnabled(false);

			Applicationmain.currentForm = frmSubproduct;
		} else if (e.getSource() == mnuItemVendor) {

			if (null == frmVendorDetails) {
				frmVendorDetails = Applicationmain.getAbstractApplicationContext().getBean(FrmVendorDetails.class);
			}
			panelForm.removeAll();
			panelForm.add(frmVendorDetails);
			frmVendorDetails.setVisible(true);
			panelButtons.setVisible(true);
			mnuMaster.setVisible(false);
			mnuExit.setVisible(false);
			btnAdd.requestFocus();
			lblMenuName.setText(vendorMenu);
			buttonVisibilityMasterLoad();
			btnBack.setEnabled(false);
			btnEdit.setEnabled(false);

			Applicationmain.currentForm = frmVendorDetails;
		}

		if (e.getSource() == btnClose) {

			try {
				Applicationmain.currentForm.formClose();
			} catch (Exception e1) {
				Applicationmain.showMessage(e1.getMessage());
			}

		} else if (e.getSource() == btnSave) {

			try {
				Applicationmain.currentForm.formSave();
			} catch (Exception e1) {
				Applicationmain.showMessage(e1.getMessage());
			}
		} else if (e.getSource() == btnView) {

			try {

				btnSave.setEnabled(false);
				btnCancel.setEnabled(false);
				btnEdit.setEnabled(true);
				btnBack.setEnabled(true);
				Applicationmain.currentForm.formView();
			} catch (Exception e1) {
				Applicationmain.showMessage(e1.getMessage());

			}
		} else if (e.getSource() == btnCancel) {

			try {
				btnAdd.requestFocus();
				Applicationmain.currentForm.formCancel();
			} catch (Exception e1) {
				Applicationmain.showMessage(e1.getMessage());
			}
		}

		else if (e.getSource() == btnAdd) {

			try {
				Applicationmain.currentForm.formAdd();
			} catch (Exception e1) {
				e1.printStackTrace();
				Applicationmain.showMessage(e1.getMessage());
			}

		} else if (e.getSource() == btnEdit) {

			try {

				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				btnBack.setEnabled(false);
				btnEdit.setEnabled(false);
				Applicationmain.currentForm.formEdit();

			} catch (Exception e1) {
				e1.printStackTrace();
				Applicationmain.showMessage(e1.getMessage());
			}

		} else if (e.getSource() == btnBack) {

			try {
				btnSave.setEnabled(true);
				btnCancel.setEnabled(true);
				btnBack.setEnabled(false);
				btnEdit.setEnabled(false);

				Applicationmain.currentForm.formBack();
			} catch (Exception e1) {
				e1.printStackTrace();
				Applicationmain.showMessage(e1.getMessage());
			}

		}

	}

	public static void buttonVisibilityMasterLoad() {
		buttonVisibility(Buttons.SAVE);
		buttonVisibility(Buttons.VIEW);
		buttonVisibility(Buttons.CLEAR);
		buttonVisibility(Buttons.EXIT);

	}

	private static void buttonVisibility(Buttons buttons) {
		try {
			switch (buttons) {
			case ADD:
				btnAdd.setEnabled(true);
				break;
			case SAVE:
				btnSave.setEnabled(true);
				break;
			case UPDATE:
				btnUpdate.setEnabled(true);
				break;
			case VIEW:
				btnView.setEnabled(true);
				break;
			case CLEAR:
				btnCancel.setEnabled(true);
				break;
			case EXIT:
				btnClose.setEnabled(true);
				break;
			case BACK:
				btnBack.setEnabled(true);
				break;
			default:
				break;
			}

		} catch (Exception e) {
		}
	}

}
