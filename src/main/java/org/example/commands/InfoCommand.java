package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.ExtraArgumentException;
@NoArgsConstructor
public class InfoCommand implements Command {
    public InfoCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
    }

    @Override
    public String getDescription() {
        return "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String getName() {
        return "info";
    }
}
