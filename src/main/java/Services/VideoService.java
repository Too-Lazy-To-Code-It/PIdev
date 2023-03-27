package Services;

import Interfaces.VideoInterface;
import Models.Tutoriel;
import Models.Video;
import Util.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoService implements VideoInterface {
    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void addVideo(Video v) {
        try {
            String req = "INSERT INTO `video`(`Title`,`Description`,`pathVideo`,`pathImage`,`ID_Tutoriel`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            // Set the values for the statement
            ps.setString(1, v.getTitle());
            ps.setString(2, v.getDescrption());
            ps.setString(3, v.getPathVideo());
            ps.setString(4, v.getPathImage());
            ps.setInt(5, v.getTutoriel().getID_Tutoriel());

            ps.executeUpdate();
            System.out.println("Video Added Successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void modifyVideo(Video v) {
        try {
            String req = "update Video set `Title`=?, `Description`=?, `pathVideo`=?, `pathImage`=? where `ID_Video`= ? ";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, v.getTitle());
            ps.setString(2, v.getDescrption());
            ps.setString(3, v.getPathVideo());
            ps.setString(4, v.getPathImage());
            ps.setInt(5, v.getID_Video());
            ps.executeUpdate();
            System.out.println("Video Modified Successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteVideo(int id) {
        String req = "DELETE FROM video where ID_Video = " + id;
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Video deleted Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<Video> fetchVideos() {
        List<Video> videos = new ArrayList<>();
        try {

            String req = "SELECT * FROM video as v,tutoriel as t where v.ID_Tutoriel=t.ID_Tutoriel";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Video v = new Video();
                Tutoriel t = new Tutoriel();

                v.setID_Video(rs.getInt("ID_Video"));
                v.setTitle(rs.getString("Title"));
                v.setDate_p(rs.getString("Date_p"));
                v.setDescrption(rs.getString("Description"));
                v.setPathImage(rs.getString("pathVideo"));
                v.setPathImage(rs.getString("pathImage"));

                t.setID_Tutoriel(rs.getInt("ID_Tutoriel"));
                t.setTitle(rs.getString("Title"));
                t.setNiveau(rs.getInt("Niveau"));
                t.setDescription(rs.getString("Description"));

                v.setTutoriel(t);
                videos.add(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return videos;
    }

    @Override
    public List<Video> fetchVideosByTutoriel(int ID_Tutoriel) {
        List<Video> videos = new ArrayList<>();
        try {

            String req = "SELECT * FROM video as v,tutoriel as t where v.ID_Tutoriel=t.ID_Tutoriel and v.ID_Tutoriel=" + ID_Tutoriel;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Video v = new Video();
                Tutoriel t = new Tutoriel();

                v.setID_Video(rs.getInt("ID_Video"));
                v.setTitle(rs.getString("Title"));
                v.setDate_p(rs.getString("Date_p"));
                v.setDescrption(rs.getString("Description"));
                v.setPathVideo(rs.getString("pathVideo"));
                v.setPathImage(rs.getString("pathImage"));

                t.setID_Tutoriel(rs.getInt("ID_Tutoriel"));
                t.setTitle(rs.getString("Title"));
                t.setNiveau(rs.getInt("Niveau"));
                t.setDescription(rs.getString("Description"));
                t.setPathImg(rs.getString("PathImg"));


                v.setTutoriel(t);
                videos.add(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return videos;
    }

    @Override
    public List<Video> fetchVideoByID(int ID_Video) {
        List<Video> videos = new ArrayList<>();
        try {

            String req = "SELECT * FROM video as v,tutoriel as t where v.ID_Tutoriel=t.ID_Tutoriel and v.ID_Video=" + ID_Video;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Video v = new Video();
                Tutoriel t = new Tutoriel();

                v.setID_Video(rs.getInt("ID_Video"));
                v.setTitle(rs.getString("Title"));
                v.setDate_p(rs.getString("Date_p"));
                v.setDescrption(rs.getString("Description"));
                v.setPathImage(rs.getString("pathVideo"));
                v.setPathImage(rs.getString("pathImage"));

                t.setID_Tutoriel(rs.getInt("ID_Tutoriel"));
                t.setTitle(rs.getString("Title"));
                t.setNiveau(rs.getInt("Niveau"));
                t.setDescription(rs.getString("Description"));

                v.setTutoriel(t);
                videos.add(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return videos;
    }

    @Override
    public List<Video> fetchVideosByName(String Name) {
        List<Video> videos = new ArrayList<>();
        try {

            String req = "SELECT * FROM video as v,tutoriel as t where v.ID_Tutoriel=t.ID_Tutoriel and v.Title like '%" + Name + "%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Video v = new Video();
                Tutoriel t = new Tutoriel();

                v.setID_Video(rs.getInt("ID_Video"));
                v.setTitle(rs.getString("Title"));
                v.setDate_p(rs.getString("Date_p"));
                v.setDescrption(rs.getString("Description"));
                v.setPathImage(rs.getString("pathVideo"));
                v.setPathImage(rs.getString("pathImage"));

                t.setID_Tutoriel(rs.getInt("ID_Tutoriel"));
                t.setTitle(rs.getString("Title"));
                t.setNiveau(rs.getInt("Niveau"));
                t.setDescription(rs.getString("Description"));

                v.setTutoriel(t);
                videos.add(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return videos;
    }


    @Override
    public void affecterVideo(Video v, Tutoriel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
