package org.example.command_handlers;

import org.example.command_support.Command;
import org.example.command_support.CommandHandler;
import org.example.commands.HelpCommand;
import org.example.utils.ResponseWriter;

import java.io.File;
import java.util.ArrayList;

public class HelpCommandHandler extends CommandHandler<HelpCommand> {
    @Override
    public void handle(HelpCommand command) {
        ResponseWriter responseWriter = this.app.getResponseWriter();
        ArrayList<String[]> help_out = new ArrayList<>();
        File directory = new File("src/main/java/org/example/commands");
        String[] commandClasses = directory.list();

        for (String filename : commandClasses) {
            if (filename.endsWith(".java")) {
                String[] com_desc = new String[2];
                try {
                    Class cls = Class.forName("org.example.commands."+filename.replace(".java", ""));
                    Command commandClassObject = (Command) cls.newInstance();
                    com_desc[0] = commandClassObject.getName();
                    com_desc[1] = commandClassObject.getDescription();
                } catch (Exception ignored) {
                }
                help_out.add(com_desc);
            }
        }
        responseWriter.write("Список команд:");
        for (String[] comhelp : help_out) {
            responseWriter.write(comhelp[0] +": "+ comhelp[1]);
        }
    }
}
