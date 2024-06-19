package pl.edu.pwr.bestiariumvratislaviensebackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.CryptidDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.dto.PageCryptidDTO;
import pl.edu.pwr.bestiariumvratislaviensebackend.model.Cryptid;
import pl.edu.pwr.bestiariumvratislaviensebackend.repositories.CryptidRepository;

import java.util.stream.Collectors;

@Service
public class CryptidService {

    private final CryptidRepository cryptidRepository;

    @Autowired
    public CryptidService(CryptidRepository cryptidRepository) {
        this.cryptidRepository = cryptidRepository;
    }

    public PageCryptidDTO getCryptids(String regex, int page) {
        Page<Cryptid> cryptids = cryptidRepository.findByNameContainingIgnoreCase(regex, PageRequest.of(page, 10));

        PageCryptidDTO result = new PageCryptidDTO();
        result.setCreatures(cryptids.getContent()
                .stream()
                .map(CryptidDTO::new)
                .collect(Collectors.toSet()));
        result.setPage(cryptids.getNumber());
        result.setPagesAmount(cryptids.getTotalPages());

        return result;
    }
}
