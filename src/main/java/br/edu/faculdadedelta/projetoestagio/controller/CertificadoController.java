package br.edu.faculdadedelta.projetoestagio.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import br.edu.faculdadedelta.projetoestagio.domain.Matricula;

@Controller
@RequestMapping(value = "/listaCertificado")
public class CertificadoController {

	/*
	 * @Autowired private ServletContext servletContext;
	 */

	public static final String LOGO = "./src/main/resources/images/logo.png";
	public static final String TITULO = "./src/main/resources/images/titulo.png";
	public static final String PATH = "./src/main/webapp/resources/pdfs/";

	private Matricula matricula = new Matricula();
	private Calendar hoje = Calendar.getInstance();

	private String retorno;
	private String nomeArquivo;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String retornaFileName() {
		String filename = new String();
		String[] corta = matricula.getId().getCurso().getNome().split("");
		for (String c : corta) {
			filename = filename.concat(c.trim());
		}
		return retorno = deAccent(filename.toLowerCase()) + String.valueOf(matricula.getId().getAluno().getId())
				+ ".pdf";
	}

	public String createPdf() throws IOException, DocumentException {
		Document document = new Document();

		String arquivo = retornaFileName();

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH + arquivo));
		Rotate event = new Rotate();

		Image logo = Image.getInstance(LOGO);
		logo.setRotationDegrees(90);
		logo.setAbsolutePosition(20, 20);

		Image titulo = Image.getInstance(TITULO);
		titulo.setAbsolutePosition(50, 260);
		titulo.setRotationDegrees(90);

		Font negrito = new Font(FontFamily.UNDEFINED, 18, Font.BOLD);
		Font normal = new Font(FontFamily.UNDEFINED, 18);

		String nomeAluno = matricula.getId().getAluno().getNome() + " " + matricula.getId().getAluno().getSobrenome();

		Chunk aluno = new Chunk(nomeAluno, negrito);
		Chunk curso = new Chunk(matricula.getId().getCurso().getNome(), negrito);
		Chunk instrutor = new Chunk(matricula.getId().getCurso().getInstrutor().getNome(), negrito);
		Chunk data = new Chunk(sdf.format(matricula.getId().getCurso().getData()), negrito);
		Chunk duracao = new Chunk(matricula.getId().getCurso().getDuracao(), negrito);

		Chunk um = new Chunk("Certificamos  que ", normal);
		Chunk dois = new Chunk("\nparticipou  do  mini-curso/oficina ", normal);
		Chunk tres = new Chunk(",\nministrado por ", normal);
		Chunk quatro = new Chunk("\nem ", normal);
		Chunk cinco = new Chunk(",  com  carga horária  de ", normal);
		Chunk ponto = new Chunk(".", normal);

		Paragraph texto = new Paragraph();
		texto.add(um);
		texto.add(aluno);
		texto.add(dois);
		texto.add(curso);
		texto.add(tres);
		texto.add(instrutor);
		texto.add(quatro);
		texto.add(data);
		texto.add(cinco);
		texto.add(duracao);
		texto.add(ponto);

		hoje.getTime();
		Paragraph dataText = new Paragraph();
		Chunk local = new Chunk("Goiânia-GO, ", normal);
		Chunk dia = new Chunk(String.valueOf(hoje.get(Calendar.DAY_OF_MONTH)), normal);
		Chunk de = new Chunk(" de ", normal);
		Chunk mes = new Chunk(retornaMes(hoje.get(Calendar.MONTH)), normal);
		Chunk ano = new Chunk(String.valueOf(hoje.get(Calendar.YEAR)), normal);
		dataText.add(local);
		dataText.add(dia);
		dataText.add(de);
		dataText.add(mes);
		dataText.add(de);
		dataText.add(ano);

		writer.setPageEvent(event);
		document.open();
		PdfContentByte canvas = writer.getDirectContent();
		PdfTemplate template = canvas.createTemplate(420f, 200f);
		PdfTemplate templateData = canvas.createTemplate(400f, 20f);

		ColumnText column = new ColumnText(template);
		column.setSimpleColumn(0, 0, 400f, 180f);
		column.addElement(texto);
		column.go();

		ColumnText columnData = new ColumnText(templateData);
		columnData.setSimpleColumn(0, 0, 400f, 20f);
		columnData.addElement(dataText);
		columnData.go();

		Image textImg = Image.getInstance(template);
		textImg.setInterpolation(true);
		textImg.setRotationDegrees(90);
		textImg.setAbsolutePosition(200, 310);

		Image dataImg = Image.getInstance(templateData);
		dataImg.setInterpolation(true);
		dataImg.setRotationDegrees(90);
		dataImg.setAbsolutePosition(500, 450);

		document.add(logo);
		document.add(titulo);
		document.add(textImg);
		document.add(dataImg);
		document.close();

		nomeArquivo = retornaFileName();
		return "download.xhtml";
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

	public String retornaMes(Integer mes) {
		String nomeMes = new String();
		switch (mes) {
		case 0:
			nomeMes = "Janeiro";
			break;
		case 1:
			nomeMes = "Fevereiro";
			break;
		case 2:
			nomeMes = "Março";
			break;
		case 3:
			nomeMes = "Abril";
			break;
		case 4:
			nomeMes = "Maio";
			break;
		case 5:
			nomeMes = "Junho";
			break;
		case 6:
			nomeMes = "Julho";
			break;
		case 7:
			nomeMes = "Agosto";
			break;
		case 8:
			nomeMes = "Setembro";
			break;
		case 9:
			nomeMes = "Outubro";
			break;
		case 10:
			nomeMes = "Novembro";
			break;
		case 11:
			nomeMes = "Dezembro";
			break;
		}
		return nomeMes;
	}
}
