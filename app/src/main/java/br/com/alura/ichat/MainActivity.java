package br.com.alura.ichat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.modelo.Mensagem;

public class MainActivity extends AppCompatActivity {

    private int idCliente = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listaMensagens = (ListView) findViewById(R.id.lv_mensagens);

        List<Mensagem> mensagens = Arrays.asList(new Mensagem(1, "Olá alunos!"), new Mensagem(2,"oi"));

        MensagemAdapter adapter = new MensagemAdapter(idCliente, mensagens, this);

        listaMensagens.setAdapter(adapter);

    }
}
