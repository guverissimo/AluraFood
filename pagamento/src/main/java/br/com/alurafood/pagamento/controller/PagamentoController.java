package br.com.alurafood.pagamento.controller;

import br.com.alurafood.pagamento.dto.PagamentoDTO;
import br.com.alurafood.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

  @Autowired
  private PagamentoService service;

  @GetMapping
  public Page<PagamentoDTO> listar(@PageableDefault(size = 10) Pageable paginacao) {
    return service.obterTodos(paginacao);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PagamentoDTO> detalhar(@PathVariable @NotNull Long id) {
    PagamentoDTO dto = service.obterPorId(id);

    return ResponseEntity.ok(dto);
  }


  @PostMapping
  public ResponseEntity<PagamentoDTO> cadastrar(@RequestBody @Valid PagamentoDTO dto, UriComponentsBuilder uriBuilder) {
    PagamentoDTO pagamento = service.criarPagamento(dto);
    URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

    return ResponseEntity.created(endereco).body(pagamento);
  }

  @PutMapping
  public ResponseEntity<PagamentoDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDTO dto) {
    PagamentoDTO atualizado = service.atualizarPagamento(id, dto);
    return ResponseEntity.ok(atualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PagamentoDTO> remover(@PathVariable @NotNull Long id) {
    service.excluirPagamento(id);
    return ResponseEntity.noContent().build();
  }

}
