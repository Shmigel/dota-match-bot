package com.shmigel.dotamatchbot.service.telegram

import com.shmigel.dotamatchbot.model.Match.Match
import com.shmigel.dotamatchbot.util.JsonParser.parse
import com.shmigel.dotamatchbot.util.Util.Const
import com.softwaremill.sttp.{HttpURLConnectionBackend, _}

/**
  * Simply api for telegram message send/update by http request
  */

object TelegramApi {

  implicit val backend = HttpURLConnectionBackend()

  /**
    * Edit existing message by id
    *
    * @param dMatch new match info
    * @param id id of existing message
    */
  def editMessage(dMatch: Match, id: Int): Unit = {
      sttp.get(uri"https://api.telegram.org/bot${Const.token}/editMessageText?chat_id=${Const.group_chat_id}&message_id=$id&text=${dMatch}").send.unsafeBody
  }

  /**
    * Send new message to telegram
    *
    * @param dMatch match info
    * @return id of new message
    */
  def sendMessage(dMatch: Match): Int = {
    val response = sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.group_chat_id}&text=${dMatch}").send.unsafeBody
    parse(response).result.message_id
  }

}