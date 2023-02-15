package Interfaces;

import Models.AllUsers;
import Models.Ban;

import java.sql.SQLException;
import java.util.List;

public interface BanInterface {
    public void AddBan(Ban B, AllUsers U ) throws SQLException;

    public void DeleteBan(int ID) throws SQLException;

    public void ModifyBan(Ban B,int ID) throws SQLException;

    public void ModifyBanByUser(Ban B, int ID) throws SQLException;

    public List<Ban> fetchBan() throws SQLException;

    public List<Ban> fetchBanbyID(int ID) throws SQLException;
    public List<Ban> fetchBanbyIDUser(int ID) throws SQLException;
}
