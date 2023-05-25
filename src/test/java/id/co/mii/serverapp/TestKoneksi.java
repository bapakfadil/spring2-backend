package id.co.mii.serverapp;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class TestKoneksi {
    @Autowired
    private DataSource application;

    @Test
    public void testConnection() {
        try {
            // Menguji koneksi
            application.getConnection();
            System.out.println("Koneksi sukses!");
        } catch (Exception e) {
            System.err.println("Koneksi gagal! " + e.getMessage());
        }
    }
}
