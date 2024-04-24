package com.infotecheduca.hotel.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HotelDao extends GenericoDAO<Hotel>{
    public void Salvar(Hotel c){
        String comandoSql = "INSERT INTO hotel (nome, email, telefone, cnpj, endereco) VALUES (?, ?)";
        save(comandoSql, c.getNome(), c.getEmail(), c.getTelefone(), c.getCnpj(), c.getEndereco());
    }
    public void alterar(Hotel c){
        String comandoSql = "UPDATE hotel SET nome = ?, email = ?, telefone = ?, cnpj = ?, endereco = ? WHERE idHotel = ?";
        save(comandoSql, c.getNome(), c.getEmail(), c.getTelefone(), c.getCnpj(), c.getEndereco(), c.getIdHotel());
    }
    public void excluir(Hotel c) {
        String comandoSql = "DELETE FROM hotel WHERE idHotel = ?";
        save(comandoSql, c.getIdHotel());
    }
    public Hotel buscarPorId(Integer id) {
        String comandoSql = "SELECT * FROM hotel WHERE idHotel = ?";
        return buscarPorId(comandoSql, new HotelRowMapper(), id);
    }
    public List<Hotel> buscarTodos() {
        String comandoSql = "SELECT * FROM hotel";
        return buscarTodos(comandoSql, new HotelRowMapper());
    }
    public static class HotelRowMapper implements RowMapper<Hotel> {
        @Override
        public Hotel mapRow(ResultSet rs) throws SQLException {
            Hotel h = new Hotel();
            h.setIdHotel(rs.getInt("idHotel"));
            h.setNome(rs.getString("nome"));
            h.setEmail(rs.getString("email"));
            h.setTelefone(rs.getString("telefone"));
            h.setCnpj(rs.getString("cnpj"));
            h.setEndereco(rs.getString("endereco"));
            return h;
        }
    }
}
