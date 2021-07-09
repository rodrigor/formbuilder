package br.ufpb.dcx.aps.formbuilder.services;

import br.ufpb.dcx.aps.formbuilder.models.Campo;
import br.ufpb.dcx.aps.formbuilder.repositories.CampoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampoService {

    @Autowired
    private CampoRepository campoRepository;

    public Campo salvarCampo(Campo campo) {
        this.campoRepository.save(campo);
        return campo;
    }

    public List<Campo> salvarTodos(List<Campo> campo) {
        this.campoRepository.saveAll(campo);
        return campo;
    }
}
