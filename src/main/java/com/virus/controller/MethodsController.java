package com.virus.controller;

import com.emida.methods.FacadeApplyPayment;
import com.emida.methods.FacadeApplyPaymentAgentLevel;
import com.emida.methods.FacadeLogin;
import com.emida.methods.FacadeLogin2;
import com.emida.methods.FacadeLogout;
import com.emida.methods.FacadePinDistSale;
import com.emida.selenium.PcterminalDomestic;
import com.emida.selenium.SeleniumDynamic;
import com.emida.selenium.mexico.SeleniumPaymentRequest;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.virus.xmlutil.XmlFormatter;
import com.virus.constant.ViewConstant;
import com.virus.entity.AutomationRecordedDetailEntity;
import com.virus.entity.AutomationRecordedItemEntity;
import com.virus.model.AjaxResponseBody;
import org.springframework.security.core.userdetails.User;
import com.virus.model.LoginMethodModel;
import com.virus.pojos.PojoDynamic;
import com.virus.repository.AutomationRecordedDetailRepository;
import com.virus.repository.AutomationRecordedItemRepository;
import com.virus.views.Views;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController
@RequestMapping("/methods")
public class MethodsController {

    @Autowired
    private AutomationRecordedDetailRepository automationRecordedDetailRepository;

    @Autowired
    private AutomationRecordedItemRepository automationRecordedItemRepository;

    private static final Log LOG = LogFactory.getLog(MethodsController.class);
    XmlFormatter formatter = new XmlFormatter();

    private String imageURL;
    private String imageFolder;
    private String seleniumfolder;
    private String csvfolder;
    private final String on = "on";
    private final String off = "off";

    //FACADES FROM PROYECT EMIDAWEBRED
    public FacadeLogin facadeLogin = new FacadeLogin();
    public FacadeLogout facadeLogout = new FacadeLogout();
    public FacadePinDistSale facadePinDistSale = new FacadePinDistSale();
    public FacadeApplyPayment facadeApplyPayment = new FacadeApplyPayment();
    public FacadeLogin2 facadeLogin2 = new FacadeLogin2();
    public FacadeApplyPaymentAgentLevel facadeApplyPaymentAgentLevel = new FacadeApplyPaymentAgentLevel();

    //AUTOMATIC  SELENIUM
    public PcterminalDomestic pcterminalDomestic = new PcterminalDomestic();
    public SeleniumDynamic seleniumDynamic = new SeleniumDynamic();
    public SeleniumPaymentRequest seleniumPaymentRequest = new SeleniumPaymentRequest();

    //LOGIN MODEL
    LoginMethodModel login;

    @GetMapping("/showmethods")
    public ModelAndView Products(Model model) {
        model.addAttribute("login", new LoginMethodModel());
        ModelAndView mav = new ModelAndView(ViewConstant.METHODS);
        LOG.info("Method Products");
        return mav;
    }

    @GetMapping("/productform")
    public String redirectContactForm() {

        LOG.info("Product-Form");
        LOG.info("Redirect to  :/products/productform");
        return "/productform";
    }

    @PostMapping("/api/loginpost")
    @ResponseBody
    public Map loginApi(@RequestParam("language") String language,
            @RequestParam("version") String version,
            @RequestParam("user") String user,
            @RequestParam("password") String password) {

        try {
            String url = "http://192.168.2.44:82/soap/servlet/rpcrouter";
            System.out.println("############" + url + language + version + user + password);
            String result = facadeLogin.login(url, version, user, password, language);
            Map<String, Object> response = new LinkedHashMap();
            String xmlresponse = formatter.format(result);
            response.put("success", true);
            response.put("data", xmlresponse);
            return response;
        } catch (Exception e) {
            System.out.println("Error " + e);
            Map<String, Object> error = new LinkedHashMap();
            error.put("data", ViewConstant.ERROR_METHODS + "\n" + e);
            return error;
        }
    }

