package br.com.alurafood.pagamento.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Positive
  private BigDecimal valor;

  @NotBlank
  @Size(max = 90)
  private String nome;

  @NotBlank
  @Size(max = 19)
  private String numero;

  @NotBlank
  @Size(max = 7)
  private String expiracao;

  @NotBlank
  @Size(max = 3, min = 3)
  private String codigo;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Status status;

  @NotNull
  private Long pedidoId;

  @NotNull
  private Long formaDePagamentoId;
}
