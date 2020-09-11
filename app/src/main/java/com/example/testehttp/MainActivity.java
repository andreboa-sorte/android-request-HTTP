package com.example.testehttp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView nome;
    private TextView id;
    private TextView bio;
    private TextView endereco;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadPessoa download = new DownloadPessoa();

        nome = (TextView) findViewById(R.id.textView5);
        id = (TextView) findViewById(R.id.textView6);
        bio = (TextView) findViewById(R.id.textView7);
        endereco = (TextView) findViewById(R.id.textView8);

        //Chama Async Task
        download.execute();
    }

    private class DownloadPessoa extends AsyncTask<Void, Void, Pessoa> {

        @Override
        protected void onPreExecute() {
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Pessoa doInBackground(Void... params) {
            Conversor util = new Conversor();
            return util.getInformacao("https://api.github.com/users/andreboa-sorte");
        }
        @Override
        protected void onPostExecute(Pessoa pessoa){
            //System.out.println(pessoa);
            nome.setText(pessoa.getNome());
            id.setText(pessoa.getId());
            bio.setText(pessoa.getBio());
            endereco.setText(pessoa.getEndereco());
            load.dismiss(); //deleta o dialog
        }

    }
}