package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.EmptyEntity;

import java.io.File;

public interface DataInsertingService {

    public File insertData(Contract contract);

    public EmptyEntity checkData(Contract contract);
}
