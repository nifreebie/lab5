package org.example.utils;

import org.example.service.AppContainer;

import java.io.*;

public class ResponseWriter {

    public void write(String str) {
            AppContainer app = AppContainer.getInstance();
            if (app.isInteractiveMode()) {
                System.out.print("[script] ");
            }
            for (File script : app.getScriptsStack()) {
                System.out.print(script.getName() + " > ");
            }
            System.out.println(str);

    }
    public void enterCommand(){
        System.out.print('>');
    }


}
