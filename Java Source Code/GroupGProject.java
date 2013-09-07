//William Suter
//The purpose of this project is to create a program that will store and display loved ones wish lists and special dates.
//The program will also display this information requested by the user

import java.io.File;
import java.io.IOException;


public class GroupGProject
{

	
	
	
	public static void main(String[] args) 
	{
		File initDirectory = new File("C:\\GroupGProject");
		
		if(!initDirectory.exists())
		{
			initDataBase();
		}
		UserGui begin = new UserGui();
		begin.startGui();

	}
	
	//This will initialize the database if it does not exist
	public static void initDataBase()
	{
		String parent = "C:\\GroupGProject";
		
		File directory = new File(parent);
		directory.mkdir();
		String personList = parent + File.separator + "Names.txt";
		
		
		File nameList = new File(personList);
		try
		{
			if(!nameList.exists())
			{
				nameList.createNewFile();
			}
		}
		catch(IOException e){}
	}

}
