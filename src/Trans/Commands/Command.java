package src.model.Trans.Commands;

/**
 * An interface used to regulare what a Command need to have as methods
 */
public interface Command {
    public void execute() throws Exception;
}
