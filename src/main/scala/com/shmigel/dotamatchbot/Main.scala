package com.shmigel.dotamatchbot


import com.shmigel.dotamatchbot.matchLoader.PageProcessing
import com.shmigel.dotamatchbot.model.Match.Match
import com.shmigel.dotamatchbot.service.telegram.TelegramService
import com.shmigel.dotamatchbot.util.DotaMatchPageImplicit._
import com.shmigel.dotamatchbot.util.Implicit._
import org.joda.time.LocalDateTime
import org.slf4j.{Logger, LoggerFactory}

object Main {
  val logger: Logger = LoggerFactory.getLogger(Main.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("Start working")

    while (true) {
      logger.debug(s"Start new cycle")

      val matchs = getMatchs

      matchs foreach TelegramService.sendMatchMessage

      logger.debug("End of cycle")
      Thread.sleep(programSleepTime())
    }
  }

  def getMatchs: List[Match] = {
    val up: List[Match] = PageProcessing.getUpcomingMatches
    val live: List[Match] = PageProcessing.getLiveMatches
    val finish: List[Match] = PageProcessing.getFinishedMatches

    up ++ live ++ finish filter(_.info.date.getDayOfMonth == LocalDateTime.now.getDayOfMonth)
  }
}
