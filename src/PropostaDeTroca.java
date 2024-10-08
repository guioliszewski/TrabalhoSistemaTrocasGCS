import java.time.LocalDateTime;


public class PropostaDeTroca {
	private Jogador jogadorPropoe;
	private Jogador jogadorRecebe;
	private Item itemProposto;
	private Item itemRecebido;
	private LocalDateTime dataHora;
	private boolean aceita;

	public PropostaDeTroca (Jogador jogadorPropoe, Jogador jogadorRecebe, Item itemProposto, Item itemRecebido) {
		this.jogadorPropoe = jogadorPropoe;
		this.jogadorRecebe = jogadorRecebe;
		this.itemProposto = itemProposto;
		this.itemRecebido = itemRecebido;
		this.dataHora = LocalDateTime.now();
		this.aceita = false;
	}

	public String getStatus(){
		if(aceita){
			return "aceita";
		} else if(!aceita && LocalDateTime.now().isAfter(dataHora)){
			return "recusada";
		} else {
			return "aguardando";
		}
	}
	
	public Jogador getJogadorPropoe() {
		return jogadorPropoe;
	}
	
	public Jogador getJogadorRecebe() {
		return jogadorRecebe;
	}

	public Item getItemProposto() {
		return itemProposto;
	}
	
	public Item getItemRecebido() {
		return itemRecebido;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public boolean isAceita() {
		return aceita;
	}

	public void aceitar() {
		this.aceita = true;
	}
	
	public void recusar() {
		this.aceita = false;
	}
	
	@Override
	public String toString() {
		return "Proposta: " + getJogadorPropoe().getNome() + " propõe a troca do item " + this.getItemProposto() + " pelo item " + this.getItemRecebido() + " do jogador " +this.getJogadorRecebe();
	}
}

