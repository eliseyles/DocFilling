package by.bntu.kharaneka.enrolleedocfillingmvp.controller;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.*;
import by.bntu.kharaneka.enrolleedocfillingmvp.exception.UserServiceException;
import by.bntu.kharaneka.enrolleedocfillingmvp.exception.ValidationException;
import by.bntu.kharaneka.enrolleedocfillingmvp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(name = "/")
public class UserController {

    private final UserService userService;
    private final DocumentService documentService;
    private final ContractService contractService;
    private final PersonalityService personalityService;
    private final AddressService addressService;

    @Autowired
    public UserController(UserService userService,
                          DocumentService documentService,
                          ContractService contractService,
                          PersonalityService personalityService,
                          AddressService addressService) {
        this.userService = userService;
        this.documentService = documentService;
        this.contractService = contractService;
        this.personalityService = personalityService;
        this.addressService = addressService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/data_filling")
    public String getDataPage(Authentication authentication, Model model) {
        model.addAttribute("checkbox", userService.findByLogin(authentication.getName())
                .map(User::getContract)
                //.map(Contract::get)
                .orElse(new Contract()));
        return "data-page";
    }

    @PostMapping("/register")
    public String registerUser(UserRegistrationForm userRegistrationForm, Model model, RedirectAttributes attributes) {
        Contract contract = contractService.saveContract(new Contract());
        try {
            userService.saveUser(userRegistrationForm, contract);
            attributes.addFlashAttribute("message", "You've successfully registered");
        } catch (UserServiceException e) {
            model.addAttribute("errorMessage", "Such user already exist. Try something else");
            return "signup";
        } catch (ValidationException e) {
            model.addAttribute("errorMessage", "Passwords don't match");
            return "signup";
        }
        return "redirect:/auth/login";
    }

    @GetMapping("/personality")
    public String getPersonalInfo(Authentication authentication, Model model) {
        model.addAttribute("personality", userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(Contract::getPersonality)
                .orElse(new Personality()));
        return "personal-data";
    }

    @PostMapping("/personality")
    public String savePersonalInfo(Authentication authentication,
                                   Personality personality) {

        contractService.saveContract(userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(contract -> {
                    contract.setPersonality(personalityService.savePersonality(personality));
                    return contract;
                }).get()
        );
        return "redirect:/data_filling";
    }

    @GetMapping("/parent/personality")
    public String getParentPersonalInfo(Authentication authentication, Model model) {
        model.addAttribute("personality", userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(Contract::getParentPersonality)
                .orElse(new Personality()));
        return "parent-personal-data";
    }

    @PostMapping("/parent/personality")
    public String saveParentPersonalityInfo(Authentication authentication,
                                            Personality personality) {

        contractService.saveContract(userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(contract -> {
                    contract.setParentPersonality(personalityService.savePersonality(personality));
                    return contract;
                }).get()
        );
        return "redirect:/data_filling";
    }

    @GetMapping("/passport")
    public String getDocumentsInfo(Authentication authentication, Model model) {
        model.addAttribute("document", userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(Contract::getDocument)
                .orElse(new Document()));
        return "passport-data";
    }

    @PostMapping("/passport")
    public String saveDocumentsInfo(Authentication authentication,
                                    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                    DocumentForm documentForm) {

        contractService.saveContract(userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(contract -> {
                    contract.setDocument(documentService.saveDocument(documentForm, date));
                    return contract;
                }).get()
        );
        return "redirect:/data_filling";
    }

    @GetMapping("/parent/passport")
    public String getParentDocumentsInfo(Authentication authentication, Model model) {
        model.addAttribute("document", userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(Contract::getParentDocument)
                .orElse(new Document()));
        return "parent-passport-data";
    }

    @PostMapping("/parent/passport")
    public String saveParentDocumentsInfo(Authentication authentication,
                                          @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                          DocumentForm documentForm) {

        contractService.saveContract(userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(contract -> {
                    contract.setParentDocument(documentService.saveDocument(documentForm, date));
                    return contract;
                }).get()
        );
        return "redirect:/data_filling";
    }

    @GetMapping("/address")
    public String getAddressInfo(Authentication authentication, Model model) {
        model.addAttribute("address", userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(Contract::getAddress)
                .orElse(new Address()));
        return "address-data";
    }

    @PostMapping("/address")
    public String saveAddressInfo(Authentication authentication,
                                  Address address) {

        contractService.saveContract(userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(contract -> {
                    contract.setAddress(addressService.saveAddress(address));
                    return contract;
                }).get()
        );
        return "redirect:/data_filling";
    }

    @GetMapping("/parent/address")
    public String getParentAddressInfo(Authentication authentication, Model model) {
        model.addAttribute("address", userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(Contract::getParentAddress)
                .orElse(new Address()));
        return "parent-address-data";
    }

    @PostMapping("/parent/address")
    public String saveParentAddressInfo(Authentication authentication,
                                        Address address) {

        contractService.saveContract(userService.findByLogin(authentication.getName())
                .map(User::getContract)
                .map(contract -> {
                    contract.setParentAddress(addressService.saveAddress(address));
                    return contract;
                }).get()
        );
        return "redirect:/data_filling";
    }

    @GetMapping("/search")
    public String getEnrolleeBySurname(Model model, String surname) {
        //System.out.println(surname);
        model.addAttribute("personalities", personalityService.findBySurnameLike(surname + "%"));
        return "try";
    }

    @GetMapping("/personal/{id}")
    public String getContract(@PathVariable("id") Integer id) {
        Contract contract = contractService.findByPersonality(id);
        return "redirect:/contract/" + contract.getId();
    }

    @GetMapping("/contract/{id}")
    public String getContractPage(@PathVariable("id") Integer id, Model model) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contract", contract);
        return "helper-data-page";
    }

