import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import games.connectfour.ConnectFour;
import games.tictactoe.TicTacToe;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents the program containing the menu and games.
 */
public class Program
{
	public static void main(String[] args)
	{
		boolean keepLooping = true;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("==[Board games]====");
		
		while(keepLooping)
		{
			// Display the menu to the user
			System.out.println("Choose what game you want to play, or if you want to exit the program.");
			System.out.println(" 1. Connect Four");
			System.out.println(" 2. Tic Tac Toe");
			System.out.println(" 3. Exit");
			
			// Initialize vars to determine what the user wants to do 
			boolean keepScanning = false;
			int actionId = -1;
			
			// Asks the user what he wants to do
			do
			{
				try
				{
					System.out.print("=> ");
					actionId = Integer.parseInt(r.readLine());
					keepScanning = actionId != 1 && actionId != 2 && actionId != 3;
				}
				catch (NumberFormatException e)
				{
					keepScanning = true;
				}
				catch (IOException e)
				{
					keepScanning = true;
				}
			}
			while(keepScanning);
			
			// Launch the wanted game or exit
			switch(actionId)
			{
				case 1:
					ConnectFour c = new ConnectFour();
					c.run();
				break;
				
				case 2:
					TicTacToe t = new TicTacToe();
					t.run();
				break;
				
				case 3:
					keepLooping = false;
				break;
			}
		}
		
		// Close the reader
		try
		{
			r.close();
		}
		catch (IOException e)
		{
		}
	}
}