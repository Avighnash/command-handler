package com.perceivedstudios.command;

import com.perceivedstudios.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;

/**
 * OutdatedVersion
 * At: 11:50 PM (Apr/09/2016)
 * subcommand-testing
 */

public class CommandListener implements Listener
{

    private static CommandListener Instance;
    private HashMap<String, Command> commands;

    public CommandListener(Main plugin)
    {
        Instance = this;

        commands = new HashMap<>();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /** Instance getter */
    public static CommandListener get()
    {
        return Instance;
    }

    /**
     * Add a command to our system
     *
     * @param command The command to add
     */
    public void addCommand(Command command)
    {
        commands.put(command.getExecutor().toLowerCase(), command);
    }

    @EventHandler
    public void processCommands(PlayerCommandPreprocessEvent event)
    {
        Player _player = event.getPlayer();
        String _commandRaw = event.getMessage().substring(1).toLowerCase();
        String[] _args = null;

        if (_commandRaw.contains(" "))
        {
            _commandRaw = _commandRaw.split(" ")[0];
            _args = event.getMessage().substring(event.getMessage().indexOf(' ') + 1).split(" ");
        }

        if (commands.containsKey(_commandRaw))
        {
            event.setCancelled(true);

            Command _command = commands.get(_commandRaw);

            // You could hypothetically implement some sort of permission system here

            _command.execute(_player, _args);
        }
    }

}
