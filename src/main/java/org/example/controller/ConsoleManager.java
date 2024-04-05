package org.example.controller;

import lombok.NoArgsConstructor;
import org.example.command_support.Command;
import org.example.command_support.CommandManager;
import org.example.command_support.exceptions.ExtraArgumentException;
import org.example.command_support.exceptions.NoArgumentException;
import org.example.service.AppContainer;
import org.example.utils.BufferedLineReader;
import org.example.utils.ResponseWriter;

import java.io.BufferedWriter;
import java.lang.reflect.InvocationTargetException;

@NoArgsConstructor
public class ConsoleManager {


    public void run()  {
        AppContainer app = AppContainer.getInstance();
        BufferedLineReader bufferedLineReader = app.getBufferedLineReader();
        ResponseWriter responseWriter = app.getResponseWriter();
        CommandManager commandManager = app.getCommandManager();
        responseWriter.write("Программа запущена, для получения списка команд введите 'help'");
        responseWriter.enterCommand();
        while (bufferedLineReader.hasNextLine()) {
            String line = bufferedLineReader.nextLine().trim();
            String[] str = line.trim().split("\\s+");
            while (str.length == 0 || str.length > 2) {
                responseWriter.write("Неверный формат ввода команды!");
                responseWriter.enterCommand();
                line = bufferedLineReader.nextLine().trim();
                str = line.trim().split("\\s+");

            }
            String commandName = parseToCommandName(str[0]);
            try {
                Command command = (Command) Class.forName("org.example.commands."+commandName+"Command").getConstructor(String[].class).newInstance((Object) str);
                commandManager.executeCommand(command);
                responseWriter.enterCommand();
            }catch(NoClassDefFoundError | ClassNotFoundException e){
                responseWriter.write("Такой команды нет");
                responseWriter.enterCommand();
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
                responseWriter.enterCommand();
            }

        }
    }
    public static String parseToCommandName(String str){
        char[] chars = str.toCharArray();
        String commandName = "";
        commandName += Character.toUpperCase(chars[0]);
        for(int i = 1; i < chars.length; i++){
            if(chars[i] == '_'){
                commandName += Character.toUpperCase(chars[i+1]);
                i++;
            }else{
                commandName += chars[i];
            }

        }
        return commandName;
    }

}
