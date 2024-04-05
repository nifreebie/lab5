package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.ExtraArgumentException;
@NoArgsConstructor
public class PrintDescendingCommand implements Command {
    public PrintDescendingCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
    }

    @Override
    public String getDescription() {
        return "вывести элементы коллекции в порядке убывания";
    }

    @Override
    public String getName() {
        return "print_descending";
    }
}
