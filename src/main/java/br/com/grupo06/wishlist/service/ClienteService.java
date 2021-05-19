package br.com.grupo06.wishlist.service;


import br.com.grupo06.wishlist.domain.entity.ClienteEntity;
import br.com.grupo06.wishlist.domain.excecao.ExcecaoEsperada;
import br.com.grupo06.wishlist.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    //Método para buscar todos os registros da tabela
    public List<ClienteEntity> listarTodos(){
        return (List<ClienteEntity>) clienteRepository.findAll();
    }

    //Método que busca um registro pelo codigo no banco de dados e retorna este registro
    public Optional<ClienteEntity> listarPorCodigo(Integer codigo){
        return clienteRepository.findById(codigo);
    }

    //Método para burcar um registro pelo nome
    public List<ClienteEntity> procurarClintePeloNome(String nome){
        return clienteRepository.buscarClientePeloNome(nome);
    }

    //Método para buscar um registro pelo cpf
    public Optional<ClienteEntity> procurarClientePorCpf(String cpf){
        return clienteRepository.buscarClienteProCpf(cpf);
    }

    //Método que salva o registro no banco de dados
    public ClienteEntity salvar(ClienteEntity cliente) throws ExcecaoEsperada {
        Integer codigoCliente = cliente.getCodigo() != null ? cliente.getCodigo() : 0;
        if(cliente.getNome().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o nome do cliente!");
        } else if(cliente.getCpf().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o CPF do cliente!");
        } else if(cliente.getTelefone().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o telfone do cliente!");
        }else if(cliente.getEmail().trim().equals("")) {
            throw new ExcecaoEsperada("Por favor, informe o e-mail do cliente!");
        }else if(cliente.getLogradouro().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o endereço do cliente!");
        }else if(cliente.getNumero().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o número do endereço do cliente!");
        }else if(cliente.getBairro().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o bairro do endereço do cliente!");
        }else if(cliente.getCidade().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe a cidade do endereço do cliente!");
        }else if(cliente.getEstado().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o estado da cidade de endereço do cliente!");
        }else if(cliente.getCep().trim().equals("")){
            throw new ExcecaoEsperada("Por favor, informe o CEP de endereço do cliente!");
        }
        //Valida se já existe um cliente com o mesmo CPF
        ClienteEntity cli = new ClienteEntity();
        cli.setCpf(cliente.getCpf());
        Example<ClienteEntity> example = Example.of(cli);
        List<ClienteEntity> lista = clienteRepository.findAll(example);
        for (ClienteEntity c: lista) {
            if(!c.getCodigo().equals(cliente.getCodigo())){
                throw new ExcecaoEsperada("CPF já cadastrado na base de dados!!");
            }
        }
        return clienteRepository.save(cliente);

    }
    //Método que atualiza um registro no banco
    public ClienteEntity atualizar(ClienteEntity cliente){
        ClienteEntity retorno = clienteRepository.getOne(cliente.getCodigo());
        if(retorno != null){
            retorno.setNome(cliente.getNome());
            retorno.setCpf(cliente.getCpf());
            retorno.setTelefone(cliente.getTelefone());
            retorno.setEmail(cliente.getEmail());
            retorno.setLogradouro(cliente.getLogradouro());
            retorno.setNumero(cliente.getNumero());
            retorno.setComplemento(cliente.getComplemento());
            retorno.setBairro(cliente.getBairro());
            retorno.setCidade(cliente.getCidade());
            retorno.setEstado(cliente.getEstado());
            retorno.setCep(cliente.getCep());

            clienteRepository.save(retorno);
        }
        return clienteRepository.save(cliente);
    }
    //Método para excluir um registro
    public void excluir (Integer codigo){
        clienteRepository.deleteById(codigo);
    }



}
