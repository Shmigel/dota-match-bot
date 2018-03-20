package com.shmigel.dotamatchbot.util

import com.shmigel.dotamatchbot.util.Implicit._
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormatter

object Util {

  def TextFormatting = new TextFormatting()

  def Const = new Const()

  class TextFormatting {

    def customTrim(text: String): String = text.replaceAll("\\s", "")

    def dataFormatter(text: String)(implicit formatter: DateTimeFormatter): LocalDateTime = {
      baseFormatter.parseLocalDateTime(text)
    }
  }

  class Const {

    def token: String = ???

    private def groupChatId: String = ???
    private def botChatId: String = ???

    def group_chat_id(): String = groupChatId
  }

}

