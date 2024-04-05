package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.ExtraArgumentException;

@NoArgsConstructor
public class PrintFieldDescendingPartNumberCommand implements Command {
    public PrintFieldDescendingPartNumberCommand(String[] str) {
        if(str.length == 2) throw new ExtraArgumentException();
    }

    @Override
    public String getDescription() {
        return "вывести значения поля partNumber всех элементов в порядке убывания";
    }

    @Override
    public String getName() {
        return "print_field_descending_part_number";
    }
}
