package br.com.alura.ichat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.app.ChatApplication;
import br.com.alura.ichat.callback.EnviarMenasgemCallback;
import br.com.alura.ichat.callback.OuvirMensagensCallBack;
import br.com.alura.ichat.component.ChatComponent;
import br.com.alura.ichat.event.MensagemEvent;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.ChatService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idCliente = 1;

    @BindView(R.id.texto_enviar)
    EditText editText;
    @BindView(R.id.btn_enviar)
    Button button;
    @BindView(R.id.lv_mensagens)
    ListView listaMensagens;
    @BindView(R.id.avatar_usuario)
    ImageView avatar;

    private List<Mensagem> mensagens;

    private ChatComponent component;

    @Inject
    ChatService chatService;

    @Inject
    Picasso picasso;

    @Inject
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        picasso.with(this).load("https://api.adorable.io/avatars/285/" + idCliente + ".png").into(avatar);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponent();
        component.inject(this);

        mensagens = new ArrayList<>();

        MensagemAdapter adapter = new MensagemAdapter(idCliente, mensagens, this);

        listaMensagens.setAdapter(adapter);

        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagensCallBack(eventBus,this));

        eventBus.register(this);

    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem(){
        chatService.enviar(new Mensagem(idCliente, editText.getText().toString())).enqueue(new EnviarMenasgemCallback());
    }

    @Subscribe
    public void exibirMensagem(MensagemEvent mensagemEvent){
        mensagens.add(mensagemEvent.mensagem);

        MensagemAdapter adapter = new MensagemAdapter(idCliente, mensagens, this);

        listaMensagens.setAdapter(adapter);
    }

    @Subscribe
    public void ouvirMensagens(MensagemEvent mensagemEvent){
        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagensCallBack(eventBus, this));
    }

    @Override
    protected void onStop() {
        super.onStop();

        eventBus.unregister(this);
    }
}
