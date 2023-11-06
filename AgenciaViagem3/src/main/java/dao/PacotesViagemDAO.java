package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connection.ConnectionMySQL;
import modelos.PacotesViagem;
import modelos.Usuario;

public class PacotesViagemDAO {
	private Connection conexao; // Você deve configurar a conexão com o banco de dados aqui

	public PacotesViagemDAO() {

	}


	public void salvarPacoteViagem(PacotesViagem pacote)  {
		String sql;
		sql = pacote.getId() == 0
				? "INSERT INTO pacotes_viagem (nomeDoPacote, descricao, destino, preco, dataDePartida, duracao) VALUES (?, ?, ?, ?, ?, ?)"
				: "UPDATE pacotes_viagem SET nomeDoPacote = ?, descricao = ?, destino = ?, preco = ?, dataDePartida = ?, duracao = ? WHERE id = ?";
		PreparedStatement pstmt =null;
		try  {
			conexao=ConnectionMySQL.createConnectionMySQL();
			 pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, pacote.getNomeDoPacote());
			pstmt.setString(2, pacote.getDescricao());
			pstmt.setString(3, pacote.getDestino());
			pstmt.setFloat(4, pacote.getPreco());
			pstmt.setDate(5, new java.sql.Date(pacote.getDataDePartida().getTime())); // Converte a data
			pstmt.setInt(6, pacote.getDuracao());
			if (pacote.getId() != 0) {
				pstmt.setInt(7, pacote.getId());
			}
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<PacotesViagem> listarPacotesViagem() {
		List<PacotesViagem> pacotesViagem = new ArrayList<>();
		String sql = "SELECT * FROM pacotes_viagem";
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			conexao=ConnectionMySQL.createConnectionMySQL();
			pstmt = conexao.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nomeDoPacote = resultSet.getString("nomeDoPacote");
				String descricao = resultSet.getString("descricao");
				String destino = resultSet.getString("destino");
				Float preco = resultSet.getFloat("preco");
				Date dataDePartida = resultSet.getDate("dataDePartida");
				int duracao = resultSet.getInt("duracao");

				PacotesViagem pacote = new PacotesViagem(id, nomeDoPacote, descricao, destino, preco, dataDePartida,
						duracao);
				pacotesViagem.add(pacote);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return pacotesViagem;
	}

	public PacotesViagem buscarPorId(int id) {
		String sql = "SELECT * FROM pacotes_viagem WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			conexao=ConnectionMySQL.createConnectionMySQL();
			pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				
				String nomeDoPacote = resultSet.getString("nomeDoPacote");
				String descricao = resultSet.getString("descricao");
				String destino = resultSet.getString("destino");
				Float preco = resultSet.getFloat("preco");
				Date dataDePartida = resultSet.getDate("dataDePartida");
				int duracao = resultSet.getInt("duracao");

				return new PacotesViagem(id, nomeDoPacote, descricao, destino, preco, dataDePartida,
						duracao);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null; // Retorna null se o usuário não for encontrado
	}

	public void excluirPacotesViagem(int id) {
		String sql = "DELETE FROM pacotes_viagem WHERE id = ?";
		PreparedStatement pstmt =null;
		try  {
			conexao=ConnectionMySQL.createConnectionMySQL();
			 pstmt = conexao.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
