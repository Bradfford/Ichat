package br.com.alura.ichat.service;

import br.com.alura.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by millfford on 08/08/2017.
 */

public interface ChatService {

    @POST("polling")
    Call<Void> enviar(@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> ouvirMensagem();

}

