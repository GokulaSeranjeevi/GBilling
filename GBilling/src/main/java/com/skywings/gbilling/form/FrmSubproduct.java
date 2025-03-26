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
import com.skywings.gbilling.common.ListItem;
import com.skywings.gbilling.common.SkywingsComboBox;
import com.skywings.gbilling.logic.OperatorLogic;
import com.skywings.gbilling.logic.SubProductLogic;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.SubProduct;
import com.skywings.gbilling.model.TaxMaster;

@Component
public class FrmSubproduct extends JInternalFrame implements FormAction, KeyListener, ActionListener {

	private ControlResize controlResize;
	private JPanel panelEntry;
	private JPanel panelMain;
	private JPanel panelContent;
	private JPanel panelView;
	private JPanel panelMenu;

	private JLabel lblSubCode, lblSubProName, lblProName, lblActive;
	private JTextField txtSubcode, txtSubProName;
	private SkywingsComboBox<String> cmbProduct;
	private JCheckBox chkActive;
	private JTable tblSubProduct;
	private JScrollPane scrPane;

	private List<Product> lstProduct;

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
	private SubProductLogic subProductLogic;

	public FrmSubproduct() {

		setBounds(-10, -110, 1930, 900);
//		pack();
		setTitle("SubProduct");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createControls();
		createListeners();

	}

	@PostConstruct
	private void loadDetails() throws Exception {

		txtSubcode.setText(subProductLogic.getSubProductCode());

		lstProduct = subProductLogic.getProduct();

		for (Product pro : lstProduct) {

			cmbProduct.addListItem(new ListItem(pro.getProName(), pro.getProCode()));

		}

		txtSubcode.setEnabled(false);
		txtSubProName.setText("");
		chkActive.setSelected(true);
		FrmMDI.btnAdd.requestFocus();

	}

	private void createListeners() {

		txtSubProName.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				if ("".equalsIgnoreCase(txtSubProName.getText())) {
					JOptionPane.showMessageDialog(null, "SubProName Shouldn't Empty!...");
					return false;
				}
				return true;
			}
		});
