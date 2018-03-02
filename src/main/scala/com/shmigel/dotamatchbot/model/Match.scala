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

  case class Info(dire: Team,
                  radiant: Team,
                  tournament: Tournament,
                  bo: BO,
                  date: LocalDateTime) {
    val id: Int = hashCode
  }

  case class Upcoming(matchInfo: Info)

  case class Live(matchInfo: Info,
                  direStatistic: LiveTeamStatistic,
                  radiantStatistic: LiveTeamStatistic)

  case class Finished(matchInfo: Info,
                      radiantScore: String,
                      direScore: String)

}