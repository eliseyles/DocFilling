package by.bntu.kharaneka.enrolleedocfillingmvp.controller;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.BoolDTO;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.User;
import by.bntu.kharaneka.enrolleedocfillingmvp.service.ContractService;
import by.bntu.kharaneka.enrolleedocfillingmvp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractRestController {

    private final UserService userService;
    private final ContractService contractService;

    @Autowired
    public ContractRestController(UserService userService, ContractService contractService) {
        this.userService = userService;
        this.contractService = contractService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/try")
    public void setCheck(@RequestBody BoolDTO checked, Authentication authentication) {
        Contract contract = userService.findByLogin(authentication.getName())
                .map(User::getContract).get();
        contract.setEighteen(checked.getIsEighteen());
        contractService.saveContract(contract);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/try/{id}")
    public void setCheck(@RequestBody BoolDTO checked, @PathVariable Integer id) {
        Contract contract = contractService.findById(id);
        contract.setEighteen(checked.getIsEighteen());
        contractService.saveContract(contract);
    }
}