//		txtProName.setInputVerifier(new InputVerifier() {
//
//			@Override
//			public boolean verify(JComponent input) {
//				if ("".equalsIgnoreCase(txtSubProName.getText())) {
//					JOptionPane.showMessageDialog(null, "Password Shouldn't Empty!...");
//					return false;
//				}
//				return true;
//			}
//		});

		chkActive.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					FrmMDI.btnSave.requestFocus();
				}
			}

		});

		cmbProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					chkActive.requestFocus();

				}

			}
		});

	}

	protected void view() throws Exception {

		String[] columnNames = { "SubCode", "SubProName", "ProName", "Active" };

		List<SubProduct> subProList = subProductLogic.getSubProduct();

		Object[][] data = new Object[subProList.size()][columnNames.length];

		for (int i = 0; i < subProList.size(); i++) {
			SubProduct subProduct = subProList.get(i);
			data[i][0] = subProduct.getSubcode();
			data[i][1] = subProduct.getSubProName();
			data[i][2] = subProduct.getProName();
			data[i][3] = subProduct.getActive() == "Y" ? "No" : "Yes";

		}
		;

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblSubProduct = new JTable(model);

		tblSubProduct.getColumnModel().getColumn(3).setMinWidth(0);
		tblSubProduct.getColumnModel().getColumn(3).setMaxWidth(0);
		tblSubProduct.getColumnModel().getColumn(3).setWidth(0);
		tblSubProduct.getColumnModel().getColumn(3).setResizable(false);

		tblSubProduct.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblSubProduct.getTableHeader().setReorderingAllowed(false);
		tblSubProduct.getTableHeader().setFont(CustomFonts.fontCalibriPlain15);
		tblSubProduct.setFont(CustomFonts.fontCalibriPlain15);
		tblSubProduct.setForeground(Color.BLACK);
		tblSubProduct.getTableHeader().setForeground(color6);
		tblSubProduct.getTableHeader().setBackground(Color.WHITE);
		tblSubProduct.setRowHeight(22);

//		tblOperView.getTableHeader().setToolTipText("Table Header Tooltip");

		tblSubProduct.setVisible(true);
		tblSubProduct.addKeyListener(this);

		scrPane = new JScrollPane(tblSubProduct);
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

		lblSubCode = new JLabel("SubProductCode");
		lblSubCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubCode.setBounds(180, 80, lblWidth, lblHeight);
		lblSubCode.setFont(CustomFonts.font);
		lblSubCode.setForeground(Color.BLACK);
		lblSubCode.setBackground(color2);
		lblSubCode.setVisible(true);

		txtSubcode = new JTextField();
		txtSubcode.setBounds(lblSubCode.getX() + lblSubCode.getWidth() + 30, lblSubCode.getY() + 10, txtwidth,
				txtheight);
		txtSubcode.setFont(CustomFonts.fontCalibriBold);
		txtSubcode.setBackground(color2);
		txtSubcode.setForeground(Color.BLACK);
		txtSubcode.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtSubcode.addKeyListener(this);

		lblSubProName = new JLabel("SubProduct Name");
		lblSubProName.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubProName.setBounds(lblSubCode.getX(), lblSubCode.getY() + lblSubCode.getHeight() + 40, lblWidth,
				lblHeight);
		lblSubProName.setFont(CustomFonts.font);
		lblSubProName.setForeground(Color.BLACK);
		lblSubProName.setBackground(color2);
		lblSubProName.setVisible(true);

		txtSubProName = new JTextField();
		txtSubProName.setBounds(lblSubProName.getX() + lblSubProName.getWidth() + 30, lblSubProName.getY() + 10,
				txtwidth + 80, txtheight);
		txtSubProName.setFont(CustomFonts.fontCalibriBold);
		txtSubProName.setBackground(color2);
		txtSubProName.setForeground(Color.BLACK);
		txtSubProName.setVisible(true);
//		txtOperId.setMaxLength(150);
		txtSubProName.addKeyListener(this);

		txtfComponent.textLength(txtSubProName, 20);

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

		lblProName = new JLabel("Product");
		lblProName.setHorizontalAlignment(SwingConstants.LEFT);
		lblProName.setBounds(lblSubProName.getX(), lblSubProName.getY() + lblSubProName.getHeight() + 40, lblWidth,
				lblHeight);
		lblProName.setFont(CustomFonts.font);
		lblProName.setForeground(Color.BLACK);
		lblProName.setBackground(color2);
		lblProName.setVisible(true);

		cmbProduct = new SkywingsComboBox<String>();
		cmbProduct.setBounds(lblProName.getX() + lblProName.getWidth() + 30, lblProName.getY() + 10, txtwidth + 80,
				txtheight);
		cmbProduct.setFont(CustomFonts.font);
		cmbProduct.setBackground(color2);
		cmbProduct.setVisible(true);
		cmbProduct.setEditable(true);
//		cmbProduct.addKeyListener(this);

//		txtOperPass = new JPasswordField();
//		txtOperPass.setBounds(lblProName.getX() + lblProName.getWidth() + 30, lblProName.getY() + 10, txtwidth + 80,
//				txtheight);
//		txtOperPass.setFont(CustomFonts.fontCalibriBold);
//		txtOperPass.setBackground(color2);
//		txtOperPass.setForeground(Color.BLACK);
//		txtOperPass.setVisible(true);
////		txtOperId.setMaxLength(150);
//		txtOperPass.addKeyListener(this);
//
//		txtfComponent.textLength(txtOperPass, 10);

		lblActive = new JLabel("Active");
		lblActive.setHorizontalAlignment(SwingConstants.LEFT);
		lblActive.setBounds(lblProName.getX(), lblProName.getY() + lblSubProName.getHeight() + 40, lblWidth, lblHeight);
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
		panelContent.add(lblSubCode);
		panelContent.add(lblSubProName);
		panelContent.add(lblProName);
		panelContent.add(lblActive);
		panelContent.add(txtSubcode);
		panelContent.add(txtSubProName);
		panelContent.add(cmbProduct);
//		panelContent.add(txtOperPass);
		panelContent.add(chkActive);

		return panelContent;
	}

	@Override
	public boolean formAdd() {

		txtSubProName.requestFocus();
		return true;
	}

	@Override
	public boolean formSave() {

		save();

		return true;
	}

	private void save() {

		if ("".equalsIgnoreCase(txtSubProName.getText())) {
			JOptionPane.showMessageDialog(null, "Operator Should Not Empty!...");
			txtSubProName.requestFocus();
			return;
		}
//		if ("".equalsIgnoreCase(txtOperPass.getText())) {
//			JOptionPane.showMessageDialog(null, "Password Should Not Empty!...");
//			txtSubProName.requestFocus();
//			return;
//		}

		SubProduct subProduct = new SubProduct();

		subProduct.setSubcode(txtSubcode.getText());
		subProduct.setSubProName(txtSubProName.getText());
		subProduct.setProCode("1");
//		operator.setOperPass(Validation.encrypt(txtOperPass.getText()));
		subProduct.setActive(chkActive.isSelected() ? "Y" : "N");

		try {
			subProductLogic.saveSubProduct(subProduct);
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

			tblSubProduct.requestFocus();

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
		selectedRow = tblSubProduct.getSelectedRow();
		if (selectedRow != -1) {
			txtSubcode.setText(tblSubProduct.getValueAt(selectedRow, 0).toString());
			txtSubProName.setText(tblSubProduct.getValueAt(selectedRow, 1).toString());
			chkActive.setSelected(tblSubProduct.getValueAt(selectedRow, 3) == "Yes" ? false : true);
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

			txtSubcode.setText(subProductLogic.getSubProductCode());
			txtSubProName.setText("");
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
			if (e.getSource() == txtSubcode) {

				txtSubProName.requestFocus();

			} else if (e.getSource() == txtSubProName) {
				cmbProduct.requestFocus();

			} else if (e.getSource() == cmbProduct) {

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
