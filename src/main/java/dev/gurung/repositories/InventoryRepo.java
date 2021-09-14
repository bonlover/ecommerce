package dev.gurung.repositories;

import dev.gurung.models.Inventory;
import dev.gurung.models.User;
import dev.gurung.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepo implements CrudRepository<Inventory> {
    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    //Read
    @Override
    public Inventory getById(Integer id){

        //Try-Connecting to DB
        try(Connection conn = cu.getConnection()){
            String sql = "select * from inventories where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Inventory inventory = new Inventory();

                inventory.setId(rs.getInt("id"));
                inventory.setProductName(rs.getString("product"));
                inventory.setPrice(rs.getDouble("price"));
                inventory.setQuantity(rs.getInt("quantity"));

                return inventory;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //Read by name of product
    public Inventory getByName(String product){

        //Try-Connecting to DB
        try(Connection conn = cu.getConnection()){
            String sql = "select * from inventories where product=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Inventory inventory = new Inventory();

                inventory.setId(rs.getInt("id"));
                inventory.setProductName(rs.getString("product"));
                inventory.setPrice(rs.getDouble("price"));
                inventory.setQuantity(rs.getInt("quantity"));

                return inventory;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }


    //Get All Users

    @Override
    public List<Inventory> getAll() {
        List<Inventory> inventories = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {

            // we will need to use a join on inventories and users to get all data needed
            String sql = "select i.id as product_id, i.product, i.price, i.quantity, u.id as customer_id, u.first_name, u.last_name from ecommerce.inventories i inner join ecommerce.users u on i.user_id = u.id;\n";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // For each record we need to create an Author Object AND a Book Object
                User u = new User();
                u.setId(rs.getInt("customer_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));

                Inventory i = new Inventory();
                i.setId(rs.getInt("product_id"));
                i.setProductName(rs.getString("product"));
                i.setPrice(rs.getDouble("price"));
                i.setQuantity(rs.getInt("quantity"));
                i.setUser(u);

                inventories.add(i);
            }

            return inventories;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    //Create
    @Override
    public Inventory add(Inventory inventory){
        try(Connection conn = cu.getConnection()){
//            Integer user_id = inventory.getId();

            String sql = "insert into inventories values(default, ?, ?, ?, ?) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, inventory.getProductName());
            ps.setDouble(2, inventory.getPrice());
            ps.setInt(3, inventory.getQuantity());
            ps.setInt(4,inventory.getUser().getId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                inventory.setId(rs.getInt("id"));
                return inventory;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //Update

    @Override
    public void update(Inventory inventory) {
        try (Connection conn = cu.getConnection()) {

            if(inventory.getId()!=null && inventory.getQuantity()!=null && inventory.getPrice()!=null ){
                String sql = "update inventories set price = ?, quantity = ? where id = ? ";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setDouble(1, inventory.getPrice());
                ps.setInt(2, inventory.getQuantity());
                ps.setInt(3, inventory.getId());

                ps.execute();
            }

            if(inventory.getId()!=null && inventory.getQuantity()!=null ){
                String sql = "update inventories set quantity = ? where id = ? ";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, inventory.getQuantity());
                ps.setInt(2, inventory.getId());

                ps.execute();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {

        try(Connection conn = cu.getConnection()){

            String sql = "delete from inventories where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
