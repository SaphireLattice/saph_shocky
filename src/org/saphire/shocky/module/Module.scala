package org.saphire.shocky.module

trait Module extends org.pircbotx.hooks.ListenerAdapter {
    def moduleName:String
    def onEnable()
    def onDisable()
}

trait SaveableModule extends Module {
    def onSave()
}