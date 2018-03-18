package com.shmigel.dotamatchbot.service.telegram

import scala.collection.mutable

object TelegramBindService {

  val idDependences = mutable.HashMap.empty[Int, Int]

  def getMessageId(matchId: Int): Option[Int] =
    idDependences.get(matchId)

  def addDependences(matchId: Int, messageId: Int): Boolean =
    idDependences.put(matchId, messageId) match {
      case None    => false
      case Some(_) => true
    }

  def removeDependences(matchId: Int): Boolean =
    idDependences.remove(matchId) match {
      case Some(_) => true
      case None => false
    }

}