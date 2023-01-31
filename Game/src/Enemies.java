import java.util.Random;

public class Enemies 
{
	private String name;
	private String race;
	// Allows for more intricate decision making statements such as avoiding fighting an enemy yet it staying alive.
	private String combatStatus;
	private int health;
	private int damage;
	private boolean isDead;
	
	
	public Enemies(String name, String race, int health, int damage, String combatStatus)
	{
		this.name = name;
		this.race = race; 
		this.health = health;
		this.damage = damage;
		isDead = false;
		this.combatStatus = combatStatus;
	}
	
	public String getCreatureName()
	{
		return name;
	}
	
	public String getCreatureRace()
	{
		return race;
	}
	
	public int getCreatureDamage()
	{
		Random rd = new Random();
		
		//int min = damage;
		//int max = damage * 2;
		//int damage = rd.nextInt(min) + max;
		return rd.nextInt(damage) + 1;
	}
	
	public int getCreatureHealth()
	{
		return health;
	}
	
	public String getCombatStatus()
	{
		return combatStatus;
	}
	
	public void setCreatureHealth(int health)
	{
		this.health = health;
	}
	
	public void setCombatStatus(String combatStatus)
	{
		this.combatStatus = combatStatus;
	}
	
	public void setCreatureIsDead(boolean isDead)
	{
		this.isDead = isDead;
	}
	
	public boolean getCreatureIsDead()
	{
		if(health <= 0)
		{
			isDead = true;
			return isDead;
		}
		
		else
		{
			isDead = false;
			return isDead;
		}
	}
}
