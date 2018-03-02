package com.shmigel.dotamatchbot.service

/**
  *
  * Designed to dependences between Match object id and message id some messager
  *
  */

trait Service {
  def getMessageId(matchId: Int): Option[Int]
  def addDependences(matchId: Int, messageId: Int): Boolean
  def removeDependences(matchId: Int): Boolean
}
