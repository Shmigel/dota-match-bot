package com.shmigel.dotamatchbot.model

import org.joda.time.LocalDateTime

object BO {
  def apply(bo: String): BO = bo match {
    case "BO1" => BO1
    case "BO2" => BO2
    case "BO3" => BO3
    case "BO5" => BO5
    case _ => BO1
  }
}

sealed abstract class BO(val id: Int)
  case object BO1 extends BO(1)
  case object BO2 extends BO(2)
  case object BO3 extends BO(3)
  case object BO5 extends BO(5)


case class Tournament(name: String)

case class Team(name: String)

case class LiveTeamStatistic(score: Int,
                             kills: Int,
                             netWorth: Int,
                             tDestroyed: Int,
                             bDestroyed: Int)


//TODO: Rewrite hashcode or delegate to Match
abstract class Match {
  def id: Int = hashCode
  def dire: Team
  def radiant: Team
  def bo: BO
  def date: LocalDateTime
}

case class UpcomingMatch(dire: Team,
                         radiant: Team,
                         tournament: Tournament,
                         bo: BO,
                         date: LocalDateTime) extends Match {
  override def hashCode(): Int = (dire.name.hashCode + radiant.hashCode).hashCode
}

case class LiveMatch(dire: Team,
                     radiant: Team,
                     tournament: Tournament,
                     bo: BO,
                     date: LocalDateTime,
                     direStatistic: LiveTeamStatistic,
                     radiantStatistic: LiveTeamStatistic) extends Match {
  override def hashCode(): Int = (dire.name.hashCode + radiant.hashCode).hashCode
}

case class FinishedMatch(dire: Team,
                         radiant: Team,
                         tournament: Tournament,
                         bo: BO,
                         date: LocalDateTime,
                         radiantScore: Int,
                         direScore: Int
                        ) extends Match {
  override def hashCode(): Int = (dire.name.hashCode + radiant.hashCode).hashCode
}