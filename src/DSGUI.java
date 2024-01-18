/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import Types.BooleanType;
import Types.CharacterType;
import Types.CollectionBooleanType;
import Types.CollectionCharacterType;
import Types.CollectionNumeralType;
import Types.CollectionStringType;
import Types.CollectionType;
import Types.NumeralType;
import Types.StringType;
import Types.ValueFields;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class DSGUI {

	private JFrame frame;
	private JTextField valueTextField;
	private JTextField idTextField;
	private JButton searchButton;
	private JButton AddButton;
	private JButton UpdateButton;
	private JButton DeleteButton;
	private JButton ClearButton;
	private JTextField SearchTextField;
	private JComboBox<String> DataTypeSelector;
	private JLabel IDLabel;
	private JLabel jLabel2;
	private JLabel valueLabel;
	private JLabel dataTypeLabel;
	private JLabel jLabel5;
	private JPanel jPanel1;
	private JScrollPane tablePane;
	private JTable table;
	private JFileChooser fileChooser;
	private FileNameExtensionFilter filter;

	private Database database;
	
	private HashQueue<ValueFields> fieldsValue;
	private final Action action = new SwingAction();

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
		fieldsValue = new HashQueue<>(2);
		frame.setBounds(100, 100, 1064, 776);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jPanel1 = new JPanel();
		jPanel1.setToolTipText("");
		jPanel1.setBackground(new Color(223, 223, 223));
		frame.getContentPane().add(jPanel1, BorderLayout.CENTER);

		ClearButton = new JButton();
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(jPanel1, "Are you sure?");
				switch (a) {
				case 0:
					database.clear();
					display();
					JOptionPane.showMessageDialog(jPanel1, "Item Cleared!");
					break;
				case 1, 2:
					break;
				}
			}
		});
		ClearButton.setText("Clear");
		ClearButton.setFont(new Font("Dialog", Font.BOLD, 14));

		jLabel2 = new JLabel();
		jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel2.setText("HashBase");
		jLabel2.setFont(new Font("Dialog", Font.BOLD, 24));

		JScrollPane tablePane = new JScrollPane();

		AddButton = new JButton();
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = valueTextField.getText().toString();
				String dataType = DataTypeSelector.getSelectedItem().toString();
				String ID = idTextField.getText();
				boolean validateResult = Validate.validate(dataType, value, fieldsValue);
				if (validateResult == true) {
					if(database.add(ID, fieldsValue.dequeue())) {
						display();
						JOptionPane.showMessageDialog(jPanel1, "Item Added!");
					}
					else {
						fieldsValue.dequeue();
						JOptionPane.showMessageDialog(jPanel1, "Already Existed!");
					}
				} else {
					fieldsValue.dequeue();
					JOptionPane.showMessageDialog(jPanel1, "Invalid Data! Please check your input.");
				}
				UpdateButton.setEnabled(false);
				DeleteButton.setEnabled(false);
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
					String value = valueTextField.getText().toString();
					String dataType = DataTypeSelector.getSelectedItem().toString();
					String ID = idTextField.getText();
					boolean validateResult = Validate.validate(dataType, value, fieldsValue);
					if (validateResult == true) {
						database.set(ID, fieldsValue.dequeue());
						display();
						JOptionPane.showMessageDialog(jPanel1, "Item Updated!");
					} else {
						fieldsValue.dequeue();
						JOptionPane.showMessageDialog(jPanel1, "Invalid Data! Please check your input.");
					}
					break;
				case 1, 2:
					break;
				}
				
				UpdateButton.setEnabled(false);
				DeleteButton.setEnabled(false);
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
				
				UpdateButton.setEnabled(false);
				DeleteButton.setEnabled(false);
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
					valueTextField.setText("[]");
				}
			}
		});

		valueTextField = new JTextField();

		idTextField = new JTextField();

		jLabel5 = new JLabel();
		jLabel5.setText("Search by ID :");
		jLabel5.setFont(new Font("Dialog", Font.BOLD, 24));

		SearchTextField = new JTextField();

