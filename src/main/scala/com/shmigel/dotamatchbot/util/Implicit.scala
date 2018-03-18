package com.shmigel.dotamatchbot.util

import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

object Implicit {
  implicit def cacheLiveTime(): Int = 10000
  implicit def programSleepTime(): Int = 30000
  implicit def baseFormatter: DateTimeFormatter = DateTimeFormat.forPattern("HH:mmyyyy-MM-dd")
}