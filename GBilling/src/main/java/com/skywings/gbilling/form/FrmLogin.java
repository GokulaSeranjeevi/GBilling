package com.skywings.gbilling.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.skywings.gbilling.common.CommonMethods;
import com.skywings.gbilling.common.ControlResize;
import com.skywings.gbilling.common.ImageResources;
import com.skywings.gbilling.common.ListItem;
import com.skywings.gbilling.common.PasswordValidation;
import com.skywings.gbilling.common.SkywingsComboBox;
import com.skywings.gbilling.common.SkywingsFont;
import com.skywings.gbilling.common.SkywingsFont.FontStyle;
import com.skywings.gbilling.common.SkywingsPasswordField;
import com.skywings.gbilling.common.TimerJob;
import com.skywings.gbilling.logic.LoginLogic;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.start.Applicationmain;
import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
@Scope("prototype")
public class FrmLogin extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControlResize controlResize;
	@Autowired
	private CommonMethods commonMethods;

	private JPanel panelMain;
	private JPanel panelTitle;
	private JPanel panelLine;
	private JPanel panelDetail;
	private JPanel panelLine2;
	private JPanel panelContent;
	private JPanel panelLogin;
	private JPanel panelLine3;

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
	private JLabel lblMinimize;
	private JLabel lblBusinessLogo;

	public static String Operator;
	public static int OperCode;
	public static int designation;

	private SkywingsComboBox<String> cmbOperName;

	public JLabel getLblDateTime() {
		return lblDateTime;
	}

	public void setLblDateTime(JLabel lblDateTime) {
		this.lblDateTime = lblDateTime;
	}

	private SkywingsPasswordField txtPassword;

	private JButton btnLogin;
	private JButton btnExit;

	private List<Operator> Operators; // = new ArrayList<Operator>();

	private Color color1 = Color.decode("#2E86C1");
	private Color color2 = Color.decode("#FFFFFF");
	private Color color3 = Color.decode("#5DADE2");
	private Color color4 = Color.decode("#AED6F1");
	private Color color5 = Color.decode("#e4dedf");

	private Color fontColor1 = Color.decode("#17202A");

	private SkywingsFont skywingsFont = new SkywingsFont();

	@Autowired
	private LoginLogic logicLogin;

	List<Operator> lstOperDetails;
	/*
	 * @Autowired private TimerJob timerJob;
	 */

	public FrmLogin() throws Exception {

		setTitle("GBilling");
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

	private void createListeners() {

		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		cmbOperName.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					txtPassword.requestFocus();

				}

			}
		});
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnLogin.requestFocus();
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
		panelMain.add(panelContentInitoalize());
		panelMain.add(panelLine3Inialize());

		getContentPane().add(panelMain);

	}

	@PostConstruct
	private void loadDetails() {

		try {

			lblServerIpValue.setText(commonMethods.getIpAddress());
			// lblDateTime.setText(timerJob.getLblDateTime());

			Operators = logicLogin.getOperators();

			for (Operator oper : Operators) {
				cmbOperName.addListItem(new ListItem(oper.getOperNameS(), oper.getOperatorId()));

			}

		} catch (Exception e) {
			throw e;
		}

	}

	private Component panelLine3Inialize() throws Exception {

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

	// Method to apply Gaussian blur to an image
	public static BufferedImage applyGaussianBlur(BufferedImage image) {
		// Create a new image with the same size
		BufferedImage blurredImage = new BufferedImage(image.getWidth(), image.getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		// Create a Graphics2D object to apply the blur
		Graphics2D g = blurredImage.createGraphics();

		// Use a Gaussian Blur filter (you can tweak the radius to adjust the blur
		// intensity)
		float[] matrix = { 1f / 16f, 2f / 16f, 1f / 16f, 2f / 16f, 4f / 16f, 2f / 16f, 1f / 16f, 2f / 16f, 1f / 16f };
		ConvolveOp op = new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null);

		// Apply the blur to the original image
		BufferedImage blurred = op.filter(image, null);

		g.dispose();
		return blurred;
	}

	private Component panelContentInitoalize() throws Exception {

		panelContent = new JPanel();
		panelContent.setBounds(0, panelLine2.getY(), 958, 500);
		panelContent.setLayout(null);
		panelContent.setBackground(color2);
		panelContent.setVisible(true);

		panelLogin = new JPanel();
		panelLogin.setBounds(270, 100, 450, 350);
		panelLogin.setLayout(null);
		panelLogin.setBackground(color2);
		panelLogin.setBorder(BorderFactory.createEtchedBorder(color1, color1));
		panelLogin.setVisible(true);

		lblBusinessLogo = new JLabel();
		lblBusinessLogo.setBounds(100, 200, 200, 207);
		lblBusinessLogo.setHorizontalAlignment(JLabel.CENTER);
		// Load the original image (you can replace the path with the actual location of
		// your image)
		/*
		 * try { BufferedImage originalImage = ImageIO .read(new
		 * File("D:\\Gokul\\GBilling\\src\\main\\resources\\resource\\B3.png"));
		 * BufferedImage blurredImage = applyGaussianBlur(originalImage);
		 * lblBusinessLogo.setIcon(new ImageIcon(blurredImage)); } catch (IOException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
//		CommonMethods.setIcon(ImageResources.BUSINESSLOGO, lblBusinessLogo);
		lblBusinessLogo.setVisible(true);

		lblLoginImage = new JLabel();
		lblLoginImage.setBounds(5, 35, 200, 207);
		lblLoginImage.setHorizontalAlignment(JLabel.CENTER);
		CommonMethods.setIcon(ImageResources.loginLogo, lblLoginImage);
		lblLoginImage.setVisible(true);

		lblUserName = new JLabel("User Name");
		lblUserName.setBounds(panelLogin.getX() - 10, panelLogin.getY() - 50, 150, 30);
		lblUserName.setFont(skywingsFont.getFont(FontStyle.BOLD, 17));
		lblUserName.setForeground(fontColor1);
		lblUserName.setVisible(true);

		cmbOperName = new SkywingsComboBox<String>();
		cmbOperName.setBounds(lblUserName.getX(), lblUserName.getY() + 30, 150, 30);
		cmbOperName.setFont(skywingsFont.getFont(FontStyle.BOLD, 17));
		cmbOperName.setBackground(color2);
		cmbOperName.setVisible(true);
		cmbOperName.setEditable(true);
		cmbOperName.addKeyListener(this);

		lblpassword = new JLabel("Password");
		lblpassword.setBounds(cmbOperName.getX(), panelLogin.getY() + 30, 150, 30);
		lblpassword.setFont(skywingsFont.getFont(FontStyle.BOLD, 17));
		lblpassword.setForeground(fontColor1);
		lblpassword.setVisible(true);

		txtPassword = new SkywingsPasswordField();
		txtPassword.setBounds(lblpassword.getX(), lblpassword.getY() + 30, 150, 30);
		txtPassword.setFont(skywingsFont.getFont(FontStyle.BOLD, 17));
		txtPassword.setForeground(fontColor1);
		txtPassword.setVisible(true);
		txtPassword.setMaxLength(30);
		txtPassword.addKeyListener(this);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(txtPassword.getX() - 10, txtPassword.getY() + 100, 70, 30);
		btnLogin.setFont(skywingsFont.getFont(FontStyle.BOLD, 17));
		btnLogin.setBackground(color1);
		btnLogin.setHorizontalAlignment(JButton.CENTER);
		btnLogin.setVisible(true);
		btnLogin.setMnemonic(KeyEvent.VK_L);
		btnLogin.addActionListener(this);

		btnExit = new JButton("Exit");
		btnExit.setBounds(btnLogin.getX() + 110, btnLogin.getY(), 70, 30);
		btnExit.setFont(skywingsFont.getFont(FontStyle.BOLD, 17));
		btnExit.setBackground(color1);
		btnExit.setHorizontalAlignment(JButton.CENTER);
		btnExit.setMargin(new Insets(0, 0, 0, 0));
		btnExit.setMnemonic(KeyEvent.VK_E);
		btnExit.setVisible(true);
		btnExit.addActionListener(this);

		userLogin = new JLabel("User Login");
		userLogin.setBounds(lblLoginImage.getX() + 70, lblLoginImage.getY() + 200, 150, 30);
		userLogin.setFont(skywingsFont.getFont(FontStyle.BOLD, 20));
		userLogin.setForeground(fontColor1);
		userLogin.setVisible(true);

		panelMain.add(panelContent);
		panelContent.add(panelLogin);
		panelLogin.add(lblUserName);
		panelLogin.add(lblLoginImage);
		panelContent.add(lblBusinessLogo);
		panelLogin.add(cmbOperName);
		panelLogin.add(lblpassword);
		panelLogin.add(txtPassword);
		panelLogin.add(btnLogin);
		panelLogin.add(btnExit);
		panelLogin.add(userLogin);

		return panelContent;
	}

	private Component panelLine2Initialize() {

		panelLine2 = new JPanel();
		panelLine2.setBounds(panelDetail.getX(), panelDetail.getY() + 30, 958, 3);
		panelLine2.setLayout(null);
		panelLine2.setBackground(color5);
		panelLine2.setVisible(true);

		panelMain.add(panelLine2);

		return panelLine2;
	}

	private Component panelDetailInitialize() {

		panelDetail = new JPanel();
		panelDetail.setBounds(panelLine.getX(), panelLine.getY() + 30, 958, 30);
		panelDetail.setLayout(null);
		panelDetail.setBackground(color2);
		panelDetail.setVisible(true);

		lblHeading = new JLabel("'G'Billing And Maintanence");
		lblHeading.setBounds(420, -10, 170, 50);
		// lblHeading.setBounds(panelDetail.getWidth() / 2, panelDetail.getY() / 2, 20,
		// 20);
		lblHeading.setFont(skywingsFont.getFont(FontStyle.BOLD, 23));
		lblHeading.setForeground(fontColor1);
		lblHeading.setVisible(true);

		lblCompanyName = new JLabel("SREE SENTHOOR MURUGAN TRADERS");
		lblCompanyName.setBounds(lblHeading.getX() + 350, lblHeading.getY(), 260, 50);
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

	private Component panelLineInialize() {

		panelLine = new JPanel();
		panelLine.setBounds(0, 30, 958, 6);
		panelLine.setLayout(null);
		panelLine.setBackground(color3);
		panelLine.setVisible(true);

		panelMain.add(panelLine);
		return panelLine;
	}

	private Component panelTitleInialize() throws Exception {

		panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 958, 30);
		panelTitle.setLayout(null);
		panelTitle.setBackground(color1);
		panelTitle.setVisible(true);

		lblOperatorLabel = new JLabel("Operator");
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

		lblServerIpValue = new JLabel();
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

		// String pass1 =
		// Validation.decrypt(String.valueOf((cmbOperName.getSelectedItemValue())));

		lstOperDetails = new ArrayList<Operator>();

		lstOperDetails = // Validation.decrypt(
				logicLogin.getOperatorDetails(Integer.valueOf(String.valueOf(cmbOperName.getSelectedItemValue())));
		String pass2 = String.valueOf(txtPassword.getText());
		if (e.getSource() == btnLogin) {

			jarCheck();
//			renameCheck();

			try {
				if ((PasswordValidation.decript(lstOperDetails.get(0).getOperPass())).equalsIgnoreCase(pass2)) {

					Operator = String.valueOf(cmbOperName.getSelectedItem());
					OperCode = Integer.valueOf(String.valueOf(cmbOperName.getSelectedItemValue()));
//				designation = lstOperDetails.get(0).getDesignation();

					JOptionPane.showMessageDialog(null, "Login Success!...");

					setVisible(false);

					FrmMainMenu frmMenu = Applicationmain.getAbstractApplicationContext().getBean(FrmMainMenu.class);
					frmMenu.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Password mismatch!...");
					txtPassword.requestFocus();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		else if (e.getSource() == btnExit) {

			System.exit(1);

		}

	}

	private void renameCheck() {

		String currentJarPath = System.getProperty("user.dir") + File.separator + "CallsDup.jar";
		String newJarName = "Calls.jar";
		String newJarPath = System.getProperty("user.dir") + File.separator + newJarName;

		File currentFile = new File(currentJarPath);
		File newFile = new File(newJarPath);

	}

	private boolean jarCheck() {

		String currentDir = System.getProperty("user.dir");
		String deletePath = currentDir + File.separator + "Calls.jar";

		File file = new File(deletePath);
		return file.exists() && file.delete();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
