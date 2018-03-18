package com.shmigel.dotamatchbot.util

import com.shmigel.dotamatchbot.util.Implicit._
import org.joda.time.LocalTime
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.{Logger, LoggerFactory}

object DotaMatchPageImplicit {

  val logger: Logger = LoggerFactory.getLogger(DotaMatchPageImplicit.getClass)

  logger.debug("The First matchs page load")
  private val firstLoad = loadDotaMatchPage

  private var last: LocalTime = firstLoad._1
  private var page: Document = firstLoad._2

  /**
    * Load math page and get time of loading
    *
    * @return page - up to date
    *         time - current time
    */
  private def loadDotaMatchPage: (LocalTime, Document) = {
    logger.debug("Load new matchs page")
    val matchPage = Jsoup.connect("https://cybersportscore.com/en/dota-2")
      .proxy("202.116.160.104", 3128)
      .get()
    val time = LocalTime.now
    (time, matchPage)
  }

  /**
    * Provide cache for loadDotaMatchPage method
    * Reload page every 10 sec(10000) on request
    *
    * @return page - up to date or cache
    */
  //TODO: func, set last as paameter, return pair
  implicit def dotaMatchPage: Document = {
    logger.info("Trying to get matchs page")
    if (last.plusMillis(cacheLiveTime()).isBefore(LocalTime.now)) {
      val loadData = loadDotaMatchPage
      last = loadData._1
      page = loadData._2
    }
    page
  }

}

