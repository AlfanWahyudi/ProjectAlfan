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

    public int update(domain.Barang b, String nama, String material, String ukuran);

    public int delete(String nama, String material, String ukuran);

    public java.util.List<domain.Barang> select();
    
}
