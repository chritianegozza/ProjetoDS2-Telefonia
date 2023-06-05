import java.text.DecimalFormat;
import java.util.Date;

public class PrePago extends Assinante{
	private int numRecargas;
	protected Recarga[] recargas;
	protected float creditos;
	
	public PrePago(long cpf, String nome, int numero) {
		super(numero, cpf, nome);
		this.recargas = new Recarga[100];
	}
	
	
	public void fazerChamada(Date data, int duracao) {
		float semCredito = duracao * 1.45f;
		this.numChamadas++;
		for (int i = 0; i < numChamadas; i++) {
			if (this.chamadas[i] == null && this.creditos > semCredito) {
				Chamada chamada = new Chamada(data, duracao);
				this.chamadas[i] = chamada;
				this.creditos = this.creditos - semCredito;
				System.out.println("A chamada foi realizada!");
			}
			else if (this.chamadas[i] == null && this.creditos < semCredito){
				this.numChamadas--;
				System.out.println("Voc� n�o possui cr�ditos para realizar essa chamada, realize uma recarga.");
			} else {
			}
		}
	}
	
	public void  recarregar(Date  data,  float valor) {
		this.numRecargas++;
		for (int i = 0; i < numRecargas; i++) {
			if (this.recargas[i] == null) {
				Recarga rec = new Recarga(data, valor);
				this.recargas[i] = rec;
				this.creditos = this.creditos + valor;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void imprimirFatura(int mes) {
		DecimalFormat formatador = new DecimalFormat("0.00");
		float valor = 0;
		float somaValor = 0;
		float rec = 0;
		System.out.println("Fatura assinante pr� pago:");
		for (int i = 0; i < numChamadas; i++) {
			if (this.chamadas[i].getData().getMonth() == mes) {
				System.out.println(this.toString());
				valor = this.chamadas[i].getDuracao() * 1.45f;
				somaValor = valor + somaValor;
				System.out.println(this.chamadas[i]);
				System.out.println("Valor da liga��o: R$" + formatador.format(valor));
		}
			else {
				System.out.println(this.toString());
				System.out.println("O assinante n�o fez liga��es durante esse m�s.");
			}
	}
		System.out.println("Valor total das liga��es: R$" + formatador.format(somaValor));
		for (int i = 0; i < numRecargas; i++) {
			if (this.recargas[i].getData().getMonth() == mes) {
				System.out.println(this.recargas[i]);
				rec = this.recargas[i].getValor() + rec;
			}
		}
		System.out.println("Valor total de recargas: R$" + formatador.format(rec));
		System.out.println("Valor total de cr�ditos: R$" + formatador.format(this.creditos));
		System.out.println();
		
	}
}
