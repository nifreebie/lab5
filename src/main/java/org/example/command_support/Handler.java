package org.example.command_support;

@FunctionalInterface
public interface Handler {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     */
    void apply(Command t);
}
