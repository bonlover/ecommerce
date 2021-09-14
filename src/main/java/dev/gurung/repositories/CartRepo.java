package dev.gurung.repositories;

import dev.gurung.models.Cart;
import dev.gurung.models.Inventory;
import dev.gurung.models.User;
import dev.gurung.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartRepo implements CrudRepository<Cart> {
    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
    @Override
    public Cart add(Cart cart) {
        try(Connection conn = cu.getConnection()){

            String sql = "insert into carts values(default, ?, ?, ?, ?) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart.getUser().getId());
            ps.setInt(2, cart.getInventory().getId());
            ps.setInt(3, cart.getNumberProducts());
            ps.setDouble(4,cart.getTotalPrice());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                cart.setId(rs.getInt("id"));
                return cart;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cart getById(Integer id) {
        //Try-Connecting to DB
        try(Connection conn = cu.getConnection()){
            String sql = "select u.id as user_id, u.first_name, u.last_name, i.id as product_id, i.product , i.price , c.id  as cart_id , c.items , c.total_price from users u inner join carts c on  u.id = c.user_id join inventories i on i.id  = c.inventory_id where c.id = ?\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));

                Inventory i = new Inventory();
                i.setId(rs.getInt("product_id"));
                i.setProductName(rs.getString("product"));
                i.setPrice(rs.getDouble("price"));


                Cart c = new Cart();
                c.setId(rs.getInt("cart_id"));
                c.setTotalPrice(rs.getDouble("total_price"));
                c.setNumberProducts(rs.getInt("items"));
                c.setUser(u);
                c.setInventory(i);

                return c;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //Get by userId
    public List<Cart> getByUserId(Integer userId) {
        List<Cart> carts = new ArrayList<>();

        try(Connection conn = cu.getConnection()){
            String sql = "select u.id as user_id, u.first_name, u.last_name, i.id as product_id, i.product , i.price , c.id  as cart_id , c.items , c.total_price from users u inner join carts c on  u.id = c.user_id join inventories i on i.id  = c.inventory_id where c.user_id = ?\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));

                Inventory i = new Inventory();
                i.setId(rs.getInt("product_id"));
                i.setProductName(rs.getString("product"));
                i.setPrice(rs.getDouble("price"));


                Cart c = new Cart();
                c.setId(rs.getInt("cart_id"));
                c.setTotalPrice(rs.getDouble("total_price"));
                c.setNumberProducts(rs.getInt("items"));
                c.setUser(u);
                c.setInventory(i);

                carts.add(c);

            }
            return carts;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public List<Cart> getAll() {
        List<Cart> carts = new ArrayList<>();

       try (Connection conn = cu.getConnection()) {

            String sql = "select u.id as user_id, u.first_name, u.last_name, i.id as product_id, i.product , i.price , c.id  as cart_id , c.items , c.total_price from users u inner join carts c on  u.id = c.user_id join inventories i on i.id  = c.inventory_id;\n";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // For each record we need to create an Author Object AND a Book Object
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));

                Inventory i = new Inventory();
                i.setId(rs.getInt("product_id"));
                i.setProductName(rs.getString("product"));
                i.setPrice(rs.getDouble("price"));


                Cart c = new Cart();
                c.setId(rs.getInt("cart_id"));
                c.setTotalPrice(rs.getDouble("total_price"));
                c.setNumberProducts(rs.getInt("items"));
                c.setUser(u);
                c.setInventory(i);

                carts.add(c);
            }

            return carts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Cart cart) {
        try (Connection conn = cu.getConnection()) {

            if(cart.getId() != null && cart.getNumberProducts()!=null && cart.getTotalPrice() != null ){

                String sql = "update carts set items = ?, total_price = ? where id = ? ";

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, cart.getNumberProducts());
                ps.setDouble(2, cart.getTotalPrice());
                ps.setInt(3, cart.getId());

                ps.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try(Connection conn = cu.getConnection()){

            String sql = "delete from carts where id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
