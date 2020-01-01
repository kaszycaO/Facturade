package FacturadeDB.Facturade.UI;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(catalog="mysql")
public class User implements Serializable {

    @Id
    private String _username;
    private String _password;

    public User(){};

    public User(String username, String password){
        this._username = username;
        this._password = password;
    }

    public String get_username(){
        return this._username;
    }

    public String get_password(){
        return this._password;
    }

    public void set_username(String username){
        this._username = username;
    }

    public void set_password(String password){
        this._password = password;
    }

}