    @GetMapping("/contract/personality/{id}")
    public String getHelperPersonalInfo(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("contract", contractService.findById(id));
        return "helper-personal-data";
    }

    @PostMapping("/contract/personality/{id}")
    public String saveHelperPersonalInfo(Contract contractDto, @PathVariable("id") Integer contractId) {
        Contract contract = contractService.findById(contractId);
        contractDto.getPersonality().setId(contract.getPersonality().getId());
        contract.setPersonality(contractDto.getPersonality());
        contractService.saveContract(contract);
        return "redirect:/contract/" + contract.getId();
    }

    @GetMapping("/contract/passport/{id}")
    public String getHelperPassportInfo(@PathVariable("id") Integer id,
                                        Model model) {
        model.addAttribute("contract", contractService.findById(id));
        return "helper-passport-data";
    }

    @PostMapping("/contract/passport/{id}")
    public String saveHelperPassportInfo(ContractDTO contractDTO,
                                         @RequestParam("document.date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                         @PathVariable("id") Integer contractId) {
        Contract contract = contractService.findById(contractId);
        contractDTO.getDocument().setId(contract.getDocument().getId());
        Document document = documentService.saveDocument(contractDTO.getDocument(), date);
        contract.setDocument(document);
        contractService.saveContract(contract);
        return "redirect:/contract/" + contractId;
    }

    @GetMapping("/contract/address/{id}")
    public String getHelperAddressInfo(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("contract", contractService.findById(id));
        return "helper-address-data";
    }

    @PostMapping("/contract/address/{id}")
    public String saveHelperAddressInfo(Contract contractDto, @PathVariable("id") Integer contractId) {
        Contract contract = contractService.findById(contractId);
        contractDto.getAddress().setId(contract.getAddress().getId());
        contract.setAddress(contractDto.getAddress());
        contractService.saveContract(contract);
        return "redirect:/contract/" + contract.getId();
    }

    @GetMapping("/contract/parent/personality/{id}")
    public String getHelperParentPersonalInfo(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("contract", contractService.findById(id));
        return "helper-parent-personal-data";
    }

    @PostMapping("/contract/parent/personality/{id}")
    public String saveHelperParentPersonalInfo(Contract contractDto, @PathVariable("id") Integer contractId) {
        Contract contract = contractService.findById(contractId);
        contractDto.getParentPersonality().setId(contract.getParentPersonality().getId());
        contract.setParentPersonality(contractDto.getParentPersonality());
        contractService.saveContract(contract);
        return "redirect:/contract/" + contract.getId();
    }

}
