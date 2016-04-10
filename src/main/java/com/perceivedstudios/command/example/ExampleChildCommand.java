package com.perceivedstudios.command.example;

import com.perceivedstudios.command.ChildCommand;
import com.perceivedstudios.command.Command;
import org.bukkit.entity.Player;

/**
 * OutdatedVersion
 * At: 11:43 PM (Apr/09/2016)
 * subcommand-testing
 */

public class ExampleChildCommand extends ChildCommand
{

    public ExampleChildCommand(Command parent)
    {
        super("one", parent);
    }

    @Override
    public void execute(Player player, String[] args)
    {
        player.sendMessage("Sub Command > 'One'");
    }

}
