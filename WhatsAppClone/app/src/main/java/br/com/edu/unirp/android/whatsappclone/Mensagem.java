package br.com.edu.unirp.android.whatsappclone;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Vinicius on 16/09/2017.
 */

@IgnoreExtraProperties
public class Mensagem {
    private String id;
    private String texto;
    private long data;
    private String displayName;
    private String uid;
    private String imagem;



    public Mensagem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return this.displayName + ": " + this.texto;//+ " - " + new Date(this.data);
    }
}
