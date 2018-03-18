package com.shmigel.dotamatchbot.service.telegram

import com.shmigel.dotamatchbot.model.Match.Match
import com.shmigel.dotamatchbot.util.JsonParser._
import com.shmigel.dotamatchbot.util.Util.Const
import com.softwaremill.sttp.{HttpURLConnectionBackend, _}
import org.slf4j.LoggerFactory

/**
  * Simply api for telegram message send/update by http request
  */

object TelegramApi {

  implicit val backend = HttpURLConnectionBackend()
  val logger = LoggerFactory.getLogger(TelegramApi.getClass)

  /**
    * Edit existing message by id
    *
    * @param dMatch new match info
    * @param id id of existing message
    */
  def editMessage(dMatch: Match, id: Int): Unit = {
    logger.debug(s"Editing text message with id $id to new text $dMatch")
    val response = sttp.get(uri"https://api.telegram.org/bot${Const.token}/editMessageText?chat_id=${Const.group_chat_id}&message_id=$id&text=$dMatch").send.body
  }

  /**
    * Send new message to telegram
    *
    * @param dMatch match info
    * @return id of new message
    */
  def sendMessage(dMatch: Match): Int = {
    logger.debug(s"Send new message with text $dMatch")
    val response = sttp.get(uri"https://api.telegram.org/bot${Const.token}/sendMessage?chat_id=${Const.group_chat_id}&text=$dMatch").send.body
    response.map(parse).map(_.result.message_id).getOrElse(-1)
  }

}