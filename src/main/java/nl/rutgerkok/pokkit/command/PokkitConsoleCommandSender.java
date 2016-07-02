package nl.rutgerkok.pokkit.command;

import nl.rutgerkok.pokkit.Pokkit;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;

/**
 * Implementation of {@link ConsoleCommandSender} on top of Nukkit.
 *
 */
final class PokkitConsoleCommandSender extends PokkitCommandSender implements ConsoleCommandSender {

    protected PokkitConsoleCommandSender(cn.nukkit.command.ConsoleCommandSender nukkit) {
        super(nukkit);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public void acceptConversationInput(String input) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Not supported by " + Pokkit.NAME);
    }

    @Override
    public boolean isConversing() {
        return false;
    }

    @Override
    public void sendRawMessage(String message) {
        this.sendMessage(message);
    }

}
