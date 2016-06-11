import org.pircbotx.hooks.Event
import org.pircbotx.hooks.types.GenericMessageEvent

import scala.collection.mutable

/**
  * Created by Saphire on 10.06.16.
  */
class CommandController {
    val commands:mutable.Map[String, Command] = mutable.Map()

    def addCommand(name:String, command:Command) = {
        commands.put(name, command)
    }

    def removeCommand(name:String) = {
        commands.remove(name)
    }

    def runCommand(param:Parameter):CommandCallback = {
        val callback = new CommandCallback()
        new CommandRunner(
            commands(param.name),
            param,
            callback
        ).run()
        callback
    }
}
