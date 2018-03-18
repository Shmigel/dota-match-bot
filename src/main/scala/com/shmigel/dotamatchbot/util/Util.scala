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

    def token: String = "450924088:AAF-x-8pY5YJYVV6ccnDYjfp1AHGN6wKNZ4"

    private def groupChatId: String = "@dotamatch"
    private def botChatId: String = "299248086"

    def group_chat_id(): String = groupChatId
  }

}

