package com.shmigel.dotamatchbot.matchLoader

import com.shmigel.dotamatchbot.model._
import com.shmigel.dotamatchbot.util.Implicit._
import com.shmigel.dotamatchbot.util.Util.TextFormatting
import org.jsoup.nodes.{Document, Element}

import scala.collection.JavaConverters._

object PageProcessing {

  def getLiveMatches(implicit document: Document): List[Match.Live] = {
    val liveEvents = document.select(".event-live").asScala

    val liveMatches = for {
      event <- liveEvents
      info = getMatchInfo(event)
      direStat = getGameStatistic(event.selectFirst(".scores .team-home"))
      radiantStat = getGameStatistic(event.selectFirst(".scores .team-away"))
    }yield Match.Live(info, direStat, radiantStat)

    liveMatches.toList
  }

  def getUpcomingMatches(implicit document: Document): List[Match.Upcoming] = {
    val upcomingEvents = document.
      select(".event_list:not(.event_list_ended) .event:not(.event-live)").asScala

    val upcomingMatches = for {
      event <- upcomingEvents
      info = getMatchInfo(event)
    }yield Match.Upcoming(info)

    upcomingMatches.toList
  }

  def getFinishedMatches(implicit document: Document): List[Match.Finished] = {
    val finishedEvents = document.select(".event_list_ended .event").asScala

    val finishedMatches = for {
      event <- finishedEvents
      info = getMatchInfo(event)
      direScore = event.select(".scores .team-away").text
      radiantScore = event.select(".scores .team-home").text
    }yield Match.Finished(info, direScore, radiantScore)

    finishedMatches.toList
  }

  def getMatchInfo(element: Element): Match.Info = {
    val direName = element.select(".teams").select("em").last.text
    val radiantName = element.select(".teams").select("em").first.text
    val tournament = element.select(".tournament").select("a").attr("title")
    val bo = element.select(".bo").text
    val date = TextFormatting.dataFormatter(TextFormatting.customTrim(element.select(".date").text))

    Match.Info(Team(direName), Team(radiantName), Tournament(tournament), BO(bo), date)
  }

  def getGameStatistic(element: Element): LiveTeamStatistic = {
    val score = element.select(".main-score").text
    val kills = element.select(".kills").text
    val networs = element.select(".net").text
    val tower = element.select(".towers").text
    val barraks = element.select(".star").text

    LiveTeamStatistic(score, kills, networs, tower, barraks)
  }

}
