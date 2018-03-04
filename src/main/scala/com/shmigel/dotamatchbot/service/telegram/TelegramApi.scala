package com.shmigel.dotamatchbot.service.telegram

import com.shmigel.dotamatchbot.model.Match
import com.shmigel.dotamatchbot.util.JsonParser.parse
import com.shmigel.dotamatchbot.util.Util.Const
import com.softwaremill.sttp.{HttpURLConnectionBackend, _}

/**
  * Simply api for telegram message send/update by http request
  */

object TelegramApi {

  implicit val backend = HttpURLConnectionBackend()

  def sendMessage(upcomingMatch: Match.Upcoming): Option[Int] = {
    TelegramBindService.getMessageId(upcomingMatch.id) match {
      case Some(_) => None
      case None    => Some(parse(sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.group_chat_id}&text=${upcomingMatch}").send.unsafeBody).result.message_id)
    }
  }

  def sendMessage(liveMatch: Match.Live): Option[Int] = {
    TelegramBindService.getMessageId(liveMatch.id) match {
      case Some(id) => sttp.get(uri"https://api.telegram.org/bot${Const.token}/editMessageText?chat_id=${Const.group_chat_id}&message_id=$id&text=${liveMatch}").send.unsafeBody; None
      case None     => Some(parse(sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.group_chat_id}&text=${liveMatch}").send.unsafeBody).result.message_id)
    }
  }

  def sendMessage(finishedMatch: Match.Finished): Option[Int] = {
    TelegramBindService.getMessageId(finishedMatch.id) match {
      case Some(id) => Some(parse(sttp.get(uri"https://api.telegram.org/bot${Const.token}/editMessageText?chat_id=${Const.group_chat_id}&message_id=$id&text=${finishedMatch}").send.unsafeBody).result.message_id)
      case None     => None
    }
  }
}