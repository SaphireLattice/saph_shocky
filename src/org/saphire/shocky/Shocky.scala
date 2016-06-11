package org.saphire.shocky

import org.apache.logging.log4j.LogManager
import org.saphire.shocky.bot.ShockyBot

object Shocky {
    val logger = LogManager.getLogger("Shocky")
    val config = new ShockyConfig("config.json")

    def main(args: Array[String]) {
        logger.info("Starting shocky")
        val bot = new ShockyBot(config.getBotConfig("quake"))
        bot.startBot()
    }
}
