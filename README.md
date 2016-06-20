# Command Handling

A simple, clean, and easy to use command system. Currently only
compatible with the Bukkit API (And of course it's forks: Paper/Spigot).

You may include this via Maven, or add the [jar](https://github.com/OutdatedVersion/command-handler/releases/download/2.0/Command-API.jar) itself. 

**Maven:** 

a) Add the repository...
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
b) Add the dependency...

```xml
<dependency>
    <groupId>com.github.OutdatedVersion</groupId>
    <artifactId>child-commands</artifactId>
    <version>2.1</version>
</dependency>
```

**Usage:**

After including the depdency you may create commands as you wish.
Be sure to start up the handler, or it won't work!

```java
new CommandHandler(<Your plugin instance>);
```

Then commands are like so:

```java
// Main Command
import com.perceivedstudios.command.Command;
import org.bukkit.entity.Player;

public class KittensCmd extends Command
{

    public KittensCmd()
    {
        super("kittens");
    }

    @Override
    public void execute(Player player, String[] args)
    {
        // Stuff
    }
    
}
```

Of course you may also have 'sub-commands'.

```java
import com.perceivedstudios.command.ChildCommand;
import org.bukkit.entity.Player;

public class ExtraKittensCmd extends ChildCommand
{

    public ExtraKittensCmd(Command parent)
    {
       super(parent, "extra");
    }
    
    @Override
    public void execute(Player player, String[] args)
    {
        player.sendMessage("kittttennnnnnssss!");
    }

}
```

```java
public KittensCmd()
{
    super("kittens");
    
    new ExtraKittensCmd(this);
}
```

This should be what you need to get started. If you have any issues
please do not hesitate to contact me on the [Spigot forums](https://www.spigotmc.org/members/outdatedversion.77839).
