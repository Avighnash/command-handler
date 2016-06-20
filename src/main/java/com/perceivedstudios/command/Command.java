package com.perceivedstudios.command;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * OutdatedVersion
 * 11:35 PM (Apr/09/2016)
 */

public abstract class Command
{

    /** See {@link #executor()} */
    private final String mainExecutor;

    /** Similar to the {@link #mainExecutor} */
    private final List<String> aliases;

    /** The permission node someone must have to use this */
    private final String permissionNode;

    /** A map that relates each key to it's sub-command */
    private HashMap<String, ChildCommand> childCommandRelation;

    public Command(String executor)
    {
        this(null, executor);
    }

    public Command(String permission, String... executors)
    {
        mainExecutor = executors[0];
        aliases = Arrays.asList(executors);
        permissionNode = permission;
        childCommandRelation = new HashMap<>();

        // Register this with our handler
        CommandHandler.get().addCommand(this);
    }

    /**
     * Gets the string that we want to
     * run this command on. For example
     * /help would have the executor of
     * 'help'
     *
     * @return {@link String} The main executor for this command
     */
    public final String executor()
    {
        return mainExecutor;
    }

    /**
     * Gets the permission node that
     * this command searches for whenever
     * a player executed this base command.
     * This will only occur if our node
     * is not <code>null</code>. Each
     * individual {@link ChildCommand}
     * may also have it's own node.
     *
     * @return {@link String} The node
     */
    public final String permissionNode()
    {
        return permissionNode;
    }

    /**
     * Gets the alternate executors
     * this command uses. For example,
     * you may have <span style="font-style: italic">/kittens</span>
     * which summons multiple cats. Though,
     * you'd also like to be able to do
     * <span style="font-style: italic">/cats</span> to achieve this
     * same goal. Supply an extra <code>String</code> within
     * the construction of this command to do this.
     *
     * @return {@link String[]} All possible nodes as an array
     */
    public final List<String> aliases()
    {
        return Collections.unmodifiableList(aliases);
    }

    /**
     * Adds a child (sub) command to this
     * parent. You may choose any way of
     * processing these sub-commands you
     * see fit.
     *
     * @param command {@link ChildCommand} The command to use
     */
    public void addChild(ChildCommand command)
    {
        childCommandRelation.put(command.executor(), command);
    }

    /**
     * Execute this command
     * This should contain all of the
     * logic for this command
     *
     * @param player The player who ran the command
     * @param args The arguments the player provided
     */
    public abstract void execute(Player player, String[] args);

    /**
     * Attempt to find a sub-command
     * matching someone's arguments
     *
     * @param player The player attempting this
     * @param args The player's provided arguments
     */
    protected void attemptChildCommand(Player player, String[] args)
    {
        String _attempt = args[0].toLowerCase();

        childCommandRelation.forEach((key, command) ->
        {
            String _permissionMessage = command.message();
            if (_attempt.equals(key) || command.aliases().contains(_attempt))
            {
                if (command.permissionNode() != null)
                    if (!player.hasPermission(_permissionMessage))
                    {
                        player.sendMessage(_permissionMessage);
                        return;
                    }

                command.execute(player, args);
                return;
            }
        });
    }

}
