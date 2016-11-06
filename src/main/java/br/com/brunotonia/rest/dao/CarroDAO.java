package br.com.brunotonia.rest.dao;

import br.com.brunotonia.rest.vo.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroDAO {

    public boolean adicionar(Carro carro) {
        String sql = "INSERT INTO carro "
                + "(\"nome\", \"fabricante\", \"pais\", \"potencia\", \"peso\", "
                + "\"vmax\", \"cilindros\", \"cc\", \"ano\", \"imagem\")"
                + " VALUES "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection cnn = PostgresqlConnect.getInstance().getConnection();
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, carro.getNome());
            ps.setString(2, carro.getFabricante());
            ps.setString(3, carro.getPais());
            ps.setBigDecimal(4, carro.getPotencia());
            ps.setBigDecimal(5, carro.getPeso());
            ps.setBigDecimal(6, carro.getVmax());
            ps.setInt(7, carro.getCilindros());
            ps.setInt(8, carro.getCc());
            ps.setInt(9, carro.getAno());
            ps.setString(10, "/home/bruno/Projetos/NetBeans/TP2 - SuperTrunfo/src/resources/imagens/IMG1.PNG");
            ps.execute();
            ps.close();
            cnn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Carro selecionar(Long id) throws Exception {
        Carro carro = null;
        String sql = "SELECT * FROM carro WHERE id = ?";
        Connection cnn = PostgresqlConnect.getInstance().getConnection();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            carro = new Carro();
            carro.setId(rs.getInt("id"));
            carro.setNome(rs.getString("nome"));
            carro.setFabricante(rs.getString("fabricante"));
            carro.setPais(rs.getString("pais"));
            carro.setPotencia(rs.getBigDecimal("potencia"));
            carro.setPeso(rs.getBigDecimal("peso"));
            carro.setVmax(rs.getBigDecimal("vmax"));
            carro.setCilindros(rs.getInt("cilindros"));
            carro.setCc(rs.getInt("cc"));
            carro.setAno(rs.getInt("ano"));
            carro.setImagem(rs.getString("imagem"));
        }
        rs.close();
        ps.close();
        cnn.close();
        return carro;
    }
    
    public Carro[] listar() throws Exception {
        String sql = "SELECT * FROM carro ORDER BY id ";
        Connection cnn = PostgresqlConnect.getInstance().getConnection();
        PreparedStatement ps = cnn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int i = 0;
        while (rs.next()) {
            i++;
        }
        Carro[] lista = new Carro[i];
        rs = ps.executeQuery();
        i = 0;
        while (rs.next()) {
            Carro carro = new Carro();
            carro.setId(rs.getInt("id"));
            carro.setNome(rs.getString("nome"));
            carro.setFabricante(rs.getString("fabricante"));
            carro.setPais(rs.getString("pais"));
            carro.setPotencia(rs.getBigDecimal("potencia"));
            carro.setPeso(rs.getBigDecimal("peso"));
            carro.setVmax(rs.getBigDecimal("vmax"));
            carro.setCilindros(rs.getInt("cilindros"));
            carro.setCc(rs.getInt("cc"));
            carro.setAno(rs.getInt("ano"));
            carro.setImagem(rs.getString("imagem"));
            lista[i] = carro;
            i++;
        }
        rs.close();
        ps.close();
        cnn.close();
        return lista;
    }

}
