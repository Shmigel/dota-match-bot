package com.shmigel.dotamatchbot.util

import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object Implicit {

  implicit val dotaMatchPage: Document = Jsoup.connect("https://cybersportscore.com/en/dota-2").get()

  implicit val baseFormatter: DateTimeFormatter = DateTimeFormat.forPattern("HH:mmyyyy-MM-dd")
}