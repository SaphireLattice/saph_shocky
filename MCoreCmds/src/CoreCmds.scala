import org.pircbotx.hooks.events.{MessageEvent, PrivateMessageEvent}
import org.saphire.shocky.module.Module

class CoreCmds extends Module {
    override val moduleName:String = "commands"
    def controller:CommandController = new CommandController

    override def onEnable() = {
        controller.addCommand("", new TestCmd)
    }

    override def onDisable() = {
    }

    def isCommand(message:String):Boolean = {
        message.trim.startsWith(",") //TODO: add code to grab that symbol from config
    }

    override def onMessage(event:MessageEvent) = {
        try {
            if (isCommand(event.getMessage))
                controller.runCommand(new Parameter(event.getMessage.trim))
        } catch {
            case e:Exception => e.printStackTrace()
        }
    }

    override def onPrivateMessage(event:PrivateMessageEvent) = {
        controller.runCommand(new Parameter(event.getMessage.trim))
    }
}

class TestCmd extends Command {
    override def command:String = "hoi?"

    override def doCommand(param:Parameter, callback: CommandCallback) = {
        callback.append("Hoi! ").append(param.tokens.nextToken())
    }
}