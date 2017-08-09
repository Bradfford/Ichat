package br.com.alura.ichat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.ChatService;

public class MainActivity extends AppCompatActivity {

    private int idCliente = 1;
    private EditText editText;
    private Button button;
    private ListView listaMensagens;
    private List<Mensagem> mensagens;
    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaMensagens = (ListView) findViewById(R.id.lv_mensagens);

        //mensagens = Arrays.asList(new Mensagem(1, "Olá alunos!"), new Mensagem(2,"oi"));
        mensagens = new ArrayList<>();

        MensagemAdapter adapter = new MensagemAdapter(idCliente, mensagens, this);

        listaMensagens.setAdapter(adapter);

        chatService = new ChatService(this);

        chatService.ouvirMensagem();

        button = (Button) findViewById(R.id.btn_enviar);

        editText = (EditText) findViewById(R.id.texto_enviar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChatService(MainActivity.this).enviar(new Mensagem(idCliente, editText.getText().toString()));
            }
        });

    }

    public void exibirMensagem(Mensagem mensagem){
        mensagens.add(mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idCliente, mensagens, this);

        listaMensagens.setAdapter(adapter);

        chatService.ouvirMensagem();
    }
}
