package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import connection.ConnectionMySQL;
import modelos.Pedido;

public class PedidoDAO {
	private Connection conexao;
	UsuarioDAO udao = new UsuarioDAO();
	PacotesViagemDAO pvdao = new PacotesViagemDAO();
public PedidoDAO () {
	
}
	

	// Método para inserir um novo Pedido
	public void salvarPedido(Pedido pedido) {
		String sql;
		PreparedStatement ps =null;
		sql = pedido.getId() == 0 ? "INSERT INTO pedidos (dataCompra, usuariosId, pacotesViagemId) VALUES (?, ?, ?)"
				: "UPDATE pedidos SET dataCompra = ?, usuariosId = ?, pacotesViagemId = ? WHERE id = ?";
		

		try  {
			conexao=ConnectionMySQL.createConnectionMySQL();
			ps = conexao.prepareStatement(sql);
			ps.setDate(1, new Date(pedido.getDataCompra().getTime())); // Correção aqui
			ps.setInt(2, pedido.getUsuario().getId());
			ps.setInt(3, pedido.getPacote().getId());
			if (pedido.getId() != 0) {
				ps.setInt(4, pedido.getId());

			}
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	// Método para buscar um Pedido pelo ID
	public Pedido buscarPedidoPorId(int id) throws SQLException {
		String sql = "SELECT * FROM pedidos WHERE id = ?";
		try (PreparedStatement ps = conexao.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Pedido pedido = new Pedido();
					pedido.setId(rs.getInt("id"));
					pedido.setDataCompra(rs.getDate("dataCompra"));
					pedido.setUsuario(udao.buscarUsuarioPorId(rs.getInt("usuariosId")));
					pedido.setPacote(pvdao.buscarPorId(rs.getInt("pacotesViagemId")));
					return pedido;
				}
			}
		}
		return null;
	}

	// Método para listar todos os Pedidos
	public List<Pedido> listarPedidos()  {
		List<Pedido> pedidos = new ArrayList<>();
		String sql = "SELECT * FROM pedidos";
		PreparedStatement ps =null;
		ResultSet rs =null;
		try  {
			conexao=ConnectionMySQL.createConnectionMySQL();
			 ps = conexao.prepareStatement(sql);
			 rs = ps.executeQuery();
			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("id"));
				pedido.setDataCompra(rs.getDate("dataCompra"));
				pedido.setUsuario(udao.buscarUsuarioPorId(rs.getInt("usuariosId")));
				pedido.setPacote(pvdao.buscarPorId(rs.getInt("pacotesViagemId")));

				pedidos.add(pedido);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pedidos;
	}

	// Método para excluir um Pedido pelo ID
	public void excluirPedido(int id)  {
		String sql = "DELETE FROM pedidos WHERE id = ?";
		PreparedStatement stmt = null;
		try  {
			conexao=ConnectionMySQL.createConnectionMySQL();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
