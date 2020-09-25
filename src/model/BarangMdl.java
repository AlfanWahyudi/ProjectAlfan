/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Barang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projectalfan.DAO.Koneksi;

/**
 *
 * @author user
 */
public class BarangMdl implements BarangInc{
    
    private final Koneksi db;
    private static final String QUERY_INSERT = "INSERT INTO barang VALUES(?,?,?,?,?)";
    private static final String QUERY_UPDATE = "UPDATE barang SET nama=?, material=?, ukuran=?, hargaSatuan=? WHERE id=?";
    private static final String QUERY_DELETE = "DELETE FROM barang WHERE id=?";
    private static final String QUERY_SELECT = "SELECT * FROM barang";

    public BarangMdl() {
        db = new Koneksi();
    }

    @Override
    public int insert(Barang b) {
        try (PreparedStatement p = db.prep(QUERY_INSERT)) {
            //p.setInt(1, b.getId());
            p.setString(2, b.getNama());
            p.setString(3, b.getMaterial());
            p.setString(4, b.getUkuran());
            p.setInt(5, b.getHargaSatuan());
            return p.executeUpdate();
        } catch (SQLException ex) {
            err(ex);
            return 0;
        }
    }

    @Override
    public int update(Barang b, int oldId) {
        try (PreparedStatement p = db.prep(QUERY_UPDATE)) {
            p.setInt(1, b.getId());
            p.setString(2, b.getNama());
            p.setString(3, b.getMaterial());
            p.setString(4, b.getUkuran());
            p.setInt(5, b.getHargaSatuan());
            p.setInt(5, oldId);
            return p.executeUpdate();
        } catch (SQLException ex) {
            err(ex);
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        try (PreparedStatement p = db.prep(QUERY_DELETE)) {
            p.setInt(1, id);
            return p.executeUpdate();
        } catch (SQLException ex) {
            err(ex);
            return 0;
        }
    }

    @Override
    public List<Barang> select() {
        List<Barang> data = new ArrayList<>();
        try (PreparedStatement p = db.prep(QUERY_SELECT)) {
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Barang b = new Barang();
                //b.setId(r.getInt(1));
                b.setNama(r.getString(2));
                b.setMaterial(r.getString(3));
                b.setUkuran(r.getString(4));
                b.setHargaSatuan(r.getInt(5));
                data.add(b);
            }
        } catch (SQLException ex) {
            err(ex);
        }
        return data;
    }

    private void err(SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error " + ex.getErrorCode(), JOptionPane.ERROR_MESSAGE);
    }
    
}
