package br.edu.faculdadedelta.projetoestagio.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.faculdadedelta.projetoestagio.domain.Matricula;

@Controller
public class CertificadoController {
	
	public static final String LOGO = "./src/main/resources/images/logo.png";
	public static final String TITULO = "./src/main/resources/images/titulo.png";
	
	private Matricula matricula = new Matricula();
	//private Calendar hoje = Calendar.getInstance();
	
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	
	public String createPdf() throws IOException, DocumentException {
        Document document = new Document();
        String filename = new String();
        String[] corta = matricula.getId().getCurso().getNome().split("");
        for(String c : corta) {
        	filename = filename.concat(c.trim());
		}
        PdfWriter writer = PdfWriter.getInstance(document, 
        		new FileOutputStream(deAccent(filename.toLowerCase()) + ".pdf"));
        Rotate event = new Rotate();
        
        Image logo = Image.getInstance(LOGO);
        Image titulo = Image.getInstance(TITULO);
        logo.setRotationDegrees(90);
        titulo.setAbsolutePosition(50, 260);
        logo.setAbsolutePosition(20, 20);
        titulo.setRotationDegrees(90);
        
        Paragraph texto = new Paragraph("Certificamos  que " + 
        		matricula.getId().getAluno().getNome() + 
        		",\n" + 
        		"participou  do  mini-curso/oficina " + 
        		matricula.getId().getCurso().getNome() + 
        		", \nministrado por " + 
        		matricula.getId().getCurso().getInstrutor().getNome() + 
        		"\nem " + sdf.format(matricula.getId().getCurso().getData()) +
        		",  com  carga horária  de " + matricula.getId().getCurso().getDuracao() + 
        		"  Hs.");
        
        writer.setPageEvent(event);
        document.open();
        document.add(logo);
        document.add(titulo);
        document.add(texto);
        document.close();
        
        return "listaCertificado.xhtml";
    }
	
	public class Rotate extends PdfPageEventHelper {
		 
        protected PdfNumber orientation = PdfPage.LANDSCAPE;
 
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
 
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }
	
	public String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
}
