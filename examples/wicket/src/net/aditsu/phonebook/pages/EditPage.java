package net.aditsu.phonebook.pages;

import net.aditsu.depeche.Provider;
import net.aditsu.depeche.Record;
import net.aditsu.phonebook.PhonebookApp;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class EditPage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	public EditPage(final PageParameters pp) {
		final Provider p = PhonebookApp.get().getProvider();
		final Integer id = pp.get("id").toOptionalInteger();
		final Record r;
		if (id == null) {
			r = p.newRecord("phonebook");
			for (int i = 1; i < r.size(); ++i) {
				r.setValue(i, null);
			}
		}
		else {
			r = p.getRecord("phonebook", id);
			if (r == null) {
				throw new AbortWithHttpErrorCodeException(404);
			}
		}
		final Form<Record> form = new StatelessForm<Record>("form", new CompoundPropertyModel<Record>(r)) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				getModelObject().save();
				setResponsePage(HomePage.class);
			}
		};
		add(form);
		form.add(new FeedbackPanel("fb"));
		form.add(new RequiredTextField<String>("name"));
		form.add(new RequiredTextField<String>("phone_number"));
	}
}
