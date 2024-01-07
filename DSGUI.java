import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import Types.CharacterType;
import Types.CollectionType;
import Types.NumeralType;
import Types.StringType;
import Types.ValueFields;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DSGUI {

	private JFrame frame;
	private JTextField valueTextField;
	private JTextField idTextField;
	private JButton searchButton;
	private JButton AddButton;
	private JButton UpdateButton;
	private JButton DeleteButton;
	private JButton ClearButton;
	private JComboBox<String> IDSelector;
	private JComboBox<String> DataTypeSelector;
	private JLabel IDLabel;
	private JLabel jLabel2;
	private JLabel valueLabel;
	private JLabel dataTypeLabel;
	private JLabel jLabel5;
	private JPanel jPanel1;
	private JScrollPane tablePane;
	private JTable table;

	private Database database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DSGUI window = new DSGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DSGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		database = new Database();
		frame = new JFrame();
		frame.setBounds(100, 100, 1064, 776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jPanel1 = new JPanel();
		jPanel1.setToolTipText("");
		jPanel1.setBackground(new Color(223, 223, 223));
		frame.getContentPane().add(jPanel1, BorderLayout.CENTER);

		ClearButton = new JButton();
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				database.clear();
				display();
				JOptionPane.showMessageDialog(jPanel1, "Item Cleared!");
			}
		});
		ClearButton.setText("Clear");
		ClearButton.setFont(new Font("Dialog", Font.BOLD, 14));

		jLabel2 = new JLabel();
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setText("Java Crud Operation");
		jLabel2.setFont(new Font("Dialog", Font.BOLD, 24));

		JScrollPane tablePane = new JScrollPane();

		AddButton = new JButton();
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = valueTextField.getText().toString();
				String dataType = DataTypeSelector.getSelectedItem().toString();
				String ID = idTextField.getText();
				database.add(ID, new StringType(value));
				display();
				JOptionPane.showMessageDialog(jPanel1, "Item Added!");
			}
		});
		AddButton.setText("Add");
		AddButton.setFont(new Font("Dialog", Font.BOLD, 14));

		UpdateButton = new JButton();
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int a = JOptionPane.showConfirmDialog(jPanel1, "Are you sure?");

				switch (a) {
				case 0:
					String value = valueTextField.getText();
					String dataType = DataTypeSelector.getSelectedItem().toString();
					String ID = idTextField.getText();
					database.set(ID, new StringType(value));
					display();
					JOptionPane.showMessageDialog(jPanel1, "Item Updated!");
					break;
				case 1, 2:
					break;
				}
			}
		});
		UpdateButton.setText("Update");
		UpdateButton.setFont(new Font("Dialog", Font.BOLD, 14));

		DeleteButton = new JButton();
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(jPanel1, "Are you sure?");

				switch (a) {
				case 0:
					String ID = idTextField.getText();
					database.delete(ID);
					display();
					JOptionPane.showMessageDialog(jPanel1, "Item Deleted!");
					break;
				case 1, 2:
					break;
				}
			}
		});
		DeleteButton.setText("Delete");
		DeleteButton.setFont(new Font("Dialog", Font.BOLD, 14));

		valueLabel = new JLabel();
		valueLabel.setText("Value :");
		valueLabel.setFont(new Font("Dialog", Font.BOLD, 24));

		dataTypeLabel = new JLabel();
		dataTypeLabel.setText("Data Type :");
		dataTypeLabel.setFont(new Font("Dialog", Font.BOLD, 24));

		IDLabel = new JLabel();
		IDLabel.setText("ID :");
		IDLabel.setFont(new Font("Dialog", Font.BOLD, 24));

		DataTypeSelector = new JComboBox<String>();
		initDataTypes();
		DataTypeSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (DataTypeSelector.getSelectedItem().toString().contains("Collection")) {
					valueTextField.setText("[ ]");
				}
			}
		});

		valueTextField = new JTextField();

		idTextField = new JTextField();

		jLabel5 = new JLabel();
		jLabel5.setText("Select by ID :");
		jLabel5.setFont(new Font("Dialog", Font.BOLD, 24));

		IDSelector = new JComboBox<String>();

		searchButton = new JButton();
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValueFields item = database.get(IDSelector.getSelectedItem().toString());
				String value = valueConvert(item);

				idTextField.setText(IDSelector.getSelectedItem().toString());
				DataTypeSelector.setSelectedItem(item.getType());
				valueTextField.setText(value);
			}
		});
		searchButton.setText("Search");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 18));
		GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
		gl_jPanel1
				.setHorizontalGroup(
						gl_jPanel1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_jPanel1.createSequentialGroup().addGap(493).addComponent(jLabel2,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(320))
								.addGroup(gl_jPanel1.createSequentialGroup().addGap(39).addGroup(gl_jPanel1
										.createParallelGroup(Alignment.LEADING).addGroup(gl_jPanel1
												.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(AddButton, GroupLayout.PREFERRED_SIZE, 109,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(UpdateButton, GroupLayout.PREFERRED_SIZE, 118,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(DeleteButton, GroupLayout.PREFERRED_SIZE, 117,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(ClearButton, GroupLayout.PREFERRED_SIZE, 117,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
										.addGroup(gl_jPanel1.createSequentialGroup()
												.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
														.addComponent(valueLabel).addComponent(dataTypeLabel)
														.addComponent(IDLabel))
												.addGap(33)
												.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_jPanel1
																.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(DataTypeSelector, Alignment.LEADING, 0,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(valueTextField, Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
														.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 240,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
												.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_jPanel1.createSequentialGroup().addGap(31)
																.addComponent(jLabel5)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(IDSelector, 0, 312, Short.MAX_VALUE))
														.addGroup(gl_jPanel1.createSequentialGroup()
																.addPreferredGap(ComponentPlacement.RELATED, 355,
																		Short.MAX_VALUE)
																.addComponent(searchButton, GroupLayout.PREFERRED_SIZE,
																		117, GroupLayout.PREFERRED_SIZE)))
												.addGap(44))))
								.addGroup(gl_jPanel1.createSequentialGroup().addGap(39)
										.addComponent(tablePane, GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
										.addContainerGap()));
		gl_jPanel1.setVerticalGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup().addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup().addGap(76)
								.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE).addComponent(valueLabel)
										.addComponent(jLabel5)
										.addComponent(IDSelector, GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(valueTextField, GroupLayout.PREFERRED_SIZE, 32,
												GroupLayout.PREFERRED_SIZE))
								.addGap(19)
								.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE).addComponent(dataTypeLabel)
										.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 48,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(DataTypeSelector, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(25)
								.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE).addComponent(IDLabel)
										.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 51,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_jPanel1.createSequentialGroup().addGap(16).addComponent(jLabel2))).addGap(24)
						.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(UpdateButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(DeleteButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(ClearButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(AddButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tablePane, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE).addContainerGap()));

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();

				idTextField.setText(model.getValueAt(index, 0).toString());
				DataTypeSelector.setSelectedItem(model.getValueAt(index, 1).toString());
				valueTextField.setText(model.getValueAt(index, 2).toString());
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] { null }, new String[] { "ID", "Data Type", "Value" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		tablePane.setViewportView(table);
		jPanel1.setLayout(gl_jPanel1);
	}

	private void display() {
		DefaultTableModel df = (DefaultTableModel) table.getModel();
		df.setRowCount(0);
		String[][] valueArr = database.displayGUI();
		if(valueArr == null) {
			valueArr = new String[0][0];
		}
		for (int i = 0; i < valueArr.length; i++) {
			df.addRow(valueArr[i]);
			if (i == valueArr.length - 1) {
				IDSelector.addItem(valueArr[i][0]);
			}
		}
	}

	private void initDataTypes() {
		DataTypeSelector.addItem("String");
		DataTypeSelector.addItem("Numerical");
		DataTypeSelector.addItem("Character");
		DataTypeSelector.addItem("Collection(String)");
		DataTypeSelector.addItem("Collection(Numerical)");
		DataTypeSelector.addItem("Collection(Character)");
	}

	private <T> T valueConvert(ValueFields value) {
		switch (value.getType()) {
		case ValueFields.STRING:
			StringType type1 = (StringType) value;
			return (T) type1.getValue();
		case ValueFields.CHARACTER:
			CharacterType type2 = (CharacterType) value;
			return (T) type2.getValue();
		case ValueFields.NUMBER:
			NumeralType type3 = (NumeralType) value;
			return (T) type3.getValue();
		case ValueFields.COLLECTION:
			CollectionType type4 = (CollectionType) value;
			return (T) type4.getValue();
		}
		return null;
	}
}
