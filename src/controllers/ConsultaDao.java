package src.controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

import src.data.Banco;
import src.models.Consulta;
import src.models.Prescricao;

/*
 *Classe para manipular os dados de uma consulta
    *ConsultaDao contém métodos para cadastrar, buscar, editar e excluir uma consulta 
 */

public class ConsultaDao {
    public static void cadastrarConsulta(Consulta consulta, Banco db) {

            /* 
             * Método para cadastrar uma consulta
             * Recebe um objeto consulta e um objeto de banco de dados
             * Insere os dados da consulta no banco de dados
             */

        String sintomas = String.join(",", consulta.getSintomas());
        String query = String.format(
                "INSERT INTO Consulta (data_consulta, hora_consulta, diagnostico, precisa_internar, sintomas, id_paciente, id_medico) VALUES ('%tF', '%tT', '%s', '%s', '%s', '%d', '%s');",
                consulta.getDataConsulta(), consulta.getHorarioConsulta(), consulta.getDiagnostico(),
                consulta.isPrecisaInternar(), sintomas, Integer.parseInt(consulta.getIdPaciente()),
                consulta.getIdMedico());
        db.queryInsup(query);
    }

    public static Consulta buscaConsulta(String codConsulta, Banco db) throws SQLException {

            /*
             * Método para buscar uma consulta
             * Recebe um código de consulta e um objeto de banco de dados
             * Retorna um objeto consulta com os dados da consulta encontrada
             */

        String query = "SELECT * FROM Consulta WHERE id_consulta = '" + codConsulta + "';";
        ResultSet rs = db.queryBusca(query);
        if (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setCodConsulta(rs.getString("id_consulta"));

            String dataConsultaStr = rs.getString("data_consulta");
            Date dataConsulta = Date.valueOf(dataConsultaStr);
            consulta.setDataConsulta(dataConsulta);

            String horarioConsultaStr = rs.getString("hora_consulta");
            Time horarioConsulta = Time.valueOf(horarioConsultaStr);
            consulta.setHorarioConsulta(horarioConsulta);

            consulta.setDiagnostico(rs.getString("diagnostico"));
            consulta.setPrecisaInternar(rs.getBoolean("precisa_internar"));
            consulta.setIdPaciente(rs.getString("id_paciente"));
            consulta.setIdMedico(rs.getString("id_medico"));

            // Obter os sintomas da consulta
            String sintomasStr = rs.getString("sintomas");
            ArrayList<String> sintomas = new ArrayList<>(Arrays.asList(sintomasStr.split(",")));
            consulta.setSintomas(sintomas);
            consulta.setPrescricoes(PrescricaoDao.listarPrescricoes(consulta.getCodConsulta(), db));
            return consulta;
        }
        return null;
    }

    public static Consulta buscaConsulta(Date dataConsulta, Time horarioConsulta, String codMedico, Banco db)
            throws SQLException {

                /*
                 * Método para buscar uma consulta
                 * Recebe uma data, um horário e um código de médico e um objeto de banco de dados
                 * Retorna um objeto consulta com os dados da consulta encontrada
                 */

        String query = "SELECT * FROM Consulta WHERE data_consulta = '" + dataConsulta + "' AND hora_consulta = '"
                + horarioConsulta + "' AND id_medico = '" + codMedico + "';";
        ResultSet rs = db.queryBusca(query);

        if (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setCodConsulta(rs.getString("id_consulta"));
            consulta.setDataConsulta(dataConsulta);
            consulta.setHorarioConsulta(horarioConsulta);
            consulta.setDiagnostico(rs.getString("diagnostico"));
            consulta.setPrecisaInternar(rs.getBoolean("precisa_internar"));
            consulta.setIdPaciente(rs.getString("id_paciente"));
            consulta.setIdMedico(rs.getString("id_medico"));

            // Obter os sintomas da consulta
            String sintomasStr = rs.getString("sintomas");
            ArrayList<String> sintomas = new ArrayList<>(Arrays.asList(sintomasStr.split(",")));
            consulta.setSintomas(sintomas);
            consulta.setPrescricoes(PrescricaoDao.listarPrescricoes(consulta.getCodConsulta(), db));
            return consulta;
        }
        return null;
    }

