package com.pfe22.ava.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.entities.ava.AvaFile.Beneficiary;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class ExportAvaService {

    public static ByteArrayInputStream AvasPDFReport(List<Ava> avas  ){
        Calendar c = Calendar.getInstance();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();

            com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.COURIER_BOLD
                    ,16 , BaseColor.BLACK);
            Paragraph atb = new Paragraph("ARAB TUNISIAN BANK  \n AGENCE : CENTRALE",font1);
            atb.setAlignment(Element.ALIGN_LEFT);
            atb.setAlignment(Element.ALIGN_TOP);
            document.add(atb);
            document.add(Chunk.NEWLINE);
            com.itextpdf.text.Font font2 = FontFactory.getFont(FontFactory.COURIER_OBLIQUE
                    ,8 , BaseColor.BLACK);
            Paragraph date = new Paragraph(String.valueOf(c.get(Calendar.DAY_OF_MONTH))+" "+
                    c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)+" "+
                    c.get(Calendar.YEAR)+" "+
                    dtf.format(now),font2);
            date.setAlignment(Element.ALIGN_RIGHT);

            document.add(date);
            document.add(Chunk.NEWLINE);

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER
            ,14 , BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("ETAT : dossiers A.V.A :  "
                    +c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)
                    +" " + c.get(Calendar.YEAR),font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);

            Stream.of("Ref","Num Client","Type AVA" ,"creation").forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
            com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(1);
            header.setPadding(10);
            header.setPhrase(new Phrase(headerTitle ,headFont));
            table.addCell(header);
            });

            for (Ava ava:avas) {
                PdfPCell refCell = new PdfPCell(new Phrase(ava.getReferenceDossierAVA()));
                refCell.setPadding(10);
                refCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                refCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(refCell);

                PdfPCell ClientCell = new PdfPCell(new Phrase(ava.getIdClient() ));
                ClientCell.setPadding(10);
                ClientCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ClientCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(ClientCell);

                PdfPCell TypeCell = new PdfPCell(new Phrase(ava.getAvaType() ));
                TypeCell.setPadding(10);
                TypeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                TypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(TypeCell);

                PdfPCell ValiditeCell = new PdfPCell(new Phrase(String.valueOf(ava.getDateCreation()).substring(0,10)));
                ValiditeCell.setPadding(10);
                ValiditeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ValiditeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(ValiditeCell);

            }
            document.add(table);
            document.close();

        }catch (DocumentException e){
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public static ByteArrayInputStream AvasPDFReportCustomMonth(List<Ava> avas, String dateList) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");
        Date dd =formatter.parse(dateList);
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c.setTime(dd);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();

            com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.COURIER_BOLD
                    ,16 , BaseColor.BLACK);
            Paragraph atb = new Paragraph("ARAB TUNISIAN BANK  \n AGENCE : CENTRALE",font1);
            atb.setAlignment(Element.ALIGN_LEFT);
            atb.setAlignment(Element.ALIGN_TOP);
            document.add(atb);
            document.add(Chunk.NEWLINE);
            com.itextpdf.text.Font font2 = FontFactory.getFont(FontFactory.COURIER_OBLIQUE
                    ,8 , BaseColor.BLACK);
            Paragraph date = new Paragraph(String.valueOf(c2.get(Calendar.DAY_OF_MONTH))+" "+
                    c2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)+" "+
                    c2.get(Calendar.YEAR)+" "+
                    dtf.format(now),font2);
            date.setAlignment(Element.ALIGN_RIGHT);

            document.add(date);
            document.add(Chunk.NEWLINE);

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER
                    ,14 , BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("ETAT : dossiers A.V.A :  "
                    +c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)
                    +" " + c.get(Calendar.YEAR),font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6);

            Stream.of("Ref","Nom Client","Type AVA" ,"Droit à transfert", "Num autorisation","creation").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPadding(10);
                header.setPaddingLeft(5);
                header.setPaddingRight(5);
                header.setPhrase(new Phrase(headerTitle ,headFont));
                table.addCell(header);
            });

            for (Ava ava:avas) {
                if (ava.getClient()!=null){
                PdfPCell refCell = new PdfPCell(new Phrase(ava.getReferenceDossierAVA()));
                refCell.setPadding(10);
                refCell.setPaddingLeft(5);
                refCell.setPaddingRight(5);
                refCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                refCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(refCell);

                PdfPCell ClientCell = new PdfPCell(new Phrase(ava.getClient().getFirstName() ));
                ClientCell.setPadding(10);
                ClientCell.setPaddingLeft(5);
                ClientCell.setPaddingRight(5);
                ClientCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ClientCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(ClientCell);

                PdfPCell TypeCell = new PdfPCell(new Phrase(ava.getAvaType() ));
                TypeCell.setPadding(10);
                TypeCell.setPaddingLeft(5);
                TypeCell.setPaddingRight(5);
                TypeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                TypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(TypeCell);

                PdfPCell DatCell = new PdfPCell(new Phrase(ava.getDat().toString() ));
                DatCell.setPadding(10);
                DatCell.setPaddingLeft(5);
                DatCell.setPaddingRight(5);
                DatCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                DatCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(DatCell);

                PdfPCell NumCell = new PdfPCell(new Phrase(ava.getNumAutorBct().toString() ));
                NumCell.setPadding(10);
                NumCell.setPaddingLeft(5);
                NumCell.setPaddingRight(5);
                NumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                NumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(NumCell);

                PdfPCell ValiditeCell = new PdfPCell(new Phrase(String.valueOf(ava.getDateCreation()).substring(0,10)));
                ValiditeCell.setPadding(10);
                ValiditeCell.setPaddingLeft(5);
                ValiditeCell.setPaddingRight(5);
                ValiditeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                ValiditeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(ValiditeCell);
                }
            }
            document.add(table);
            document.close();

        }catch (DocumentException e){
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public static ByteArrayInputStream BenefPDFReport(List<Ava> beneficiaries){
        Calendar c = Calendar.getInstance();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();

            com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.COURIER_BOLD
                    ,16 , BaseColor.BLACK);
            Paragraph atb = new Paragraph("ARAB TUNISIAN BANK  \n AGENCE : CENTRALE",font1);
            atb.setAlignment(Element.ALIGN_LEFT);
            atb.setAlignment(Element.ALIGN_TOP);
            document.add(atb);
            document.add(Chunk.NEWLINE);
            com.itextpdf.text.Font font2 = FontFactory.getFont(FontFactory.COURIER_OBLIQUE
                    ,8 , BaseColor.BLACK);
            Paragraph date = new Paragraph(String.valueOf(c.get(Calendar.DAY_OF_MONTH))+" "+
                    c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)+" "+
                    c.get(Calendar.YEAR)+" "+
                    dtf.format(now),font2);
            date.setAlignment(Element.ALIGN_RIGHT);

            document.add(date);
            document.add(Chunk.NEWLINE);

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER
                    ,14 , BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("ETAT : bénéficiaires A.V.A :  "
                    +c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)
                    +" " + c.get(Calendar.YEAR),font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);

            Stream.of("Ref dossier","Num bénéficiaire"," nom prenom" ,"Creation").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPadding(10);
                header.setPhrase(new Phrase(headerTitle ,headFont));
                table.addCell(header);
            });

            for (Ava ava:beneficiaries) {
                if (ava.getBeneficiaries()!=null){


                    for (Beneficiary beneficiary:ava.getBeneficiaries()) {
                            if (  ( beneficiary.getDateCreation().after(getFirstDateOfMonth(new Date())) )
                        &&  ( beneficiary.getDateCreation().before(getLAstDateOfMonth(new Date())) ) ){
                                PdfPCell refCell = new PdfPCell(new Phrase(ava.getReferenceDossierAVA()));
                                refCell.setPadding(10);
                                refCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                refCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(refCell);

                                PdfPCell idBenefCell = new PdfPCell(new Phrase(beneficiary.getIdBenef()));
                                idBenefCell.setPadding(10);
                                idBenefCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                idBenefCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(idBenefCell);

                                PdfPCell TypeCell = new PdfPCell(new Phrase(beneficiary.getNomPrenomBenef() ));
                                TypeCell.setPadding(10);
                                TypeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                TypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(TypeCell);

                                PdfPCell ValiditeCell = new PdfPCell(new Phrase(String.valueOf(beneficiary.getDateCreation()).substring(0,10)));
                                ValiditeCell.setPadding(10);
                                ValiditeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                ValiditeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                table.addCell(ValiditeCell);


                            }


                    }


                }

            }
            document.add(table);
            document.close();

        }catch (DocumentException e){
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    public static ByteArrayInputStream BenefPDFReportCustomMonth(List<Ava> beneficiaries,String CustomDate) throws ParseException {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter=new SimpleDateFormat( "yyyy-MM-dd");
        Date dd =formatter.parse(CustomDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(dd);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();

            com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.COURIER_BOLD
                    ,16 , BaseColor.BLACK);
            Paragraph atb = new Paragraph("ARAB TUNISIAN BANK  \n AGENCE : CENTRALE",font1);
            atb.setAlignment(Element.ALIGN_LEFT);
            atb.setAlignment(Element.ALIGN_TOP);
            document.add(atb);
            document.add(Chunk.NEWLINE);
            com.itextpdf.text.Font font2 = FontFactory.getFont(FontFactory.COURIER_OBLIQUE
                    ,8 , BaseColor.BLACK);
            Paragraph date = new Paragraph(String.valueOf(c.get(Calendar.DAY_OF_MONTH))+" "+
                    c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)+" "+
                    c.get(Calendar.YEAR)+" "+
                    dtf.format(now),font2);
            date.setAlignment(Element.ALIGN_RIGHT);

            document.add(date);
            document.add(Chunk.NEWLINE);

            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER
                    ,14 , BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("ETAT : bénéficiaires A.V.A :  "
                    +c2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE)
                    +" " + c2.get(Calendar.YEAR),font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);

            Stream.of("Ref dossier","Num bénéficiaire"," nom prenom" ,"Creation").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPadding(10);
                header.setPhrase(new Phrase(headerTitle ,headFont));
                table.addCell(header);
            });

            for (Ava ava:beneficiaries) {
                if (ava.getBeneficiaries()!=null){


                    for (Beneficiary beneficiary:ava.getBeneficiaries()) {
                        if (  ( beneficiary.getDateCreation().after(getFirstDateOfMonth(dd)) )
                                &&  ( beneficiary.getDateCreation().before(getLAstDateOfMonth(dd)) ) ){
                            PdfPCell refCell = new PdfPCell(new Phrase(ava.getReferenceDossierAVA()));
                            refCell.setPadding(10);
                            refCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            refCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(refCell);

                            PdfPCell idBenefCell = new PdfPCell(new Phrase(beneficiary.getIdBenef()));
                            idBenefCell.setPadding(10);
                            idBenefCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            idBenefCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(idBenefCell);

                            PdfPCell TypeCell = new PdfPCell(new Phrase(beneficiary.getNomPrenomBenef() ));
                            TypeCell.setPadding(10);
                            TypeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            TypeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(TypeCell);

                            PdfPCell ValiditeCell = new PdfPCell(new Phrase(String.valueOf(beneficiary.getDateCreation()).substring(0,10)));
                            ValiditeCell.setPadding(10);
                            ValiditeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            ValiditeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(ValiditeCell);


                        }


                    }


                }

            }
            document.add(table);
            document.close();

        }catch (DocumentException e){
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
    public static Date getLAstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
}
