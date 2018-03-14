package com.shmigel.dotamatchbot.util

import org.joda.time.LocalTime
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object DotaMatchPageImplicit {

  println("field1")
  private val firstLoad = loadDotaMatchPage
  println("field2")
  private var last: LocalTime = firstLoad._1
  private var page: Document = firstLoad._2
  println("field3")

  /**
    * Load math page, get time of loading
    *
    * @return page - up to date
    *         time - current time
    */
  private def loadDotaMatchPage: (LocalTime, Document) = {
    println("load")
    val matchPage = Jsoup.connect("https://cybersportscore.com/en/dota-2").get()
    val time = LocalTime.now
    println("loaded")
    (time, matchPage)
  }

  /**
    * Provide cache for loadDotaMatchPage method
    * Reload page every 10 sec(10000)
    *
    * @return page - up to date or cache
    */
  //TODO: func, set last as paameter, return pair
  implicit def dotaMatchPage: Document = {
    println("in M")
    println(s"$last ${LocalTime.now.getSecondOfMinute} ${page.charset()}")
    if (last.plusMillis(10000).getSecondOfMinute <= LocalTime.now.getSecondOfMinute) {
      println("in if")
      val loadData = loadDotaMatchPage
      last = loadData._1
      page = loadData._2
    }
    println("going to return")
    page
  }

}

