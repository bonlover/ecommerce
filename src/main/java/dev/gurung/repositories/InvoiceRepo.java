package dev.gurung.repositories;

import dev.gurung.models.Cart;
import dev.gurung.models.Inventory;
import dev.gurung.models.Invoice;
import dev.gurung.models.User;
import dev.gurung.utils.ConnectionUtil;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepo implements CrudRepository<Invoice>{

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    @Override
    public Invoice add(Invoice invoice) {
        try(Connection conn = cu.getConnection() ){

            String sql = "insert into invoices values(default, ?, ?, default) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, invoice.getUser().getId());
            ps.setInt(2, invoice.getInventory().getId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                invoice.setId(rs.getInt("id"));
                return invoice;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Invoice getById(Integer id) {
        return null;
    }

    // Invoice of user by id
    public List<Invoice> getInvoiceByUserId(Integer userId) {
        List<Invoice> invoices = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {
            String sql = "select u.id as user_id, u.first_name, u.last_name, i.id as product_id, i.product , v.id  as invoice_id , v.invoice_date from users u inner join invoices v on  u.id = v.customer_id inner join inventories i on i.id  = v.product_id where v.customer_id = ?\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));

                Inventory i = new Inventory();
                i.setId(rs.getInt("product_id"));
                i.setProductName(rs.getString("product"));

                Invoice v = new Invoice();
                v.setId(rs.getInt("invoice_id"));
                v.setInvoiceDate(rs.getDate("invoice_date"));
                v.setUser(u);
                v.setInventory(i);

                invoices.add(v);

            }
            return invoices;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();

        try(Connection conn = cu.getConnection()){
            String sql = "select u.id as user_id, u.first_name, u.last_name, i.id as product_id, i.product , i.price , v.id  as invoice_id , v.invoice_date  from users u inner join invoices v on  u.id = v.customer_id inner join inventories i on i.id  = v.product_id \n";
            PreparedStatement ps = conn.prepareStatement(sql);
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


                Invoice v = new Invoice();
                v.setId(rs.getInt("invoice_id"));
                v.setInvoiceDate(rs.getDate("invoice_date"));
                v.setUser(u);
                v.setInventory(i);

                invoices.add(v);

            }
            return invoices;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Invoice invoice) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
