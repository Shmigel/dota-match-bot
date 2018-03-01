package com.shmigel.dotamatchbot.telegramService

import scala.collection.mutable

object TelegramService {

  // Represent dependency between match and message in telegram via ids
  private val idDependences = mutable.HashMap.empty[Int, Int]

  def addDependences(matchId: Int, messageId: Int): Boolean =
    idDependences.put(matchId, messageId) match {
      case Some(_) => true
      case None    => false
    }

}
