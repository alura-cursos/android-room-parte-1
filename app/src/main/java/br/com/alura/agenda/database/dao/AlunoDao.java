package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Aluno;

@Dao
public interface AlunoDao {

    @Insert
    void salva(Aluno aluno);

    @Delete
    void remove(Aluno aluno);

    @Query("SELECT * FROM Aluno")
    List<Aluno> todos();

    @Update
    void edita(Aluno aluno);

}
