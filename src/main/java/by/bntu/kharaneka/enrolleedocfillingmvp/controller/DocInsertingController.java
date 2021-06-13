package by.bntu.kharaneka.enrolleedocfillingmvp.controller;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.User;
import by.bntu.kharaneka.enrolleedocfillingmvp.service.ContractService;
import by.bntu.kharaneka.enrolleedocfillingmvp.service.DataInsertingService;
import by.bntu.kharaneka.enrolleedocfillingmvp.service.UserService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class DocInsertingController {

    private final UserService userService;
    private final ContractService contractService;
    private final DataInsertingService dataInsertingService;

    @Autowired
    public DocInsertingController(UserService userService,
                                  ContractService contractService,
                                  DataInsertingService dataInsertingService) {
        this.userService = userService;
        this.contractService = contractService;
        this.dataInsertingService = dataInsertingService;
    }


    @RequestMapping(value = "/downloadFile")
    public String getLogFile(HttpServletResponse response,
                           Authentication authentication) {
        Contract contract = userService.findByLogin(authentication.getName())
                .map(User::getContract).get();
        String url = "forward:/data_filling";
        switch (dataInsertingService.checkData(contract)) {
            case PERSONALITY:
                url = "forward:/personality";
                break;
            case DOCUMENT:
                url = "forward:/passport";
                break;
            case ADDRESS:
                url = "forward:/address";
                break;
            case PARENT_PERSONALITY:
                url = "forward:/parent/personality";
                break;
            case PARENT_DOCUMENT:
                url = "forward:/parent/passport";
                break;
            case PARENT_ADDRESS:
                url = "forward:/parent/address";
                break;
            default:

                try {

                    InputStream inputStream = new FileInputStream(dataInsertingService.insertData(contract));
                    response.setContentType("application/force-download");
                    response.setHeader("Content-Disposition", "attachment; filename=dogovor.docx");
                    IOUtils.copy(inputStream, response.getOutputStream());
                    response.flushBuffer();
                    inputStream.close();
                } catch (Exception e) {
                    //LOGGER.debug("Request could not be completed at this moment. Please try again.");
                    e.printStackTrace();
                }
        }
        return url;
    }

    @PostMapping
    @RequestMapping(value = "/check")
    public void setCheck(HttpSession session,
                             Authentication authentication, Boolean isEighteen) {
        Contract contract = userService.findByLogin(authentication.getName())
                .map(User::getContract).get();
        contract.setEighteen(isEighteen);
        contractService.saveContract(contract);
    }
}
