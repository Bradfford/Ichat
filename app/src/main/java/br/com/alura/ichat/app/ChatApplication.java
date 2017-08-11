package br.com.alura.ichat.app;

import android.annotation.SuppressLint;
import android.app.Application;

import br.com.alura.ichat.component.ChatComponent;
import br.com.alura.ichat.component.DaggerChatComponent;

/**
 * Created by Millfford on 11/08/2017.
 */

public class ChatApplication extends Application{

    private ChatComponent component;

    @SuppressLint("MissingSuperCall")
    public void onCreate(){
        component = DaggerChatComponent.builder().build();
    }

    public ChatComponent getComponent() {
        return component;
    }
}
