package com.shmigel.dotamatchbot

import com.shmigel.dotamatchbot.matchLoader.PageProcessing
import com.shmigel.dotamatchbot.service.telegram.{TelegramBindService, TelegramService}
import org.joda.time.LocalDateTime

class forTest extends Runnable {
  override def run(): Unit = {
    while (true) {
      var t2 = LocalDateTime.now()
      println(s"${t2.getHourOfDay} ${t2.getMinuteOfHour} ${t2.getSecondOfMinute}")

      val up = PageProcessing.getUpcomingMatches
      val live = PageProcessing.getLiveMatches
      val finish = PageProcessing.getFinishedMatches

      up foreach TelegramService.sendMessage

      live foreach TelegramService.sendMessage

      finish foreach TelegramService.sendMessage

      println(TelegramBindService.idDependences)

      val dt = LocalDateTime.now
      println(s"${dt.getHourOfDay} ${dt.getMinuteOfHour} ${dt.getSecondOfMinute}")
      println("Load complete")
      Thread.sleep(30000)
    }
  }
}
