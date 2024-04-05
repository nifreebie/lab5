package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.CommandWithIdArgument;
import org.example.command_support.exceptions.NoArgumentException;
@NoArgsConstructor
public class RemoveByIdCommand implements Command, CommandWithIdArgument {
    public long id;
    public RemoveByIdCommand(String[] str){
        if(str.length == 1) throw new NoArgumentException();
        if(checkArgForId(str[1])){
            this.id = Long.parseLong(str[1]);
        }else{
            throw new NumberFormatException();


        }
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

}
