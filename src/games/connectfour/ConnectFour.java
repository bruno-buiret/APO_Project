package games.connectfour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

import games.AIPlayer;
import games.Board;
import games.Game;
import games.HistoryEntry;
import games.Player;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents a game of connect four.
 */
public class ConnectFour extends Game
{
	/**
	 * @brief Holds the serialization version number.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @brief Creates a new connect four game.
	 * @see games.Game.Game()
	 */
	public ConnectFour()
	{
		super();
		this.board = new Board(7, 6, new ConnectFourPlayerFormatter());
	}
	
	/**
	 * @brief Runs a game of connect four.
	 * @see games.Game.run()
	 * @todo Optimize this method because there is a lot of repeated code with the TicTacToe's one.
	 */
	public void run()
	{
		// Create the players
		boolean keepScanning = false;
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int actionId = -1;
		
		// Display the adversary menu
		System.out.println("What kind of game would you like to play?");
		System.out.println(" 1. Human vs Human");
		System.out.println(" 2. Human vs Stupid AI");
		System.out.println(" 3. Human vs Smart AI");
		System.out.println(" 4. Human vs Chuck Norris");
		
		// Asks the user what they want
		do
		{
			try
			{
				System.out.print("=> ");
				actionId = Integer.parseInt(r.readLine());
				keepScanning = actionId != 1 && actionId != 2 && actionId != 3 && actionId != 4;
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
		
		System.out.println();
		
		// The first player is always human
		this.players.add(new HumanPlayer(1, this));
		
		// Create the second player
		switch(actionId)
		{
			case 1:
				this.players.add(new HumanPlayer(2, this));
			break;
			
			case 2:
				this.players.add(new StupidAI(2, this));
			break;
			
			case 3:
				this.players.add(new SmartAI(2, this));
			break;
			
			case 4:
				this.players.add(new ChuckNorris(2, this));
			break;
		}
		
		// Main loop
		boolean keepLooping = true;
		int playerIndex = 0;
		
		while(keepLooping)
		{
			// Display the board
			System.out.println(this.board);
			
			// Let the current player play
			boolean keepPlaying = false;
			
			System.out.println(String.format("Current player: %s (%c)",
				this.players.get(playerIndex),
				this.board.getFormatter().getPlayerRepresentation(this.players.get(playerIndex))
			));
			
			do
			{
				Position position = this.players.get(playerIndex).getNextPosition();
				
				if(position != null)
				{
					try
					{
						this.play(this.players.get(playerIndex), position);
						System.out.println(String.format("%s played column %d.", this.players.get(playerIndex), position.getX()));
						keepPlaying = false;
					}
					catch(InvalidParameterException e)
					{
						if(!(this.players.get(playerIndex) instanceof AIPlayer))
						{
							System.err.println(e.getMessage());
						}
						
						keepPlaying = true;
					}
				}
				else
				{
					keepPlaying = true;
				}
			}
			while(keepPlaying);
			
			// Check if the player won or if the game is finished
			if(this.check(this.players.get(playerIndex)))
			{
				System.out.println(String.format("%s has won.", this.players.get(playerIndex)));
				keepLooping = false;
			}
			else if(this.history.size() == this.board.getHeight() * this.board.getWidth())
			{
				System.out.println("Nobody won.");
				keepLooping = false;
			}
			
			// Change player
			playerIndex = ++playerIndex % this.players.size();
		}
	}
	
	/**
	 * @brief Plays a position on the board.
	 * @see games.Game.play(Player, Position)
	 * 
	 * This method takes into account gravity by overriding the ordinate of the given
	 * position and then placing the pawn.
	 */
	protected void play(Player player, Position position) throws InvalidParameterException
	{
		int x = position.getX();
		int y = 0;
		
		while(y + 1 < this.board.getHeight() && this.board.getAt(x, y + 1) == null)
		{
			y++;
		}
		
		if(this.board.getAt(x, y) == null)
		{
			this.board.setAt(x, y, player);
			
			if(y != position.getY())
			{
				this.history.add(new HistoryEntry(player, new Position(x, y)));
			}
			else
			{
				this.history.add(new HistoryEntry(player, position));
			}
		}
		else
		{
			throw new InvalidParameterException(String.format("Column %d is already filled.", x));
		}
	}
	
	/**
	 * @brief Checks if a player won a game of connect four.
	 * @see games.Game.check(Player)
	 * @todo Implement this method.
	 */
	protected boolean check(Player player)
	{
		// Check horizontals, verticals, diagonals
		return false;
	}
}