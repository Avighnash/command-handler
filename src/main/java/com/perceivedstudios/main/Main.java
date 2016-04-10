package com.perceivedstudios.main;

import com.perceivedstudios.command.CommandListener;
import com.perceivedstudios.command.example.ExampleCmd;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * OutdatedVersion
 * At: 11:34 PM (Apr/09/2016)
 * subcommand-testing
 */

public class Main extends JavaPlugin
{

    @Override
    public void onEnable()
    {

        // This needs to be registered
        // before any commands
        new CommandListener(this);

        // Remember: sub-commands are
        // registered in their respective
        // parents not here
        new ExampleCmd();
    }

    @Override
    public void onDisable()
    {

    }

}
