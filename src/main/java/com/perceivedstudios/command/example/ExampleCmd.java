package com.perceivedstudios.command.example;

import com.perceivedstudios.command.Command;
import org.bukkit.entity.Player;

/**
 * OutdatedVersion
 * At: 11:42 PM (Apr/09/2016)
 * subcommand-testing
 */

public class ExampleCmd extends Command
{

    public ExampleCmd()
    {
        super("example");

        registerChildCommand(new ExampleChildCommand(this));
    }

    @Override
    public void execute(Player player, String[] args)
    {
        // Do stuff

        // oh no we ran out of stuff to do! let's see if a sub command exists...
        attemptSubCommand(player, args);
    }

}
