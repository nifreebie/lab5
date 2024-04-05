package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.ExtraArgumentException;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class HelpCommand implements Command {
    public HelpCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();

    }
    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }
}
