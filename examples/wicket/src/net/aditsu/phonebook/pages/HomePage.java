package net.aditsu.phonebook.pages;

import java.util.ArrayList;
import java.util.List;

import net.aditsu.depeche.Provider;
import net.aditsu.depeche.Record;
import net.aditsu.phonebook.PhonebookApp;
import net.aditsu.phonebook.components.ActionsPanel;
import net.aditsu.phonebook.models.RecordProvider;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;
	
	public HomePage() {
		final List<IColumn<Record, String>> cols = new ArrayList<IColumn<Record,String>>();
		cols.add(new PropertyColumn<Record, String>(Model.of("Name"), "name", "name"));
		cols.add(new PropertyColumn<Record, String>(Model.of("Phone number"), "phone_number", "phone_number"));
		cols.add(new AbstractColumn<Record, String>(Model.of("Actions")) {
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(final Item<ICellPopulator<Record>> cellItem, final String componentId,
					final IModel<Record> rowModel) {
				cellItem.add(new ActionsPanel(componentId, rowModel.getObject().getInt("id")));
			}
		});
		
		final Provider p = PhonebookApp.get().getProvider();
		final SortableDataProvider<Record, String> dp = new RecordProvider(p.query("phonebook"));
		
		add(new DefaultDataTable<Record, String>("tab", cols, dp, 10));
		
		add(new BookmarkablePageLink<Void>("add", EditPage.class));
	}
}