    @PostMapping("/api/logoutpost")
    @ResponseBody
    public Map logoutApi(@RequestParam("lan") String language,
            @RequestParam("ver") String version,
            @RequestParam("us") String user,
            @RequestParam("pass") String password) {

        try {
            String url = "http://192.168.2.44:82/soap/servlet/rpcrouter";
            System.out.println("############" + url + language + version + user + password);
            String result = facadeLogout.logout(url, version, user, password, language);
            Map<String, Object> response = new LinkedHashMap();
            String xmlresponse = formatter.format(result);
            response.put("success", true);
            response.put("data", xmlresponse);
            return response;
        } catch (Exception e) {
            System.out.println("Error " + e);
            Map<String, Object> error = new LinkedHashMap();
            error.put("data", ViewConstant.ERROR_METHODS + "\n" + e);
            return error;
        }
    }

    @PostMapping("/api/pindistsalepost")
    @ResponseBody
    public Map pinDistSaleApi(@RequestParam("language") String language,
            @RequestParam("versionn") String versionn,
            @RequestParam("terminalid") String terminalid,
            @RequestParam("clerkid") String clerkid,
            @RequestParam("productid") String productid,
            @RequestParam("amount") String amount,
            @RequestParam("account") String account,
            @RequestParam("invoice") String invoice) {

        try {
            /*String url = "http://192.168.2.44:82/soap/servlet/rpcrouter";
            System.out.println("### " + language + " ###" + versionn
                    + " ###" + terminalid + " ###" + clerkid + " ###" + productid
                    + " ###" + amount + " ###" + account + " ###" + invoice);

            String result = facadePinDistSale.pinDistSale(url, versionn, terminalid, clerkid, productid, account, amount, invoice, language);
            String xmlresponse = formatter.format(result);
            Map<String, Object> response = new LinkedHashMap();
            response.put("success", true);
            response.put("data", xmlresponse);
            return response;*/
            for (int i = 0; i < 1; i++) {
//                PinDistSaleThread thread = new PinDistSaleThread(language, versionn, terminalid, clerkid, productid, amount, account, i + "");
                PinDistSaleThread thread = new PinDistSaleThread(language, versionn, terminalid, clerkid, productid, amount, account, invoice);
                thread.start();
                Thread.sleep(100);
            }
            Map<String, Object> response = new LinkedHashMap();
            response.put("success", true);
            response.put("data", "Holaaaa");
            return response;
        } catch (Exception e) {
            System.out.println("Error " + e);
            Map<String, Object> error = new LinkedHashMap();
            error.put("data", ViewConstant.ERROR_METHODS + "\n" + e);
            return error;
        }

    }

    @PostMapping("/api/applypaymentpost")
    @ResponseBody
    public Map applypaymentApi(@RequestParam("v") String version,
            @RequestParam("m") String merchantid,
            @RequestParam("u") String amount,
            @RequestParam("p") String description,
            @RequestParam("r") String refnumber,
            @RequestParam("u") String username,
            @RequestParam("p") String password) {

        try {
            String url = "http://192.168.2.44:82/soap/servlet/rpcrouter";
            String result = facadeApplyPayment.applyPayment(url, version, merchantid, amount, description, refnumber, username, password);
            Map<String, Object> response = new LinkedHashMap();
            String xmlresponse = formatter.format(result);
            response.put("success", true);
            response.put("data", xmlresponse);
            return response;
        } catch (Exception e) {
            System.out.println("Error " + e);
            Map<String, Object> error = new LinkedHashMap();
            error.put("data", ViewConstant.ERROR_METHODS + "\n" + e);
            return error;
        }
    }

    @PostMapping("/api/login2post")
    @ResponseBody
    public Map login2Api(@RequestParam("languagee") String language,
            @RequestParam("versione") String version,
            @RequestParam("usere") String user,
            @RequestParam("passworde") String password) {

        try {
            String url = "http://192.168.2.44:82/soap/servlet/rpcrouter";
            System.out.println("############" + url + language + version + user + password);
            String result = facadeLogin2.login2(url, version, user, password, language);
            Map<String, Object> response = new LinkedHashMap();
            String xmlresponse = formatter.format(result);
            response.put("success", true);
            response.put("data", xmlresponse);
            return response;
        } catch (Exception e) {
            System.out.println("Error " + e);
            Map<String, Object> error = new LinkedHashMap();
            error.put("data", ViewConstant.ERROR_METHODS + "\n" + e);
            return error;
        }

    }

