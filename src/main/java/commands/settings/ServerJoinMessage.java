package commands.settings;

import commands.Command;
import core.SSSS;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.MSGS;
import utils.STATICS;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Created by zekro on 17.05.2017 / 15:02
 * DiscordBot/commands.SettingsCore
 * © zekro 2017
 */
public class ServerJoinMessage implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {

        if (core.Perms.check(permission(), event)) return;

        if (args.length < 1) {
            event.getTextChannel().sendMessage(MSGS.error().setDescription(help()).build()).queue();
            return;
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(args).forEach(s -> sb.append(s + " "));

        SSSS.setSERVERJOINMESSAGE(sb.toString().substring(0, sb.toString().length() - 1), event.getGuild());
        event.getTextChannel().sendMessage(MSGS.success().setDescription("Сообщение при подключении к серверу изменено на `" + sb.toString().substring(0, sb.toString().length() - 1) + "`.").build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return "Использование: .joinmsg <сообщение>\n" +
                "Установите в `OFF` для отключения сообщения при подключении к серверу.\n" +
                "Переменные: `[USER]` - имя подключившегося пользователя\n" +
                "           `[GUILD]` название этого сервера.";
    }

    @Override
    public String description() {
        return "Установка сообщения при подключении к серверу.";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.settings;
    }

    @Override
    public int permission() {
        return 4;
    }
}
