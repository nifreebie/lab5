package org.example.command_handlers;

import org.example.command_support.Command;
import org.example.command_support.CommandHandler;
import org.example.command_support.CommandManager;
import org.example.command_support.exceptions.ExtraArgumentException;
import org.example.command_support.exceptions.NoArgumentException;
import org.example.command_support.exceptions.StopExecuteScriptException;
import org.example.commands.ExecuteScriptCommand;
import org.example.controller.ConsoleManager;
import org.example.utils.BufferedLineReader;
import org.example.utils.ResponseWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ExecuteScriptCommandHandler extends CommandHandler<ExecuteScriptCommand> {

    @Override
    public void handle(ExecuteScriptCommand command) {
        CommandManager commandManager = this.app.getCommandManager();
        ResponseWriter responseWriter = this.app.getResponseWriter();
        File scriptFile = new File(command.fileName);
        if (this.app.getScriptsStack().contains(scriptFile)) {
            responseWriter.write("Попытка вызвать скрипт, который уже исполняется!");
        }else{
            this.app.getScriptsStack().add(scriptFile);
            try (FileInputStream input = new FileInputStream("src/main/java/org/example/data/" + scriptFile + ".txt")) {
                BufferedLineReader bufferedLineReader = new BufferedLineReader(input);
                this.app.setBufferedLineReader(bufferedLineReader);
                while (bufferedLineReader.hasNextLine()) {
                    try{
                        String line = bufferedLineReader.nextLine().trim();
                        String[] str = line.trim().split("\\s+");
                        while (str.length == 0 || str.length > 2) {
                            responseWriter.write("Неверный формат ввода команды!");
                            responseWriter.write(">");
                            line = bufferedLineReader.nextLine().trim();
                            str = line.trim().split("\\s+");

                        }
                        String commandName = ConsoleManager.parseToCommandName(str[0]);
                        try {
                            Command execCommand = (Command) Class.forName("org.example.commands."+commandName+"Command").getConstructor(String[].class).newInstance((Object) str);
                            commandManager.executeCommand(execCommand);
                        }catch(NoClassDefFoundError | ClassNotFoundException e){
                            responseWriter.write("Команды "+ str[0] + " не существует!");
                        }
                        catch (InstantiationException | IllegalAccessException |
                               NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        } catch(InvocationTargetException e){
                            Throwable cause = e.getCause();
                            if(cause.getClass().equals(ExtraArgumentException.class)){
                                responseWriter.write("У команды не должно быть аргумета!");
                            }
                            if(cause.getClass().equals(NoArgumentException.class)){
                                responseWriter.write("У команды должен быть аргумет!");
                            }
                            if(cause.getClass().equals(NumberFormatException.class)){
                                responseWriter.write("Неверный формат аргумента!");
                            }
                        }
                    }catch(StopExecuteScriptException e){
                        responseWriter.write("Ошибка при исполнении скрипта!");
                        break;

                    }

                }

            } catch (FileNotFoundException e) {
                responseWriter.write("Такого файла не существует!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.app.getScriptsStack().removeLast();
        }
    }
}
