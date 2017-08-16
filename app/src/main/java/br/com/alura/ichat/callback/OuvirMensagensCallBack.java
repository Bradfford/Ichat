package br.com.alura.ichat.callback;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import br.com.alura.ichat.MainActivity;
import br.com.alura.ichat.event.MensagemEvent;
import br.com.alura.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Millfford on 11/08/2017.
 */

public class OuvirMensagensCallBack implements  retrofit2.Callback<Mensagem> {

    private MainActivity mainActivity;
    private EventBus eventBus;

    public OuvirMensagensCallBack(EventBus eventBus, Context context){
        this.mainActivity =  (MainActivity) context;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()){
            Mensagem mensagem = response.body();

            eventBus.post(new MensagemEvent(mensagem));

        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
    }
}
