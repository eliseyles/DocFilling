package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Address;
import by.bntu.kharaneka.enrolleedocfillingmvp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findById(Integer id) {
        return addressRepository.getOne(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }
}
