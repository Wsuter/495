/*William Suter
 * This is the add wish class, it used to add to wish lists for each individuals database.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

public class AddToWishList implements ActionListener{
	
	JFrame main = new JFrame("Add an item to the Wish List");
	JPanel mainPanel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JTextField item = new JTextField();
	JLabel intro = new JLabel();
	JButton cancel = new JButton("Cancel");
	JButton finish = new JButton("Finish");
	String name = "";
	
	//The constructor
	public AddToWishList(String name)
	{
		this.name = name;
	}
	
	//This method will initiate the GUI
	public void initiate()
	{
		main.setSize(350, 200);
		main.setLocationRelativeTo(null);
		cancel.addActionListener(this);
		finish.addActionListener(this);
		intro = new JLabel("Add an item to the wish list for " + name);
		
		BoxLayout box = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(box);
		
		panel1.add(intro);
		item.setPreferredSize(new Dimension(340, 35));
		panel2.add(item);
		panel3.add(cancel);
		panel3.add(finish);
		
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		
		main.add(mainPanel);
		main.pack();
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	//This method adds the information to a specified users database
	public void addToDatabase(String eventInformation)
	{
		try
		{
			String parentFile = "C:\\GroupGProject" + File.separator + name + File.separator + "wishList.txt";
			FileWriter output = new FileWriter(parentFile, true);
			BufferedWriter buf = new BufferedWriter(output);
			PrintWriter printer = new PrintWriter(buf);
			printer.println(eventInformation);
			printer.close();
		}
		catch(IOException e){}
	}
	//The actions to be performed
	public void actionPerformed(ActionEvent a) 
	{
		if(a.getSource() == finish)
		{
			String allInfo = item.getText();
			
			addToDatabase(allInfo);
			main.dispose();
		}
		if(a.getSource() == cancel)
		{
			main.dispose();
		}
	}

}
