package com.perceivedstudios.command;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * OutdatedVersion
 * At: 11:35 PM (Apr/09/2016)
 * subcommand-testing
 */

public abstract class Command
{

    /** See {@link #getExecutor()} */
    private final String mainExecutor;
    /** A map that relates each key to it's sub-command */
    private HashMap<String, ChildCommand> childCommandRelation;

    public Command(String main)
    {
        this.mainExecutor = main;

        childCommandRelation = new HashMap<>();

        // Register
        CommandListener.get().addCommand(this);
    }

    /**
     * Gets the string that we want to
     * run this command on. For example
     * /help would have the executor of
     * 'help'
     *
     * @return {@link String} The main executor for this command
     */
    public String getExecutor()
    {
        return mainExecutor;
    }

    /**
     * Adds a child (sub) command to this
     * parent. You may choose any way of
     * processing these sub-commands you
     * see fit.
     *
     * @param command {@link ChildCommand} The command to use
     */
    public void registerChildCommand(ChildCommand command)
    {
        childCommandRelation.put(command.getExecutor(), command);
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
    protected void attemptSubCommand(Player player, String[] args)
    {
        String _attempt = args[0];

        childCommandRelation.forEach((key, command) ->
        {

            if (_attempt.equalsIgnoreCase(key))
            {
                command.execute(player, args);
                return;
            }

        });
    }

}
