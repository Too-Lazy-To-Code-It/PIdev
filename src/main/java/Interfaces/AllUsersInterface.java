package Interfaces;

import Models.AllUsers;

import java.sql.SQLException;
import java.util.List;

public interface AllUsersInterface {

    public String generateSalt();
    public String hashPassword(String password, String salt);
    public void AddAu(AllUsers u) throws SQLException;

    public  void CreateAU(AllUsers u);

    public void DeleteAu(int ID) throws SQLException;

    public void ModifyAu(AllUsers u,int ID) throws SQLException;

    public List<AllUsers> fetchAU() throws SQLException;

    public AllUsers fetchAUbyID(int ID) throws SQLException;
    public AllUsers fetchAUbyEmail(String Email) throws SQLException;
    public AllUsers fetchAUbyNickname(String Nickname) throws SQLException;
    public String generateVerificationCode();
    public void sendVerificationCode(String recipientEmail, String verificationCode);


}
