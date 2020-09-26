/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectalfan.controller;

import domain.Barang;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;
import model.BarangInc;
import model.BarangMdl;
import projectalfan.View.FrameUtama;

/**
 *
 * @author user
 */
public class BarangCtl {
    private final FrameUtama frame;
    private final BarangInc brg;
    private List<Barang> data;
    private int oldId ;
    private final String save = "Simpan";
    private final String add = "Tambah";
    private final String edit = "Rubah";
    private final String empty = "Harap Lengkapi data!";
    private final String ok = "Data berhasil disimpan!";

    public BarangCtl(FrameUtama frame) {
        this.frame = frame;
        this.brg = new BarangMdl();
    }

    public void tambah() {
        if (frame.getBtnTambah().getText().equals(add)) {
            setFieldEnabled(true);
            frame.getBtnTambah().setText(save);
            focus();
        } else if (frame.getBtnTambah().getText().equals(save)) {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(frame, empty);
                focus();
            } else {
                Barang b = new Barang();
                b.setNama(frame.getTxtNama().getText());
                b.setMaterial(frame.getTxtMaterial().getText());
                b.setUkuran(frame.getTxtUkuran().getText());
                b.setHargaSatuan(Integer.parseInt(frame.getTxtHargaSatuan().getText()));
                int status = brg.insert(b);
                if (status != 0) {
                    frame.getBtnTambah().setText(add);
                    setFieldEnabled(false);
                    muatData();
                    JOptionPane.showMessageDialog(frame, ok);
                }
                

            }
        }
    }

    public void edit() {
        if (frame.getBtnEdit().getText().equals(edit)) {
            Barang b = new Barang();
            oldId = Integer.parseInt(frame.getBarangTable().getValueAt(frame.getBarangTable().getSelectedRow(), 0).toString());
            frame.getTxtNama().setText(frame.getBarangTable().getValueAt(frame.getBarangTable().getSelectedRow(), 1).toString());
            frame.getTxtMaterial().setText(frame.getBarangTable().getValueAt(frame.getBarangTable().getSelectedRow(), 2).toString());
            frame.getTxtUkuran().setText(frame.getBarangTable().getValueAt(frame.getBarangTable().getSelectedRow(), 3).toString());
            frame.getTxtHargaSatuan().setText(frame.getBarangTable().getValueAt(frame.getBarangTable().getSelectedRow(), 4).toString());
            frame.getBtnEdit().setText(save);
            setFieldEnabled(true);
            focus();
        } else if (frame.getBtnEdit().getText().equals(save)) {
            if (isEmpty()) {
                JOptionPane.showMessageDialog(frame, empty);
                focus();
            } else {
                Barang b = new Barang();
                b.setNama(frame.getTxtNama().getText());
                b.setMaterial(frame.getTxtMaterial().getText());
                b.setUkuran(frame.getTxtUkuran().getText());
                b.setHargaSatuan(Integer.parseInt(frame.getTxtHargaSatuan().getText()));
                int status = brg.update(b, oldId);
                if (status != 0) {
                    frame.getBtnEdit().setText(edit);
                    setButtonEnabled(true);
                    setFieldEnabled(false);
                    muatData();
                    JOptionPane.showMessageDialog(frame, ok);
                }
            }
        }
    }

    public void hapus() {
        if (frame.getBarangTable().getSelectedRow() != -1) {
            JLabel label = new JLabel("TIDAK ADA OPSI UNDO!");
            label.setForeground(new java.awt.Color(255, 0, 0));
            Object[] pesan = {
                "Anda yakin ingin menghapus data terpilih?", label
            };
            int op = JOptionPane.showConfirmDialog(frame, pesan, "Peringatan!", JOptionPane.OK_CANCEL_OPTION);
            if (op == JOptionPane.OK_OPTION) {
                int id = Integer.parseInt(frame.getBarangTable().getValueAt(frame.getBarangTable().getSelectedRow(), 0).toString());
                int status = brg.delete(id);
                if (status != 0) {
                    setButtonEnabled(false);
                    setFieldEnabled(false);
                    muatData();
                }
            }
        }
    }

    public void tabelKlik() {
        if (frame.getBarangTable().getSelectedRow() != -1) {
            if (frame.getBtnEdit().getText().equals(edit)) {
                setButtonEnabled(false);
                clear();
            } else {
                frame.getBtnEdit().setText(edit);
                clear();
            }
            setFieldEnabled(false);
        }
    }

    public void muatData() {
        frame.getBtnRefresh().setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) frame.getBarangTable().getModel();
        setButtonEnabled(true);
        setFieldEnabled(false);
        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                model.setRowCount(0);
                data = brg.select();
                for (int i = 0; i < data.size(); i++) {
                    Object[] dataTable = {
                          data.get(i).getId() , data.get(i).getNama(), data.get(i).getMaterial(), data.get(i).getUkuran(),data.get(i).getHargaSatuan()
                        
                            
                            
                    };
                    model.addRow(dataTable);
                }
                return null;
            }

            @Override
            public void done() {
                frame.getBtnRefresh().setEnabled(true);
                frame.getBtnTambah().setText(add);
                frame.getBtnEdit().setText(edit);
                clear();
            }
        };
        sw.execute();
    }

    private void setButtonEnabled(boolean mode) {
        frame.getBtnTambah().setEnabled(mode);
        frame.getBtnEdit().setEnabled(!mode);
        frame.getBtnHapus().setEnabled(!mode);
    }

    private void setFieldEnabled(boolean mode) {
        frame.getTxtNama().setEnabled(mode);
        frame.getTxtMaterial().setEnabled(mode);
        frame.getTxtUkuran().setEnabled(mode);
        frame.getTxtHargaSatuan().setEnabled(mode);
    }

    private boolean isEmpty() {
        return frame.getTxtNama().getText().isEmpty() || frame.getTxtMaterial().getText().isEmpty() 
                || frame.getTxtUkuran().getText().isEmpty() || frame.getTxtHargaSatuan().getText().isEmpty();
    }

    private void clear() {
        frame.getTxtNama().setText(null);
        frame.getTxtMaterial().setText(null);
        frame.getTxtUkuran().setText(null);
        frame.getTxtHargaSatuan().setText(null);
    }

    private void focus() {
        if (frame.getTxtNama().getText().isEmpty()) {
            frame.getTxtNama().requestFocus();
        } else {
            frame.getTxtMaterial().requestFocus();
        }
    }

    public void hanyaNomor(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
            showHint(frame.getTxtHargaSatuan());
        } else if (frame.getTxtHargaSatuan().getText().length() > 9) {
            evt.consume();
        }
    }

    @SuppressWarnings("Convert2Lambda")
    private void showHint(Component component) {
        final ToolTipManager ttm = ToolTipManager.sharedInstance();
        final int oldDelay = ttm.getInitialDelay();
        ttm.setInitialDelay(0);
        ttm.mouseMoved(new MouseEvent(component, 0, 0, 0,
                0, 0,
                0, false));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ttm.setInitialDelay(oldDelay);
            }
        });
    }
    
}
