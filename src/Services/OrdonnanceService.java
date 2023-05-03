/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import entites.Animal;
import entites.Ordonnance;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Ennou
 */
public class OrdonnanceService implements IOrdonnance<Ordonnance> {

    public Connection conx;
    public Statement stm;

    public OrdonnanceService() {
        conx = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajoutOrdonnance(Ordonnance o) throws SQLException {
        String req = "INSERT INTO ordonnance(description, traitement,date,id,rendezvous_id)" + " VALUES ('" + o.getDescription() + "','" + o.getTraitement() + "','" + o.getDate() + "','" + o.getId() + "','" + o.getRendezVous().getId() + "')";
        System.out.println(req);
        stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("ordonnace ajoutée");
    }

//    @Override
//    public void ajoutOrdonnancee(Ordonnance o) throws SQLException{
//        String req="INSERT INTO `ordonnace`(`description`, `traitement`,'date') VALUES (?,?,?)";
//        
//        PreparedStatement ps = conx.prepareStatement(req);
//        ps.setString(1, o.getDescription());
//        ps.setString(2,o.getTraitement());
//        ps.setDate(3,o.getDate());
//        
//        ps.executeUpdate();
//        System.out.println("animal ajoutée");
//
//    }
//
    @Override
    public List<Ordonnance> afficherListeO() throws SQLException {
        String req = "SELECT * FROM `ordonnance`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        List<Ordonnance> ordonnances = new ArrayList<>();
        while (rs.next()) {
            Ordonnance o = new Ordonnance(rs.getInt("id"), rs.getString("description"), rs.getString("traitement"), rs.getDate("date"));
            ordonnances.add(o);
        }
        return ordonnances;
    }

    @Override
    public void supprimerOrdonnance(int id) {
        try {
            String sql = "DELETE FROM Ordonnance WHERE id=" + id + "";
            PreparedStatement ste = conx.prepareStatement(sql);

            ste.executeUpdate();
            System.out.println("ordonnance Supprimée ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifier(int id, Ordonnance o) throws SQLException {
        try {

            PreparedStatement st;
            st = conx.prepareStatement("UPDATE `ordonnance` SET `description`=?,`traitement`=?, `date`=?, `rendezvous_id`=? WHERE id=?");
            st.setString(1, o.getDescription());
            st.setString(2, o.getTraitement());
            st.setDate(3, new java.sql.Date(o.getDate().getTime()));
            st.setInt(4, o.getRendezVous().getId());
            st.setInt(5, id);

            if (st.executeUpdate() == 1) {
                System.out.println("Ordonnance modifier avec success");
            } else {
                System.out.println("Ordonnannce n'existe pas");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void pdfOrdonnance(Ordonnance ordonnance) throws IOException, SQLException {
        String absolutePath = new File("").getAbsolutePath();

        File outputFile = new File(absolutePath + "\\src\\PDF\\" + ordonnance.getRendezVous().getAnimal().getNom() + ".pdf");
        // Create a new PDF document
        Document document = new Document(new PdfDocument(new PdfWriter(outputFile)));

        // Load a font for the document
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        // Add details
        document.add(new Paragraph("Ordonnance")
                .setFont(font)
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER.CENTER));

        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2}))
                .setWidth(UnitValue.createPercentValue(80))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);

        table.addCell("Animal")
                .setFont(font)
                .setBold();

        table.addCell(String.valueOf(ordonnance.getRendezVous().getAnimal().getNom()))
                .setFont(font);

        table.addCell("Date du Rendez-vous")
                .setFont(font)
                .setBold();

        table.addCell(String.valueOf(ordonnance.getRendezVous().getDate()))
                .setFont(font);

        table.addCell("Traitement")
                .setFont(font)
                .setBold();

        table.addCell(String.valueOf(ordonnance.getTraitement()))
                .setFont(font);

        table.addCell("Description")
                .setFont(font)
                .setBold();

        table.addCell(String.valueOf(ordonnance.getDescription()))
                .setFont(font);

        table.addCell("Date d ordonnance")
                .setFont(font)
                .setBold();

        table.addCell(String.valueOf(ordonnance.getDate()))
                .setFont(font);

        document.add(table);
        // Close pdf
        document.close();

        //Open pdf
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(outputFile);
        }
    }

}
