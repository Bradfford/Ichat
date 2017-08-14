package br.com.alura.ichat.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import br.com.alura.ichat.service.ChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Millfford on 11/08/2017.
 */

@Module
public class ChatModule{

    private Application app;

    public ChatModule(Application app){
        this.app = app;
    }

    @Provides
    public ChatService getChatService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.101.19:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);

        return chatService;
    }

    @Provides
    public Picasso getPicasso(){
        Picasso picasso = new Picasso.Builder(app).build();
        return  picasso;

    }

}
