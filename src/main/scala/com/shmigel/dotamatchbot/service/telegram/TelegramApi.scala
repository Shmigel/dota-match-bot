package com.shmigel.dotamatchbot.service.telegram

import com.shmigel.dotamatchbot.model.Match
import com.shmigel.dotamatchbot.util.Util.Const
import com.softwaremill.sttp.{HttpURLConnectionBackend, _}

/**
  * Simply api for telegram message send/update by http request
  */

object TelegramApi {
  implicit val backend = HttpURLConnectionBackend()

  def sendMessage(upcomingMatch: Match.Upcoming): String = {
    val request = sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.chat_id}&text=${upcomingMatch}")
    request.send().unsafeBody
  }

  def sendMessage(liveMatch: Match.Live): String = {
    val request = sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.chat_id}&text=${liveMatch}")
    request.send().unsafeBody
  }

  def sendMessage(finishedMatch: Match.Finished): String = {
    val request = sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.chat_id}&text=${finishedMatch}")
    request.send().unsafeBody
  }
}

case class TelegramResponse(ok: Boolean, message_id: Int)