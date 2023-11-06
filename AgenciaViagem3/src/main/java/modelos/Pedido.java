package modelos;


import java.util.Date;

public class Pedido {
    private int id;
    private Date dataCompra;
    private Usuario usuario;
    private PacotesViagem pacote;

    public Pedido() {
        // Construtor padrão
    }

	public Pedido(int id, Date dataCompra, Usuario usuario, PacotesViagem pacote) {
		super();
		this.id = id;
		this.dataCompra = dataCompra;
		this.usuario = usuario;
		this.pacote = pacote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PacotesViagem getPacote() {
		return pacote;
	}

	public void setPacote(PacotesViagem pacote) {
		this.pacote = pacote;
	}

    
}
