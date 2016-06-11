package org.saphire.shocky.module

import java.io.File

import com.google.gson.JsonParser
import org.saphire.shocky.Shocky

import scala.reflect.internal.util.ScalaClassLoader.URLClassLoader

object ModuleLoader {
    def modulesDir = new File("out/production") //Main storage for modules. Module can be a folder or jar containing `module.json`
    //TODO: val tempDir = new File("temp") //For downloading modules from https, http is prohibited

    def loadModule(loader:URLClassLoader):Module = { //actual loader
        Shocky.logger.info()
        val json = scala.io.Source.fromInputStream(loader.getResourceAsStream("module.json")).mkString
        println(json)
        val config = ModuleConfig.fromJson(new JsonParser().parse(json).getAsJsonObject)
        loader.loadClass(config.classname).newInstance().asInstanceOf[Module]
    }

    def loadModule(file:File):Module = {
        val loader:URLClassLoader = new URLClassLoader(List(file.toURI.toURL), this.getClass.getClassLoader)
        loadModule(loader)
    }

    def loadModule(name:String):Module = {
        modulesDir.listFiles().foreach((entry:File) => {
            if ((entry.isDirectory && entry.getName.equals(name) ) || (entry.isFile && entry.getName.equals(name + ".jar"))) {
                return loadModule(entry)
            }
        })
        throw new Exception("No such module")
    }
}