    @PostMapping("/api/applypaymentagentlevelpost")
    @ResponseBody
    public Map applypaymentagentlevelApi(@RequestParam("versiona") String version,
            @RequestParam("merchantida") String merchantid,
            @RequestParam("amounta") String amount,
            @RequestParam("descriptiona") String description,
            @RequestParam("refnumbera") String refnumber,
            @RequestParam("usernamea") String username,
            @RequestParam("passworda") String password) {
        try {
            String url = "http://192.168.2.44:82/soap/servlet/rpcrouter";
            String result = facadeApplyPaymentAgentLevel.applyPaymentAgentLevel(url, version, merchantid, amount, description, refnumber, username, password);
            Map<String, Object> response = new LinkedHashMap();
            String xmlresponse = formatter.format(result);
            response.put("success", true);
            response.put("data", xmlresponse);
            return response;
        } catch (Exception e) {
            System.out.println("Error " + e);
            Map<String, Object> error = new LinkedHashMap();
            error.put("data", ViewConstant.ERROR_METHODS + "\n" + e);
            return error;
        }
    }

    @PostMapping("/api/loginselenium")
    @ResponseBody
    public Map loginseleniumApi(@RequestParam("usuario") String user,
            @RequestParam("contrasena") String pass) {
        try {
            String url = "192.168.2.47:8888";
            Map<String, Object> response = new LinkedHashMap();
            //pcterminalDomestic.login(user, pass, url);
            //response.put("data", xmlresponse);
            response.put("data", " Succes");
            return response;
        } catch (Exception e) {
            System.out.println("Error " + e);
            Map<String, Object> error = new LinkedHashMap();
            error.put("data", "ERROR SELENIUM" + "\n" + e);
            return error;
        }
    }

    @PostMapping("/api/selenium")
    @ResponseBody
    public Map allseleniumApi(@RequestParam("user") String user,
            @RequestParam("pass") String pass,
            @RequestParam("log") String log,
            @RequestParam("top") String top,
            @RequestParam("pin") String pin) {
        Map<String, Object> responseImg = new LinkedHashMap();
        Map<String, Object> result = new LinkedHashMap();
        try {
            System.out.println("LOGIN: " + log + " TOPUP: " + top + " PIN:" + pin);

            String fromMethodFolder = (folderNumberAleatory());
            if (log.equals(on) && top.equals(on) && pin.equals(on)) {
                result = pcterminalDomestic.login_TopUp_Pin(user, pass, "http://192.168.2.47:8888", fromMethodFolder, ViewConstant.SELENIUM_FOLDER);
            } else if (log.equals(on) && top.equals(on) && pin.equals(off)) {
                result = pcterminalDomestic.login_TopUp(user, pass, "http://192.168.2.47:8888", fromMethodFolder, ViewConstant.SELENIUM_FOLDER);
            } else if (log.equals(on) && top.equals(off) && pin.equals(off)) {
                result = pcterminalDomestic.onlyLoginLogout(user, pass, "http://192.168.2.47:8888", fromMethodFolder, ViewConstant.SELENIUM_FOLDER);
            } else if (log.equals(on) && top.equals(off) && pin.equals(on)) {
                result = pcterminalDomestic.login_Pin(user, pass, "http://192.168.2.47:8888", fromMethodFolder, ViewConstant.SELENIUM_FOLDER);
            }
            List<String> a = listFolder(fromMethodFolder);
            Object value = result.get("1");
            System.out.println(value);
            responseImg.put("result", value);
            responseImg.put("gs", a.toString());
        } catch (Exception e) {
            LOG.error("ERROR API SELENIUM : " + e);
        }
        return responseImg;

    }

