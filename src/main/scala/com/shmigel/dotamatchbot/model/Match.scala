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

case class LiveTeamStatistic(score: String,
                             kills: String,
                             netWorth: String,
                             tDestroyed: String,
                             bDestroyed: String)

object Match {

  abstract class Match {
    val id: Int = info.hashCode()
    val info: Info
  }

  case class Info(dire: Team,
                  radiant: Team,
                  tournament: Tournament,
                  bo: BO,
                  date: LocalDateTime) {
    override def hashCode(): Int = dire.hashCode() + radiant.hashCode()
  }

  case class Upcoming(info: Info) extends Match

  case class Live(info: Info,
                  direStatistic: LiveTeamStatistic,
                  radiantStatistic: LiveTeamStatistic) extends Match

  case class Finished(info: Info,
                      radiantScore: String,
                      direScore: String) extends Match

}