/*William Suter
 * this class is basically the central GUI hub
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import javax.swing.*;
public class UserGui implements ActionListener
{
	ArrayList<String> nameList = new ArrayList<>();
	JFrame main = new JFrame("Special Dates and Wishlists");
	JPanel buttonPanel = new JPanel();
	JPanel displayPanel = new JPanel();
	JPanel textPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel endPanel = new JPanel();
	JButton addPerson = new JButton("Add Person Profile");
	JButton addInfo = new JButton("Add Event");
	JButton displayInfo = new JButton("Display Event");
	JButton eventEdit = new JButton("Edit/Delete Event");
	JButton addWish = new JButton("Add to Wish List");
	JButton displayWish = new JButton("Display Wishlist");
	static String name = "No Name";
	static JComboBox namesBox;
	JTextArea display = new JTextArea();
	JScrollPane displayPane = new JScrollPane(display);
	
	
	//This portion initializes the GUI
	public void startGui()
	{
		addPerson.addActionListener(this);
		addInfo.addActionListener(this);
		displayInfo.addActionListener(this);
		eventEdit.addActionListener(this);
		addWish.addActionListener(this);
		displayWish.addActionListener(this);
		main.setSize(425, 400);
		main.setLocationRelativeTo(null);
		
		
		
		initializeNameList();
		namesBox.setPreferredSize(new Dimension(100, 30));
		displayPanel.add(namesBox);
		displayPanel.add(displayInfo);
		displayPanel.add(addInfo);
		
		buttonPanel.add(eventEdit);
		buttonPanel.add(displayWish);
		buttonPanel.add(addWish);
		
		
		displayPane.setPreferredSize(new Dimension(400, 275));
		textPanel.add(displayPane);
		textPanel.setPreferredSize(new Dimension(400, 275));
		
		endPanel.setPreferredSize(new Dimension(400, 35));
		endPanel.add(addPerson);
		
		BoxLayout box = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(box);
		
		mainPanel.add(displayPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(textPanel);
		mainPanel.add(endPanel);
		
		main.add(mainPanel);
		
		name = (String) namesBox.getSelectedItem();
		
		main.setVisible(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//This method will initialize the JComboBox names, it is used to populate the combo box.
	public void initializeNameList()
	{
		
		int lineCount = 0;
		BufferedReader reader = null;
		String current = "";
		try
		{
			nameList.clear();
			FileReader nameReader = new FileReader("C:\\GroupGProject" + File.separator + "Names.txt");
			reader = new BufferedReader(nameReader);
			while((current = reader.readLine()) != null)
			{
				nameList.add(current);
				lineCount++;
			}
			reader.close();
		}
		catch(IOException e){}
		
		if(lineCount == 0)
		{
			String[] nameArray = {"No Name"};
			namesBox = new JComboBox(nameArray);
		}
		else
		{
			Collections.sort(nameList);
			namesBox = new JComboBox(nameList.toArray());
		}
	}
	//This method takes the information for a specific persons special events and displays the events in a text area.
	public void showEvents()
	{
		ArrayList<String> eventsArray = new ArrayList<String>();
		name = (String) namesBox.getSelectedItem();
		display.setText(null);
		int count = 0;
		String current = "";
		String total = "Special Events for: " + name + "\n";
		String fileName = "C:\\GroupGProject" + File.separator + name + File.separator + "event.txt";
		try
		{
			nameList.clear();
			FileReader eventReader = new FileReader(fileName);
			BufferedReader bufReader = new BufferedReader(eventReader);
			while((current = bufReader.readLine()) != null)
			{
				eventsArray.add(current);
				//total += current + "\n";
				count++;
			}
			if(count == 0)
			{
				display.append("No events for this person");
			}
			else
			{
				Collections.sort(eventsArray);
				for(int i = 0; i < eventsArray.size(); i++)
				{
					display.append(eventsArray.get(i) + "\n");
				}
			}
			bufReader.close();
		}
		catch(IOException e){}
	}
	
	//This shows the wish lists
	public void showWishes()
	{
		ArrayList<String> eventsArray = new ArrayList<String>();
		name = (String) namesBox.getSelectedItem();
		display.setText(null);
		int count = 0;
		String current = "";
		String total = "WishList for: " + name + "\n";
		display.append(total);
		String fileName = "C:\\GroupGProject" + File.separator + name + File.separator + "wishList.txt";
		try
		{
			nameList.clear();
			FileReader eventReader = new FileReader(fileName);
			BufferedReader bufReader = new BufferedReader(eventReader);
			while((current = bufReader.readLine()) != null)
			{
				eventsArray.add(current);
				count++;
			}
			if(count == 0)
			{
				display.append("No wishes for this person");
			}
			else
			{
				Collections.sort(eventsArray);
				for(int i = 0; i < eventsArray.size(); i++)
				{
					display.append(eventsArray.get(i) + "\n");
				}
			}
			bufReader.close();
		}
		catch(IOException e){}
	}
	//The actions performed
	public void actionPerformed(ActionEvent a) 
	{
		if(a.getSource() == namesBox)
		{
			name = (String) namesBox.getSelectedItem();
		}
		if(a.getSource() == addPerson)
		{
			AddUser newUser = new AddUser(this);
			newUser.initiate();
		}
		if(a.getSource() == displayInfo)
		{
			name = (String) namesBox.getSelectedItem();
			if(name == "No Name")
			{
				display.setText(null);
				display.append("There is no information to display");
			}
			else
			{
				showEvents();
			}
		}
		if(a.getSource() == addInfo)
		{
			name = (String) namesBox.getSelectedItem();
			if(name == "No Name")
			{
				display.setText(null);
				display.append("There are no people to add events for.");
			}
			else
			{
				AddEvent addEvent = new AddEvent(name);
				addEvent.initiate();
			}
		}
		if(a.getSource() == eventEdit)
		{
			editEvents change = new editEvents((String) namesBox.getSelectedItem());
			change.initiate();
		}
		if(a.getSource() == addWish)
		{
			AddToWishList add = new AddToWishList((String) namesBox.getSelectedItem());
			add.initiate();
		}
		if(a.getSource() == displayWish)
		{
			showWishes();
		}
		
	}
	
	
	
	
	
	
}
