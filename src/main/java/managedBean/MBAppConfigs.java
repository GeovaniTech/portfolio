package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import util.CookieUtil;

@Named(MBAppConfigs.MANAGED_BEAN_NAME)
@SessionScoped
public class MBAppConfigs implements Serializable {
	private static final long serialVersionUID = 3526359354412555046L;

	public static final String MANAGED_BEAN_NAME = "MBAppConfigs";
	
	private String language;
	
	private List<Locale> localeList;
	
	public MBAppConfigs() {
		this.setLocaleList(new ArrayList<Locale>());
		this.getLocaleList().add(new Locale("pt_BR"));
		this.getLocaleList().add(new Locale("en_US"));
		
		//Initial Configurations
		this.setLanguage(Locale.getDefault().getLanguage());
		
		this.getConfigsFromCookies();
	}
	
 	public void updateUserConfigs() { 		
 		this.createCookiePreferences();
 	}
	
 	public boolean getConfigsFromCookies() { 		
 		if(CookieUtil.getLanguageCookie() != null) {
 			this.setLanguage(CookieUtil.getLanguageCookie());
 			
 			return true;
 		}
 		return false;
 	}
 	
	public void refreshPage() {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			ec.redirect("https://port.devpree.com.br/portfolio");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 	
	public void createCookiePreferences() {	
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		Cookie language = new Cookie("language", this.getLanguage());
		language.setMaxAge(60*60*24*30);
		language.setPath("/portfolio");
		
		response.addCookie(language);
		
		this.refreshPage();
	}
	
	// Getters and Setters
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<Locale> getLocaleList() {
		return localeList;
	}
	public void setLocaleList(List<Locale> localeList) {
		this.localeList = localeList;
	}
	
	
}
