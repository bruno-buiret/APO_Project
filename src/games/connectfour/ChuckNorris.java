package games.connectfour;

import games.Game;
import games.AIPlayer;
import games.Position;

/**
 * @author Bruno Buiret (11202344)
 * @version 1.0
 * @brief This class represents an AI that can win in only three moves.
 */
public class ChuckNorris extends AIPlayer
{
    /**
     * @brief Holds the serialization version number.
     */
    private static final long serialVersionUID = 1319664328717854832L;

    /**
	 * @see games.Player.Player(int, Game)
	 */
	public ChuckNorris(int number, Game game)
	{
		super(number, game);
		throw new RuntimeException("This class hasn't been implemented yet.");
	}

	/**
	 * @see games.Player.getNextPosition()
	 * @todo Implement this method.
	 */
	public Position getNextPosition()
	{
		return null;
	}
}