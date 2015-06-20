package net.aditsu.phonebook;

import net.aditsu.depeche.Depeche;
import net.aditsu.depeche.Provider;
import net.aditsu.phonebook.pages.EditPage;
import net.aditsu.phonebook.pages.HomePage;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class PhonebookApp extends WebApplication {
	private Provider p;
	
	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected void init() {
		p = Depeche.getDefaultProvider();
		mountPage("edit/${id}", EditPage.class);
	}
	
	public Provider getProvider() {
		return p;
	}
	
	public static PhonebookApp get() {
		return (PhonebookApp) Application.get();
	}
	
	@Override
	protected void onDestroy() {
		p.close();
	}
}
