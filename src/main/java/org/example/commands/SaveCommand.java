package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.ExtraArgumentException;
@NoArgsConstructor
public class SaveCommand implements Command {
    public SaveCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }

    @Override
    public String getName() {
        return "save";
    }

}
