package com.shmigel.dotamatchbot.service.telegram

case class TelegramResponse(ok: Boolean, result: Result)
case class Result(message_id: Int, chat: Chat)
case class Chat(id: Long)
