package br.com.alura.ichat.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.alura.ichat.R;
import br.com.alura.ichat.modelo.Mensagem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Millfford Bradshaw on 07/08/2017.
 */

public class MensagemAdapter extends BaseAdapter {

    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.avatar_mensagem)
    ImageView avatar;

    @Inject
    Picasso picasso;


    private List<Mensagem> mensagens;
    private Activity activity;
    private int idCliente;

    public MensagemAdapter(int idCliente, List<Mensagem> mensagens, Activity activity){
        this.mensagens = mensagens;
        this.activity = activity;
        this.idCliente = idCliente;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View linha =  activity.getLayoutInflater().inflate(R.layout.mensagem, parent, false);

        ButterKnife.bind(this, linha);

        Mensagem mensagem = (Mensagem) getItem(position);

        picasso.with(activity).load("https://api.adorable.io/avatars/285/" + mensagem.getId() + ".png").into(avatar);

        if(idCliente != mensagem.getId()){
            linha.setBackgroundColor(Color.CYAN);
        }

        texto.setText(mensagem.getTexto());

        return linha;
    }
}
