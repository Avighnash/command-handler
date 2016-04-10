package com.perceivedstudios.command;

import org.bukkit.entity.Player;

/**
 * OutdatedVersion
 * At: 11:36 PM (Apr/09/2016)
 * subcommand-testing
 */

public abstract class ChildCommand
{

    private final String childExecutor;
    private Command parentCommand;

    public ChildCommand(String executor, Command parent)
    {
        this.parentCommand = parent;
        this.childExecutor = executor;
    }

    /**
     * The primary parent command
     * that this sub-command
     * belongs to
     *
     * @return {@link Command} The command
     */
    public Command getParent()
    {
        return parentCommand;
    }

    /**
     * This is what we'll look for
     * when iterating over the sub
     * commands of a command. In the
     * example of '/help one'. The
     * executor would be 'one'
     *
     * @return This sub-command's executor
     */
    public String getExecutor()
    {
        return childExecutor;
    }

    /**
     * Similar to it's parent this
     * contains all of the logic
     * for a command
     *
     * @param player The player who ran the command
     * @param args The arguments the player provided
     */
    public abstract void execute(Player player, String[] args);

}
