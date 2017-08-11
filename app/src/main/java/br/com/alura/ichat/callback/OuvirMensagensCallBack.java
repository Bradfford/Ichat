package br.com.alura.ichat.callback;

import br.com.alura.ichat.MainActivity;
import br.com.alura.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Millfford on 11/08/2017.
 */

public class OuvirMensagensCallBack implements  retrofit2.Callback<Mensagem> {

    private MainActivity mainActivity;

    public OuvirMensagensCallBack(MainActivity mainActivity){
        this.mainActivity =  mainActivity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()){
            Mensagem mensagem = response.body();
            mainActivity.exibirMensagem(mensagem);
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        mainActivity.ouvirMensagens();
    }
}
