package com.shmigel.dotamatchbot.telegramService

import com.shmigel.dotamatchbot.model.Match
import com.shmigel.dotamatchbot.util.Util.Const
import com.softwaremill.sttp.{HttpURLConnectionBackend, _}

object TelegramApi {
  implicit val backend = HttpURLConnectionBackend()

  def sendTelegramMessage(dMatch: Match): String = {
    val request = sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.chat_id}&text=$dMatch")
    request.send().unsafeBody
  }
}
