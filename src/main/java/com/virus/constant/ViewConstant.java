
package com.virus.constant;

// @author Kevin
 
public class ViewConstant {
    
    //////CONSTANTS
    public static final String CONTACT_FORM = "contactform" ;
    public static final String CONTACTS = "contacts" ;
    public static final String LOGIN = "login" ;    
    public static final String HOME = "home";    
    public static final String PRODUCTS = "products";
    public static final String METHODS = "methods";
    public static final String SELENIUM = "methodsemida";
    public static final String SHOWRECORDS = "showrecords";
    public static final String ADDRECORDS = "addrecords";
    public static final String SHOWREPORTS = "showreports";
    
    
    //////PATH'S
    public static final String PATH_SHOWMETHODS = "redirect:/methods/showmethods";
    public static final String PATH_SHOWRECORDS = "redirect:/records/showrecorded";
    public static final String PATH_SHOWREPORTS = "redirect:/records/showreports";
    
    
    ///////ERRORS
    public static final String ERROR_METHODS = "Ha ocurrido un error, para mas informacion Revise el LOG del Soap ";
    public static final String ERROR_FOLDER = "El directorio No existe o No tiene permisos.";
    
    
    ///////SELENIUM
    
    public static  String IMAGE_URL;
    public static  String IMAGE_FOLDER;
    public static  String SELENIUM_CHROME;
    public static  String SELENIUM_EDGE;
    public static  String SELENIUM_FIREFOX;
    public static  String CSV_FOLDER; 
    
    
    //////SCHEDULER
    
    public static boolean SCHEDULER_METHOD;
    public static int TIME_EXECUTE;
    public static String QUANTITY_TRANSACTIONS;    
    public static String TIME_BETWEEN_TRANSACTIONS;
    public static String URL_WS;
}
