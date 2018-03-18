package com.shmigel.dotamatchbot.util

import com.google.gson.Gson
import com.shmigel.dotamatchbot.service.telegram.TelegramResponse

object JsonParser {

  private val parser = new Gson()

  def parse(json: String): TelegramResponse = {
    parser.fromJson(json, classOf[TelegramResponse])
  }

}
