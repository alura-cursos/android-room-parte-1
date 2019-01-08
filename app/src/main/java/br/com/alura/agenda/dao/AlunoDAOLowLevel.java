package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAOLowLevel extends SQLiteOpenHelper {

    public AlunoDAOLowLevel(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCriacaoTabela = "CREATE TABLE alunos (" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT," +
                "telefone TEXT," +
                "email TEXT);";
        db.execSQL(sqlCriacaoTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salva(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", aluno.getNome());
        valores.put("telefone", aluno.getTelefone());
        valores.put("email", aluno.getEmail());
        db.insert("alunos", null, valores);
    }

    public void edita(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = pegaValoresDoAluno(aluno);
        String[] params = {String.valueOf(aluno.getId())};
        db.update("alunos", valores, "id = ?", params);
    }

    public List<Aluno> todos() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM alunos";
        Cursor c = db.rawQuery(query, null);

        List<Aluno> alunos = new ArrayList<>();

        while (c.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(c.getInt(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEmail(c.getString(c.getColumnIndex("email")));
            alunos.add(aluno);
        }

        c.close();
        return alunos;
    }

    private ContentValues pegaValoresDoAluno(Aluno aluno) {
        ContentValues valores = new ContentValues();
        valores.put("id", aluno.getId());
        valores.put("nome", aluno.getNome());
        valores.put("telefone", aluno.getTelefone());
        valores.put("email", aluno.getEmail());
        return valores;
    }

    public void remove(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(aluno.getId())};
        db.delete("alunos", "id = ?", params);
    }

}
