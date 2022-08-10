package com.example.telegrambotexample

import com.example.telegrambotexample.configuration.BotConfigurationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BotConfigurationProperties::class)
class TelegramBotExampleApplication

fun main(args: Array<String>) {
	runApplication<TelegramBotExampleApplication>(*args)
}
