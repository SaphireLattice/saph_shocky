trait Command {
    def command:String
    def doCommand(param:Parameter, callback:CommandCallback)
}

class CommandRunner(cmd:Command, param:Parameter, callback:CommandCallback) extends Runnable {
    def run() = {
        cmd.doCommand(param, callback)
    }
}