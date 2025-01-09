package fr.afpa.pompey.cda22045.myyebook.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Setter
public class Emprunter {

    private Integer empId;
    private LocalDate empDate;
    private Integer cliId;
    private Integer livId;
    private Integer libId;

    public Emprunter() {}

    public Emprunter(@NotNull Integer empId,@NotNull LocalDate empDate, @NotNull Integer libId, @NotNull Integer cliId, @NotNull Integer livId) {

        this.empId = empId;
        this.empDate = empDate;
        this.libId = libId;
        this.cliId = cliId;
        this.livId = livId;
    }





    @Override
    public String toString() {
        return "Emprunter [cliId=" + cliId + ", livId=" + livId + "]";
    }
}

