package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
     public static void main(String[] args){
         ApiContextInitializer.init();
         TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

         try {
             telegramBotsApi.registerBot(new Bot());
         }
         catch (TelegramApiException e){
             e.printStackTrace();
         }
     }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message != null && message.hasText()) {
            if(message.getText().equals("/help")){
                sendMsg(message, "Дуров, Пацук \n то є шпілька з вуїнами");
            }
            if(message.getText().equals("/start")){
                sendMsg(message, "Mаш 200 гривен, кого буш купляв?");
            }
            else
                sendMsg(message, "я тебе не шарю,путнік!");
        }
    }

    private void sendMsg(Message message, String s){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);

        try{
            execute(sendMessage);
        }
        catch(TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "NovoBot2000";
    }

    @Override
    public String getBotToken() {
        return "776436581:AAEGK1fOid9hc8Xp31UKD-x5BcU3ieEnJhQ";
    }
}

