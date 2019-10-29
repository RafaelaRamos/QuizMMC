package ifpb.com.edu.br.quizmmc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class QuizData extends SQLiteOpenHelper {
    private SQLiteDatabase  sqLiteDatabase;
    private static final String NOME_BANCO = "quiz.db";
    private static final String TABELA = "questoes";
    private static final String ID = "_id";
    private static final String PERGUNTA = "pergunta";
    private static final String ALTERNATIVAA = "opcaoA";
    private static final String ALTERNATIVAB = "opcaoB";
    private static final String ALTERNATIVAc = "opcaoC";
    private static final String CORRETA = "correta";
    private static final int VERSAO = 1;


    public QuizData(Context context) {
        super(context,NOME_BANCO , null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this. sqLiteDatabase= sqLiteDatabase;
        String sql ="CREATE TABLE "+ TABELA+
                "("
                + ID + " integer primary key autoincrement,"
                + PERGUNTA + " text,"
                + ALTERNATIVAA + " text,"
                + ALTERNATIVAB+ " text"
                + ALTERNATIVAc+"text"
                +CORRETA+"integer"+")";
        sqLiteDatabase.execSQL(sql);

        fillQuestionsTable();


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABELA);
        onCreate(sqLiteDatabase);
    }
    private void fillQuestionsTable() {
        Questao q1 = new Questao("pergunta 1","opcaoA","opcaoB","opcaoC",1);
        addQuestion(q1);
        Questao q2 = new Questao("pergunta 2","opcaoA","opcaoB","opcaoC",1);
        addQuestion(q2);
        Questao q3 = new Questao("pergunta 3","opcaoA","opcaoB","opcaoC",1);
        addQuestion(q3);
        Questao q4 = new Questao("pergunta 4","opcaoA","opcaoB","opcaoC",1);
        addQuestion(q4);
        Questao q5 = new Questao("pergunta 5","opcaoA","opcaoB","opcaoC",1);
        addQuestion(q5);
    }

    private void addQuestion(Questao q) {
        ContentValues cv = new ContentValues();

        cv.put(PERGUNTA, q.getPergunta());
        cv.put(ALTERNATIVAA, q.getOpcaoA());
        cv.put(ALTERNATIVAB, q.getOpcaoB());
        cv.put(ALTERNATIVAc, q.getOpcaoC());
        cv.put(CORRETA, q.getCorreta());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABELA, null, cv);
    }

    public List<Questao> getAllQuestions() {
        List<Questao> questionList = new ArrayList<>();
         sqLiteDatabase = getReadableDatabase();
        Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM " + TABELA, null);

        if (c.moveToFirst()) {
            do {
                Questao q = new Questao();
                q.setPergunta(c.getString(c.getColumnIndex(PERGUNTA)));
                q.setOpcaoA(c.getString(c.getColumnIndex(ALTERNATIVAA)));
                q.setOpcaoB(c.getString(c.getColumnIndex(ALTERNATIVAB)));
                q.setOpcaoC(c.getString(c.getColumnIndex(ALTERNATIVAc)));
                q.setCorreta(c.getInt(c.getColumnIndex(CORRETA)));


                questionList.add(q);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
