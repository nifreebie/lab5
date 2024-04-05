package org.example.utils;

import org.example.command_support.exceptions.StopExecuteScriptException;
import org.example.model.*;
import org.example.service.AppContainer;
import org.example.validation.ValidationException;
import org.example.validation.Validator;
import org.example.validation.rules.*;

import java.util.List;

public class ValidReader {
    static AppContainer appContainer = AppContainer.getInstance();
    static ResponseWriter responseWriter = appContainer.getResponseWriter();

    public static String readName() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите имя:");
        return readValidValue();
    }

    public static Integer readCoordinateX() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите координату x:");
        return Integer.parseInt(readValidValue(List.of(new MinRule(-497), new IntRule())));

    }

    public static Float readCoordinateY() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите координату y:");
        return Float.parseFloat(readValidValue(List.of(new MaxRule(745f), new FloatRule())));
    }

    public static int readPrice() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите цену:");
        return Integer.parseInt(readValidValue(List.of(new MinRule(1), new IntRule())));
    }

    public static String readPartNumber() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите partNumber:");
        return readValidValue(List.of(new StringLengthRule(74)));
    }

    public static UnitOfMeasure readUnitOfMeasure() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Выберите единицу измерения:");
        for (int i = 0; i < UnitOfMeasure.values().length; i++) {
            if (!appContainer.isInteractiveMode()) responseWriter.write(" • " + i + " - " + UnitOfMeasure.values()[i]);
        }
        String uofm = readValidValue(List.of(new IntRule(), new EnumRule(UnitOfMeasure.values())), false);
        return UnitOfMeasure.values()[Integer.parseInt(uofm)];

    }

    public static Long readEmployeesCount() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите количесство сотрдуников:");
        return Long.parseLong(readValidValue(List.of(new MinRule(1), new LongRule())));
    }

    public static OrganizationType readOrganizationType() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Выберите тип организации:");
        for (int i = 0; i < OrganizationType.values().length; i++) {
            if (!appContainer.isInteractiveMode()) responseWriter.write(" • " + i + " - " + OrganizationType.values()[i]);
        }
        String ot = readValidValue(List.of(new IntRule(), new EnumRule(OrganizationType.values())), false);
        return OrganizationType.values()[Integer.parseInt(ot)];
    }

    public static Address readAddress() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите адрес:");
        String street = readValidValue(List.of(), true);
        return new Address(street);
    }

    public static Coordinates readCoordinates() {
        Integer x = ValidReader.readCoordinateX();
        Float y = ValidReader.readCoordinateY();
        return new Coordinates(x, y);

    }

    public static String readOrganizationName() {
        if (!appContainer.isInteractiveMode()) responseWriter.write("Введите имя организации:");
        return readValidValue();
    }

    public static Organization readOrganization() {
        String orgName = readOrganizationName();
        Long emCount = readEmployeesCount();
        OrganizationType orgType = readOrganizationType();
        Address address = readAddress();
        return new Organization(orgName, emCount, orgType, address);
    }

    private static String readValidValue(List<Rule> rules, boolean nullable) {
        BufferedLineReader reader = appContainer.getBufferedLineReader();
        while (true) {
            String input = reader.nextLine().trim().toLowerCase();
            if (nullable && input.isBlank()) {
                return null;
            }
            try {
                Validator.validate(input, rules);
                return input;
            } catch (ValidationException e) {
                for (String errorMsg : e.getErrors()) {
                    responseWriter.write(errorMsg);
                }
                if (appContainer.isInteractiveMode()) {
                    throw new StopExecuteScriptException();
                }
            }
        }

    }

    private static String readValidValue(List<Rule> rules) {
        return readValidValue(rules, false);
    }

    private static String readValidValue() {
        return readValidValue(List.of(), false);
    }

}
