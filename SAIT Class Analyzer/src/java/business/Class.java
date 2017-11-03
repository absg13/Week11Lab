package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

/**
 *
 * @author 738377
 */
public class Class implements Serializable {
    
    private String          name;
    private String          code;
    private int             currentgrade;
    private int             desiredgrade;

    public Class() {
        name = "";
        code = "";
        currentgrade = 0;
        desiredgrade = 0;
    }

    public Class(String name, String code, int currentgrade, int desiredgrade) {
        this.name = name;
        this.code = code;
        this.currentgrade = currentgrade;
        this.desiredgrade = desiredgrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCurrentgrade() {
        return currentgrade;
    }

    public void setCurrentgrade(int currentgrade) {
        this.currentgrade = currentgrade;
    }

    public int getDesiredgrade() {
        return desiredgrade;
    }

    public void setDesiredgrade(int desiredgrade) {
        this.desiredgrade = desiredgrade;
    }
}
