import java.util.Random;

public class Player 
{
	private String name;
	private String race;
	private int health;
	private int damage;
	
	public Player(String name, String race, int health, int damage)
	{
		this.name = name;
		this.race = race; 
		this.health = health;
		this.damage = damage;
	}
	
	public String getPlayerName()
	{
		return name;
	}
	
	public String getPlayerRace()
	{
		return race;
	}
	
	public int getPlayerHealth()
	{
		return health;
	}
	
	public int getPlayerDamage()
	{
		Random rd = new Random();
		
		//int min = damage;
		//int max = damage * 2;
		//int damage = rd.nextInt(min) + max;
		return rd.nextInt(damage) + 1;
	}
	
	public void setPlayerHealth(int health)
	{
		this.health = health;
	}
	
	public void setPlayerDamage(int damage)
	{
		this.damage = damage;
	}
	
}
