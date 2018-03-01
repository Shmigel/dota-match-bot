package com.shmigel.dotamatchbot.matchLoader

import com.shmigel.dotamatchbot.model._

import collection.JavaConverters._
import com.shmigel.dotamatchbot.util.Util.TextFormatting
import org.jsoup.nodes.{Document, Element}
import org.joda.time.LocalDateTime
import org.joda.time.LocalDateTime.now

object PageProcessing {

  def isToday(date: LocalDateTime): Boolean = date.getDayOfMonth == now.getDayOfMonth

  /*
    TODO: Complete all method
    TODO: String => Match
    TODO: Write tests
    TODO: Add score to all type of match
  */
  def getLiveMatches(implicit document: Document): Option[List[Match]] = {

    val liveEvents = document.select(".live-event").asScala

    val liveMatches = for {
      event <- liveEvents
      direName = event.select(".teams").select("em").last.text
      radiantName = event.select(".teams").select("em").first.text
      tournament = event.select(".tournament").select("a").attr("title")
      bo = event.select(".bo").text
      date = TextFormatting.dataFormatter(TextFormatting.customTrim(event.select(".date").text))
      direStat = getGameStatistic(event.selectFirst(".scores .team-home"))
      radiantStat = getGameStatistic(event.selectFirst(".scores .team-away"))
    }yield LiveMatch(Team(direName), Team(radiantName), Tournament(tournament), BO(bo), date,
      direStat, radiantStat)

    Some(getTodayMatch(liveMatches.toList).getOrElse(List.empty))
  }

  def getUpcomingMatches(implicit document: Document): Option[List[Match]] = {

    val upcomingEvents = document.
      select(".event_list:not(.event_list_ended) .event:not(.event-live)").asScala

    val upcomingMatches = for {
      event <- upcomingEvents
      direName = event.select(".teams").select("em").last.text
      radiantName = event.select(".teams").select("em").first.text
      tournament = event.select(".tournament").select("a").attr("title")
      bo = event.select(".bo").text
      date = TextFormatting.dataFormatter(TextFormatting.customTrim(event.select(".date").text))
    }yield UpcomingMatch(Team(direName), Team(radiantName), Tournament(tournament), BO(bo), date)

    Some(getTodayMatch(upcomingMatches.toList).getOrElse(List.empty))
  }

  def getFinishedMatches(implicit document: Document): Option[List[Match]] = {

    val finishedEvents = document.select(".event_list_ended .event").asScala

    val finishedMatches = for {
      event <- finishedEvents
      direName = event.select(".teams").select("em").last.text
      radiantName = event.select(".teams").select("em").first.text
      tournament = event.select(".tournament").select("a").attr("title")
      bo = event.select(".bo").text
      date = TextFormatting.dataFormatter(TextFormatting.customTrim(event.select(".date").text))
      direScore = event.select(".scores .team-away").text
      radiantScore = event.select(".scores .team-home").text
    }yield FinishedMatch(Team(direName), Team(radiantName), Tournament(tournament), BO(bo), date,
      direScore.toInt, radiantScore.toInt)

    Some(getTodayMatch(finishedMatches.toList).getOrElse(List.empty))
  }

  def getMatchInfo() = ???

  def getMatchStatistic(element: Element) = ???

  def getGameStatistic(element: Element): LiveTeamStatistic = {

    val score = element.select(".team-score").text.toInt
    val kills = element.select(".kills tip").text.toInt
    val networs = element.select(".net tip").text.toInt
    val tower = element.select(".towers tip").text.toInt
    val barraks = element.select(".star tip").text.toInt

    LiveTeamStatistic(score, kills, networs, tower, barraks)
  }

  def getTodayMatch(dMatch: List[Match]): Option[List[Match]] = {
    dMatch.isEmpty match {
      case false => Some(dMatch.filter(i => isToday(i.date)))
      case true => None
    }
  }
}
