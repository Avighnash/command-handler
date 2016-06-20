package com.perceivedstudios.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * OutdatedVersion
 * 11:50 PM (Apr/09/2016)
 */

public class CommandHandler implements Listener
{

    private static CommandHandler Instance;
    private final HashMap<String, Command> commands;

    private String missingPermissionMessage;

    public CommandHandler(JavaPlugin plugin)
    {
        if (Instance != null)
            throw new RuntimeException("You may only initialize this **once**. Use #end() to refresh this handler.");

        Instance = this;
        commands = new HashMap<>();
        missingPermissionMessage = ChatColor.RED + "Uh oh... you're missing the approval to use that command.";

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /** Grab the instance of this handler */
    public static CommandHandler get()
    {
        return Instance;
    }

    /** Closes this command listener */
    public static void end()
    {
        if (Instance != null)
        {
            Bukkit.getLogger().info("Someone has disabled our command handler. May wanna check that out?");
            HandlerList.unregisterAll(Instance);
            Instance = null;
        }
    }

    /** @return {@link String} The message used */
    public String message()
    {
        return missingPermissionMessage;
    }

    /**
     * Updates the invalid permission
     * message. Sent to players whenever
     * they have do not have the required
     * permission to use a message.
     *
     * @param val The new message. You may use chat colors.
     * @return {@link CommandHandler} This instance
     */
    public CommandHandler message(String val)
    {
        missingPermissionMessage = ChatColor.translateAlternateColorCodes('&', val);
        return Instance;
    }

    /**
     * Adds a command to our handler
     * so it may be used.
     *
     * @param command The command to add
     */
    public Command addCommand(Command command)
    {
        commands.put(command.executor().toLowerCase(), command);
        return command;
    }

    /**
     * Unregisters a command from
     * this handler. It will no
     * longer be processed.
     *
     * @param command The command to remove
     * @return {@link Command} The removed command
     */
    public CommandHandler removeCommand(Command command)
    {
        command.aliases().forEach(commands::remove);
        return Instance;
    }

    /**
     * Attempts to find a
     * command by one of it's
     * executors.
     *
     * @param executor A possible executor
     * @return The {@link Command} or <code>null</code>
     */
    public Command findCommand(String executor)
    {
        return commands.get(executor);
    }

    @EventHandler (ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void handleCommands(PlayerCommandPreprocessEvent event)
    {
        Player _player = event.getPlayer();
        String _commandRaw = event.getMessage().substring(1).toLowerCase();
        String[] _args = null;

        if (_commandRaw.contains(" "))
        {
            _commandRaw = _commandRaw.split(" ")[0];
            _args = event.getMessage().substring(event.getMessage().indexOf(' ') + 1).split(" ");
        }

        if (_args == null) _args = new String[0];

        if (commands.containsKey(_commandRaw))
        {
            event.setCancelled(true);

            Command _command = commands.get(_commandRaw);

            if (_command.permissionNode() != null)
                if (!_player.hasPermission(_command.permissionNode()))
                {
                    _player.sendMessage(missingPermissionMessage);
                    return;
                }

            _command.execute(_player, _args);
        }
    }

}
