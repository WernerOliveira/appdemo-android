package com.example.opts.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.opts.config.DadosOpenHelper;
import com.example.opts.config.Globais;
import com.example.opts.config.Tabelas;
import com.example.opts.model.Impressora;

import java.util.ArrayList;

public class ImpressoraController {

    private SQLiteDatabase conexao;
    private Context context;

    public ImpressoraController(Context context) {
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }

    //metodos (buscar, listar, alterar, incluir e deletar)
    public Impressora login(String nome, String senha) {
        try {
            Impressora objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_IMPRESSORAS);
            sql.append(" WHERE nome = '" + nome + "'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if (resultado.moveToNext()) {
                objeto = new Impressora();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                objeto.setTombo(resultado.getString(resultado.getColumnIndexOrThrow("tombo")));
                objeto.setComentario(resultado.getString(resultado.getColumnIndexOrThrow("comentario")));
            } else {

            }

            return objeto;

        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
            return null;
        }
    }

    public Impressora buscar(int id) {
        try {
            Impressora objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_IMPRESSORAS);
            sql.append(" WHERE id = '" + id + "'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if (resultado.moveToNext()) {
                objeto = new Impressora();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                objeto.setTombo(resultado.getString(resultado.getColumnIndexOrThrow("tombo")));
                objeto.setComentario(resultado.getString(resultado.getColumnIndexOrThrow("comentario")));
            }

            return objeto;

        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
            return null;
        }
    }

    public boolean incluir(Impressora objeto) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());
            valores.put("tombo", objeto.getTombo());
            valores.put("comentario", objeto.getComentario());

            conexao.insertOrThrow(Tabelas.TB_IMPRESSORAS, null, valores);

            return true;

        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
            return false;

        }
    }

    public boolean alterar(Impressora objeto) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());
            valores.put("tombo", objeto.getTombo());
            valores.put("comentario", objeto.getComentario());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tabelas.TB_IMPRESSORAS, valores, "id = ?", parametros);

            return true;

        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
            return false;

        }
    }

    public boolean excluir(Impressora objeto) {
        try {
            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.delete(Tabelas.TB_IMPRESSORAS, "id = ?", parametros);

            return true;

        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
            return false;

        }
    }

    public ArrayList<Impressora> lista() {

        ArrayList<Impressora> listagem = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_IMPRESSORAS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.moveToFirst()) {
                Impressora objeto;
                do{
                    objeto = new Impressora();
                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                    objeto.setTombo(resultado.getString(resultado.getColumnIndexOrThrow("tombo")));
                    objeto.setComentario(resultado.getString(resultado.getColumnIndexOrThrow("comentario")));

                    listagem.add(objeto);
                } while (resultado.moveToNext());
            }

            return listagem;

        } catch (Exception ex) {
            Globais.exibirToast(context, ex.getMessage());
            return listagem;
        }
    }
}