    @PostMapping("/api/selenium/dom")
    @ResponseBody
    public Map seleniumApiDom(@RequestParam("user") String user,
            @RequestParam("pass") String pass,
            @RequestParam("clerk") String clerk,
            @RequestParam("sim") String sim,
            @RequestParam("email") String email,
            @RequestParam("zip") String zip,
            @RequestParam("esn") String esn,
            @RequestParam("npa") String npa,
            @RequestParam("log") String log,
            @RequestParam("act") String act,
            @RequestParam("actlo") String actlo) {

        System.out.println("LOGIN: " + log + " ACT1: " + act + " ACT2:" + actlo);
        Map<String, Object> responseImg = new LinkedHashMap();
        Map<String, Object> result = new LinkedHashMap();

        try {
            String fromMethodFolder = (folderNumberAleatory());

            System.out.println("ENTRO DOM::::");

            if (log.equals(on) && act.equals(off) && actlo.equals(off)) {
                result = pcterminalDomestic.onlyLoginLogout(user, pass, "http://192.168.2.52:8888", fromMethodFolder, ViewConstant.SELENIUM_FOLDER);
            } else if (log.equals(on) && act.equals(on) && actlo.equals(off)) {
                result = pcterminalDomestic.activationLycaDom(user, pass, "http://192.168.2.52:8888", fromMethodFolder, sim, zip, email, clerk, ViewConstant.SELENIUM_FOLDER);
            } else if (log.equals(on) && act.equals(off) && actlo.equals(on)) {
                result = pcterminalDomestic.activationLocusDom(user, pass, "http://192.168.2.52:8888", fromMethodFolder, esn, npa, "231123", "Bogota", zip, clerk, ViewConstant.SELENIUM_FOLDER);
            } else if (log.equals(on) && act.equals(on) && actlo.equals(on)) {
                result = pcterminalDomestic.activationLyca_Locus_Dom(user, pass, "http://192.168.2.52:8888", fromMethodFolder, sim, esn,
                        npa, "4654654", "Bogota", zip, email, clerk, ViewConstant.SELENIUM_FOLDER);
            }
            List<String> a = listFolder(fromMethodFolder);
            Object value = result.get("1");
            System.out.println(value);
            responseImg.put("result", value);
            responseImg.put("gs", a.toString());
        } catch (Exception ex) {
            LOG.error("ERROR API SELENIUM DOM : " + ex);
        }
        return responseImg;
    }

    /*    
    
    NUEVO
    
    
     */
    @PostMapping("/api/selenium/mexpaymentrequest")
    @ResponseBody
    public Map seleniumApiPaymentRequest(@RequestParam("us") String user,
            @RequestParam("pas") String pass,
            @RequestParam("acc") String acc,
            @RequestParam("date") String date,
            @RequestParam("val") String val) {

        Map<String, Object> responseImg = new LinkedHashMap();
        Map<String, Object> result = new LinkedHashMap();

        try {
            String fromMethodFolder = (folderNumberAleatory());
            System.out.println("ENTRO MEX::::");

            result = seleniumPaymentRequest.PaymentRequest("http://192.168.2.47:8888", user, pass, acc, date, val, fromMethodFolder, ViewConstant.SELENIUM_FOLDER);
            List<String> a = listFolder(fromMethodFolder);
            Object value = result.get("1");
            System.out.println(value);
            responseImg.put("result", value);
            responseImg.put("gs", a.toString());
        } catch (Exception ex) {
            LOG.error("ERROR API SELENIUM MEX : " + ex);
        }
        return responseImg;
    }

    @PostMapping("/api/selenium/dynamic")
    @ResponseBody
    public Map seleniumApiDynamic(@RequestParam("files") MultipartFile multipartFile) {
        Map<String, Object> responseImg = new LinkedHashMap();
        try {
            String fromMethodFolder = (folderNumberAleatory());
            seleniumDynamic.ReadFile(convert(multipartFile), ViewConstant.SELENIUM_FOLDER, fromMethodFolder);
            List<String> a = listFolder(fromMethodFolder);
            responseImg.put("gs", a.toString());
        } catch (IOException IOException) {
            LOG.error("ERROR : " + IOException);
        }
        return responseImg;
    }

