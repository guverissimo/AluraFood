package br.com.alurafood.pagamento.service;

import br.com.alurafood.pagamento.dto.PagamentoDTO;
import br.com.alurafood.pagamento.model.Pagamento;
import br.com.alurafood.pagamento.model.Status;
import br.com.alurafood.pagamento.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class PagamentoService {

  @Autowired
  private PagamentoRepository repository;

  @Autowired
  private ModelMapper modelMapper;

  public Page<PagamentoDTO> obterTodos(Pageable paginacao) {
    return repository
            .findAll(paginacao)
            .map(p -> modelMapper.map(p, PagamentoDTO.class));
  }

  public PagamentoDTO obterPorId(Long id) {
    Pagamento pagamento = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());

    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public PagamentoDTO criarPagamento(PagamentoDTO dto) {
    Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
    pagamento.setStatus(Status.CRIADO);
    repository.save(pagamento);

    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public PagamentoDTO atualizarPagamento(Long id, PagamentoDTO dto) {
    Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
    pagamento.setId(id);
    pagamento = repository.save(pagamento);
    return modelMapper.map(pagamento, PagamentoDTO.class);
  }

  public void excluirPagamento(Long id) {
    repository.deleteById(id);
  }


}
