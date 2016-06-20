package com.perceivedstudios.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * OutdatedVersion
 * 11:36 PM (Apr/09/2016)
 */

public abstract class ChildCommand
{

    private Command parentCommand;

    private final String childExecutor;
    private final List<String> aliases;
    private final String permissionNode;

    private String permissionMessage;

    public ChildCommand(Command parent, String... executors)
    {
        this(parent, null, executors);
    }

    public ChildCommand(Command parent, String permissionNode, String... executors)
    {
        parentCommand = parent;
        this.permissionNode = permissionNode;
        childExecutor = executors[0];
        aliases = Arrays.asList(executors);

        permissionMessage = ChatColor.RED + "Uh oh... you're missing the approval to use that command.";
    }

    /**
     * The primary parent command
     * that this sub-command
     * belongs to.
     *
     * @return {@link Command} The command
     */
    public final Command parent()
    {
        return parentCommand;
    }

    /**
     * This is what we'll look for
     * when iterating over the sub
     * commands of a command. In the
     * example of '/help one'. The
     * executor would be 'one'.
     *
     * @return This sub-command's executor
     */
    public String executor()
    {
        return childExecutor;
    }

    /**
     * Gets the executors that
     * the parent command will
     * look for.
     *
     * @return {@link List} Aliases as a list
     */
    public final List<String> aliases()
    {
        return Collections.unmodifiableList(aliases);
    }

    /**
     * Gets the permission
     * node that this child
     * command will execute on.
     *
     * @return {@link String} The node
     */
    public final String permissionNode()
    {
        return permissionNode;
    }

    /**
     * Gets the message that
     * this command will use
     * if someone does not have
     * the required permission.
     *
     * @return {@link String} The message
     */
    public String message()
    {
        return permissionMessage;
    }

    /**
     * Changes the message that
     * this command will send if
     * someone does not have the
     * required permission to execute.
     *
     * @param val The message. You may use colors.
     * @return {@link ChildCommand} The command
     */
    public ChildCommand message(String val)
    {
        permissionMessage = ChatColor.translateAlternateColorCodes('&', val);
        return this;
    }

    /**
     * Similar to it's parent this
     * contains all of the logic
     * for a command.
     *
     * @param player The player who ran the command
     * @param args The arguments the player provided
     */
    public abstract void execute(Player player, String[] args);

}
