package Models;

import java.util.Date;

public class AllUsers {
    private int ID_User;
    private String Name;
    private String Last_Name;

    private String Nickname;
    private String Email;
    private Date Birthday;
    private String Password;
    private String Nationality;
    private String type;

    public AllUsers() {
    }

    public AllUsers(int ID_User, String name, String last_Name, String nickname, String email, Date birthday, String password, String nationality, String type) {
        this.ID_User = ID_User;
        Name = name;
        Last_Name = last_Name;
        Nickname = nickname;
        Email = email;
        Birthday = birthday;
        Password = password;
        Nationality = nationality;
        this.type = type;
    }

    public AllUsers(String name, String last_Name, String nickname, String email, Date birthday, String password, String nationality, String type) {
        Name = name;
        Last_Name = last_Name;
        Nickname = nickname;
        Email = email;
        Birthday = birthday;
        Password = password;
        Nationality = nationality;
        this.type = type;
    }

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AllUsers{" +
                "ID_User=" + ID_User +
                ", Name='" + Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Nickname='" + Nickname + '\'' +
                ", Email='" + Email + '\'' +
                ", Birthday=" + Birthday +
                ", Password='" + Password + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

