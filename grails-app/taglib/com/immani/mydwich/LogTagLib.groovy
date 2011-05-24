package com.immani.mydwich

class LogTagLib {
  def logMsg = { attrs, body ->
    def level = (attrs['level'] ?: "debug").toLowerCase()
    if (log.isEnabledFor(org.apache.log4j.Level.toLevel(level))) {
      log."$level"(body())
    }
  }
}
