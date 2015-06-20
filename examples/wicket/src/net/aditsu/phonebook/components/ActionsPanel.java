package net.aditsu.phonebook.components;

import net.aditsu.depeche.Provider;
import net.aditsu.phonebook.PhonebookApp;
import net.aditsu.phonebook.pages.EditPage;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ActionsPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public ActionsPanel(final String id, final int recId) {
		super(id);
		add(new BookmarkablePageLink<Void>("edit", EditPage.class, new PageParameters().add("id", recId)));
		add(new Link<Void>("del") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				final Provider p = PhonebookApp.get().getProvider();
				p.query("phonebook").filterPK(recId).delete();
			}
		});
	}
}
