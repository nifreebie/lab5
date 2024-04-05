package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.ExtraArgumentException;
@NoArgsConstructor
public class ShowCommand implements Command {
    public ShowCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }
}
