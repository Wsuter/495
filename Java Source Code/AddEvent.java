/*William Suter
 * This is the add event class, it used to add special events to each individuals database.
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

public class AddEvent implements ActionListener{
	
	JFrame main = new JFrame("Add an event.");
	JPanel mainPanel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JTextField monthField = new JTextField();
	JTextField dayField = new JTextField();
	JTextField yearField = new JTextField();
	JTextField specialEvent = new JTextField();
	JLabel intro = new JLabel();
	JLabel day = new JLabel("Day");
	JLabel month = new JLabel("Month");
	JLabel year = new JLabel("Year");
	JLabel event = new JLabel("Event");
	JButton cancel = new JButton("Cancel");
	JButton finish = new JButton("Finish");
	String name = "";
	
	//The constructor
	public AddEvent(String name)
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
		intro = new JLabel("Enter the day, month, year, and special event for " + name);
		
		BoxLayout box = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(box);
		
		panel1.add(intro);

		dayField.setPreferredSize(new Dimension(50, 30));
		monthField.setPreferredSize(new Dimension(50, 30));
		yearField.setPreferredSize(new Dimension(50, 30));
		specialEvent.setPreferredSize(new Dimension(100, 30));
		
		panel2.add(day);
		panel2.add(dayField);
		panel2.add(month);
		panel2.add(monthField);
		panel2.add(year);
		panel2.add(yearField);
		
		panel3.add(event);
		panel3.add(specialEvent);
		
		panel4.add(cancel);
		panel4.add(finish);
		
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		
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
			String parentFile = "C:\\GroupGProject" + File.separator + name + File.separator + "event.txt";
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
			String d = dayField.getText();
			String m = monthField.getText();
			String y = yearField.getText();
			String e = specialEvent.getText();
			String allInfo = m + "\\" + d + "\\" + y + " " + e;
			
			addToDatabase(allInfo);
			main.dispose();
		}
		if(a.getSource() == cancel)
		{
			main.dispose();
		}
	}

}
