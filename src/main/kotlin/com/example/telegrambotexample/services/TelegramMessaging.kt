package com.example.telegrambotexample.services

import com.example.telegrambotexample.configuration.BotConfigurationProperties
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.*

@Service
class TelegramMessaging(
    private val botConfigurationProperties: BotConfigurationProperties

    ): TelegramLongPollingBot() {
    companion object {
        val log = LoggerFactory.getLogger(TelegramMessaging::class.java)
    }

    override fun getBotToken() = botConfigurationProperties.telegramToken
    override fun getBotUsername() =  botConfigurationProperties.telegramName

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {

            val message = update.message
            val chatId = message.chatId
            if (message.hasText()) {
                val messageText = message.text
                log.debug(">>>> ${update.message}")
                when {
                    messageText == "/get-uuid" -> sendNotification(chatId,UUID.randomUUID().toString()).subscribe()
                }
            } else {
                "Я понимаю только текст"
            }
        }
    }
    fun sendNotification(chatId: Long, responseText: String): Mono<Unit> {
        return Mono.fromCallable {
            val responseMessage = SendMessage(chatId.toString(), responseText)
            responseMessage.enableHtml(true)
            execute(responseMessage)
        }
            .map {}
            .subscribeOn(Schedulers.boundedElastic())
    }
}