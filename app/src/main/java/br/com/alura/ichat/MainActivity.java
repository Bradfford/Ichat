package br.com.alura.ichat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.app.ChatApplication;
import br.com.alura.ichat.callback.EnviarMenasgemCallback;
import br.com.alura.ichat.callback.OuvirMensagensCallBack;
import br.com.alura.ichat.component.ChatComponent;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.ChatService;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idCliente = 1;
    private EditText editText;
    private Button button;
    private ListView listaMensagens;
    private List<Mensagem> mensagens;

    private ChatComponent component;

    @Inject
    ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        listaMensagens = (ListView) findViewById(R.id.lv_mensagens);

        mensagens = new ArrayList<>();

        MensagemAdapter adapter = new MensagemAdapter(idCliente, mensagens, this);

        listaMensagens.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.texto_enviar);

        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagensCallBack(this));

        button = (Button) findViewById(R.id.btn_enviar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatService.enviar(new Mensagem(idCliente, editText.getText().toString())).enqueue(new EnviarMenasgemCallback());
            }
        });

    }

    public void exibirMensagem(Mensagem mensagem){
        mensagens.add(mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idCliente, mensagens, this);

        listaMensagens.setAdapter(adapter);

        ouvirMensagens();;
    }

    public void ouvirMensagens(){
        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagensCallBack(this));
    }

}
