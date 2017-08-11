package br.com.alura.ichat.callback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Millfford on 11/08/2017.
 */

public class EnviarMenasgemCallback implements retrofit2.Callback<Void> {
    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        //TODO
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
