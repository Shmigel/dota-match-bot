package com.shmigel.dotamatchbot.util

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}

import org.joda.time.LocalDateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import scala.io.Source

object Util {

  def TextFormatting = new TextFormatting()

  def Const = new Const()

  def Implicit = new Implicit()

  class Implicit {
    implicit val dotaMatchPage: Document = Jsoup.connect("https://cybersportscore.com/en/dota-2").get()

    implicit val baseFormatter: DateTimeFormatter = DateTimeFormat.forPattern("HH:mmyyyy-MM-dd")
  }

  class TextFormatting {

    def customTrim(text: String): String = text.replaceAll("\\s", "")

    def dataFormatter(text: String)(implicit formatter: DateTimeFormatter): LocalDateTime = {
      baseFormatter.parseLocalDateTime(text)
    }
  }

  class Const {

    def token: String = "450924088:AAF-x-8pY5YJYVV6ccnDYjfp1AHGN6wKNZ4"

    def chat_id: String = "@dotamatch"

    def lastMessageId: Option[Int] =
      Option(Source.fromFile("last_message_id").mkString.toInt)

    def saveLastMessageId(text: Int): Unit =
      Files.write(Paths.get("last_message_id"), text.toString.getBytes(StandardCharsets.UTF_8))
  }

}

