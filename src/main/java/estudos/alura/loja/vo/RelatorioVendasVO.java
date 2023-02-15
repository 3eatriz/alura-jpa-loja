package estudos.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioVendasVO {
	private String nomeProduto;
	private Long quantVendida;
	private LocalDate dataUltimaVenda;
	
	public RelatorioVendasVO() {}

	public RelatorioVendasVO(String nomeProduto, Long quantVendida, LocalDate dataUltimaVenda) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantVendida = quantVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Long getQuantVendida() {
		return quantVendida;
	}

	public void setQuantVendida(Long quantVendida) {
		this.quantVendida = quantVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	public void setDataUltimaVenda(LocalDate dataUltimaVenda) {
		this.dataUltimaVenda = dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioVendasVO [ " + nomeProduto + " - " + quantVendida + " - "
				+ dataUltimaVenda + " ]";
	}	
	
	
}
