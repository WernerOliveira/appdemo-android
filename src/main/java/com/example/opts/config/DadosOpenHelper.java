package com.example.opts.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 3; //versao do BD
    private static final String NM_BANCO = "banco";
    //private static final String TB_USUARIOS = "usuarios";    //tá em config.Tabelas
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //CREATES AQUI DENTRO
            StringBuilder sqlUsuarios = new StringBuilder();
            sqlUsuarios.append("CREATE TABLE IF NOT EXISTS ");
            sqlUsuarios.append(Tabelas.TB_USUARIOS);
            sqlUsuarios.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlUsuarios.append("login VARCHAR(30) NOT NULL, ");
            sqlUsuarios.append("senha VARCHAR(32) NOT NULL, ");
            sqlUsuarios.append("email VARCHAR(100) NULL, ");
            sqlUsuarios.append("telefone VARCHAR(20) NULL, ");
            sqlUsuarios.append("tipo INT NOT NULL) ");
            db.execSQL(sqlUsuarios.toString());

            StringBuilder sqlComputadores = new StringBuilder();
            sqlComputadores.append("CREATE TABLE IF NOT EXISTS ");
            sqlComputadores.append(Tabelas.TB_COMPUTADORES);
            sqlComputadores.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlComputadores.append("nome VARCHAR(36) NOT NULL, ");
            sqlComputadores.append("tombo VARCHAR(9) NULL, ");
            sqlComputadores.append("comentario VARCHAR(200) NULL) ");
            db.execSQL(sqlComputadores.toString());

            StringBuilder sqlImpressoras = new StringBuilder();
            sqlImpressoras.append("CREATE TABLE IF NOT EXISTS ");
            sqlImpressoras.append(Tabelas.TB_IMPRESSORAS);
            sqlImpressoras.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlImpressoras.append("nome VARCHAR(36) NOT NULL, ");
            sqlImpressoras.append("tombo VARCHAR(9) NULL, ");
            sqlImpressoras.append("comentario VARCHAR(200) NULL) ");
            db.execSQL(sqlImpressoras.toString());

            StringBuilder sqlOutros = new StringBuilder();
            sqlOutros.append("CREATE TABLE IF NOT EXISTS ");
            sqlOutros.append(Tabelas.TB_OUTROS);
            sqlOutros.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlOutros.append("nome VARCHAR(36) NOT NULL, ");
            sqlOutros.append("tombo VARCHAR(9) NULL, ");
            sqlOutros.append("comentario VARCHAR(200) NULL) ");
            db.execSQL(sqlOutros.toString());

            /*StringBuilder sqlChamados = new StringBuilder();
            sqlChamados.append("CREATE TABLE IF NOT EXISTS ");
            sqlChamados.append(Tabelas.TB_CHAMADOS);
            sqlChamados.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlChamados.append("nome VARCHAR(36) NOT NULL, ");
            sqlChamados.append("tombo VARCHAR(9) NULL, ");
            sqlChamados.append("comentario VARCHAR(200) NULL) ");
            db.execSQL(sqlChamados.toString());

            StringBuilder sqlChamado = new StringBuilder();
            sqlChamado.append("CREATE TABLE IF NOT EXISTS ");
            sqlChamado.append(Tabelas.TB_CHAMADO);
            sqlChamado.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sqlChamado.append("nome VARCHAR(36) NOT NULL, ");
            sqlChamado.append("tombo VARCHAR(9) NULL, ");
            sqlChamado.append("spinuser NULL, ");
            sqlChamado.append("spinitem NULL, ");
            sqlChamado.append("comentario VARCHAR(2000) NULL) ");
            db.execSQL(sqlChamado.toString());*/

            StringBuilder sqlUserAdmin = new StringBuilder();
            sqlUserAdmin.append("INSERT INTO ");
            sqlUserAdmin.append(Tabelas.TB_USUARIOS);
            sqlUserAdmin.append(" (login, senha, tipo) VALUES('admin', '"+ Globais.md5("123") + "', 1)");
            db.execSQL(sqlUserAdmin.toString());

        } catch (Exception ex){
            Globais.exibirToast(context, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            if (newVersion >= 2) {
                try {
                    StringBuilder sqlUpdate = new StringBuilder();
                    sqlUpdate.append(" ALTER TABLE usuarios ADD COLUMN email VARCHAR(100) ");
                    db.execSQL(sqlUpdate.toString());
                } catch (Exception ex){

                }

                try {
                    StringBuilder sqlUpdate = new StringBuilder();
                    sqlUpdate.append(" ALTER TABLE usuarios ADD COLUMN telefone VARCHAR(20) ");
                    db.execSQL(sqlUpdate.toString());
                } catch (Exception ex){

                }
            }

            if (newVersion >= 3) {
                try {
                    StringBuilder sqlUpdate = new StringBuilder();
                    sqlUpdate.append(" ALTER TABLE usuarios ADD COLUMN tipo INT ");
                    db.execSQL(sqlUpdate.toString());
                } catch (Exception ex){

                }

                try {
                    StringBuilder sqlImpressoras = new StringBuilder();
                    sqlImpressoras.append("CREATE TABLE IF NOT EXISTS ");
                    sqlImpressoras.append(Tabelas.TB_IMPRESSORAS);
                    sqlImpressoras.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ");
                    sqlImpressoras.append("nome VARCHAR(36) NOT NULL, ");
                    sqlImpressoras.append("tombo VARCHAR(9) NULL, ");
                    sqlImpressoras.append("comentario VARCHAR(200) NULL) ");
                    db.execSQL(sqlImpressoras.toString());
                } catch (Exception ex){

                }
            }

        } catch (Exception ex) {

        }
    }
}
