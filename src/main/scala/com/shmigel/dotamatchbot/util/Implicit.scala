package com.shmigel.dotamatchbot.util

import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

object Implicit {
  implicit def baseFormatter: DateTimeFormatter = DateTimeFormat.forPattern("HH:mmyyyy-MM-dd")
}