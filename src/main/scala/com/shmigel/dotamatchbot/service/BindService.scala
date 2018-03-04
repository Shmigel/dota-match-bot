package com.shmigel.dotamatchbot.service

/**
  * Designed to dependencies between Match object id and message id some messager
  * Provide methods to control dependencies
  */

trait BindService {
  def getMessageId(matchId: Int): Option[Int]
  def addDependences(matchId: Int, messageId: Int): Boolean
  def removeDependences(matchId: Int): Boolean
}
