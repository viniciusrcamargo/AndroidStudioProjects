package br.com.edu.unirp.android.whatsappclone;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;


/**
 * Created by Vinicius on 07/10/2017.
 */


public class ListaChatAdapter extends BaseAdapter{

    private List<Mensagem> lista;
    private Activity activity;

    public ListaChatAdapter(List<Mensagem> lista, Activity activity) {
        this.lista = lista;
        this.activity = activity;
    }

    public List<Mensagem> getLista() {
        return lista;
    }

    public void setLista(List<Mensagem> lista) {
        this.lista = lista;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        View view = this.getActivity().getLayoutInflater().inflate(R.layout.item_chat, viewGroup, false);

        TextView txtNome = (TextView)view.findViewById(R.id.txtNome);
        TextView txtTexto = (TextView)view.findViewById(R.id.txtTexto);
        ImageView imagem = (ImageView)view.findViewById(R.id.imagem);

        Mensagem mensagem = lista.get(i);

        txtNome.setText(mensagem.getDisplayName());
        txtTexto.setText(mensagem.getTexto());

        if(mensagem.getImagem() != null){
            downloadImagem(imagem, mensagem.getImagem());
        }

        return view;
    }

    private void downloadImagem(final ImageView imagem,final String param){
        new Thread(){
            @Override
            public void run() {
                try{
                URL url = new URL(param);
                    final Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imagem.setImageBitmap(bitmap);
                        }
                    });
                }
                catch(Exception ex){

                }
            }
        }.start();
    }
}
