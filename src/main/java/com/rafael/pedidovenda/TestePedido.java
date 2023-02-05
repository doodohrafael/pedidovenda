package com.rafael.pedidovenda;

import static javax.persistence.Persistence.createEntityManagerFactory;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.rafael.pedidovenda.model.Cliente;
import com.rafael.pedidovenda.model.EnderecoEntrega;
import com.rafael.pedidovenda.model.ItemPedido;

import static com.rafael.pedidovenda.model.FormaPagamento.CARTAO_CREDITO;
import com.rafael.pedidovenda.model.Pedido;
import com.rafael.pedidovenda.model.Produto;
import static com.rafael.pedidovenda.model.StatusPedido.ORCAMENTO;
import com.rafael.pedidovenda.model.Usuario;

public class TestePedido {

	public static void main(String[] args) {

		EntityManagerFactory factory = createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Cliente cliente = manager.find(Cliente.class, 1L);
		Produto produto = manager.find(Produto.class, 1L);
		Usuario vendedor = manager.find(Usuario.class, 1L);
		
		EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
		enderecoEntrega.setLogradouro("Rua dos Mercados");
		enderecoEntrega.setNumero("1000");
		enderecoEntrega.setCidade("Uberlândia");
		enderecoEntrega.setUf("MG");
		enderecoEntrega.setCep("38400-123");
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(new Date());
		pedido.setDataEntrega(new Date());
		pedido.setFormaPagamento(CARTAO_CREDITO);
		pedido.setObservacao("Aberto das 08 às 18h");
		pedido.setStatus(ORCAMENTO);
		pedido.setValorDesconto(ZERO);
		pedido.setValorFrete(ZERO);
		pedido.setValorTotal(new BigDecimal(23.2));
		pedido.setVendedor(vendedor);
		pedido.setEnderecoEntrega(enderecoEntrega);
		
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setQuantidade(10);
		item.setValorUnitario(new BigDecimal(2.32));
		item.setPedido(pedido);
		
		pedido.getItens().add(item);
		
		manager.persist(pedido);
		
		trx.commit();

	}

}
