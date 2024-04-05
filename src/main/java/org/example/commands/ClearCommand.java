package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.ExtraArgumentException;
@NoArgsConstructor
public class ClearCommand implements Command {
    public ClearCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
