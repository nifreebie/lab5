package org.example.command_support;

@FunctionalInterface
public interface Handler {

    /**
     * Applies this function to the given argument.
     *
     * @param command the function argument
     */
    void apply(Command command);
}
