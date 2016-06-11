package org.saphire.shocky

import javax.net.ssl.SSLSocketFactory

import com.google.gson.JsonParser
import org.pircbotx.Configuration
import org.saphire.shocky.bot.ShockyListenerManager

class ShockyConfig(config:String) {
    private val source = scala.io.Source.fromFile(config)
    private val stringConfig: String =  source.mkString
    private def jsonConfig = new JsonParser().parse(stringConfig).getAsJsonObject

    def getBotConfig(id:String):Configuration = {
        if (!jsonConfig.get("bots").getAsJsonObject.has(id))
            throw new Exception("Invalid bot ID")
        val jsonConf = jsonConfig.get("bots").getAsJsonObject.get(id).getAsJsonObject
        val conf = new Configuration.Builder()
                .setName(jsonConf.get("nickname").getAsString)
                .setLogin(jsonConf.get("username").getAsString)
                .setRealName(jsonConf.get("realname").getAsString)
                .setVersion(jsonConf.get("version").getAsString)
                .setAutoNickChange(true)
                if (jsonConf.has("password"))
                    conf.setServerPassword(jsonConf.get("password").getAsString)
                .setSocketFactory(SSLSocketFactory.getDefault)

        val servers = jsonConf.get("servers").getAsJsonArray
        for (server <- 0 until servers.size()) {
            val str = servers.get(server).getAsString
            val semicolon = str.indexOf(":")
            val (host:String, port:Int) = (str.substring(0, semicolon), str.substring(semicolon + 1).toInt)
            conf.addServer(host, port)
        }

        val channels = jsonConf.get("channels").getAsJsonArray
        for (channel <- 0 until channels.size())
            conf.addAutoJoinChannel(channels.get(channel).getAsString)

        conf.setListenerManager(new ShockyListenerManager(jsonConf.get("modules").getAsJsonArray))
                .buildConfiguration()
    }
}
