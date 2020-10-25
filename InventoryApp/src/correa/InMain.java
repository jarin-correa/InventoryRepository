package correa;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class InMain {

	private JFrame frame;
	private JTextField title_t;
	private JTextField price_t;
	private JTextField quantity_t;
	private JTextField sku_t;
	public JComboBox btnBuild;
	public JButton btnOptions;
	public JTextArea textArea;
	public JScrollPane scrollPane;
	public InList inventory;
	public String[] options = { "remove", "display book info", "add book", "display inventory" };
	public String selectedComboBoxSelection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InMain window = new InMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @return
	 */
	public InMain() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel SKU_label = new JLabel("SKU:");
		SKU_label.setBounds(6, 33, 35, 16);
		frame.getContentPane().add(SKU_label);
		JLabel title_label = new JLabel("Title:");
		title_label.setBounds(236, 74, 35, 16);
		frame.getContentPane().add(title_label);
		JLabel price_label = new JLabel("Price:");
		price_label.setBounds(236, 33, 44, 16);
		frame.getContentPane().add(price_label);
		JLabel quantity_label = new JLabel("Quantity:");
		quantity_label.setBounds(6, 74, 61, 16);
		frame.getContentPane().add(quantity_label);
		JLabel title = new JLabel("Campus Bookstore App");
		title.setBounds(133, 6, 158, 16);
		frame.getContentPane().add(title);
		title_t = new JTextField();
		title_t.setBounds(283, 69, 130, 26);
		frame.getContentPane().add(title_t);
		title_t.setColumns(10);
		price_t = new JTextField();
		price_t.setBounds(283, 28, 130, 26);
		frame.getContentPane().add(price_t);
		price_t.setColumns(10);
		quantity_t = new JTextField();
		quantity_t.setBounds(79, 69, 130, 26);
		frame.getContentPane().add(quantity_t);
		quantity_t.setColumns(10);
		sku_t = new JTextField();
		sku_t.setBounds(79, 28, 130, 26);
		frame.getContentPane().add(sku_t);
		sku_t.setColumns(10);
		// String [] options = {"add", "remove", "display book info", "display
		// inventory"};
		btnBuild = new JComboBox(options);
		btnBuild.setBounds(79, 108, 114, 27);
		frame.getContentPane().add(btnBuild);
		btnBuild.setSelectedItem(null); // possibly take out?
		btnOptions = new JButton("SELECT");
		btnOptions.setBounds(246, 107, 117, 29);
		frame.getContentPane().add(btnOptions);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(18, 155, 415, 103);
		frame.getContentPane().add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		// ArrayList of Video
		inventory = new InList();
	}

	/**
	 * This is to create our output once the button is clicked
	 */
	public void createEvents() {
		btnOptions.addActionListener(new ActionListener() { // when btnBuild button is pressed
			public void actionPerformed(ActionEvent e) { // the output comes out
				buildOutput();
			}
		});
	}

	public void buildOutput() {
		// Passing to Video Constructor
		// Combo Box Selection choice:
		if (btnBuild.getSelectedItem() == null) {
			textArea.setText("NO OPTION SELECTED");
			return;
		} else {
			selectedComboBoxSelection = (String) btnBuild.getSelectedItem();
		}
		System.out.println(selectedComboBoxSelection);

		if (selectedComboBoxSelection.equals("add book")) {
			String sku = sku_t.getText();
			String title = title_t.getText();
			String price = price_t.getText();
			int p = Integer.parseInt(price);
			String quantity = quantity_t.getText();
			int q = Integer.parseInt(quantity);
			if (sku.equals("") || title.equals("") || price.equals("") || quantity.equals("")) {
				textArea.setText("Not all elements are inputted, please try again.");
			}
			Book b = new Book(sku, title, p, q);
			inventory.addBook(b);
			textArea.setText("book successfully added!");

		}
		if (selectedComboBoxSelection.equals("remove")) {
			String sku = sku_t.getText();
			String remove = "Book does not exsist in inventory";
			for (int i = 0; i < inventory.getBookList().size(); i++) {
				if (inventory.getBookList().get(i).getSKU().equals(sku)) {
					inventory.getBookList().remove(i);
					remove = "Booked successfully removed";
				}
				inventory.bookMap.remove(sku);
				textArea.setText(remove);
			}

		}
		if (selectedComboBoxSelection.equals("display book info")) {
			String sku = sku_t.getText();
			String result = "Book does not exsist in inventory";
			for (int i = 0; i < inventory.getBookList().size(); i++) {
				if (inventory.getBookList().get(i).getSKU().equals(sku)) {
					inventory.getBookList().remove(i);
					result = inventory.getBookList().get(i).toString();
				}
				textArea.setText(result);
			}
		}
		if (selectedComboBoxSelection.equals("display inventory")) {
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setText("Inventory: " + inventory.display());
		}
	}

}
