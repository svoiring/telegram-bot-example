package com.example.telegrambotexample.configuration

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "bot")
class BotConfigurationProperties() {

    var telegramToken: String? = null
    var telegramName: String? = null

}