package br.com.alura.agenda.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
}
