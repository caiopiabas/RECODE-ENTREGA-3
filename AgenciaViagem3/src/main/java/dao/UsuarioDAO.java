package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionMySQL;
import modelos.Usuario;

public class UsuarioDAO {
	private Connection connection;

	public UsuarioDAO() {

	}

	

	// Método para adicionar um usuário ao banco de dados
	public void salvarUsuario(Usuario usuario)  {
		String sql;
		sql = usuario.getId() == 0 ? "INSERT INTO usuarios (nome, email) VALUES (?, ?)"
				: "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
		PreparedStatement stmt =null;
		try  {
			connection=ConnectionMySQL.createConnectionMySQL();
			 stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			if (usuario.getId() != 0) {
				stmt.setInt(3, usuario.getId());
			}
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Método para buscar um usuário pelo ID
	public Usuario buscarUsuarioPorId(int id) {
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection=ConnectionMySQL.createConnectionMySQL();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
				 rs = stmt.executeQuery();
				if (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					return usuario;
				}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null; // Retorna null se o usuário não for encontrado
	}

	// Método para listar todos os usuários
	public List<Usuario> listarTodosUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuarios";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection=ConnectionMySQL.createConnectionMySQL();
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuarios.add(usuario);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuarios;
	}

	// Método para excluir um usuário do banco de dados
	public void excluirUsuario(int id)  {
		String sql = "DELETE FROM usuarios WHERE id = ?";
		PreparedStatement stmt = null;
		try  {
			connection=ConnectionMySQL.createConnectionMySQL();
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
