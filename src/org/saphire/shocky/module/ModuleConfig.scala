package org.saphire.shocky.module

import com.google.gson.JsonObject
import scala.collection.mutable

class ModuleConfig(aname:String, aclassname:String, adepends:scala.collection.mutable.Set[String]) {
    def name = aname
    def classname = aclassname
    def depends = adepends
}

object ModuleConfig {
    def fromJson(json:JsonObject):ModuleConfig = {
        val depends:mutable.Set[String] = mutable.Set()
        val dependsJson = json.get("depends").getAsJsonArray
        for (i <- 0 until depends.size)
            depends.add(dependsJson.get(i).getAsString)
        new ModuleConfig(json.get("name").getAsString, json.get("classname").getAsString, depends)
    }
}