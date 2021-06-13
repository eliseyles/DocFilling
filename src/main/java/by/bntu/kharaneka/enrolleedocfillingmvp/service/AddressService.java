package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Address;

import java.util.List;

public interface AddressService {

    public Address findById(Integer id);

    public List<Address> findAll();

    public Address saveAddress(Address address);

    public void deleteById(Integer id);
}
