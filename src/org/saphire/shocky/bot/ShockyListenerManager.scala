package org.saphire.shocky.bot

import com.google.gson.JsonArray
import org.pircbotx.hooks.managers.ThreadedListenerManager
import org.saphire.shocky.module._

class ShockyListenerManager(modulesJson:JsonArray) extends ThreadedListenerManager { //TODO: reimplement Threaded LM here in Scala
    for (i <- 0 until modulesJson.size())
        this.addListener(ModuleLoader.loadModule(modulesJson.get(i).getAsString))
}
