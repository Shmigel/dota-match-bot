package com.shmigel.dotamatchbot.service.telegram

import com.shmigel.dotamatchbot.model.Match.{Finished, Live, Match, Upcoming}

object TelegramService {

  def sendMessage(dMatch: Match): Option[Boolean] = {
    dMatch match {
      case dMatch: Upcoming =>
        TelegramApi.sendMessage(dMatch) match {
          case Some(id) => Some(TelegramBindService.addDependences(dMatch.id, id))
          case None => None
        }

      case dMatch: Live =>
        TelegramApi.sendMessage(dMatch) match {
          case Some(id) => Some(TelegramBindService.addDependences(dMatch.id, id))
          case None => None
        }

      case dMatch: Finished =>
        TelegramApi.sendMessage(dMatch) match {
          case Some(id) => Some(TelegramBindService.removeDependences(dMatch.id))
          case None => None
        }
    }
  }

}