    @JsonView(Views.Public.class)
    @RequestMapping(value = "/api/selenium/dynamicform")
    public AjaxResponseBody getValues(@RequestBody List<PojoDynamic> formDynamic) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("TEST :" + formDynamic.toString());
        String userId = "";
        LOG.info("FROM CONTROLLER TO INSERT FORM");
        String uniqueID = UUID.randomUUID().toString();
        PojoDynamic formDynamics = new PojoDynamic();
        for (int index = 0; index < formDynamic.size(); index++) {
            formDynamics = formDynamic.get(index);
            if (formDynamics != null) {
                try {
                    AutomationRecordedDetailEntity automationRecordedDetailEntity = new AutomationRecordedDetailEntity(formDynamics.getOptionselect(),
                            formDynamics.getDivxpath(), formDynamics.getValuetosend(), uniqueID);
                    automationRecordedDetailRepository.save(automationRecordedDetailEntity);
                    LOG.info("INSERT SUCCESS DETAIL");
                } catch (Exception ex) {
                    LOG.error("ERROR FAIL INSERT + EXCEPTION {0}" + ex.toString());
                }
            }
        }
        try {
            AutomationRecordedItemEntity automationRecordedItemEntity = new AutomationRecordedItemEntity("TEST1", "description 1", uniqueID, user.getUsername());
            automationRecordedItemRepository.save(automationRecordedItemEntity);
            LOG.info("INSERT SUCCESS ITEM");
        } catch (Exception ex) {
            LOG.error("ERROR FAIL INSERT + EXCEPTION {0}" + ex.toString());
        }
        return null;
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(ViewConstant.CSV_FOLDER + file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        return convFile;
    }

    public static String folderNumberAleatory() {
        Random generador = new Random();
        int d1 = generador.nextInt(100);
        int d2 = generador.nextInt(200);
        int d3 = generador.nextInt(300);
        int d4 = generador.nextInt(400);
        int foldernumber = d1 + d2 + d3 + d4;
        File dir = new File((ViewConstant.IMAGE_FOLDER + "\\" + foldernumber));
        String folderComplete = ViewConstant.IMAGE_FOLDER + "\\" + foldernumber;
        dir.mkdir();
        return folderComplete;
    }

    public static List<String> listFolder(String directory) {
        String sDirectorio = directory;
        List<String> imagesList = new ArrayList<String>();
        File f = new File(sDirectorio);
        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int i = 0; i < ficheros.length; i++) {
                String[] parts = ficheros[i].getAbsolutePath().split("\\\\");
                int partsInt = parts.length;
                imagesList.add(ViewConstant.IMAGE_URL + parts[partsInt - 2] + "\\" + parts[partsInt - 1]);
                System.out.println("IMGS: >>>>>" + ViewConstant.IMAGE_URL + parts[partsInt - 2] + "\\" + parts[partsInt - 1]);
            }
        } else {
            LOG.info(ViewConstant.ERROR_FOLDER);
        }
        return imagesList;
    }

    public static List<String> listNameFolder(String directory) {
        String sDirectorio = directory;
        List<String> imagesList = new ArrayList<String>();
        File f = new File(sDirectorio);
        if (f.exists()) {
            File[] ficheros = f.listFiles();
            for (int i = 0; i < ficheros.length; i++) {
                String[] parts = ficheros[i].getAbsolutePath().split("\\\\");
                int partsInt = parts.length;
                imagesList.add(parts[partsInt - 1]);
            }
            for (String image : imagesList) {
                LOG.info(ViewConstant.IMAGE_URL + image);
            }
        } else {
            LOG.info(ViewConstant.ERROR_FOLDER);
        }
        return imagesList;
    }

    public static String gson(List<String> list) {
        Gson gson = new Gson();

        System.out.println(list);

        String gs = gson.toJson(list);
        String gsComplete = (ViewConstant.IMAGE_URL + gs);
        return gsComplete;

    }

    @Value("${image.url}")
    public void setImageUrl(String imageurl) {
        this.imageURL = imageurl;
        ViewConstant.IMAGE_URL = imageURL;
    }

    @Value("${image.folder}")
    public void setImageFolder(String imgagefolder) {
        this.imageFolder = imgagefolder;
        ViewConstant.IMAGE_FOLDER = imageFolder;
    }

    @Value("${selenium.folder}")
    public void setSeleniumFolder(String seleniumFolder) {
        this.seleniumfolder = seleniumFolder;
        ViewConstant.SELENIUM_FOLDER = seleniumfolder;
    }

    @Value("${csv.folder}")
    public void setCSVFolder(String csvFolder) {
        this.csvfolder = csvFolder;
        ViewConstant.CSV_FOLDER = csvfolder;
    }

}