    public static Consulta buscaConsulta(Date dataConsulta, Time horarioConsulta, Banco db) throws SQLException {

                /*
                 * Método para buscar uma consulta
                 * Recebe uma data, um horário e um objeto de banco de dados
                 * Retorna um objeto consulta com os dados da consulta encontrada
                 */
        
        String query = "SELECT * FROM Consulta WHERE data_consulta = '" + dataConsulta + "' AND hora_consulta = '"
                + horarioConsulta + "'";
        ResultSet rs = db.queryBusca(query);

        if (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setCodConsulta(rs.getString("id_consulta"));
            consulta.setDataConsulta(dataConsulta);
            consulta.setHorarioConsulta(horarioConsulta);
            consulta.setDiagnostico(rs.getString("diagnostico"));
            consulta.setPrecisaInternar(rs.getBoolean("precisa_internar"));
            consulta.setIdPaciente(rs.getString("id_paciente"));
            consulta.setIdMedico(rs.getString("id_medico"));

            // Obter os sintomas da consulta
            String sintomasStr = rs.getString("sintomas");
            ArrayList<String> sintomas = new ArrayList<>(Arrays.asList(sintomasStr.split(",")));
            consulta.setSintomas(sintomas);
            consulta.setPrescricoes(PrescricaoDao.listarPrescricoes(consulta.getCodConsulta(), db));
            return consulta;
        }
        return null;
    }

    public static void excluirConsulta(Consulta consulta, Banco db) throws SQLException {

            /*
             * Método para excluir uma consulta
             * Recebe um objeto consulta e um objeto de banco de dados
             * Remove os dados da consulta do banco de dados
             */

        String query = String.format("DELETE FROM Consulta WHERE id_Consulta = '%s';", consulta.getCodConsulta());
        db.queryInsup(query);
        ArrayList<Prescricao> prescricores = PrescricaoDao.listarPrescricoes(consulta.getCodConsulta(), db);
        for (Prescricao prescricao : prescricores) {
            PrescricaoDao.excluirPrescricao(prescricao, db);
        }
    }

    public static ArrayList<Consulta> listarConsultas(Banco db) throws SQLException {
                
            /*
            * Método para listar as consultas
            * Recebe um objeto de banco de dados
            * Retorna um ArrayList com as consultas encontradas
            */


        String query = "SELECT * FROM Consulta;";
        ResultSet rs = db.queryBusca(query);
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        while (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setCodConsulta(rs.getString("id_consulta"));

            String dataConsultaStr = rs.getString("data_consulta");
            Date dataConsulta = Date.valueOf(dataConsultaStr);
            consulta.setDataConsulta(dataConsulta);

            String horarioConsultaStr = rs.getString("hora_consulta");
            Time horarioConsulta = Time.valueOf(horarioConsultaStr);
            consulta.setHorarioConsulta(horarioConsulta);

            consulta.setDiagnostico(rs.getString("diagnostico"));
            consulta.setPrecisaInternar(rs.getBoolean("precisa_internar"));
            consulta.setIdPaciente(rs.getString("id_paciente"));
            consulta.setIdMedico(rs.getString("id_medico"));

            // Obter os sintomas da consulta
            String sintomasStr = rs.getString("sintomas");
            ArrayList<String> sintomas = new ArrayList<>(Arrays.asList(sintomasStr.split(",")));
            consulta.setSintomas(sintomas);
            consultas.add(consulta);
        }
        for (Consulta consulta : consultas) {
            consulta.setPrescricoes(PrescricaoDao.listarPrescricoes(consulta.getCodConsulta(), db));
        }
        return consultas;
    }
}
