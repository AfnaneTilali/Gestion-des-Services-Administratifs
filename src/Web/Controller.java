package Web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import Entities.*;
import Metier.*;

@WebServlet(name="CS",urlPatterns= {"*.cc","/controller","*.php"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String role = null ;
	Etudiant re= null;
	Etudiant ree= null;
	Parent reP= null;
	Parent reeP= null;
	Prof rePr= null;
	Etudiant MEE ;
	Parent MP ;
	Parent MPP ;
	Prof MPr ;
	int cptPr=0;
	int cptE;
	Module module ;
	private IntEtudiant metierE ;
	private IntParent metierP;
	private IntProf metierPr;
	private IntOther metierO ;
	private IntAbs metierAbs;
	private IRes metierR;

	public Controller() {
		super();
		metierE = new CEtudiant();
		metierP = new CParent();
		metierPr = new CProf();
		metierAbs = new CAbs();
		metierR = new CRes();
		metierO = new COther();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path= request.getServletPath();
		HttpSession  session = request.getSession();
		System.out.println("path : " + path);

		Inscription inscription = new Inscription();
		MEtudiant Metudiant = new MEtudiant();
		MOther Mother = new MOther();

		List<Filiere> ListFiliere = new ArrayList<Filiere>();
		ListFiliere = metierO.getAllFiliere();
		Mother.setListfili(ListFiliere);

		List<Etudiant> ListEtudiant = new ArrayList<Etudiant>();
		ListEtudiant = metierE.getAllEtudiant();
		Metudiant.setListEtud(ListEtudiant);

		List<Inscription> ListInsc = new ArrayList<Inscription>();
		ListInsc = metierO.getAllInscription();
		Mother.setListInsc(ListInsc);

		List<Equipement> Listequipement = new ArrayList<Equipement>();
		Listequipement = metierO.getAllEquipement();
		Mother.setListEquipement(Listequipement);

		List<Module> ListModule = new ArrayList<Module>();
		ListModule = metierO.getAllModule();
		Mother.setListModule(ListModule);

		List<PresProf> ListPresProf = new ArrayList<PresProf>();
		ListPresProf = metierO.getAllPresProf();
		Mother.setListPresProf(ListPresProf);

		List<Salle> ListSalle = new ArrayList<Salle>();
		ListSalle = metierO.getAllSalle();
		Mother.setListSalle(ListSalle);

		List<Semestre> ListSemestre = new ArrayList<Semestre>();
		ListSemestre = metierO.getAllSemestre();
		Mother.setListSemestre(ListSemestre);

		List<Parent> ListParent = new ArrayList<Parent>();
		ListParent = metierP.getAllParent();
		MParent Mparent = new MParent();
		Mparent.setListParent(ListParent);

		MAbs Mabs = new MAbs();
		List<Absence> ListAbs = new ArrayList<Absence>();
		ListAbs = metierAbs.getAllAbs();
		Mabs.setListAbs(ListAbs);

		List<AbsSupp> ListAbsSupp = new ArrayList<AbsSupp>();
		ListAbsSupp = metierAbs.getAllAbsSupp();
		Mabs.setListAbsSupp(ListAbsSupp);

		List<Prof> ListProf = new ArrayList<Prof>();
		ListProf = metierPr.getAllProf();
		MProf Mprof = new MProf();
		Mprof.setListProf(ListProf);	
		if(path.equals("/BachMek.php")) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		else if(path.equals("/login.php")) {
			String Email = request.getParameter("email");
			String Password = request.getParameter("password"); 
			System.out.println("Email : " + Email);
			System.out.println("Password : " + Password);
			Login metier = new Login();
			// Validation du champ email. 
			try {
				role =  metier.validation(Password, Email);
				System.out.println("role  =  " + role); 
			} catch ( Exception e ) {
				metier.setErreur( "err", e.getMessage() );
			}
			System.out.println("metier.getErreurs() : " + metier.getErreurs());
			// Initialisation du résultat global de la validation. 
			if ( metier.getErreurs().isEmpty() ) {
				session.setAttribute("roleU",role);
				System.out.println();
			} else {
				session.setAttribute("roleU",null);
				request.setAttribute("Login", metier );
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			if(session.getAttribute("roleU").equals("admin"))	{
				// you have to add the rquest attribute
				request.setAttribute("MEtudiant", Metudiant );
				request.getRequestDispatcher("Admin/Home.jsp").forward(request, response);
			}
			else if(session.getAttribute("roleU").equals("Prof")) {
				request.getRequestDispatcher("Prof/Home.jsp").forward(request, response);
			}else if(session.getAttribute("roleU").equals("Etudiant")) {
				request.getRequestDispatcher("Etudiant/Home.jsp").forward(request, response);
			}
		}
		else if(path.equals("/deconnexion.php")) {
			session = request.getSession();
			session.invalidate();
			request.getRequestDispatcher("Login.jsp").forward(request,response);
		}
		else if(path.contentEquals("/HomeA.php")) {
			request.getRequestDispatcher("Admin/Home.jsp").forward(request, response);
		}else if(path.contentEquals("/HomePr.php")) {
			request.getRequestDispatcher("Prof/Home.jsp").forward(request, response);
		}else if(path.contentEquals("/HomeE.php")) {
			request.getRequestDispatcher("Etudiant/Home.jsp").forward(request, response);
		}
		else if(path.contentEquals("/.php")) {
			request.getRequestDispatcher(".jsp").forward(request, response);
		}else if(path.contentEquals("/.php")) {
			request.getRequestDispatcher(".jsp").forward(request, response);
		}else if(path.contentEquals("/.php")) {
			request.getRequestDispatcher(".jsp").forward(request, response);
		}else if(path.contentEquals("/.php")) {
			request.getRequestDispatcher(".jsp").forward(request, response);
		}
		else if(path.contentEquals("/ListAllEtudiant.php")) {
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Admin/List/ListAllEtudiant.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllProf.php")) {
			request.setAttribute("Mprof",Mprof);
			request.getRequestDispatcher("Admin/List/ListAllProf.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllParent.php")) {
			request.setAttribute("Mparent",Mparent);
			request.getRequestDispatcher("Admin/List/ListAllParent.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllFiliere.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllFiliere.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllSemestre.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllSemestre.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllModule.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllModule.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllInscription.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllInscription.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllInscription.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllInscription.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllSalle.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllSalle.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllEquipement.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllEquipement.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllPresProf.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListAllPresProf.jsp").forward(request, response);
		}else if(path.contentEquals("/ListEtudiantPar.php")) {
			request.setAttribute("Mother",Mother);
			request.getRequestDispatcher("Admin/List/ListEtudiantPar.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAbsSupp.php")) {
			request.setAttribute("Mabs",Mabs);
			request.getRequestDispatcher("Admin/List/ListAbsSupp.jsp").forward(request, response);
		}else if(path.contentEquals("/ListAllAbs.php")) {
			request.setAttribute("Mabs",Mabs);
			request.getRequestDispatcher("Admin/Abs/ListAllAbs.jsp").forward(request, response);
		}else if(path.contentEquals("/ListEtudiantParAll.php")) {

			String filiere = request.getParameter("FiliereE");
			System.out.println("Filiere : " + filiere);
			String Semestre = request.getParameter("Semestre");
			int semestre;
			if(Semestre.equals("")) {
				semestre = 0;
			}else semestre = Integer.parseInt(Semestre);
			System.out.println("Semestre : " + semestre);
			String NbrAbs = request.getParameter("NbrAbs");
			int nbrAbs;
			if(NbrAbs.equals("")) {
				nbrAbs = 0;
			}else nbrAbs = Integer.parseInt(NbrAbs);
			System.out.println("NbrAbs : "+nbrAbs);
			String annUniv = request.getParameter("AnnUniv");
			System.out.println("AnnUniv : " + annUniv);

			if((semestre!=0)) {
				List<Etudiant> listEtudParS = new ArrayList<Etudiant>();
				listEtudParS = metierE.getEtudiantParSemestre(semestre);
				System.out.println("listEtudParS : "+ listEtudParS);
				Metudiant.setListEtud(listEtudParS);
			}
			if(!filiere.equals("")) {
				List<Etudiant> listEtudParF = new ArrayList<Etudiant>();
				listEtudParF = metierE.getEtudiantParFiliere(filiere);
				System.out.println("listEtudParF : "+ listEtudParF);
				Metudiant.setListEtud(listEtudParF);
			}
			if(nbrAbs!=0) {
				List<Etudiant> listEtudParNbrAbs = new ArrayList<Etudiant>();
				listEtudParNbrAbs = metierE.getEtudiantParNbrAbs(nbrAbs);
				System.out.println("listEtudParNbrAbs : "+ listEtudParNbrAbs);
				Metudiant.setListEtud(listEtudParNbrAbs);
			}
			if(!annUniv.equals("")) {
				List<Etudiant> listEtudParAnnUniv = new ArrayList<Etudiant>();
				listEtudParAnnUniv = metierE.getEtudiantParAnnUniv(annUniv);
				System.out.println("listEtudParAnnUniv : "+ listEtudParAnnUniv);
				Metudiant.setListEtud(listEtudParAnnUniv);
			}
			if(!filiere.equals("")&&(semestre!=0)) {
				List<Etudiant> listEtudParFS = new ArrayList<Etudiant>();
				listEtudParFS = metierE.getEtudiantParFiliereSemestre(filiere,semestre);
				System.out.println("listEtudParFS : "+ listEtudParFS);
				Metudiant.setListEtud(listEtudParFS);
			}
			if(!filiere.equals("")&&(nbrAbs!=0)) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParFiliereNbrAbs(filiere,nbrAbs);
				System.out.println("listEtudParFNbrAbs : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}
			if(!filiere.equals("")&&(!annUniv.equals(""))) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParFiliereAnnUniv(filiere,annUniv);
				System.out.println("listEtudParFAnnUniv : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}
			if(!filiere.equals("")&&(!annUniv.equals(""))&&(semestre!=0)) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParFiliereSemestreAnnUniv(filiere,semestre,annUniv);
				System.out.println("listEtudParF S AnnUniv : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}
			if(!filiere.equals("")&&(nbrAbs!=0)&&(semestre!=0)) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParFiliereSemestreNbrAbs(filiere,semestre,nbrAbs);
				System.out.println("listEtudParF S NbrAbs : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}
			if(!filiere.equals("")&&(nbrAbs!=0)&&(!annUniv.equals(""))){
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParFiliereAnnUnivNbrAbs(filiere,nbrAbs,annUniv);
				System.out.println("listEtudParF AnnUniv NbrAbs : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}if((!annUniv.equals(""))&&(semestre!=0)) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParSemestreAnneUniv(semestre,annUniv);
				System.out.println("listEtudPar S AnnUniv : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}if((nbrAbs!=0)&&(semestre!=0)) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParSemestreNbrAbs(semestre,nbrAbs);
				System.out.println("listEtudPar S NbrAbs : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}if(!annUniv.equals("")&&(nbrAbs!=0)&&(semestre!=0)) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParSemestreAnnUnivNbrAbs(semestre,annUniv,nbrAbs);
				System.out.println("listEtudPar S AnnUniv NbrAbs : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}if((nbrAbs!=0)&&!annUniv.equals("")) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParAnnUnivNbrAbs(nbrAbs,annUniv);
				System.out.println("listEtudPar AnnUniv NbrAbs : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}if(!filiere.equals("")&&!annUniv.equals("")&&(nbrAbs!=0)&&(semestre!=0)) {
				List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
				listEtudiants = metierE.getEtudiantParFiliereSemestreAnnUnivNbrAbs(filiere,semestre,annUniv,nbrAbs);
				System.out.println("listEtudPar F S AnnUniv NbrAbs : "+ listEtudiants);
				Metudiant.setListEtud(listEtudiants);
			}
			request.setAttribute("MEtudiant",Metudiant);			
			request.getRequestDispatcher("Admin/List/ListEtudiantParAll.jsp").forward(request, response);
		}else if(path.equals("/supprimerAbsList.php")){
			int idAbs = Integer.parseInt(request.getParameter("IdAbs"));
			System.out.println("idAbs : " + idAbs);
			Absence abs = metierAbs.getAbsId(idAbs);
			System.out.println(abs);
			metierAbs.addAbsSupp(abs);
			//	metierAbs.getAllAbs();
			metierAbs.SupprimerAbs(idAbs);
			ListAbs = metierAbs.getAllAbs();
			Mabs.setListAbs(ListAbs);
			ListAbsSupp = metierAbs.getAllAbsSupp();
			Mabs.setListAbsSupp(ListAbsSupp);
			request.setAttribute("Mabs", Mabs);
			request.getRequestDispatcher("Admin/Abs/ListAllAbs.jsp").forward(request,response);
		}else if(path.equals("/supprimerParentList.php")){
			String CniP = request.getParameter("CNIP");
			System.out.println("CniP : " + CniP);
			Etudiant e = metierE.getEtudiantParParent(CniP);
			String CniE = e.getCni();
			metierE.SupprimerEtudiant(CniE);
			metierP.SupprimerParent(CniP);
			ListParent=metierP.getAllParent();
			Mparent.setListParent(ListParent);
			request.setAttribute("Mparent", Mparent);
			request.getRequestDispatcher("Admin/List/ListAllParent.jsp").forward(request,response);
		}else if(path.equals("/supprimerProfList.php")){
			String CniPr = request.getParameter("CNIPr");
			System.out.println("CniPr : " + CniPr);
			metierPr.SupprimerProf(CniPr);
			ListProf=metierPr.getAllProf();
			Mprof.setListProf(ListProf);
			request.setAttribute("Mprof", Mprof);
			request.getRequestDispatcher("Admin/List/ListAllProf.jsp").forward(request,response);
		}else if(path.equals("/supprimerFiliereList.php")){
			String NomF = request.getParameter("NomF");
			System.out.println("NomF : " + NomF);
			metierO.SupprimerFiliere(NomF);
			ListFiliere=metierO.getAllFiliere();
			Mother.setListfili(ListFiliere);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllFiliere.jsp").forward(request,response);
		}else if(path.equals("/supprimerEquipementList.php")){
			int IdEq = Integer.parseInt(request.getParameter("IdEquipement"));
			System.out.println("IdEquipement : " + IdEq);
			metierO.SupprimerEquipement(IdEq);
			Listequipement=metierO.getAllEquipement();
			Mother.setListEquipement(Listequipement);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllEquipement.jsp").forward(request,response);
		}else if(path.equals("/supprimerSalleList.php")){
			int NumS = Integer.parseInt(request.getParameter("NumS"));
			System.out.println("Num Salle : " + NumS);
			metierO.SupprimerSalle(NumS);
			ListSalle=metierO.getAllSalle();
			Mother.setListSalle(ListSalle);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllSalle.jsp").forward(request,response);
		}else if(path.equals("/supprimerSemestreList.php")){
			String numS =request.getParameter("NumS");
			int NumS = Integer.parseInt(numS);
			System.out.println("Num Semestre : " + NumS);
			String NomF = request.getParameter("NomF");
			System.out.println("Nom Fil : "+ NomF);
			metierO.SupprimerSemestre(NumS,NomF);
			ListSemestre=metierO.getAllSemestre();
			Mother.setListSemestre(ListSemestre);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllSemestre.jsp").forward(request,response);
		}else if(path.equals("/supprimerInscriptionList.php")){
			int IdInsc = Integer.parseInt(request.getParameter("IdInsc"));
			System.out.println("Id Insc : " + IdInsc);
			metierO.SupprimerInscription(IdInsc);
			ListInsc=metierO.getAllInscription();
			Mother.setListInsc(ListInsc);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllInscription.jsp").forward(request,response);
		}else if(path.equals("/supprimerModuleList.php")){
			String NomM = request.getParameter("NomM");
			System.out.println("NomM : " + NomM);
			String NomF = request.getParameter("NomF");
			System.out.println("NomF : " + NomF);
			metierO.SupprimerModule(NomM,NomF);
			ListModule=metierO.getAllModule();
			Mother.setListModule(ListModule);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllModule.jsp").forward(request,response);
		}else if(path.equals("/supprimerEtudiantList.php")){
			String CNIE = request.getParameter("CNIE");
			System.out.println("CNIE : " + CNIE);
			metierE.SupprimerEtudiant(CNIE);
			ListEtudiant=metierE.getAllEtudiant();
			Metudiant.setListEtud(ListEtudiant);
			request.setAttribute("MEtudiant", Metudiant);
			request.getRequestDispatcher("Admin/List/ListAllEtudiant.jsp").forward(request,response);
		}
		else if(path.contentEquals("/ModifierEtudiant.php")) {
			String CNI = request.getParameter("CNI");
			System.out.println("CNI Etud : " + CNI);
			Etudiant etudiant = new Etudiant();
			etudiant = metierE.getEtudiant(CNI);
			Metudiant.setEtudiant(etudiant);
			request.setAttribute("MEtudiant", Metudiant);
			System.out.println(" etudiant : " + Metudiant.getEtudiant() );
			System.out.println("liste Filiere : " + Mother.getListfili());
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Modification/ModifierEtudiant.jsp").forward(request, response);		
		}else if(path.equals("/updateE.php")){
			System.out.println("update controller");
			System.out.println("re  =  " + re); 
			Etudiant ME = new Etudiant();
			ME = metierE.getEtudiant(re.getCni());
			System.out.println("ME : "+ ME);
			String Nom = request.getParameter("Nom");
			String Prenom = request.getParameter("Prenom");
			String CIN = request.getParameter("CIN");
			System.out.println(CIN);
			//	String CNI = request.getParameter("CNI");
			String Adress = request.getParameter("Adresse");
			String DateN = request.getParameter("DateN");
			String VilleN = request.getParameter("VilleN");
			String Sexe = request.getParameter("Sexe");
			String Filiere = request.getParameter("Filiere");
			String Email = request.getParameter("Email");
			int Phone = Integer.parseInt(request.getParameter("Phone"));
			String AnneeUniv = request.getParameter("AnneeUniv");
			String Country = request.getParameter("Country");
			ME.setNom(Nom);
			ME.setPrenom(Prenom);
			ME.setCin(CIN);
			//	ME.setCni(CNI);
			ME.setAdresse(Adress);
			ME.setDateN(DateN);
			ME.setVilleN(VilleN);
			ME.setSexe(Sexe);
			ME.setFiliere(Filiere);
			ME.setEmail(Email);
			ME.setPhone(Phone);
			ME.setNomF(Filiere);
			ME.setCountry(Country);
			ME.setAnneeUniv(AnneeUniv);
			metierE.ModifierInfoEtudiant(ME);
			System.out.println("New ME : "+ ME);
			ListEtudiant = metierE.getAllEtudiant();
			Metudiant.setListEtud(ListEtudiant);
			request.setAttribute("MEtudiant", Metudiant);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllEtudiant.jsp").forward(request, response);
		}
		else if(path.contentEquals("/ModifierParent.php")) {
			String CNIp = request.getParameter("CNIP");
			System.out.println("CNI parent : " + CNIp);
			Parent parent = new Parent();
			parent = metierP.getParent(CNIp);
			Mparent.setParent(parent);
			System.out.println(" parent : " + Mparent.getParent() );
			request.setAttribute("Mparent", Mparent);
			request.getRequestDispatcher("Admin/Modification/ModifierParent.jsp").forward(request, response);
		}else if(path.equals("/updateP.php")){
			System.out.println("update Parent controller");
			System.out.println("reeP  =  " + reeP); 
			MPP = metierP.getParent(reeP.getCniP());
			System.out.println("MPP : "+ MPP);
			String Nom = request.getParameter("NomP");
			String Prenom = request.getParameter("PrenomP");
			String CNI = request.getParameter("CNIP");
			String Email = request.getParameter("EmailP");
			int Phone = Integer.parseInt(request.getParameter("Phone"));
			MPP.setNomP(Nom);
			MPP.setPrenomP(Prenom);
			MPP.setCniP(CNI);
			MPP.setEmailP(Email);
			MPP.setPhone(Phone);
			metierP.ModifierInfoParent(MPP);
			System.out.println("New MPP : "+ MPP);
			ListParent = metierP.getAllParent();
			Mparent.setListParent(ListParent);
			request.setAttribute("Mparent", Mparent);
			request.getRequestDispatcher("Admin/List/ListAllParent.jsp").forward(request, response);
			System.out.println("End update Parent E controller");
		}else if(path.equals("/modifParentList.php")) {
			System.out.println("hhh");
			String cni = request.getParameter("CNIP");
			System.out.println("Cni : " + cni);
			Parent reP = metierP.getParent(cni);
			Mparent.setParent(reP);
			request.setAttribute("Mparent", Mparent );
			request.getRequestDispatcher("Admin/Modification/ModifierParent.jsp").forward(request,response);
		}else if(path.equals("/ModificationEtudList.php")) {
			System.out.println("hhh");
			String cni = request.getParameter("cnie");
			System.out.println("Cni : " + cni);
			Etudiant reE = metierE.getEtudiant(cni);
			Metudiant.setEtudiant(reE);
			request.setAttribute("MEtudiant", Metudiant );
			request.setAttribute("Mother", Mother );
			request.getRequestDispatcher("Admin/Modification/ModifierEtudiant.jsp").forward(request,response);

		}else if(path.equals("/updateEtudiantList.php")){
			String Nom = request.getParameter("Nom");
			String Prenom = request.getParameter("Prenom");
			String CIN = request.getParameter("CIN");
			String CNI = request.getParameter("CNI");
			String Adress = request.getParameter("Adresse");
			String DateN = request.getParameter("DateN");
			String VilleN = request.getParameter("VilleN");
			String Sexe = request.getParameter("Sexe");
			String Filiere = request.getParameter("Filiere");
			String Email = request.getParameter("Email");
			int Phone = Integer.parseInt(request.getParameter("Phone"));
			Etudiant etudiant = new Etudiant();
			etudiant.setNom(Nom);
			etudiant.setPrenom(Prenom);
			etudiant.setCni(CNI);
			etudiant.setEmail(Email);
			etudiant.setPhone(Phone);
			etudiant.setCin(CIN);
			etudiant.setAdresse(Adress);
			etudiant.setDateN(DateN);
			etudiant.setVilleN(VilleN);
			etudiant.setSexe(Sexe);
			etudiant.setFiliere(Filiere);
			metierE.ModifierInfoEtudiant(etudiant);
			System.out.println("New parent : "+ etudiant);
			ListEtudiant = metierE.getAllEtudiant();
			Metudiant.setListEtud(ListEtudiant);
			request.setAttribute("MEtudiant", Metudiant);
			request.getRequestDispatcher("Admin/List/ListAllEtudiant.jsp").forward(request, response);
			System.out.println("End update Etudiant par list controller");
		}


























		else if(path.contentEquals("/InscriptionProf.php")) {
			request.setAttribute("Mprof",Mprof);
			request.getRequestDispatcher("Prof/InscriptionProf.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterProf.php")) {
			request.setAttribute("Mprof",Mprof);
			request.getRequestDispatcher("Admin/Ajout/AjouterProf.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterEtudiant.php")) {
			request.setAttribute("Mother", Mother);
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Admin/Ajout/AjouterEtudiant.jsp").forward(request, response);
		}else if(path.equals("/add.php")) {
			System.out.println("add Controller");
			String Nom = request.getParameter("Nom");
			String AnneeUniv = request.getParameter("AnneeUniv");
			String Prenom = request.getParameter("Prenom");
			String CIN = request.getParameter("CIN");
			String CNI = request.getParameter("CNI");
			String Adress = request.getParameter("Adresse");
			String DateN = request.getParameter("DateN");
			String VilleN = request.getParameter("VilleN");
			String Sexe = request.getParameter("Sexe");
			String Filiere = request.getParameter("Filiere");
			String country = request.getParameter("Country");
			String Email = request.getParameter("Email");
			int Phone = Integer.parseInt(request.getParameter("Phone"));
			int numS = Integer.parseInt(request.getParameter("Semestre"));
			String CniP  = request.getParameter("CniP");
			final Part imgFilePart = request.getPart("photo");
			InputStream image = null;
			// obtains the upload file part in this multipart request
			if (imgFilePart != null) {
				// prints out some information for debugging
				System.out.println("name : "+imgFilePart.getName());
				System.out.println("size : " + imgFilePart.getSize());
				System.out.println("type : " + imgFilePart.getContentType());
				// obtains input stream of the upload file
				image = imgFilePart.getInputStream();
			}
			final Part filePart = request.getPart("file");
			InputStream pdfFileBytes = null;
			if (!filePart.getContentType().equals("application/pdf"))
			{
				System.out.println("<br/> Invalid File");
				return;
			}
			pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
			final byte[] bytes = new byte[pdfFileBytes.available()];
			pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
			if (pdfFileBytes != null) {
				pdfFileBytes.close();
			}
			String DateIns =request.getParameter("DateIns");
			Etudiant etudiant = new Etudiant();
			etudiant.setNom(Nom);
			etudiant.setPrenom(Prenom);
			etudiant.setCin(CIN);
			etudiant.setCni(CNI);
			etudiant.setAdresse(Adress);
			etudiant.setDateN(DateN);
			etudiant.setVilleN(VilleN);
			etudiant.setSexe(Sexe);
			etudiant.setEmail(Email);
			etudiant.setPhone(Phone);
			etudiant.setFiliere(Filiere);
			etudiant.setDateN(DateN);
			etudiant.setBytes(bytes);
			etudiant.setNomF(Filiere);
			etudiant.setCniP(CniP);
			etudiant.setAnneeUniv(AnneeUniv);
			etudiant.setCountry(country);
			etudiant.setImage(image);
			inscription.setDateInscrtiption(DateIns);
			inscription.setCniE(CNI);
			inscription.setNomF(Filiere);
			inscription.setNumS(numS);
			String NomP = request.getParameter("NomP");
			String PrenomP = request.getParameter("PrenomP");
			String CNIP = request.getParameter("CNIP");
			String EmailP = request.getParameter("EmailP");
			int PhoneP = Integer.parseInt(request.getParameter("Phone"));
			Parent parent = new Parent();
			parent.setNomP(NomP);
			parent.setPrenomP(PrenomP);
			parent.setCniP(CNIP);
			parent.setEmailP(EmailP);
			parent.setPhone(PhoneP);
			etudiant.setCniP(CNIP);
			metierP.addParent(parent);
			metierE.addEtudiant(etudiant,inscription);
			ListParent = metierP.getAllParent();
			Mparent.setListParent(ListParent);
			request.setAttribute("Mparent", Mparent);
			ListEtudiant = metierE.getAllEtudiant();
			Metudiant.setListEtud(ListEtudiant);
			request.setAttribute("MEtudiant", Metudiant);
			request.getRequestDispatcher("Admin/List/ListAllEtudiant.jsp").forward(request, response);
		}else if(path.contentEquals("/RechercheEtudiant.php")) {
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Admin/Recherche/RechercheEtudiant.jsp").forward(request, response);
		}else if(path.equals("/SearchEtudiant.php")){
			String CNI = request.getParameter("CNIE");
			System.out.println("CNI : " + CNI);
			CEtudiant metier = new CEtudiant();
			re =  metier.RechercheEtudiant(CNI);
			Metudiant.setEtudiant(re);
			String CNIP ;
			if(re==null) {
				request.getRequestDispatcher("Admin/MenuR/RNE.jsp").forward(request, response);
			}else{
				CNIP= re.getCniP();
				CParent metierP = new CParent();
				reP =  metierP.RechercheParent(CNIP);
				Mparent.setParent(reP);
				System.out.println("Metudiant : "+ Metudiant.getEtudiant());
				request.setAttribute("MEtudiant", Metudiant );
				System.out.println("Mparent : "+ Mparent.getParent());
				request.setAttribute("Mparent", Mparent );
				request.getRequestDispatcher("Admin/MenuR/RE.jsp").forward(request, response);
			}
		}else if(path.contentEquals("/AjouterFil.php")) {
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Ajout/AjouterFiliere.jsp").forward(request, response);
		}else if(path.equals("/addF.php")) {
			System.out.println("add F Controller");
			String Nom = request.getParameter("NomF");
			String Chef = request.getParameter("Chef");
			int NbrModule = Integer.parseInt(request.getParameter("NbrModule"));
			int NbrSemestre = Integer.parseInt(request.getParameter("NbrSemestre"));
			Filiere filiere = new Filiere();
			filiere.setNomF(Nom);
			filiere.setNbrModules(NbrModule);
			filiere.setNbrSemestre(NbrSemestre);
			filiere.setChef(Chef);
			metierO.addFiliere(filiere);
			ListFiliere = metierO.getAllFiliere();
			Mother.setListfili(ListFiliere);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllFiliere.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterSalle.php")) {
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Ajout/AjouterSalle.jsp").forward(request, response);
		}else if(path.equals("/addSalle.php")) {
			int NumS = Integer.parseInt(request.getParameter("NumS"));
			int Etage = Integer.parseInt(request.getParameter("Etage"));
			Salle salle = new Salle();
			salle.setEtageS(Etage);
			salle.setNumS(NumS);
			metierO.addSalle(salle);
			ListSalle = metierO.getAllSalle();
			Mother.setListSalle(ListSalle);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllSalle.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterProf.php")) {
			request.setAttribute("Mprof", Mprof);
			request.getRequestDispatcher("Admin/Ajout/AjouterProf.jsp").forward(request, response);
		}else if(path.equals("/addPr.php")) {
			System.out.println("add Prof Controller");
			String Nom = request.getParameter("NomPr");
			String Prenom = request.getParameter("PrenomPr");
			String CNI = request.getParameter("CNIPr");
			String Email = request.getParameter("EmailPr");
			int Phone = Integer.parseInt(request.getParameter("PhonePr"));
			Prof prof = new Prof();
			prof.setNomPr(Nom);
			prof.setPrenomPr(Prenom);
			prof.setCniPr(CNI);
			prof.setEmailPr(Email);
			prof.setPhonePr(Phone);
			metierPr.addProf(prof);
			ListProf = metierPr.getAllProf();
			Mprof.setListProf(ListProf);
			request.setAttribute("Mprof", Mprof);
			request.getRequestDispatcher("Admin/List/ListAllProf.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterSemestre.php")) {
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Ajout/AjouterSemestre.jsp").forward(request, response);
		}else if(path.equals("/addSem.php")) {
			System.out.println("add Sem Controller");
			String Nom = request.getParameter("NomF");
			int NbrModule = Integer.parseInt(request.getParameter("NbrModule"));
			int NumSemestre = Integer.parseInt(request.getParameter("NumS"));
			Semestre semestre = new Semestre();
			semestre.setNomF(Nom);
			semestre.setNbrModule(NbrModule);
			semestre.setNumS(NumSemestre);
			metierO.addSemestre(semestre);
			ListSemestre = metierO.getAllSemestre();
			Mother.setListSemestre(ListSemestre);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllSemestre.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterEquipement.php")) {
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Ajout/AjouterEquipement.jsp").forward(request, response);
		}else if(path.equals("/AddEquipement.php")) {
			System.out.println("add Sem Controller");
			String Nom = request.getParameter("NomEq");
			int NumEq = Integer.parseInt(request.getParameter("NumEq"));
			int NumSemestre = Integer.parseInt(request.getParameter("NumS"));
			Equipement eq = new Equipement();
			eq.setNomEq(Nom);
			eq.setNumEq(NumEq);
			eq.setNumS(NumSemestre);
			metierO.addEquipement(eq);
			Listequipement = metierO.getAllEquipement();
			Mother.setListEquipement(Listequipement);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllEquipement.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterModule.php")) {
			request.setAttribute("Mother", Mother);
			request.setAttribute("Mprof", Mprof);			
			request.getRequestDispatcher("Admin/Ajout/AjouterModule.jsp").forward(request, response);
		}else if(path.equals("/AddModule.php")) {
			System.out.println("add Sem Controller");
			String NomF = request.getParameter("NomF");
			String NomM = request.getParameter("NomM");
			String NomPr = request.getParameter("NomPr");
			int seuille = Integer.parseInt(request.getParameter("Seuille"));
			int NumSemestre = Integer.parseInt(request.getParameter("NumS"));
			Module mod = new Module();
			mod.setNomF(NomF);
			mod.setNomM(NomM);
			mod.setSeuille(seuille);
			mod.setNumS(NumSemestre);
			String CniPr =metierPr.getCniPr(NomPr);
			mod.setCniPr(CniPr);			
			metierO.addModule(mod);
			ListModule = metierO.getAllModule();
			Mother.setListModule(ListModule);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllModule.jsp").forward(request, response);
		}else if(path.contentEquals("/AjouterInscription.php")) {
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Ajout/AjouterInscription.jsp").forward(request, response);
		}else if(path.equals("/AddInsc.php")) {
			System.out.println("add Insc Controller");
			String Nom = request.getParameter("NomF");
			String Date = request.getParameter("Date");
			String CniE = request.getParameter("CNIE");
			int NumS = Integer.parseInt(request.getParameter("NumS"));

			Inscription insc = new Inscription();
			insc.setCniE(CniE);
			insc.setDateInscrtiption(Date);
			insc.setNomF(Nom);
			insc.setNumS(NumS);
			metierO.addInscription(insc);
			ListInsc = metierO.getAllInscription();
			Mother.setListInsc(ListInsc);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/List/ListAllInscription.jsp").forward(request, response);
		}
		else if(path.contentEquals("/UpListParent.php")) {
			String masterPath = request.getServletContext().getRealPath("/WEB-INF/GB.pdf" ); 
			response.setContentType( "application/pdf" );

			try ( PdfReader reader = new PdfReader( masterPath ); 
					PdfWriter writer = new PdfWriter( response.getOutputStream() ); 
					PdfDocument document = new PdfDocument( reader, writer ) ) {
				PdfPage page = document.getPage( 1 );
				PdfCanvas canvas = new PdfCanvas( page );
				FontProgram fontProgram =FontProgramFactory.createFont();
				PdfFont font = PdfFontFactory.createFont(fontProgram, "utf-8", true );
				canvas.setFontAndSize( font, 12 );
				canvas.beginText(); int top = 600;
				canvas.setTextMatrix( 250, 680 );
				canvas.showText( "List All Parent" );
				canvas.setTextMatrix( 88, 630 );
				canvas.showText( "Nom");
				canvas.setTextMatrix( 150, 630 );
				canvas.showText(" Prenom");
				canvas.setTextMatrix( 215, 630 );
				canvas.showText(" CNI");
				canvas.setTextMatrix( 280, 630 );
				canvas.showText(" Email");
				canvas.setTextMatrix( 440, 630 );
				canvas.showText(" Phone");
				for(Parent p : ListParent ) { 
					// p =Mparent.getParent();
					canvas.setTextMatrix( 88, top );
					canvas.showText( "" +p.getNomP());
					canvas.setTextMatrix( 150, top );
					canvas.showText(p.getPrenomP()); 
					canvas.setTextMatrix( 215, top ); 
					canvas.showText(p.getCniP()); 
					canvas.setTextMatrix( 280, top );
					canvas.showText(p.getEmailP() );
					String Phone = String.valueOf(p.getPhone());
					canvas.setTextMatrix( 440, top ); canvas.showText(Phone); top -= 20;
				}
				canvas.endText();
			}
		}else if(path.contentEquals("/GetFiliereSemestre.php")) {
			request.setAttribute("MEtudiant",Metudiant);
			request.setAttribute("MProf",Mprof);
			request.setAttribute("Mabs",Mabs);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Abs/GetFiliereSemestre.jsp").forward(request, response);
		}else if(path.contentEquals("/ListeEtudiantParFiliereSemestre.php")) {
			request.setAttribute("Mabs",Mabs);
			request.setAttribute("Mprof",Mprof);
			request.setAttribute("MEtudiant",Metudiant);
			System.out.println("Start List Etud Par Fil Semestre controller");
			String filieree = request.getParameter("FiliereE");
			int Semestree = Integer.parseInt(request.getParameter("Semestre"));
			System.out.println("Filiere : " + filieree);
			System.out.println("Semestree : " + Semestree);
			List<Etudiant> listEtudParFS = new ArrayList<Etudiant>();
			listEtudParFS = metierE.getEtudiantParFiliereSemestre(filieree, Semestree);
			Metudiant.setListEtud(listEtudParFS);
			ListModule = metierPr.getModuleParFS(filieree,Semestree);
			Mother.setListModule(ListModule);	
			module = metierAbs.getModuleParFS(filieree, Semestree);
			System.out.println("Module par Fil et Sem : "+ module);
			System.out.println("ListModule : "+ ListModule);
			Metudiant.setFiliere(filieree);
			Metudiant.setNumS(Semestree);
			request.setAttribute("Mother", Mother);
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Admin/Abs/ListeEtudiantParFiliereSemestre.jsp").forward(request, response);
		}else if(path.contentEquals("/AddAbs.php")) {
			String jspCible ="";
			System.out.println("add Abs Controller");
			String fil = request.getParameter("filiere");
			int numS = Integer.parseInt(request.getParameter("semestre"));
			String nomM = request.getParameter("Module");
			System.out.println("Module : "+ nomM);
			int Cren = Integer.parseInt(request.getParameter("creneau"));
			String Prof = request.getParameter("Profs");
			String CniPr = metierPr.getCniPr(Prof) ;
			String [] NomE = request.getParameterValues("NomE");
			System.out.println("NomE : " + NomE );
			//	String [] PrenomE = request.getParameterValues("PrenomE");
			String [] CniE = request.getParameterValues("CniE");
			//	String [] DateN = request.getParameterValues("DateN");
			String [] absence = request.getParameterValues("absence");
			String date = request.getParameter("DateAbs");
			module.setNomM(nomM);
			cptPr = metierAbs.getNbrPr(fil, numS,Prof);
			System.out.println("Nbr d'heure de la pres du prof " + Prof + " est : "+ cptPr);
			cptPr++;
			module.setNbrPres(cptPr);
			System.out.println("Nbr d'heure de la pres du prof nv " + Prof + " est : "+ module.getNbrPres());
			metierAbs.addPrs(module);
			SimpleDateFormat D = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date datee = null;
			try {
				datee = D.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			java.sql.Date sqlDate = new java.sql.Date(datee.getTime());
			PresProf presProf = new PresProf();
			presProf.setDatePres(sqlDate);
			presProf.setCniPr(CniPr);
			presProf.setNomM(nomM);
			presProf.setNomF(fil);
			presProf.setNumS(numS);
			switch(Cren){
			case 1: { presProf.setCren1(true); break;}
			case 2: { presProf.setCren2(true); break;}
			case 3: { presProf.setCren3(true); break;}
			case 4: { presProf.setCren4(true); break;}
			}
			metierO.addPresProf(presProf);
			ListPresProf = metierO.getAllPresProf();
			Mother.setListPresProf(ListPresProf);
			Etudiant e;
			for(int i=0;i<CniE.length;i++) {
				Absence abs = new Absence();
				abs.setDateAbs(sqlDate);				
				switch(Cren){
				case 1: { abs.setCren1(true); break;}
				case 2: { abs.setCren2(true); break;}
				case 3: { abs.setCren3(true); break;}
				case 4: { abs.setCren4(true); break;}
				}

				abs.setCniE(CniE[i]);
				abs.setCniPr(CniPr);
				abs.setNomM(nomM);
				if(absence[i].equals("Absent")) {
					metierAbs.addAbs(abs);
					ListAbs= metierAbs.getAllAbs();
					Mabs.setListAbs(ListAbs);
					e = metierE.getEtudiant(CniE[i]);
					System.out.println("Etudiant : "+ e);
					cptE = e.getNbrAbs();
					System.out.println("cptE : "+ cptE);
					cptE++;
					e.setNbrAbs(cptE);
					System.out.println("new cptE : "+ cptE);
					metierE.ModifierInfoEtudiant(e);
					String CniPE= e.getCniP() ;
					System.out.println("CniPE : " + CniPE );
					Parent p = new Parent();
					p = metierP.getParent(CniPE);
					System.out.println("Parent E : " + p);
					String EmailP = p.getEmailP() ;
					System.out.println("Email Parent : "+EmailP );
					int NbrAbsE = metierAbs.CountParModuleEtCNIE(nomM, CniE[i]);
					System.out.println("NbrAbsE = "+ NbrAbsE);
					int SeuilleM = metierO.getModule(nomM, fil).getSeuille();
					System.out.println("SeuilleM = "+SeuilleM);
					System.out.println("Abs : "+abs);
					String EMAIL ="Afnanetilali13";
					String EmailParent = EmailP;
					String USER_NAME = EMAIL;
					String PASSWORD = "afnane 13"; 
					String from = USER_NAME;
					String pass = PASSWORD;
					String[] to = { EmailParent }; 
					if(NbrAbsE>=SeuilleM) {
						String SUBJECT =request.getParameter("subject"); 
						String MSG  ="Salut votre Etudiant a d'epasser " + SeuilleM + "h dans la module de "+nomM +"\n ";
						System.out.println("MSG : "+ MSG + " EMAIL : "+ USER_NAME +" EmailProf : "+ EmailParent );
						Properties props = System.getProperties();
						String host = "smtp.gmail.com";
						props.put("mail.smtp.starttls.enable", "true");
						props.put("mail.smtp.host",host); 
						props.put("mail.smtp.user", from);
						props.put("mail.smtp.password",pass);
						props.put("mail.smtp.port", "587");
						props.put("mail.smtp.auth","true");
						Session session1 = Session.getDefaultInstance(props); 
						MimeMessage message = new MimeMessage(session1); 
						try {
							message.setFrom(new  InternetAddress(from)); 
							InternetAddress[] toAddress = new InternetAddress[to.length]; // To get the array of addresses 
							for( int j = 0;j < to.length; j++ ) {
								toAddress[j] = new InternetAddress(to[j]); 
							} 
							for( int j = 0; j < toAddress.length; j++) {
								message.addRecipient(Message.RecipientType.TO, toAddress[j]); }
							message.setSubject(SUBJECT);
							message.setText(MSG);
							Transport transport =
									session1.getTransport("smtp");
							transport.connect(host, from, pass);
							transport.sendMessage(message, message.getAllRecipients());
							System.out.println("Email Senden Successfully.");
							transport.close();
							jspCible = "Admin/Abs/Msg.jsp";
						}
						catch (AddressException ae){
							ae.printStackTrace(); 
						} catch (MessagingException me){
							me.printStackTrace();
						} 
					}
					else jspCible = "Admin/Abs/ListAllAbs.jsp";

					int Seuille =10;
					if(cptE>=Seuille) {
						//String SUBJECT =request.getParameter("subject"); 
						String MSG="Salut votre Etudiant a d'epasser " + Seuille + "h \n ";
						System.out.println("MSG : "+ MSG + " EMAIL : "+ USER_NAME +" EmailProf : "+ EmailParent ); 
						Properties props = System.getProperties(); 
						String host = "smtp.gmail.com";
						props.put("mail.smtp.starttls.enable", "true"); 
						props.put("mail.smtp.host",host); 
						props.put("mail.smtp.user", from); 
						props.put("mail.smtp.password",pass); 
						props.put("mail.smtp.port", "587"); 
						props.put("mail.smtp.auth","true"); 
						Session session1 = Session.getDefaultInstance(props); 
						MimeMessage message = new MimeMessage(session1); 
						try { message.setFrom(new
								InternetAddress(from)); 
						InternetAddress[] toAddress = new
								InternetAddress[to.length]; // To get the array of addresses 
						for( int j = 0;j < to.length; j++ ) {
							toAddress[j] = new InternetAddress(to[j]);
						}
						for( int j = 0; j < toAddress.length; j++) {
							message.addRecipient(Message.RecipientType.TO, toAddress[j]); 
						}
						//message.setSubject(SUBJECT); 
						message.setText(MSG); 
						Transport transport =
								session1.getTransport("smtp");
						transport.connect(host, from, pass);
						transport.sendMessage(message, message.getAllRecipients());
						System.out.println("Email Senden Successfully."); transport.close();
						jspCible = "Admin/Abs/Msg.jsp";
						}
						catch (AddressException ae) {
							ae.printStackTrace(); 
						} 
						catch (MessagingException me) {
							me.printStackTrace(); 
						} 
					}					
					else jspCible = "Admin/Abs/ListAllAbs.jsp";
				}
				else jspCible = "Admin/Abs/ListAllAbs.jsp";

			}

			request.setAttribute("Mother",Mother);
			request.setAttribute("Mabs",Mabs);
			request.getRequestDispatcher(jspCible).forward(request, response);
			//	request.getRequestDispatcher("Admin/Abs/ListAllAbs.jsp").forward(request, response);

		}
		else if(path.contentEquals("/AjouterAbs.php")) {
			request.setAttribute("Mother",Mother);			
			request.setAttribute("Mprof",Mprof);
			request.getRequestDispatcher("Admin/Abs/AjouterAbs.jsp").forward(request, response);
		}else if(path.contentEquals("/AddAbsEtud.php")) {
			String fil = request.getParameter("filiere");
			//		int numS = Integer.parseInt(request.getParameter("semestre"));
			String nomM = request.getParameter("Module");
			System.out.println("Module : "+ nomM);
			int Cren = Integer.parseInt(request.getParameter("creneau"));
			String Prof = request.getParameter("Profs");
			String CniPr = metierPr.getCniPr(Prof) ;
			String CniE = request.getParameter("CNIE");
			String date = request.getParameter("DateAbs");
			//module.setNomM(nomM);
			SimpleDateFormat D = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date datee = null;
			try {
				datee = D.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(datee.getTime());
			final Part filePart = request.getPart("file");
			InputStream pdfFileBytes = null;
			if (!filePart.getContentType().equals("application/pdf"))
			{
				System.out.println("<br/> Invalid File");
				return;
			}
			pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
			final byte[] bytes = new byte[pdfFileBytes.available()];
			pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
			if (pdfFileBytes != null) {
				pdfFileBytes.close();
			}
			Etudiant e;
			Absence abs = new Absence();
			abs.setBytes(bytes);
			abs.setDateAbs(sqlDate);				
			switch(Cren){
			case 1: { abs.setCren1(true); break;}
			case 2: { abs.setCren2(true); break;}
			case 3: { abs.setCren3(true); break;}
			case 4: { abs.setCren4(true); break;}
			}
			abs.setCniE(CniE);
			abs.setCniPr(CniPr);
			abs.setNomM(nomM);
			metierAbs.addAbs(abs);
			e = metierE.getEtudiant(CniE);
			System.out.println("Etudiant : "+ e);
			cptE = e.getNbrAbs();
			System.out.println("cptE : "+ cptE);
			cptE++;
			e.setNbrAbs(cptE);
			System.out.println("new cptE : "+ cptE);
			metierE.ModifierInfoEtudiant(e);
			String CniPE= e.getCniP() ;
			System.out.println("CniPE : " + CniPE );
			Parent p = new Parent();
			p = metierP.getParent(CniPE);
			System.out.println("Parent E : " + p);
			String EmailP = p.getEmailP() ;
			System.out.println("Email Parent : "+EmailP );
			int NbrAbsE = metierAbs.CountParModuleEtCNIE(nomM, CniE);
			System.out.println("NbrAbsE = "+ NbrAbsE);
			int SeuilleM = metierO.getModule(nomM, fil).getSeuille();
			System.out.println("SeuilleM = "+SeuilleM);
			System.out.println("abs : "+ abs);
			String EMAIL ="Afnanetilali13";
			String EmailParent = EmailP;
			String USER_NAME = EMAIL;
			String PASSWORD = "afnane 13"; 
			String from = USER_NAME;
			String pass = PASSWORD;
			String[] to = { EmailParent }; 
			if(NbrAbsE>=SeuilleM) {
				String SUBJECT =request.getParameter("subject"); 
				String MSG  ="Salut votre Etudiant a d'epasser " + SeuilleM + "h dans la module de "+nomM +"\n ";
				System.out.println("MSG : "+ MSG + " EMAIL : "+ USER_NAME +" EmailProf : "+ EmailParent );
				Properties props = System.getProperties();
				String host = "smtp.gmail.com";
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host",host); 
				props.put("mail.smtp.user", from);
				props.put("mail.smtp.password",pass);
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth","true");
				Session session1 = Session.getDefaultInstance(props); 
				MimeMessage message = new MimeMessage(session1); 
				try {
					message.setFrom(new  InternetAddress(from)); 
					InternetAddress[] toAddress = new InternetAddress[to.length]; // To get the array of addresses 
					for( int j = 0;j < to.length; j++ ) {
						toAddress[j] = new InternetAddress(to[j]); 
					} 
					for( int j = 0; j < toAddress.length; j++) {
						message.addRecipient(Message.RecipientType.TO, toAddress[j]); }
					message.setSubject(SUBJECT);
					message.setText(MSG);
					Transport transport =
							session1.getTransport("smtp");
					transport.connect(host, from, pass);
					transport.sendMessage(message, message.getAllRecipients());
					System.out.println("Email Senden Successfully.");
					transport.close();
					request.getRequestDispatcher("Admin/Abs/Msg.jsp").forward(request, response); 
				}
				catch (AddressException ae){
					ae.printStackTrace(); 
				} catch (MessagingException me){
					me.printStackTrace();
				} 
			}
			int Seuille =10;
			if(cptE>=Seuille) {
				//String SUBJECT =request.getParameter("subject"); 
				String MSG="Salut votre Etudiant a d'epasser " + Seuille + "h \n ";
				System.out.println("MSG : "+ MSG + " EMAIL : "+ USER_NAME +" EmailProf : "+ EmailParent ); 
				Properties props = System.getProperties(); 
				String host = "smtp.gmail.com";
				props.put("mail.smtp.starttls.enable", "true"); 
				props.put("mail.smtp.host",host); 
				props.put("mail.smtp.user", from); 
				props.put("mail.smtp.password",pass); 
				props.put("mail.smtp.port", "587"); 
				props.put("mail.smtp.auth","true"); 
				Session session1 = Session.getDefaultInstance(props); 
				MimeMessage message = new MimeMessage(session1); 
				try { message.setFrom(new
						InternetAddress(from)); 
				InternetAddress[] toAddress = new
						InternetAddress[to.length]; // To get the array of addresses 
				for( int j = 0;j < to.length; j++ ) {
					toAddress[j] = new InternetAddress(to[j]);
				}
				for( int j = 0; j < toAddress.length; j++) {
					message.addRecipient(Message.RecipientType.TO, toAddress[j]); 
				}
				//message.setSubject(SUBJECT); 
				message.setText(MSG); 
				Transport transport =
						session1.getTransport("smtp");
				transport.connect(host, from, pass);
				transport.sendMessage(message, message.getAllRecipients());
				System.out.println("Email Senden Successfully."); transport.close();
				request.getRequestDispatcher("Admin/Abs/Msg.jsp").forward(request, response); 
				}
				catch (AddressException ae) {
					ae.printStackTrace(); 
				} 
				catch (MessagingException me) {
					me.printStackTrace(); 
				} 
			}
			request.setAttribute("Mother",Mother);
			request.setAttribute("Mabs",Mabs);
			request.getRequestDispatcher("Admin/Abs/ListAllAbs.jsp").forward(request, response);

		}else if(path.contentEquals("/GetFiliereSemestreEtud.php")) {
			request.setAttribute("MEtudiant",Metudiant);
			request.setAttribute("MProf",Mprof);
			request.setAttribute("Mabs",Mabs);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Admin/Abs/GetFiliereSemestreEtud.jsp").forward(request, response);
		}else if(path.contentEquals("/ListeEtudiantParFiliereSemestreEtud.php")) {
			request.setAttribute("Mabs",Mabs);
			request.setAttribute("Mprof",Mprof);
			request.setAttribute("MEtudiant",Metudiant);
			System.out.println("Start List Etud Par Fil Semestre controller");
			String filieree = request.getParameter("FiliereE");
			int Semestree = Integer.parseInt(request.getParameter("Semestre"));
			System.out.println("Filiere : " + filieree);
			System.out.println("Semestree : " + Semestree);
			List<Etudiant> listEtudParFS = new ArrayList<Etudiant>();
			listEtudParFS = metierE.getEtudiantParFiliereSemestre(filieree, Semestree);
			Metudiant.setListEtud(listEtudParFS);
			ListModule = metierPr.getModuleParFS(filieree,Semestree);
			Mother.setListModule(ListModule);	
			module = metierAbs.getModuleParFS(filieree, Semestree);
			System.out.println("Module par Fil et Sem : "+ module);
			System.out.println("ListModule : "+ ListModule);
			Metudiant.setFiliere(filieree);
			Metudiant.setNumS(Semestree);
			request.setAttribute("Mother", Mother);
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Admin/Abs/AjouterAbs.jsp").forward(request, response);
		}
		else if(path.contentEquals("/RechercheAbs.php")) {
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Admin/Abs/RechercheAbs.jsp").forward(request, response);
		} 
		else if(path.equals("/SearchAbs.php")){ 
			String CniE = request.getParameter("CNIE");
			String date = request.getParameter("DateAbs");
			SimpleDateFormat D = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date datee = null;
			try {
				datee = D.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(datee.getTime());
			Absence abs = metierAbs.RechercheAbs(CniE, sqlDate);
			Mabs.setAbs(abs);

			if(abs==null) {
				request.getRequestDispatcher("Admin/MenuR/RNAbs.jsp").forward(request,response);
			}
			else{
				System.out.println("Mabs : "+ Mabs.getAbs());
				request.setAttribute("Mabs", Mabs );
				request.getRequestDispatcher("Admin/MenuR/RAbs.jsp").forward(request,response); 
			} 
		}
		else if(path.contentEquals("/InscriptionE.php")) {
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Etudiant/Inscription.jsp").forward(request, response);
		}
		else if(path.equals("/AddE.php")) {
			System.out.println("add Controller");
			String Nom = request.getParameter("Nom");
			String AnneeUniv = request.getParameter("AnneeUniv");
			String Prenom = request.getParameter("Prenom");
			String CIN = request.getParameter("CIN");
			String CNI = request.getParameter("CNI");
			String Adress = request.getParameter("Adresse");
			String DateN = request.getParameter("DateN");
			String VilleN = request.getParameter("VilleN");
			String Sexe = request.getParameter("Sexe");
			String Filiere = request.getParameter("Filiere");
			String country = request.getParameter("Country");
			String Email = request.getParameter("Email");
			int Phone = Integer.parseInt(request.getParameter("Phone"));
			int numS = Integer.parseInt(request.getParameter("Semestre"));
			String CniP  = request.getParameter("CniP");
			final Part imgFilePart = request.getPart("photo");
			InputStream image = null;
			// obtains the upload file part in this multipart request
			if (imgFilePart != null) {
				// prints out some information for debugging
				System.out.println("name : "+imgFilePart.getName());
				System.out.println("size : " + imgFilePart.getSize());
				System.out.println("type : " + imgFilePart.getContentType());
				// obtains input stream of the upload file
				image = imgFilePart.getInputStream();
			}
			final Part filePart = request.getPart("file");
			InputStream pdfFileBytes = null;
			if (!filePart.getContentType().equals("application/pdf"))
			{
				System.out.println("<br/> Invalid File");
				return;
			}
			pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
			final byte[] bytes = new byte[pdfFileBytes.available()];
			pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
			if (pdfFileBytes != null) {
				pdfFileBytes.close();
			}
			String DateIns =request.getParameter("DateIns");
			Etudiant etudiant = new Etudiant();
			etudiant.setNom(Nom);
			etudiant.setPrenom(Prenom);
			etudiant.setCin(CIN);
			etudiant.setCni(CNI);
			etudiant.setAdresse(Adress);
			etudiant.setDateN(DateN);
			etudiant.setVilleN(VilleN);
			etudiant.setSexe(Sexe);
			etudiant.setEmail(Email);
			etudiant.setPhone(Phone);
			etudiant.setFiliere(Filiere);
			etudiant.setDateN(DateN);
			etudiant.setBytes(bytes);
			etudiant.setNomF(Filiere);
			etudiant.setCniP(CniP);
			etudiant.setAnneeUniv(AnneeUniv);
			etudiant.setCountry(country);
			etudiant.setImage(image);
			inscription.setDateInscrtiption(DateIns);
			inscription.setCniE(CNI);
			inscription.setNomF(Filiere);
			inscription.setNumS(numS);
			String NomP = request.getParameter("NomP");
			String PrenomP = request.getParameter("PrenomP");
			String CNIP = request.getParameter("CNIP");
			String EmailP = request.getParameter("EmailP");
			int PhoneP = Integer.parseInt(request.getParameter("Phone"));
			Parent parent = new Parent();
			parent.setNomP(NomP);
			parent.setPrenomP(PrenomP);
			parent.setCniP(CNIP);
			parent.setEmailP(EmailP);
			parent.setPhone(PhoneP);
			etudiant.setCniP(CNIP);
			metierP.addParent(parent);
			metierE.addEtudiant(etudiant,inscription);
			ListParent = metierP.getAllParent();
			Mparent.setListParent(ListParent);
			request.setAttribute("Mparent", Mparent);
			ListEtudiant = metierE.getAllEtudiant();
			Metudiant.setListEtud(ListEtudiant);
			request.setAttribute("MEtudiant", Metudiant);
			request.getRequestDispatcher("Etudiant/ListAllEtudiant.jsp").forward(request, response);
		}else if(path.contentEquals("/RechercheEtudiantE.php")) {
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Etudiant/RechercheEtudiant.jsp").forward(request, response);
		}else if(path.equals("/SearchEtudiantE.php")){
			String CNI = request.getParameter("CNIE");
			System.out.println("CNI : " + CNI);
			CEtudiant metier = new CEtudiant();
			ree =  metier.RechercheEtudiant(CNI);
			Metudiant.setEtudiant(ree);
			String CNIP ;
			if(ree==null) {
				request.getRequestDispatcher("Etudiant/RNE.jsp").forward(request, response);
			}else{
				CNIP= ree.getCniP();
				CParent metierP = new CParent();
				reeP =  metierP.RechercheParent(CNIP);
				Mparent.setParent(reeP);
				System.out.println("Metudiant : "+ Metudiant.getEtudiant());
				request.setAttribute("MEtudiant", Metudiant );
				System.out.println("Mparent : "+ Mparent.getParent());
				request.setAttribute("Mparent", Mparent );
				request.getRequestDispatcher("Etudiant/RE.jsp").forward(request, response);
			}
		}
		else if(path.contentEquals("/ModifierEtudiantE.php")) {
			String CNI = request.getParameter("CNI");
			System.out.println("CNI Etud : " + CNI);
			Etudiant etudiant = new Etudiant();
			etudiant = metierE.getEtudiant(CNI);
			Metudiant.setEtudiant(etudiant);
			request.setAttribute("MEtudiant", Metudiant);
			System.out.println(" etudiant : " + Metudiant.getEtudiant() );
			System.out.println("liste Filiere : " + Mother.getListfili());
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Etudiant/ModifierEtudiant.jsp").forward(request, response);		
		}else if(path.equals("/updateEe.php")){
			System.out.println("update controller");
			System.out.println("re  =  " + re); 
			Etudiant ME = new Etudiant();
			ME = metierE.getEtudiant(re.getCni());
			System.out.println("ME : "+ ME);
			String Nom = request.getParameter("Nom");
			String Prenom = request.getParameter("Prenom");
			String CIN = request.getParameter("CIN");
			System.out.println(CIN);
			//	String CNI = request.getParameter("CNI");
			String Adress = request.getParameter("Adresse");
			String DateN = request.getParameter("DateN");
			String VilleN = request.getParameter("VilleN");
			String Sexe = request.getParameter("Sexe");
			String Filiere = request.getParameter("Filiere");
			String Email = request.getParameter("Email");
			int Phone = Integer.parseInt(request.getParameter("Phone"));
			String AnneeUniv = request.getParameter("AnneeUniv");
			String Country = request.getParameter("Country");
			ME.setNom(Nom);
			ME.setPrenom(Prenom);
			ME.setCin(CIN);
			//	ME.setCni(CNI);
			ME.setAdresse(Adress);
			ME.setDateN(DateN);
			ME.setVilleN(VilleN);
			ME.setSexe(Sexe);
			ME.setFiliere(Filiere);
			ME.setEmail(Email);
			ME.setPhone(Phone);
			ME.setNomF(Filiere);
			ME.setCountry(Country);
			ME.setAnneeUniv(AnneeUniv);
			metierE.ModifierInfoEtudiant(ME);
			System.out.println("New ME : "+ ME);
			ListEtudiant = metierE.getAllEtudiant();
			Metudiant.setListEtud(ListEtudiant);
			request.setAttribute("MEtudiant", Metudiant);
			request.setAttribute("Mother", Mother);
			request.getRequestDispatcher("Etudiant/Home.jsp").forward(request, response);
		}else if(path.contentEquals("/ModifierParentE.php")) {
			String CNIp = request.getParameter("CNIP");
			System.out.println("CNI parent : " + CNIp);
			Parent parent = new Parent();
			parent = metierP.getParent(CNIp);
			Mparent.setParent(parent);
			System.out.println(" parent : " + Mparent.getParent() );
			request.setAttribute("Mparent", Mparent);
			request.getRequestDispatcher("Etudiant/ModifierParent.jsp").forward(request, response);
		}else if(path.equals("/updatePE.php")){
			System.out.println("update Parent controller");
			System.out.println("reeP  =  " + reeP); 
			MPP = metierP.getParent(reeP.getCniP());
			System.out.println("MPP : "+ MPP);
			String Nom = request.getParameter("NomP");
			String Prenom = request.getParameter("PrenomP");
			String CNI = request.getParameter("CNIP");
			String Email = request.getParameter("EmailP");
			int Phone = Integer.parseInt(request.getParameter("Phone"));
			MPP.setNomP(Nom);
			MPP.setPrenomP(Prenom);
			MPP.setCniP(CNI);
			MPP.setEmailP(Email);
			MPP.setPhone(Phone);
			metierP.ModifierInfoParent(MPP);
			System.out.println("New MPP : "+ MPP);
			ListParent = metierP.getAllParent();
			Mparent.setListParent(ListParent);
			request.setAttribute("Mparent", Mparent);
			request.getRequestDispatcher("Etudiant/Home.jsp").forward(request, response);
			System.out.println("End update Parent E controller");
		}
		else if(path.contentEquals("/declarerProbleme.php")) {
			request.setAttribute("Mprof", Mprof);
			request.setAttribute("MEtudiant",Metudiant);
			request.getRequestDispatcher("Etudiant/DeclarerProbleme.jsp").forward(request, response);			
		}else if(path.contentEquals("/DeclarerProb.php")) {
			PrintWriter out = response.getWriter();
			String FNAME =request.getParameter("fname");
			String LNAME =request.getParameter("lname");
			String SUBJECT =request.getParameter("subject");
	//		String EMAIL =request.getParameter("email");
			String EMAIL ="Afnanetilali13";
			String msg =request.getParameter("message");
			String emailto =request.getParameter("emailto");
			String MSG =msg + "\n" + "From : " +LNAME+ " "+ FNAME ;
			String EmailProf = metierPr.getProfN(emailto).getEmailPr();
			String USER_NAME = EMAIL;  // GMail user name (just the part before "@gmail.com")
		//	String PASSWORD = request.getParameter("password"); // GMail password
			String PASSWORD = "afnane 13";
			System.out.println("MSG : "+ MSG + " EMAIL : "+ USER_NAME + " EmailProf : "+ EmailProf );
			String from = USER_NAME;
			String pass = PASSWORD;
			String[] to = { EmailProf }; // list of recipient email addresses
			Properties props = System.getProperties();
			String host = "smtp.gmail.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			Session session1 = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session1);
			try {
				message.setFrom(new InternetAddress(from));
				InternetAddress[] toAddress = new InternetAddress[to.length];
				// To get the array of addresses
				for( int i = 0; i < to.length; i++ ) {
					toAddress[i] = new InternetAddress(to[i]);
				}
				for( int i = 0; i < toAddress.length; i++) {
					message.addRecipient(Message.RecipientType.TO, toAddress[i]);
				}
				message.setSubject(SUBJECT);
				message.setText(MSG);
				Transport transport = session1.getTransport("smtp");
				transport.connect(host, from, pass);
				transport.sendMessage(message, message.getAllRecipients());
		//		out.println("Email Senden Successfully.");
			//	out.println("<center> <h2 style ='color:red;'>Email Senden Successfully.</h2> ");
				transport.close();
				request.getRequestDispatcher("Etudiant/Home.jsp").forward(request, response);
			}
			catch (AddressException ae) {
				ae.printStackTrace();
			}
			catch (MessagingException me) {
				me.printStackTrace();
			}
		}
		else if(path.contentEquals("/InscriptionPr.php")) {
			request.setAttribute("Mprof",Mprof);
			request.getRequestDispatcher("Prof/Inscription.jsp").forward(request, response);
		}else if(path.equals("/AddPr.php")) {
			System.out.println("add Prof Controller");
			String Nom = request.getParameter("NomPr");
			String Prenom = request.getParameter("PrenomPr");
			String CNI = request.getParameter("CNIPr");
			String Email = request.getParameter("EmailPr");
			int Phone = Integer.parseInt(request.getParameter("PhonePr"));
			Prof prof = new Prof();
			prof.setNomPr(Nom);
			prof.setPrenomPr(Prenom);
			prof.setCniPr(CNI);
			prof.setEmailPr(Email);
			prof.setPhonePr(Phone);
			metierPr.addProf(prof);
			ListProf = metierPr.getAllProf();
			Mprof.setListProf(ListProf);
			request.setAttribute("Mprof", Mprof);
			request.getRequestDispatcher("Prof/Home.jsp").forward(request, response);
		}
	}
}