package Services;

import Interfaces.AllUsersInterface;
import Models.AllUsers;
import Util.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AllUsersService implements AllUsersInterface {
    Connection cnx = MyConnection.getInstance().getCnx();


    @Override
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    @Override
    public String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password: " + e.getMessage());
        }
    }

    @Override
    public void AddAu(AllUsers u) {
        String salt = generateSalt();
        String hashedPassword = hashPassword(u.getPassword(), salt);
        try {
            String req = "INSERT INTO allusers(`Name`, `Last_Name`, `Email`, `Birthday`, `Password`, `Nationality`, `type`,`Nickname`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getName());
            ps.setString(2, u.getLast_Name());
            ps.setString(3, u.getEmail());
            ps.setDate(4, u.getBirthday() != null ? Date.valueOf(u.getBirthday()) : null);
            ps.setString(5, hashedPassword);
            ps.setString(6, u.getNationality());
            ps.setString(7, u.getType());
            ps.setString(8, u.getNickname());
            ps.executeUpdate();
            System.out.println("User Added Successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void DeleteAu(int ID) throws SQLException {
        try {
            String req = "DELETE FROM allusers WHERE ID_User=" + ID;
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.executeUpdate();
            System.out.println("User  Deleted Successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void ModifyAu(AllUsers u, int ID) throws SQLException {
        String salt = generateSalt();
        String hashedPassword = hashPassword(u.getPassword(), salt);
        try {
            String req = "UPDATE `allusers` SET `Name`=?,`Last_Name`=?,`Email`=?,`Birthday`=?,`Password`=?,`Nationality`=?,`type`=?,`Nickname`=? WHERE ID_User=" + ID;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getName());
            ps.setString(2, u.getLast_Name());
            ps.setString(3, u.getEmail());
            ps.setDate(4, Date.valueOf(u.getBirthday()));
            ps.setString(5,hashedPassword );
            ps.setString(6, u.getNationality());
            ps.setString(7, u.getType());
            ps.setString(8, u.getNickname());
            ps.executeUpdate();
            System.out.println("User Modified Successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<AllUsers> fetchAU() {
        List<AllUsers> Allusers = new ArrayList<>();
        try {

            String req = "SELECT * FROM allusers";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                AllUsers u = new AllUsers();
                u.setID_User(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setLast_Name(rs.getString(3));
                u.setNickname(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setBirthday(rs.getDate(6).toLocalDate());
                u.setPassword(rs.getString(7));
                u.setNationality(rs.getString(8));
                u.setType(rs.getString(9));


                Allusers.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Allusers;
    }

    @Override
    public List<AllUsers> fetchAUbyID(int ID) throws SQLException {
        List<AllUsers> Allusers = new ArrayList<>();
        try {

            String req = "SELECT * FROM allusers WHERE `ID_User`=" + ID;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                AllUsers u = new AllUsers();
                u.setID_User(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setLast_Name(rs.getString(3));
                u.setNickname(rs.getString(4));
                u.setEmail(rs.getString(5));
                u.setBirthday(rs.getDate(6).toLocalDate());
                u.setPassword(rs.getString(7));
                u.setNationality(rs.getString(8));
                u.setType(rs.getString(9));


                Allusers.add(u);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Allusers;
    }
}
