/*William Suter
 * This class creates a GUI that allows the user to edit and delete special events
 * 
 * 
 */





import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class editEvents implements ActionListener 
{
	ArrayList<String> eventArray = new ArrayList<>();
	String name = "";
	String currentEvent= "";
	UserGui update;
	String nameInfo = "";
	Boolean deleteOrEdit = false;
	JFrame main = new JFrame("Edit or Delete Event");
	JFrame secondMain = new JFrame("Delete Event");
	JFrame thirdMain = new JFrame("Edit Event");
	JTextField monthField = new JTextField();
	JTextField dayField = new JTextField();
	JTextField yearField = new JTextField();
	JTextField specialEvent = new JTextField();
	JPanel mainPanel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel startPanel = new JPanel();
	JPanel endPanel =new JPanel();
	JComboBox eventBox;
	JButton edit = new JButton("Edit");
	JButton delete = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	JButton finish = new JButton("Finish");
	JButton finish2 = new JButton("Finish");
	JLabel choice = new JLabel("Do you wish to edit or delete an event?");
	JLabel deleteInfo = new JLabel("Select an Event");
	JLabel day = new JLabel("Day");
	JLabel month = new JLabel("Month");
	JLabel year = new JLabel("Year");
	JLabel event = new JLabel("Event");
	
	JTextField nameField = new JTextField();
	static String info = "Enter the name of the person you wish to add.";
	static JLabel intro = new JLabel(info);
	
	public editEvents(String name)
	{
		this.name = name;
	}
	
	//This initializes the current selection
	public void initiate()
	{
		boolean isInfo = fillBox();
		main.setSize(400, 135);
		main.setLocationRelativeTo(null);
		if(!isInfo)
		{
			JPanel none = new JPanel();
			JLabel noInfo = new JLabel("There is no information to edit or delete for this person");
			none.add(noInfo);
			main.add(none);
		}
		else
		{
			edit.addActionListener(this);
			delete.addActionListener(this);
			cancel.addActionListener(this);
			panel1.add(edit);
			panel1.add(delete);
			panel1.add(cancel);
			panel2.add(choice);
			panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
			panel3.add(panel2);
			panel3.add(panel1);
			main.add(panel3);
		}
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//This method will fill the event box, as well as the array list used to write to a file in the future
	public boolean fillBox()
	{
		boolean hasInfo = false;
		int lineCount = 0;
		String current = "";
		try
		{
			
			eventArray.clear();
			FileReader eventReader = new FileReader("C:\\GroupGProject" + File.separator + name + File.separator + "event.txt");
			BufferedReader reader = new BufferedReader(eventReader);
			while((current = reader.readLine()) != null)
			{
				eventArray.add(current);
				lineCount++;
				System.out.println(lineCount);
			}
			reader.close();
		}
		catch(IOException e){}
		
		if(lineCount == 0)
		{
			hasInfo = false;
		}
		else
		{
			hasInfo = true;
			Collections.sort(eventArray);
		}
		return hasInfo;
	}
	
	//This will create a new GUI that will be used to select an event to delete or edit
	public void selectEvents()
	{
		
		eventBox = new JComboBox(eventArray.toArray());
		eventBox.setPreferredSize(new Dimension(175, 25));
		finish.addActionListener(this);
		cancel.addActionListener(this);
		eventBox.addActionListener(this);
		secondMain.setSize(new Dimension(400, 100));
		BoxLayout box = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(box);
		
		startPanel.add(deleteInfo);
		endPanel.add(eventBox);
		endPanel.add(finish);
		endPanel.add(cancel);
		mainPanel.add(startPanel);
		mainPanel.add(endPanel);
		
		secondMain.add(mainPanel);
		secondMain.setVisible(true);
		secondMain.setLocationRelativeTo(null);
		secondMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	//This section of the code deletes an event
	public void removeEvent(String eventToDelete)
	{
		File inFile = new File("C:\\GroupGProject" + File.separator + name + File.separator + "event.txt");
		File tempFile = new File("C:\\GroupGProject" + File.separator + "temp.txt");
		tempFile.delete();
		
		try 
		{
			tempFile.createNewFile();
		} 
		catch (IOException e1) {}
		
		String line = "";

		
		try 
		{
			BufferedReader read = new BufferedReader(new FileReader(inFile));
			BufferedWriter write = new BufferedWriter(new FileWriter(tempFile));


			while((line = read.readLine()) != null) 
			{
			    String cutLine = line.trim();
			    if(!cutLine.equals(eventToDelete))
			    {
					write.write(line + "\n");
			    }
			    else
			    {
			    	System.out.println(eventToDelete);
			    }
			}
			write.close();
			read.close();
			inFile.delete();
			tempFile.renameTo(inFile);
		} 
		catch (IOException e) {}
	}
	
	//This method will allow the user to alter an event
	public void changeEvent()
	{
		panel1.removeAll();
		panel2.removeAll();
		panel3.removeAll();
		mainPanel.removeAll();
		thirdMain.setSize(350, 200);
		thirdMain.setLocationRelativeTo(null);
		cancel.addActionListener(this);
		finish2.addActionListener(this);
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
		panel4.add(finish2);
		
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		
		thirdMain.add(mainPanel);
		thirdMain.pack();
		thirdMain.setVisible(true);
		thirdMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

	//These are the actions performed
	public void actionPerformed(ActionEvent a) 
	{
		if(a.getSource() == cancel)
		{
			main.dispose();
			secondMain.dispose();
			thirdMain.dispose();
		}
		if(a.getSource() == edit)
		{
			deleteOrEdit = true;
			main.dispose();
			selectEvents();
		}
		if(a.getSource() == delete)
		{
			deleteOrEdit = false;
			main.dispose();
			selectEvents();
		}
		if(a.getSource() == finish)
		{
			if(deleteOrEdit == false)
			{
				currentEvent = (String) eventBox.getSelectedItem();
				removeEvent(currentEvent);
				secondMain.dispose();
			}
			else
			{
				currentEvent = (String) eventBox.getSelectedItem();
				changeEvent();
				secondMain.dispose();
			}
		}
		if(a.getSource() == finish2)
		{
			String d = dayField.getText();
			String m = monthField.getText();
			String y = yearField.getText();
			String e = specialEvent.getText();
			String allInfo = m + "\\" + d + "\\" + y + " " + e;
			if((d!= "" || d != null) && (m!= "" || m != null) && (y!= "" || y != null) && (e != "" || e != null))
			{
				addToDatabase(allInfo);
				removeEvent(currentEvent);
				thirdMain.dispose();
			}
		}
		
	}
}
