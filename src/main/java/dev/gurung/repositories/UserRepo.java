package dev.gurung.repositories;

import dev.gurung.models.User;
import dev.gurung.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements CrudRepository<User>{

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //Read
    @Override
    public User getById(Integer id){

        //Try-Connecting to DB
        try(Connection conn = cu.getConnection()){
            String sql = "select * from users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //Get All Users
    @Override
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        try(Connection conn = cu.getConnection()){

            String sql = "select * from users where role = 'Customer' ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                );

                users.add(user);
            }

            return  users;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmail(String email) {

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from users where email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

               if (rs.next()) {

                   User u = new User(
                           rs.getInt(1),
                           rs.getString(2),
                           rs.getString(3),
                           rs.getString(4),
                           rs.getString(5),
                           rs.getString(6)
                   );
                   return u;
               }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    //Create
    @Override
    public User add(User user){
        try(Connection conn = cu.getConnection()){

//            String sql = "insert into users(id, first_name, last_name, email, password, role) values(default, ?, ?, ?, ?, default) returning *";
            String sql = "insert into users values(default, ?, ?, ?, ?, default) returning *";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4,user.getPassword());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                user.setId(rs.getInt("id"));
                return user;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //Update
    @Override
    public void update(User user){
        try(Connection conn = cu.getConnection()){

            String sql = "update users set first_name = ?, last_name = ? where id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getId());

            ps.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Delete
    @Override
    public void delete(Integer id){
        try(Connection conn = cu.getConnection()){

            String sql = "delete from users where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
