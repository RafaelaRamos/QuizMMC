package ifpb.com.edu.br.quizmmc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public RadioGroup radioGroup;
    public TextView textoPergunta;
    public TextView contQuestao;
    public RadioButton opcaoA;
    public RadioButton opcaoB;
    public RadioButton opcaoC;
    private Context mContext;
    private Questao q;
    public Button botaoOk;
    private List<Questao> questoes = new ArrayList<>();
    private TextView textAlert ;
    private Button bntalert;
    private ImageView img;



    int[] respostas = new int[5];
    int gabarito[] = new int[5];


    int respostasCorretas = 0;
    int numeroPergunta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoOk = (Button) findViewById(R.id.button);
        botaoOk.setEnabled(false);

        textoPergunta = (TextView) findViewById(R.id.textView2);
        contQuestao = (TextView) findViewById(R.id.textView5);
        opcaoA = (RadioButton) findViewById(R.id.opcaoA);
        opcaoB = (RadioButton) findViewById(R.id.opcaoB);
        opcaoC = (RadioButton) findViewById(R.id.opcaoC);
        img = (ImageView) findViewById(R.id.imageView);

       // QuizData dbHelper = new QuizData(this);
        Questao q1 = new Questao("pergunta 1 ","opcaoA","opcaoB","opcaoC",1);

        Questao q2 = new Questao("pergunta 2","opcaoA","opcaoB","opcaoC",1);

        Questao q3 = new Questao("pergunta 3","opcaoA","opcaoB","opcaoC",1);

        Questao q4 = new Questao("pergunta 4","OpcaoA","opcaoB","opcaoC",1);

        Questao q5 = new Questao("pergunta 5","opcaoA","opcaoB","opcaoC",1);

        questoes.add(q1);
        questoes.add(q2);
        questoes.add(q3);
        questoes.add(q4);
        questoes.add(q5);

        System.out.println(questoes);

        radioGroup = (RadioGroup) findViewById(R.id.grupoRadio);

        atualizaPerguntas(botaoOk);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.opcaoA:

                        respostas[numeroPergunta-1] = 1;
                        break;

                    case R.id.opcaoB:

                        respostas[numeroPergunta-1] = 2;
                        break;

                    case R.id.opcaoC:

                        respostas[numeroPergunta-1] = 3;
                        break;
                }
                botaoOk.setEnabled(true);
            }
        });



    }    public void atualizaPerguntas(View view) {

        if(numeroPergunta==questoes.size()){


            opcaoA.setEnabled(false);
            opcaoB.setEnabled(false);
            opcaoC.setEnabled(false);
            radioGroup.clearCheck();
            confereResultado();



        }else {contQuestao.setText(numeroPergunta+1 +"/5");
            q =questoes.get(numeroPergunta);
            textoPergunta.setText(q.getPergunta());
            opcaoA.setText(q.getOpcaoA());
            opcaoB.setText(q.getOpcaoB());
            opcaoC.setText(q.getOpcaoC());
            gabarito[numeroPergunta]= q.getCorreta();
            numeroPergunta++;
            botaoOk.setEnabled(false);
            radioGroup.clearCheck();
        }
    }






    public  void confereResultado(){
        int contadorLista = 0;
        for (int numero : respostas) {
            System.out.println(numero);
            if(numero==gabarito[contadorLista])
            {
                respostasCorretas++;
                System.out.println("Resposta Correta!!!");
            }else{
                System.out.println("Resposta Errada!!!");
            }
            contadorLista++;
        }
        alertaResultado();
    }




    public void alertaResultado(){

        final LayoutInflater inflater = LayoutInflater.from(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final AlertDialog alert = builder.create();
        builder.setTitle("Resultado");

        if(respostasCorretas <3){

            builder.setIcon(R.drawable.ruim);
            builder.setMessage("Voce acertou " + respostasCorretas + " questoes! Não desanime, continue treinando, não se esqueça que você pode ver o vídeo quantas vezes for necessário");
        }
        else if(respostasCorretas <5)
        {

            builder.setIcon(R.drawable.rgl);
            builder.setMessage("Voce acertou " + respostasCorretas + " questoes!Foi por pouco,continue treinando");
        }
        else{

            builder.setIcon(R.drawable.otimos);
            builder.setMessage("Voce acertou " + respostasCorretas + " questoes! Parabéns pelo desempenho,continue treinando");


        }



        builder.setPositiveButton("Inicio", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        telaquiz();
                    } });
        builder.show();

       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        textAlert.setText("Voce acertou " + respostasCorretas + " questoes!");
        textAlert = (TextView) findViewById(R.id.textView3);
        View dialogLayout = inflater.inflate(R.layout.dialog_personalizado, null);


        builder.setView(dialogLayout);
        builder.show();
        botaoOk.setEnabled(false);*/
    }

       /* AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Resultado!");
        alertDialog.setMessage("Voce acertou " + respostasCorretas + " questoes!");
        alertDialog.show();



    }*/

    public void telaquiz() {

        Intent intent = new Intent(this, InicialActivity.class);
        startActivity(intent);
    }
}
