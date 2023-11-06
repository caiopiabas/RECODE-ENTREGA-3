package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PacotesViagemDAO;
import modelos.PacotesViagem;


@WebServlet(urlPatterns = { "/pacotesviagem", "/pacotesviagem-save", "/pacotesviagem-delete" })
public class PacotesViagemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PacotesViagemDAO pvdao = new PacotesViagemDAO();

	public PacotesViagemController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String route = request.getServletPath();
		switch (route) {
		case "/pacotesviagem":
			read(request, response);
			break;
		case "/pacotesviagem-save":
			save(request, response);
			break;
		case "/pacotesviagem-delete":
			delete(request, response);
			break;
		default:
			response.sendRedirect("/AgenciaViagem3/index.html");
			break;
		}
	}

	protected void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<PacotesViagem> pacotes = pvdao.listarPacotesViagem();
		request.setAttribute("pacotes", pacotes);
		RequestDispatcher rd = request.getRequestDispatcher("./pacotesviagem.jsp");
		rd.forward(request, response);
	}

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PacotesViagem pacote = new PacotesViagem();
		if (request.getParameter("id") != null) {
			pacote.setId(Integer.parseInt(request.getParameter("id")));
		}
		pacote.setNomeDoPacote(request.getParameter("nome"));
		pacote.setDescricao(request.getParameter("descricao"));
		pacote.setDestino(request.getParameter("destino"));
		pacote.setPreco(Float.parseFloat(request.getParameter("preco")));
		pacote.setDuracao(Integer.parseInt(request.getParameter("duracao")));
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String data=request.getParameter("data");
		try {
			pacote.setDataDePartida(sdf.parse(data));
		} catch (Exception e) {
			
		}
		pvdao.salvarPacoteViagem(pacote);
		response.sendRedirect("./pacotesviagem");
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		pvdao.excluirPacotesViagem(id);
		response.sendRedirect("./pacotesviagem");
	}
}
