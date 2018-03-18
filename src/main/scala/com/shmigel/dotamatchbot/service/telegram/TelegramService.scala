package com.shmigel.dotamatchbot.service.telegram

import com.shmigel.dotamatchbot.model.Match.{Finished, Live, Match, Upcoming}
import com.shmigel.dotamatchbot.service.telegram.TelegramApi._
import com.shmigel.dotamatchbot.service.telegram.TelegramBindService._
import org.slf4j.{Logger, LoggerFactory}

/**
  * Provide full control of telegram message sending
  */
object TelegramService {

  val logger: Logger = LoggerFactory.getLogger(TelegramService.getClass)

  def sendMatchMessage(dMatch: Match): Unit = {

    dMatch match {

      case dMatch: Upcoming =>
        getMessageId(dMatch.id) match {
          case Some(id) => None
          case None => val messageId = sendMessage(dMatch)
            addDependences(dMatch.id, messageId)
        }

      case dMatch: Live =>
        getMessageId(dMatch.id) match {
          case Some(id) => editMessage(dMatch, id)
          case None => val messageId = sendMessage(dMatch)
            addDependences(dMatch.id, messageId)
        }

      case dMatch: Finished =>
        getMessageId(dMatch.id) match {
          case Some(id) => editMessage(dMatch, id); removeDependences(dMatch.id)
          case None => None
        }

    }
  }
}
