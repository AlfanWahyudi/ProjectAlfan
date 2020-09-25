/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public interface BarangInc {
    public int insert(domain.Barang b);

    public int update(domain.Barang b, int oldId);

    public int delete(int id);

    public java.util.List<domain.Barang> select();
    
}
