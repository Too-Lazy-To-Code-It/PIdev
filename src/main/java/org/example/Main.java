package org.example;

import Interfaces.AllUsersInterface;
import Interfaces.BanInterface;
import Models.AllUsers;
import Services.AllUsersService;
import Services.BanService;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;



public class Main {
    public static void main(String[] args) throws SQLException {
        AllUsersInterface au = new AllUsersService();
        BanInterface B = new BanService();

        //player init
        AllUsers u;
       // u = new AllUsers("Adam", "Rafraf ","AdamRafraf", "Adam@gmail.com", LocalDate.of(2000,9,9), "PASSWORDENCRYPTED", "Tunisian", "Artiste");
        //add action
        // au.AddAu(u);
     au.sendVerificationCode("2lazy2nameit@gmail.com","200");

        //select
       // au.ModifyAu(u, 3);
        //au.DeleteAu(1);
        System.out.println(au.fetchAU());
        //System.out.println(au.fetchAUbyID(1));


    }
}