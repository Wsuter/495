/*William Suter
 * This portion of the program will allow the user to add a new person to their database
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
public class AddUser implements ActionListener
{
	UserGui update;
	String nameInfo = "";
	JFrame main = new JFrame("Add a person");
	JPanel mainPanel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JTextField nameField = new JTextField();
	static String info = "Enter the name of the person you wish to add.";
	static JLabel intro = new JLabel(info);
	JButton cancel, finish;
	
	//Constructor
	public AddUser(UserGui update)
	{
		this.update = update;
	}
	
	
	//This section starts the GUI for adding a new person
	public void initiate()
	{
		
		main.setSize(300, 175);
		main.setLocationRelativeTo(null);
		
		BoxLayout box = new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
		mainPanel.setLayout(box);
		
		panel1.add(intro);
		
		
		nameField.setPreferredSize(new Dimension(280, 40));
		panel2.add(nameField);
		
		cancel = new JButton("Cancel");
		finish = new JButton("Add");
		cancel.addActionListener(this);
		finish.addActionListener(this);
		panel3.add(cancel);
		panel3.add(finish);
		
		
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		
		main.add(mainPanel);
		
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//This portion creates a new directory with text files that represent the database to be used
	public void addToDatabase(String name)
	{
		
		if(name != null)
		{
			nameInfo = name;
		}
		info = "Enter the name of the person you wish to add.";
		intro.setText(info);
		try
		{
			String parentFile = "C:\\GroupGProject" + File.separator + name;
			File f = new File(parentFile);
			File eventFile = new File(parentFile + File.separator + "event.txt");
			File wishListFile = new File(parentFile + File.separator + "wishList.txt");
			if(f.exists())
			{
				info = "That person already exists";
				intro.setText(info);
				
			}
			else
			{
				if(update.namesBox.getItemAt(0) == "No Name")
				{
					update.namesBox.removeItemAt(0);
				}
				f.mkdir();
				eventFile.createNewFile();
				wishListFile.createNewFile();
				
				FileWriter output = new FileWriter("C:\\GroupGProject" + File.separator + "Names.txt", true);
				BufferedWriter buf = new BufferedWriter(output);
				PrintWriter printer = new PrintWriter(buf);
				printer.println(name);
				printer.close();
				update.namesBox.addItem(name);
			}
			nameField.setText("");
			
		}
		catch(IOException e){}
	}
	//Returns the name of the new person
	public String getName()
	{
		return nameInfo;
	}
	//Action listeners
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == finish)
		{
			String newName = nameField.getText();
			addToDatabase(newName);
		}
		if(e.getSource()  == cancel)
		{
			main.dispose();
		}
	}

}
