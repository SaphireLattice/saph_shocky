package org.saphire.shocky

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.saphire.shocky.bot.ShockyBot

object Shocky {
    def main(args: Array[String]) {
        val logger: Logger = LogManager.getLogger("Shocky")

        val config = new ShockyConfig("config.json")

        logger.info("Starting shocky")
        val bot = new ShockyBot(config.getBotConfig("quake"))
        bot.startBot()
    }
}
