package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.exceptions.NoArgumentException;
@NoArgsConstructor
public class ExecuteScriptCommand implements Command {
    public String fileName;

    public ExecuteScriptCommand(String[] str) {
        if(str.length == 1) throw new NoArgumentException();
        else{
            this.fileName = str[1];
        }

    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