//                // my own part
//                HashSet<String> uniqueIds = new HashSet<String>();
//                idTextField.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        String enteredId = idTextField.getText();
//
//                        if (!enteredId.isEmpty() && !uniqueIds.contains(enteredId)) {
//                            // Add the ID to the set and JComboBox if it's valid
//                            uniqueIds.add(enteredId);
//                            IDSelector.addItem(enteredId);
//                        } else {
//                            // Handle case where the ID is not valid
//                            // Display an error message and prompt the user to reinput
//                            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid, unique ID.");
//                            idTextField.setText(""); // Clear the input field
//                        }
//                    }
//                });

		searchButton = new JButton();
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String index = SearchTextField.getText();
				if(database.contains(index)) {
					ValueFields value = database.get(index);
					JOptionPane.showMessageDialog(jPanel1, "Index Exists!");
					idTextField.setText(index);
					DataTypeSelector.setSelectedItem(value.getType());
					switch(value.getType()) {
					case ValueFields.STRING:
						StringType type1 = (StringType) value;
						valueTextField.setText(type1.getValue());
						break;
					case ValueFields.BOOLEAN:
						BooleanType type2 = (BooleanType) value;
						valueTextField.setText(type2.getValue().toString());
						break;
					case ValueFields.NUMBER:
						NumeralType type3 = (NumeralType) value;
						valueTextField.setText(type3.getValue().toString());
						break;
					case ValueFields.CHARACTER:
						CharacterType type4 = (CharacterType) value;
						valueTextField.setText(type4.getValue().toString());
						break;
					case ValueFields.COLLECTIONSTRING:
						CollectionStringType type5 = (CollectionStringType) value;
						valueTextField.setText(type5.getValue().toString());
						break;
					case ValueFields.COLLECTIONBOOLEAN:
						CollectionBooleanType type6 = (CollectionBooleanType) value;
						valueTextField.setText(type6.getValue().toString());
						break;
					case ValueFields.COLLECTIONNUMBER:
						CollectionNumeralType type7 = (CollectionNumeralType) value;
						valueTextField.setText(type7.getValue().toString());
						break;
					case ValueFields.COLLECTIONCHARACTER:
						CollectionCharacterType type8 = (CollectionCharacterType) value;
						valueTextField.setText(type8.getValue().toString());
						break;
					}
					UpdateButton.setEnabled(true);
					DeleteButton.setEnabled(true);
				}
			}
		});
		searchButton.setText("Search");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 18));
		
		JButton ExportCSV = new JButton();
		ExportCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter = new FileNameExtensionFilter("TXT", "txt");
				fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(filter);
				fileChooser.setDialogTitle("Specify a file to save");
				
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    database.toCSV(fileChooser.getSelectedFile());
				    JOptionPane.showMessageDialog(jPanel1, "Exported!");
				}
//				formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
//				date = new Date();
//				String name = formatter.format(date) + ".csv";
//				database.toCSV(name);
			}
		});
		ExportCSV.setAction(action);
		ExportCSV.setText("Export to TXT");
		ExportCSV.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton ImportCSV = new JButton();
		ImportCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter = new FileNameExtensionFilter("TXT", "txt");
				fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(filter);
				fileChooser.setDialogTitle("Specify a file to import");
				
				int userSelection = fileChooser.showSaveDialog(fileChooser);
				
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
				    database.importTXT(file.getAbsolutePath());
				    JOptionPane.showMessageDialog(jPanel1, "Imported!");
				}
				display();
			}
		});
		ImportCSV.setText("Import from TXT");
		ImportCSV.setFont(new Font("Dialog", Font.BOLD, 14));
		GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
		gl_jPanel1.setHorizontalGroup(
			gl_jPanel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AddButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(UpdateButton, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(DeleteButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(ClearButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(ExportCSV, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(ImportCSV, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addComponent(valueLabel)
								.addComponent(dataTypeLabel)
								.addComponent(IDLabel))
							.addGap(33)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(DataTypeSelector, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(valueTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
								.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
							.addGap(61)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addGap(31)
									.addComponent(jLabel5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(SearchTextField, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
									.addGap(0, 0, Short.MAX_VALUE))
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 355, Short.MAX_VALUE)
									.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
							.addGap(44))))
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(39)
					.addComponent(tablePane, GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(439)
					.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
					.addGap(374))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(26)
					.addComponent(jLabel2)
					.addGap(18)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(valueLabel)
						.addComponent(jLabel5)
						.addComponent(SearchTextField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(valueTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(dataTypeLabel)
						.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(DataTypeSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(IDLabel)
						.addComponent(idTextField, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(UpdateButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(DeleteButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClearButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(ExportCSV, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(ImportCSV, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tablePane, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		UpdateButton.setEnabled(false);
		DeleteButton.setEnabled(false);
		
		table = new JTable();
		table.putClientProperty(ExportCSV, gl_jPanel1);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UpdateButton.setEnabled(true);
				DeleteButton.setEnabled(true);
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
		if (valueArr == null) {
			valueArr = new String[0][0];
		}
		for (int i = 0; i < valueArr.length; i++) {
			df.addRow(valueArr[i]);
		}
	}

	private void initDataTypes() {
		DataTypeSelector.addItem("String");
		DataTypeSelector.addItem("Boolean");
		DataTypeSelector.addItem("Numerical");
		DataTypeSelector.addItem("Character");
		DataTypeSelector.addItem("Collection(String)");
		DataTypeSelector.addItem("Collection(Boolean)");
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
		case ValueFields.COLLECTIONSTRING:
			CollectionStringType type4 = (CollectionStringType) value;
			return (T) type4.getValue();
		case ValueFields.COLLECTIONBOOLEAN:
			CollectionBooleanType type5 = (CollectionBooleanType) value;
			return (T) type5.getValue();
		case ValueFields.COLLECTIONNUMBER:
			CollectionNumeralType type6 = (CollectionNumeralType) value;
			return (T) type6.getValue();
		case ValueFields.COLLECTIONCHARACTER:
			CollectionCharacterType type7 = (CollectionCharacterType) value;
			return (T) type7.getValue();
		case ValueFields.BOOLEAN:
			BooleanType type8 = (BooleanType) value;
			return (T) type8.getValue();
		}
		return null;
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}